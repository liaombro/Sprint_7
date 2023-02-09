package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import serializable_objects.Courier;

import static io.restassured.RestAssured.given;

public class CourierLoginApi extends BaseApi {
    public static final String COURIER_LOGIN_ENDPOINT = "/api/v1/courier/login";


    @Step("Запрос на логин курьера")
    public Response courierLogin(Courier requestBody) {
        return given()
                .spec(spec)
                .body(requestBody)
                .when()
                .post(COURIER_LOGIN_ENDPOINT);
    }

    @Step("Запрос на получение ID курьера")
    public String getCourierId(Courier courier) {

        Response response = given()
                .spec(spec)
                .body(courier)
                .when()
                .post(COURIER_LOGIN_ENDPOINT);

        Courier courierWithId = response.body().as(Courier.class);
        return courierWithId.getId();
    }


}
