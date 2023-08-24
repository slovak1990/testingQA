package testovoe08.appmanager;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.util.Formatter;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class BaseObject {
    public MyAppManager app;

    public BaseObject(MyAppManager app) {
        this.app = app;
    }

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

    @Step("Нажать на элемент с JS")
    public void clickOnElementWithJS(SelenideElement locator) {
        locator.scrollTo();
        locator.shouldBe(enabled).click(usingJavaScript());
    }

    @Step("Сделать фокус и нажать на элемент")
    public void hoverAndClickOnElement(SelenideElement locator) {
        locator.hover().click();
    }

    @Step("Сделать фокус на элемент")
    public void hoverOnElement(SelenideElement locator) {
        locator.hover();
    }

    @Step("Скролл к элементу")
    public void scrollIntoViewElement(SelenideElement locator) {
        locator.scrollIntoView("{block: \"end\", inline: \"end\"}");
    }

    // ввод текста в поля
    @Step("Ввод текстового поля")
    public void inputField(SelenideElement locator, String str) {
        locator.hover().shouldBe(enabled).setValue(str);
    }

    @Step("Заполнить поле ввода")
    public void setValueInput(SelenideElement locator, String value) {
        locator.shouldBe(enabled).setValue(value);
    }

    @Step("Очистить и заполнить поле ввода")
    public void cleanAndSetValueInput(SelenideElement locator, String value) {
        locator.sendKeys(Keys.CONTROL + "a");
        locator.sendKeys(Keys.BACK_SPACE);
        locator.setValue(value);
    }

    @Step("Получение значения поля")
    public String getFieldValue(SelenideElement locator) {
        return locator.getValue();
    }

    @Step("Ввод текстового поля значением и нажатие Enter")
    public void inputFieldAndEnter(SelenideElement locator, String str) {
        locator.hover().shouldBe(enabled).setValue(str).pressEnter();
    }

    @Step("Ввод значения в текстовое поле с помощью Actions")
    public void setValueInputWithActions(SelenideElement locator, String value) {
        actions()
                .doubleClick(locator)
                .click(locator)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(value)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
    }
}