package ils03.api.helpers;

import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Clients extends RestClient {

    private static final String AUTH_PATH = "/Auth/signIn";
    private static final String VEHICLE_PATH = "/vehicle/index";

    @Step("Авторизация")
    public ValidatableResponse authorization(String login, String password) {
        return given()
                .spec(getAuthSpec())
                .multiPart("l", login, "multipart/form-data")
                .multiPart("p", password, "multipart/form-data")
                .when()
                .post(AUTH_PATH)
                .then().log().all();
    }

    @Step("Получение общего списка автомобилей")
    public ValidatableResponse vehicleIndex(Cookie cookie) {
        return given()
                .spec(getSpecWithCookie(cookie))
                .when()
                .post(VEHICLE_PATH)
                .then().log().all();
    }

    @Step("Получение информации по автомобилю")
    public ValidatableResponse getVehicleIndex(Cookie cookie, int vehicleID) {
        return given()
                .spec(getSpecWithCookie(cookie))
                .multiPart("data[ids][]", vehicleID)
                .when()
                .post(VEHICLE_PATH)
                .then().log().all();
    }
}
