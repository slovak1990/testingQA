package ils03.ui.tests;

import ils03.ui.TestBase;
import ils03.ui.pojo.MainPage;
import io.qameta.allure.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Owner("Тимур Мугинов")
@Epic("Сайт 'http://u920152e.beget.tech/#'")
@Feature("Главная страница")
@Story("Проверка чекбокса")
public class CheckDefaultRadiButton extends TestBase {
    MainPage mainPage = new MainPage();
    String[] titleValue = {"Авторизация", "Сколько Вам лет?"};
    String[] inputs = {"email", "password", "submit", "radio"};
    String email = RandomStringUtils.randomAlphabetic(6) + "@testdata.com";
    String password = RandomStringUtils.randomAlphabetic(6);

    @Test
    @Description("Тест проверяет выбранный чекбокс по умолчанию")
    public void testCheckDefaultRadiButton() {
        mainPage.setInputValue(inputs[0], email);
        mainPage.setInputValue(inputs[1], password);
        mainPage.clickEnterButton();
        assertThat("Тайтл возраста не отображается!", mainPage.isAuthTitleExist(titleValue[1]), equalTo(true));
        assertThat("Чекбокс по умолчанию не на первом элементе!", mainPage.isRadiButtonChecked(inputs[3]), equalTo(true));
    }
}
