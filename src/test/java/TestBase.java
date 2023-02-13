import io.qameta.allure.Step;
import io.restassured.response.Response;

import static client.BaseApi.BASE_ENDPOINT;
import static io.restassured.RestAssured.given;

public class TestBase {






    @Step("Запрос на отмену заказа")
    public Response deleteOrder(String track){
        return null;
        //rewrite
    }

}
