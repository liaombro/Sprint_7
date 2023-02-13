package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import serializable_objects.OrderList;

import static io.restassured.RestAssured.given;

public class ListOrdersApi extends  BaseApi {
    public static final String LIST_ORDERS_ENDPOINT = "/api/v1/orders";

    @Step("Получение списка всех заказов")
    public OrderList getOrdersList(){
        Response response =  getOrders();
        return response.body().as(OrderList.class);
    }

    @Step("Запрос на получение списка всех заказов")
    public Response getOrders(){
        return given()
                .spec(spec)
                .when()
                .get(LIST_ORDERS_ENDPOINT);
    }

}
