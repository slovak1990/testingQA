package testovoe08.steps;

import io.qameta.allure.Step;
import testovoe08.model.UserData;
import testovoe08.pages.LoginPage;

public class LoginStep extends LoginPage {
    @Step("Авторизация пользователем {user}")
    public void login(UserData user) {
        app.go().toLoginPage();
        inputLogin(user.getLogin());
        inputPassword(user.getPassword());
        clickOnLoginButton();
    }
}