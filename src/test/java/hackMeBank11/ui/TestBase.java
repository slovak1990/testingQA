package hackMeBank11.ui;

import com.codeborne.selenide.Browsers;
import hackMeBank11.ui.pojo.appmanager.MyAppManager;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    protected static final MyAppManager app
            = new MyAppManager(Browsers.CHROME);
    Logger logger = LoggerFactory.getLogger(TestBase.class);

//    @BeforeTest
//    public void globalLogs(final ITestContext context) {
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
//        logger.info("Cьют: " + context.getCurrentXmlTest().getSuite().getName());
//    }
//
    @BeforeClass
    public static void setUp() {
        app.init();
        //context.setAttribute("app", app);
    }
//
//    @BeforeMethod
//    public void logTestStart(Method m, Object[] p) {
//        logger.info("Тест " + m.getName() + " с параметрами " + Arrays.asList(p) + " запущен:-->");
//    }
//
//    @AfterClass(alwaysRun = true)
//    @Step("Закрываем браузер")
//    public void tearDown() {
//        app.stop();
//    }
}