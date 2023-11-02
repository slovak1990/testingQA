package hackMeBank11.ui.pojo.appmanager;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;

import static com.codeborne.selenide.Selenide.*;

public class MyAppManager {
    private final String browser;
    private BaseObject baseObject;
    private BrowserHelper browserHelper;

    public MyAppManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        Configuration.browser = browser;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "http://tl.af-ctf.ru/#inputForAuth";

        baseObject = new BaseObject(this);
        browserHelper = new BrowserHelper(this);

        open("http://tl.af-ctf.ru/#inputForAuth");
    }

    public void stop() {
        localStorage().clear();
        clearBrowserCookies();
        closeWebDriver();
    }

    public BaseObject base() {
        return baseObject;
    }
    public BrowserHelper browser() {return browserHelper;}
}