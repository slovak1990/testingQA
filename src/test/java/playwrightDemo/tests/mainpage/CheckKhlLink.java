package playwrightDemo.tests.mainpage;

import com.microsoft.playwright.Page;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Test;
import playwrightDemo.appmanager.BaseObject;
import playwrightDemo.pages.MainPage;

import static org.junit.Assert.assertEquals;

@Epic("Главная страница")
@Feature("Ссылки в хэдэре")
@Story("Проверка работы ссылок")
public class CheckKhlLink extends BaseObject {
    MainPage mainPage = new MainPage();
    String linkName = "khl";
    String khlUrl = "https://khl.ru/";

    @Test
    @Description(value = "Тест проверяет работу перехода по ссылке 'КХЛ' в хэдере страницы")
    public void khlLinkTest() {
        Page popUp = page.waitForPopup(() -> {
            page.locator(mainPage.linksLocator(linkName)).click();
        });
        assertEquals(khlUrl, popUp.url());
    }
}
