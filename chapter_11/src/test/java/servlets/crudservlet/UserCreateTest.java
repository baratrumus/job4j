package servlets.crudservlet;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import servlets.userapp.UserCreateServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//позволяет  подменить работу статических методов.
@RunWith(PowerMockRunner.class)

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})

//нам нужно подменить класс ValidateService.
@PrepareForTest(ValidateService.class)

public class UserCreateTest {

    @Test
    @Ignore
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        Logic validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        //ServletConfig realConfig = ucs.getServletConfig();
        ServletConfig mockConfig = Mockito.mock(ServletConfig.class);
        //when(realConfig).thenReturn(mockConfig);

        ServletContext mockContext = Mockito.mock(ServletContext.class);
        when(mockConfig.getServletContext()).thenReturn(mockContext);

        UserCreateServlet ucs = new UserCreateServlet() {
            @Override
            public ServletConfig getServletConfig() {
                return mockConfig;
            }
        };

        RequestDispatcher mockDispatcher = Mockito.mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/WEB-INF/views/list.jsp")).thenReturn(mockDispatcher);

        when(req.getParameter("name")).thenReturn("Anatoly");
        ucs.doPost(req, resp);

        //объект validate проставляется при вызове статического метод ValidateService.getInstance();
        assertThat(validate.findAll().values().iterator().next().getName(), is("Anatoly"));
    }
}
