package simbirsoft09.tests.loginPage;

import io.qameta.allure.*;
import org.junit.Test;
import simbirsoft09.TestBase;
import testovoe08.steps.LoginStep;

@Owner("Тимур Мугинов")
@Epic("Страница авторизации")
@Feature("Хэдер")
@Story("Проверка наличия хэдера и заголовка")
public class CheckHeaderPage extends TestBase {
    LoginStep loginStep = new LoginStep();

    String[] headerButton = {"Home", "Logout"};
    String[] logButton = {"customer", "manager"};
    String titleText = "XYZ Bank";

    @Test
    @Description("Тест проверяет наличие хэдера и текстовое значение заголовка")
    public void testCheckHeaderPage() {

    }
}