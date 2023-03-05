package ils03.unit;

import io.qameta.allure.Description;
import org.junit.Test;

public class UnitTests {

    Method method = new Method();
    private double a;
    private double b;
    private double c;

    @Test
    @Description("Решение с 2мя корнями")
    public void testGreaterThanZero() {
        a = -1;
        b = 4.5;
        c = 7.8;
        method.quadraticEquation(a, b, c);
    }

    @Test
    @Description("Решение с 1 корнем")
    public void testLessThanZero() {
        a = 16;
        b = 8;
        c = 1;
        method.quadraticEquation(a, b, c);
    }

    @Test
    @Description("Решение без корней")
    public void testNotHave() {
        a = 4;
        b = 1.8;
        c = 12;
        method.quadraticEquation(a, b, c);
    }
}
