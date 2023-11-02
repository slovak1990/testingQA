package hackMeBank11.ui.pojo.appmanager;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class BrowserHelper extends BaseObject {
    public BrowserHelper(MyAppManager app) {
        super (app);
    }

    @Step("Переключиться на окно по индексу {indexWindow}")
    public void switchToNextWindow(int indexWindow) {
        Selenide.switchTo().window(indexWindow);
    }

    @Step("Получить текстовое значение ифноокна браузера")
    public String getTextAlertWindow() {
        return Selenide.switchTo().alert().getText();
    }

    @Step("Подвтердить всплывающее окно браузера")
    public void confirmAlert() {
        Selenide.confirm();
    }
}