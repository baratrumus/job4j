package ru.job4j.pseudo;


import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenDrawSquare() {
        Shape tr = new Triangle();
        assertThat(
                tr.draw(),
                is(
                        new StringBuilder()
                        .append("   +   ")
                        .append(System.lineSeparator())
                        .append("  +++  ")
                        .append(System.lineSeparator())
                        .append(" +++++ ")
                        .append(System.lineSeparator())
                        .append("+++++++")
                                .toString()
                )
        );
    }
}
