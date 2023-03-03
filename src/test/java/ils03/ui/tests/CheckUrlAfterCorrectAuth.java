package ils03.ui.tests;

import ils03.ui.TestBase;
import ils03.ui.pojo.MainPage;
import io.qameta.allure.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

@Owner("Тимур Мугинов")
@Epic("Сайт 'http://u920152e.beget.tech/#'")
@Feature("Главная страница")
@Story("Проверка введённых данных в url после авторизации")
public class CheckUrlAfterCorrectAuth extends TestBase {
    MainPage mainPage = new MainPage();
    String[] inputs = {"email", "password", "submit", "radio"};
    String email = RandomStringUtils.randomAlphabetic(6) + "@testdata.com";
    String password = RandomStringUtils.randomAlphabetic(6);


    @Test
    @Description("Тест проверяет ввод корректных данных в поля и отображение их в url после входа")
    public void testCheckUrlAfterCorrectAuth() {
        mainPage.setInputValue(inputs[0], email);
        mainPage.setInputValue(inputs[1], password);
        mainPage.clickEnterButton();
        assertThat("В url не содержится введённых данных в инпуты!", mainPage.isInputsValueHaveInUrl(email, password));
    }
}
