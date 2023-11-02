package simbirsoft09.appmanager;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

public class MyAppManager {
    private final Properties properties;
    private final String browser;
    private BaseObject baseObject;
    private FileHelper fileHelper;
    private NavigationHelper navigationHelper;

    public MyAppManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
        WebDriverManager.chromedriver().setup();
        Configuration.browser = browser;
        Configuration.timeout = 15000;
        Configuration.browserSize = properties.getProperty("browser.size");
        Configuration.baseUrl = System.getProperty("baseUrlSimbir", properties.getProperty("baseUrlSimbir"));
        Configuration.headless = false;

        baseObject = new BaseObject(this);
        fileHelper = new FileHelper(this);
        navigationHelper = new NavigationHelper(this);
        open(Configuration.baseUrl);
    }

    public void stop() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            localStorage().clear();
            clearBrowserCookies();
            closeWebDriver();
        }
    }

    public BaseObject base() {
        return baseObject;
    }

    public FileHelper doc() {
        return fileHelper;
    }

    public NavigationHelper go() {
        return navigationHelper;
    }
}