package simbirsoft09.pages;

import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import simbirsoft09.TestBase;

public class LoginPage extends TestBase {
    // локаторы
    @Name("Хэдер страницы")
    private final String HEADER = "//div[@class='box mainhdr']";

    @Name("Заголовок страницы")
    private final String TITLE = HEADER + "/strong";

    @Name("Кнопки в хэдере")
    private final String HEADER_BUTTONS = HEADER + "/button[text()='%s']";

    @Name("Кнопки авторизации по ролям")
    private final String LOGIN_BUTTONS = "//div[@class='center']/button[@ng-click='%s()']";

    // проверки на наличие
    @Step("Проверка наличия хэдера страницы")
    public boolean isHeaderExist() {
        return app.base().isEnableElement(app.base().getSelenideElement(HEADER));
    }

    @Step("Проверить наличие текста {expectedValue} в тайтле страницы")
    public boolean isHeaderHaveText(String expectedValue) {
        return app.base().elementShouldHaveText(app.base().getSelenideElement(TITLE), expectedValue);
    }

    // клик на элемент
    @Step("Нажать на кнопку авторизации по роли {button}")
    public void clickLoginButton(String button) {
        app.base().clickOnElement(app.base().getSelenideElement(LOGIN_BUTTONS, button));
    }

    @Step("Нажать на кнопку {button} в хэдере")
    public void clickHeaderButton(String button) {
        app.base().clickOnElement(app.base().getSelenideElement(HEADER_BUTTONS, button));
    }
}