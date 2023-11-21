package hackMeBank11.api.tests;

import hackMeBank11.api.helpers.RestClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

@Owner("Тимур Мугинов")
@Epic("Регистрация")
@Feature("/calluserforsignup")
public class RegistrationTest {

    RestClient rest = new RestClient();

    String userName = RandomStringUtils.randomAlphabetic(8);
    String login = RandomStringUtils.randomAlphabetic(6) + "@test.ru";
    String phoneNumber = "79" + RandomStringUtils.randomNumeric(9);
    String password = RandomStringUtils.randomAlphanumeric(8);
    boolean type = true;
    String infoText = "Сейчас на ваш телефон поступит звонок, последние 4 цифры являются кодом";


    @Test
    @Description("200")
    public void registration200() {
        Response response = rest.registration(userName, login, phoneNumber, password, password);
        assertThat("Регистрация не подтвреждена", response.path("type").equals(type));
        assertThat("Инфосообщение '" + infoText + "' не появилось", response.path("text"));
    }

    @Test
    @Description("404") // запрос ушел с ошибкой, но в ответ 200
    public void registration404() {
        Response response = rest.registration("", "", "", "", "");
        assertThat("Регистрация подвреждена", response.path("type").equals(false));
    }
}