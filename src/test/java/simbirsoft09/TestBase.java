package simbirsoft09;

import com.codeborne.selenide.Browsers;
import io.qameta.allure.Step;
import org.junit.AfterClass;
import org.junit.Before;
import simbirsoft09.appmanager.MyAppManager;

import java.io.IOException;

public class TestBase {
    protected static final MyAppManager app
            = new MyAppManager(System.getProperty("browser", Browsers.CHROME));

    @Before
    @Step("Открываем браузер с настройками")
    public void setUp() throws IOException {
        app.init();
    }

    @AfterClass()
    @Step("Закрываем браузер")
    public static void tearDown() {
        app.stop();
    }
}