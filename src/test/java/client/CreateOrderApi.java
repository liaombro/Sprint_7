package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import serializable_objects.Order;

import static client.BaseApi.BASE_ENDPOINT;
import static client.BaseApi.spec;
import static io.restassured.RestAssured.given;

public class CreateOrderApi {

    public static final String CREATE_ORDER_ENDPOINT = "/api/v1/orders";
    @Step("Запрос на создание заказа")
    public Response createOrder(Order order) {
        Response response = given()
                .spec(spec)
                .body(order)
                .when()
                .post(CREATE_ORDER_ENDPOINT);

        Number trackId = response.then().extract().path("track");
        long id = trackId.longValue();
        order.setTrack(id);

        return response;
    }

}
