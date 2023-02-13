package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import serializable_objects.Courier;

import static io.restassured.RestAssured.given;

public class CreateCourierApi extends BaseApi {
    public static final String COURIER_CREATE_ENDPOINT = "/api/v1/courier";

    @Step("Запрос на создание курьера")
    public Response createCourier(Courier requestBody) {

        return given()
                .spec(spec)
                .body(requestBody)
                .when()
                .post(COURIER_CREATE_ENDPOINT);
    }
}
