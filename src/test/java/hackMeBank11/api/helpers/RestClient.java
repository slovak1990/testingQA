package hackMeBank11.api.helpers;

import hackMeBank11.api.models.RegistrationModel;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RestClient {
    private final static String BASE_URL = "http://tl.af-ctf.ru";

    public RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    public ResponseSpecification responseSpec(Integer statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(20000L))
                .build();
    }

    @Step("Регистрация")
    public Response registration(String userName, String login, String phoneNumber, String password, String password2) {
        RegistrationModel registrationModel = RegistrationModel.builder()
                .userName(userName)
                .login(login)
                .phoneNumber(phoneNumber)
                .password(password)
                .passwordValidation(password)
                .build();

        String filePath = "src/test/java/hackMeBank11/api/resources/registrationModel.json";
        FileHelper.createJsonFile(registrationModel, filePath);
        File jsonFile = new File(filePath);

        return given()
                .spec(requestSpec())
                .body(jsonFile)
                .post(BASE_URL + "/calluserforsignup")
                .then()
                .spec(responseSpec(200))
                .extract().response().prettyPeek();
    }
}