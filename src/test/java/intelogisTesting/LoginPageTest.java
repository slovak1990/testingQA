package intelogisTesting;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static intelogisTesting.LoginPage.LOGIN_PAGE_URL;
import static intelogisTesting.WorkTable.WORK_TABLE_URL;

public class LoginPageTest {

    private LoginPage loginPage = new LoginPage();
    private String login = "rekame2869@seinfaq.com";
    private String password = "XRCa91zn4fsJzcHW";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = Browsers.CHROME;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
    }

    @After
    public void tearsDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @Description("Correct validation with login and password")
    public void loginTest() {
        loginPage.setLoginField(login)
                .setPasswordField(password)
                .clickEnterButton();

        webdriver().shouldHave(url(WORK_TABLE_URL));
    }
}
