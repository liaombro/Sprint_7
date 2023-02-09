package client;

import io.restassured.response.Response;
import serializable_objects.DeleteOrderBody;
import serializable_objects.Order;

import static io.restassured.RestAssured.given;

public class CancelOrderApi extends BaseApi {

    public static final String CANCEL_ORDER_ENDPOINT = "/api/v1/orders/cancel";

    public void cancelOrder(Order order){
        Long id = (long) order.getId();
        cancelOrderById(id);
    }

    public Response cancelOrderById(long id){
        DeleteOrderBody body = new DeleteOrderBody();
        Long ID = id;
        body.setTrackId(ID);

        return given()
                .spec(spec)
                .body(body)
                .when()
                .put(CANCEL_ORDER_ENDPOINT);
    }
}
