package ils03.api.helpers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    private final static String BASE_URL = "https://release.intelogis.ru/newTms/vehicle/index";

    protected RequestSpecification getAuthSpec() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.MULTIPART)
                .build();
    }


    protected RequestSpecification getSpecWithCookie(Cookie cookie) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.MULTIPART)
                .addCookie(cookie)
                .build();
    }
}
