package ils03.ui.pojo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.util.Formatter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    public static final String MAIN_PAGE_URL = "http://u920152e.beget.tech/#";

    // локаторы
    @Name("Метки заголовков")
    private final String LABELS = "//label[text() = '%s']";

    @Name("Инпуты")
    private final String INPUTS = "//input[@type = '%s']";

    @Name("Кнопка войти")
    private SelenideElement enterButton = $(byXpath("//button[@class='form_auth_button']"));

    @Name("Элемент таймера")
    private SelenideElement timer = $(byXpath("//span[@id='timer-counter']"));

    @Name("Элемент тайтла авторизации")
    private final String TITLES = "//p[contains(text(), '%s')]";

    //* Работа с полем ввода
    @Step("Ввод текстового поля")
    public void setInputField(SelenideElement locator, String str) {
        locator.hover().shouldBe(enabled).setValue(str);
    }

    // проверка на наличие
    @Step("Проверка существования элемента")
    public Boolean isEnableElement(SelenideElement locator) {
        return locator.shouldBe(visible).is(enabled);
    }

    @Step("Проверка наличия заголовков {labelName}")
    public Boolean isLabelExist(String labelName) {
        return isEnableElement(getSelenideElement(LABELS, labelName));
    }

    @Step("Проверка наличия введённой почты {email} и пароля {password} в url страницы")
    public Boolean isInputsValueHaveInUrl(String email, String password) {
        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
        String str = url.replace("%40", "@");
        return str.contains(email) && str.contains(password);
    }

    @Step("Проверка отображения таймера")
    public Boolean isTimerExist() {
        return isEnableElement(timer);
    }

    @Step("Проверка отображения тайтла авторизации")
    public Boolean isAuthTitleExist(String titleValue) {
        return isEnableElement(getSelenideElement(TITLES, titleValue));
    }

    @Step("Проверка отмеченного чекбокса")
    public Boolean isRadiButtonChecked(String inputName) {
        return isElementContainsAttribute(getSelenideCollection(INPUTS, inputName), "checked", 0);
    }

    @Step("Проверка текста {containsText} на наличие")
    public Boolean isElementHaveContainsText(String containsText) {
        String value = getSelenideElement(TITLES, containsText).getText();
        return value.contains(containsText);
    }

    //Проверки на содержание атрибута
    @Step("Проверить, что элемент коллекции содержит атрибут {atr}")
    public boolean isElementContainsAttribute(ElementsCollection locator, String atr, int index) {
        return locator.get(index).has(Condition.attribute(atr));
    }

    // получение элементов
    @Step("Получение элемента Selenide")
    public final SelenideElement getSelenideElement(String locator, Object... value) {
        return $(byXpath(new Formatter().format(locator, value).toString()));
    }

    @Step("Получить коллекцию селенид элементов")
    public final ElementsCollection getSelenideCollection(String locator, Object... value) {
        return $$(byXpath(new Formatter().format(locator, value).toString()));
    }

    // клик на различные элементы
    @Step("Нажать на элемент")
    public void clickOnElement(SelenideElement locator) {
        locator.scrollTo();
        locator.shouldBe(enabled).click();
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickEnterButton() {
        clickOnElement(enterButton);
    }

    @Step("Нажать на кнопку 'Отправить'")
    public void clickSubmitButton(String inputName) {
        clickOnElement(getSelenideElement(INPUTS, inputName));
    }

    // ввод текста в поля
    @Step("Ввод текста в поля по имени {inputName}")
    public void setInputValue(String inputName, String value) {
        setInputField(getSelenideElement(INPUTS, inputName), value);
    }
}
