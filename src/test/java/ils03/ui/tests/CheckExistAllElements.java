package ils03.ui.tests;

import ils03.ui.TestBase;
import ils03.ui.pojo.MainPage;
import io.qameta.allure.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Owner("Тимур Мугинов")
@Epic("Сайт 'http://u920152e.beget.tech/#'")
@Feature("Главная страница")
@Story("Проверка отображения")
public class CheckExistAllElements extends TestBase {

    MainPage mainPage = new MainPage();
    String[] labels = {"Введите Ваш имейл", "Введите Ваш пароль"};
    String[] titleValue = {"Авторизация", "Сколько Вам лет?"};

    @Test
    @Description("Тест проверяет наличие элементов на странице")
    public void testCheckExistAllElements() {
        assertThat("Заголовок '" + labels[0] + "' не отображается!", mainPage.isLabelExist(labels[0]));
        assertThat("Заголовок '" + labels[1] + "' не отображается!", mainPage.isLabelExist(labels[1]));
        assertThat("Таймер не отображается!", mainPage.isTimerExist(), equalTo(true));
        assertThat("Тайтл авторизации не отображается!", mainPage.isAuthTitleExist(titleValue[0]), equalTo(true));
    }
}

