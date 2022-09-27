package intelogisTesting;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "https://ilswebreact-develop.azurewebsites.net/";

    @FindBy(how = How.XPATH, using = "//*[@id='login']")
    private SelenideElement loginField;

    @FindBy(how = How.XPATH, using = "//*[@id='password']")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//*[@class='ant-btn ant-btn-primary']")
    private SelenideElement enterButton;

    @Step("Setting login value")
    public LoginPage setLoginField(String login) {
        loginField.setValue(login);
        return this;
    }

    @Step("Setting password value")
    public LoginPage setPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    @Step("Click on enter button")
    public WorkTable clickEnterButton() {
        enterButton.click();
        return page(WorkTable.class);
    }
}
