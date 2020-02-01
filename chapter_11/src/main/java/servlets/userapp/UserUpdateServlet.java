package servlets.userapp;

import servlets.crudservlet.Logic;
import servlets.crudservlet.User;
import servlets.crudservlet.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class UserUpdateServlet  extends HttpServlet {

    private final Logic logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = logic.findById(req.getParameter("id"));
        req.setAttribute("user", user);
        req.setAttribute("roleMap", logic.getRoles());
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer roleKey = Integer.parseInt(req.getParameter("roles"));
        logic.update(req.getParameter("id"),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("pass"),
                roleKey);
        req.setAttribute("userMap", logic.findAll());
        String newRoleName = logic.getRoles().get(roleKey);
        session.setAttribute("roleName", newRoleName);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(req, resp);
    }
}
