import client.CancelOrderApi;
import client.CreateOrderApi;
import client.ListOrdersApi;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import serializable_objects.Order;
import serializable_objects.OrderList;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertTrue;

public class OrderListTest extends TestBase {

    private OrderList orders;
    private ListOrdersApi api = new ListOrdersApi();



    @Test
    @DisplayName("Запрос на получение списка заказов возвращает статус-код 200.")
    @Severity(SeverityLevel.MINOR)
    public void ordersListStatusCodeIs200(){
        Response response = api.getOrders();

        response.then().statusCode(200);
    }

    @Test
    @DisplayName("Список заказов не пуст.")
    @Severity(SeverityLevel.CRITICAL)
    public void ordersListIsNotEmpty(){

        orders = api.getOrdersList();

        assertTrue("List of orders should be non-empty",orders.getOrders().size()>0);
    }

    @Test
    @DisplayName("Каждый заказ из списка имеет ненулевой trackId")
    @Severity(SeverityLevel.NORMAL)
    public void ordersHaveTrackId(){

        orders = api.getOrdersList();

        for (Order order : orders.getOrders()) {
            MatcherAssert.assertThat("Track number is not null", order.getTrack(), notNullValue());
        }
    }


    @Test
    @DisplayName("Список заказов содержит созданный ранее заказ")
    @Severity(SeverityLevel.CRITICAL)
    public void orderListContainsCreatedOrder(){
        Order order = new Order(true);
        CreateOrderApi createOrderApi = new CreateOrderApi();
        Response responseId = createOrderApi.createOrder(order);
        Number id = responseId
                .then()
                .extract()
                .path("track");
        long trackId = id.longValue();
        CancelOrderApi cancelApi = new CancelOrderApi();

        orders = api.getOrdersList();

        MatcherAssert.assertThat("List should contain added element",
                orders.getTrackIds(), hasItem(trackId));

        cancelApi.cancelOrderById(trackId);
    }



}
