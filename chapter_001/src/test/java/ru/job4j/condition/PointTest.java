package ru.job4j.condition;

import org.junit.Test;
import ru.job4j.condition.Point;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class Класс для тестирования вычисления дистанций
 * @author ivannikov
 * @since 05.04.2019
 * @version 1
 */

public class PointTest {
    @Test
    public void whenZeroAndTenThenTen() {
        Point point = new Point();
        double result = point.distance(0, 0, 0, 10);
        assertThat(result, is(10D));
    }
}
