package hackMeBank11.ui.tests;

import hackMeBank11.ui.TestBase;
import hackMeBank11.ui.pojo.ui.steps.MainStep;
import io.qameta.allure.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Owner("Тимур Мугинов")
@Epic("Главная страница")
@Feature("Раздел 'Авторизация'")
@Story("Проверка подраздела авторизации")
public class AuthTest extends TestBase {

    MainStep mainStep = new MainStep();

    String[] values = {RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphabetic(6) + "@gmail.com",
            "7" + RandomStringUtils.randomAlphanumeric(10)};
    String[] fields = {"login", "email", "phone", "password", "passwordValidation", "authCode"};
    String title = "Карта HackMeBank уже сегодня";
    String password = "123456";
    String infoText = "Сейчас на ваш телефон поступит звонок, последние 4 цифры являются кодом";

    @Test
    @Description(value = "Тест проверяет заполнение полей авторизации")
    public void testCheckAuth() {
        mainStep.setTextValueInField(fields[0], values[0]);
        mainStep.setTextValueInField(fields[1], values[1]);
        mainStep.setTextValueInField(fields[2], values[2]);
        mainStep.setTextValueInField(fields[3], password);
        mainStep.setTextValueInField(fields[4], password);
        System.out.println(app.browser().getTextAlertWindow());
        assertThat("Инфоокно с текстом '" + infoText + "' не отображается",
                app.browser().getTextAlertWindow().equals(infoText), equalTo(true));

        app.browser().confirmAlert();
        assertThat("Модальное окно подвтерждения авторизации не отображается",
                mainStep.isAuthModalWindowExist(), equalTo(true));
        assertThat("Кол-во полей ввода кода не совпадает с ожидаемым",
                mainStep.isCountFieldsEqualExpected(fields[5], 4), equalTo(true));
    }

    @Test
    @Description(value = "Тест проверяет отображение заголовка в подразделе авторизации")
    public void testCheckTitle() {
        assertThat("Тайтл '" + title + "' не отображается", mainStep.isTitleExist(title), equalTo(true));
    }
}