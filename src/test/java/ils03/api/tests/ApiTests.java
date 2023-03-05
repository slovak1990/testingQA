package ils03.api.tests;

import ils03.api.helpers.Clients;
import ils03.api.helpers.User;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiTests {

    private Clients clients = new Clients();
    private User user = new User();
    private String login;
    private String password;
    private ValidatableResponse response;
    private Cookie cookie;
    private int statusCode;
    private int vehicleID;
    private boolean expectedVehicle;

    @Before
    public void setUp() {
        clients = new Clients();
        login = user.getLogin();
        password = user.getPassword();
        response = clients.authorization(login, password);
        cookie = response.extract().detailedCookie("PHPSESSID");
    }

    @Test
    @Description("Проверка авторизации /Auth/signIn")
    public void testStatusCodeAuth() {
        statusCode = response.extract().statusCode();
        assertThat("Статус код не совпадает", statusCode == 200, equalTo(true));
    }

    @Test
    @Description("Проверка отображения списка автомобилей /vehicle/index")
    public void testVehicleIndex() {
        ValidatableResponse vehicleResponse = clients.vehicleIndex(cookie);
        statusCode = vehicleResponse.extract().statusCode();
        assertThat("Статус код не совпадает", statusCode == 200, equalTo(true));
    }

    @Test
    @Description("Проверка получения информации по ID автомобиля {vehicleID} /vehicle/index")
    public void testGetVehicle() {
        vehicleID = 69317;
        ValidatableResponse vehicleResponse = clients.getVehicleIndex(cookie, vehicleID);
        statusCode = vehicleResponse.extract().statusCode();
        int id = (Integer) vehicleResponse.extract().jsonPath().getList("data.ID").get(0);
        expectedVehicle = id == vehicleID;
        assertThat("Статус код не совпадает", statusCode == 200, equalTo(true));
        assertThat("Не совпадают ID автомобиля из запроса с ID автомобиля в ответе", expectedVehicle, equalTo(true));
    }
}
