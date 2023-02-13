package client;

import io.qameta.allure.Step;
import serializable_objects.Courier;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class DeleteCourierApi extends BaseApi {


    public static final String COURIER_DELETE_ENDPOINT = "/api/v1/courier";
    @Step("Запрос на удаление курьера")
    public void deleteCourierById(String id) {
        String endpoint = String.format("%s/%s", COURIER_DELETE_ENDPOINT, id);
        given()
                .spec(spec)
                .when()
                .delete(endpoint);
    }
    public void deleteCourier(Courier courier){
        CourierLoginApi courierLoginApi = new CourierLoginApi();
        String id = courierLoginApi.getCourierId(courier);
        deleteCourierById(id);

    }
}
