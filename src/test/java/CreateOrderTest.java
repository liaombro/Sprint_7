import client.CancelOrderApi;
import client.CreateOrderApi;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import serializable_objects.Order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest extends TestBase {

    private String[] colors;

    private Order order;

    private CreateOrderApi api = new CreateOrderApi();


    public CreateOrderTest(String[] colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters(name = "Создание заказа с параметром colours, тест-кейс {index}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {new String[]{"BLACK", "GREY"}},
                {new String[]{"GREY, BLACK"}},
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{}}
        };
    }

    @Step
    @After
    @DisplayName("Инвалидация тестовых данных (отмена заказа)")
    public void clean() {
        CancelOrderApi cancelOrderApi = new CancelOrderApi();
        cancelOrderApi.cancelOrder(order);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void responseIsCorrectWhenColoursSpecified() {
        order = Order.getInstance();
        order.setColor(colors);

        Response response = api.createOrder(order);

        response
                .then()
                .statusCode(201)
                .and()
                .body("track", notNullValue());
    }

}
