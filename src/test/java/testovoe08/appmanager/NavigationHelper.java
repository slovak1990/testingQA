package testovoe08.appmanager;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class NavigationHelper extends BaseObject {
    public NavigationHelper(MyAppManager app) {
        super(app);
    }

    @Name("URL страницы авторизации")
    final private String startUrl = Configuration.baseUrl;

    @Step("Переход к заданной странице по URL")
    public void toPage(String url) {
        if (!WebDriverRunner.url().toLowerCase().equals(url)) {
            open(url);
        }
    }

    @Step("Переход к странице по индексу")
    public void switchToPageByIndex(int index) {
        switchTo().window(index);
    }

    @Step("Переход на страницу авторизации")
    public void toLoginPage() {
        toPage(startUrl);
    }
}