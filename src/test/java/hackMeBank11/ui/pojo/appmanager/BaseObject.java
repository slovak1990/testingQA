package hackMeBank11.ui.pojo.appmanager;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Formatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class BaseObject {
    public MyAppManager app;
    public BaseObject(MyAppManager app) {
        this.app = app;
    }

    // Работа с полем ввода
    @Step("Ввод текстового поля")
    public void setInputField(SelenideElement locator, String str) {
        locator.hover().shouldBe(enabled).setValue(str);
    }

    // Проверка на наличие
    @Step("Проверка существования элемента")
    public Boolean isEnableElement(SelenideElement locator) {
        return locator.shouldBe(visible).is(enabled);
    }

    @Step("Проверка элемента на наличие с заданным ожиданием в {wait} сек")
    public Boolean isExistElementWithWait(SelenideElement locator, int wait) {
        return locator.should(exist, Duration.ofSeconds(wait)).exists();
    }

    // Проверки на содержание атрибута
    @Step("Проверить, что элемент коллекции содержит атрибут {atr}")
    public boolean isElementContainsAttribute(ElementsCollection locator, String atr, int index) {
        return locator.get(index).has(Condition.attribute(atr));
    }

    // Получение элементов
    @Step("Получение элемента Selenide")
    public final SelenideElement getSelenideElement(String locator, Object... value) {
        return $(byXpath(new Formatter().format(locator, value).toString()));
    }

    @Step("Получить коллекцию селенид элементов")
    public final ElementsCollection getSelenideCollection(String locator, Object... value) {
        return $$(byXpath(new Formatter().format(locator, value).toString()));
    }

    @Step("Получить количество элементов в коллекции без ожидаемого размера")
    public int getCountOfCollection(ElementsCollection collection) {
        return $$(collection).size();
    }

    // Клик на различные элементы
    @Step("Нажать на элемент")
    public void clickOnElement(SelenideElement locator) {
        locator.scrollTo();
        locator.shouldBe(enabled).click();
    }
}