package hackMeBank11.ui.pojo.ui.page;

import com.codeborne.selenide.SelenideElement;
import hackMeBank11.ui.TestBase;
import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage extends TestBase {
    /* локаторы */
    @Name("Поля ввода")
    private final String FIELDS = "//label//input[@name='%s']";

    @Name("Модальное окно подтверждения авторизации")
    private final SelenideElement modalAuth = $x("//div[@id='modalWindow']");

    @Name("Заголовки подразделов на странице")
    private final String TITLES_ON_PAGE = "//h1[text()='%s']";

    /* Работа с полем ввода */
    @Step("Ввод текстового значения {value} в поле ввода {fieldName}")
    public void setTextValueInField(String fieldName, String value) {
        app.base().setInputField(app.base().getSelenideElement(FIELDS, fieldName), value);
    }

    /* Проверки */
    @Step("Проверить наличие модального окна подтверждения авторизации")
    public boolean isAuthModalWindowExist() {
        return app.base().isExistElementWithWait(modalAuth, 10);
    }

    @Step("Проверить кол-во полей {fieldName} с ожидаемым результатом {expectedCount}")
    public boolean isCountFieldsEqualExpected(String fieldName, int expectedCount) {
        return app.base().getCountOfCollection(app.base().getSelenideCollection(FIELDS, fieldName)) == expectedCount;
    }

    @Step("Проверить наличие тайтла {title} на странице")
    public boolean isTitleExist(String title) {
        return app.base().isExistElementWithWait(app.base().getSelenideElement(TITLES_ON_PAGE, title), 10);
    }
}