import client.CourierLoginApi;
import client.CreateCourierApi;
import client.DeleteCourierApi;
import serializable_objects.Courier;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierLoginTest extends TestBase {


    private Courier courier;
    private Courier courier2;
    CourierLoginApi api = new CourierLoginApi();


    public static final String INSUFFICIENT_DATA_ERROR = "Недостаточно данных для входа";
    public static final String INCORRECT_LOGIN_DATA_ERROR = "Учетная запись не найдена";

    @Before
    @Step("Создание тестовых данных (курьер)")
    public void createCourier() {

        courier = new Courier();
        courier.setRandomLogin();
        courier.setRandomPassword();
        CreateCourierApi api = new CreateCourierApi();

        api.createCourier(courier);
    }

    @After
    @Step("Удаление тестовых данных (курьер)")
    public void clean() {
        DeleteCourierApi api = new DeleteCourierApi();
        api.deleteCourier(courier);
    }



    @Test
    @DisplayName("Логин курьера, позитивный сценарий")
    @Severity(SeverityLevel.CRITICAL)
    public void responseIsCorrectWhenCourierLogsIn() {
        String StringId = api.getCourierId(courier);
        int expectedId = Integer.parseInt(StringId);

        Response response = api.courierLogin(courier);

        response.then()
                .statusCode(200).and().body("id", equalTo(expectedId));

    }

    @Test
    @DisplayName("Сообщение об ошибке, если в запросе не передан login")
    public void errorMessageWhenNoLoginField() {
        String courierPassword = courier.getPassword();
        courier2 = new Courier();
        courier2.setPassword(courierPassword);

        Response response = api.courierLogin(courier2);

        response
                .then()
                .statusCode(400).and().body("message", equalTo(INSUFFICIENT_DATA_ERROR));

    }

    @Test
    @DisplayName("Сообщение об ошибке, если в запросе неверный login")
    public void errorMessageWhenIncorrectLoginField() {
        String courierPassword = courier.getPassword();
        courier2 = new Courier();
        courier2.setPassword(courierPassword);
        courier2.setLogin("ololotrololo");

        Response response = api.courierLogin(courier2);

        response
                .then()
                .statusCode(404).and().body("message", equalTo(INCORRECT_LOGIN_DATA_ERROR));

    }

    @Test
    @DisplayName("Сообщение об ошибке, если в запросе не передан password")
    public void errorMessageWhenNoPasswordField() {
        String courierLogin = courier.getLogin();
        courier2 = new Courier();
        courier2.setLogin(courierLogin);

        Response response = api.courierLogin(courier2);

        response
                .then()
                .statusCode(400).and().body("message", equalTo(INSUFFICIENT_DATA_ERROR));


    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Сообщение об ошибке, если в запросе неверный password")
    public void errorMessageWhenIncorrectPasswordField() {
        String courierLogin = courier.getLogin();
        courier2 = new Courier();
        courier2.setLogin(courierLogin);
        courier2.setPassword("ololotrololo");

        Response response = api.courierLogin(courier2);

        response
                .then()
                .statusCode(404).and().body("message", equalTo(INCORRECT_LOGIN_DATA_ERROR));

    }
}
