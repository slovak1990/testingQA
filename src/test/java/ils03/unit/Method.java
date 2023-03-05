package ils03.unit;

import io.qameta.allure.Description;

public class Method {

    @Description("Метод решения квадратного уравнения")
    public String quadraticEquation(double a, double b, double c) {
        System.out.println("Программа решает квадратное уравнение вида:");
        System.out.println("ax^2 + bx + c = 0");
        String value;

        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double x1, x2;
            x1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            x2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Корни уравнения: x1 = " + x1 + ", x2 = " + x2);
            return value = "Уравнение имеет 2 корня!";
        } else if (discriminant == 0) {
            double x;
            x = -b / (2 * a);
            System.out.println("Уравнение имеет единственный корень: x = " + x);
            return value = "Уравнение имеет 1 корень!";
        } else {
            System.out.println("Уравнение не имеет действительных корней!");
            return value = "Уравнение не имеет действительных корней!";
        }
    }
}
