package playwrightDemo.appmanager;

import com.microsoft.playwright.*;
import io.qameta.allure.Step;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseObject {
    protected Browser browser;
    protected Page page;
    protected BrowserContext context;
    private Boolean isTraceEnabled = false;
    final static protected String BASE_URL = "https://uralhockey.ru/";

    @BeforeClass
    public void setUp() {
        // инициализация браузера
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));

        // создаем контекст для браузера
        context = browser.newContext(new Browser.NewContextOptions().setScreenSize(1920, 1080));

        // трейсинг замедляет скорость заполнения полей
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(false));

        isTraceEnabled = false;

        // создаем новую страницу
        page = context.newPage();
        page.navigate(BASE_URL);
    }

    @AfterClass
    @Step("Закрыть браузер")
    public void tearDown() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
    }
}