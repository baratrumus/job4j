package ru.job4j.codereview;

import ru.job4j.sorting.User;
import java.util.*;

/**
 * дефолтный конструктор, также создался бы автоматически.
 * Опять же нет класса User и не переопределен compareTo для сортировки User в TreeSet.
 * В остальном вроде хорошо.
 */

public class Sorter {

    public Sorter() {

    }

    Set<User> sort(List<User> list) {
        TreeSet<User> sortedList = new TreeSet<>(list);
        return sortedList;
    }

    List<User> sortnamelength(List<User> list) {
        Comparator<User> compar = new Comparator<>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        list.sort(compar);
        return list;
    }

    List<User> sortboth(List<User> list) {
        Comparator<User> compar1 = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator<User> compar2 = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        list.sort(compar1.thenComparing(compar2));
        return list;
    }
}