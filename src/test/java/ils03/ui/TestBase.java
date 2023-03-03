package ils03.ui;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ils03.ui.pojo.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.open;
import static ils03.ui.pojo.MainPage.MAIN_PAGE_URL;

public class TestBase {

    MainPage mainPage = new MainPage();

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = Browsers.CHROME;
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void tearsDown() {
        Selenide.closeWebDriver();
    }
}
