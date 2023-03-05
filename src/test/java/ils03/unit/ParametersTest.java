package ils03.unit;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;

@Epic("Метод решения квадратного уравнения")
@Feature("Тест с параметрами")
@RunWith(Parameterized.class)
public class ParametersTest {
    private double a;
    private double b;
    private double c;
    private final String value;

    public ParametersTest(double a, double b, double c, String value) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.value = value;
    }

    @Parameterized.Parameters(name = "Входные данные теста и ответы")
    public static Object[] myParameters() {
        return new Object[][]{
                {-1, 4.5, 7.8, "Уравнение имеет 2 корня!"},
                {16, 8, 1, "Уравнение имеет 1 корень!"},
                {4, 1.8, 12, "Уравнение не имеет действительных корней!"}
        };
    }

    @Test
    @Description("Параметризованный тест")
    public void withParametersTest() {
        Method method = new Method();

        String actual = method.quadraticEquation(a, b, c);

        assertThat("Не найдены решения по уравнению!", actual.equals(value));
    }
}
