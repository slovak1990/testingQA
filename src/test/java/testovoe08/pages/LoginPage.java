package testovoe08.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import testovoe08.TestBase;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends TestBase {
    // локаторы
    @Name("Поле 'Логин'")
    final private SelenideElement loginField = $(byId("loginEmail"));

    @Name("Поле 'Пароль'")
    final private SelenideElement loginPassword = $(byId("loginPassword"));

    @Name("Кнопка 'Вход'")
    final private SelenideElement buttonLogin = $(byId("authButton"));

    // работа с полем ввода
    @Step("Ввод логина {login}")
    public void inputLogin(String login) {
        app.base().inputField(loginField, login);
    }

    @Step("Ввод пароля {password}")
    public void inputPassword(String password) {
        app.base().inputField(loginPassword, password);
    }

    // клик на элементы
    @Step("Нажать на кнопку 'Вход'")
    public void clickOnLoginButton() {
        app.base().clickOnElement(buttonLogin);
    }
}