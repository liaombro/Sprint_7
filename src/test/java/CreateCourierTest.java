import client.CourierLoginApi;
import client.CreateCourierApi;
import client.DeleteCourierApi;
import org.junit.Before;
import serializable_objects.Courier;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest extends TestBase {
    private Courier courier;
    private boolean needsAfterStep = false;
    CreateCourierApi api = new CreateCourierApi();

    public static final String COURIER_ALREADY_EXISTS_ERROR = "Этот логин уже используется";
    public static final String INSUFFICIENT_DATA_ERROR = "Недостаточно данных для создания учетной записи";

    @Step("Удаление тестовых данных (курьер)")
    public void clean() {
        if (needsAfterStep) {
            CourierLoginApi courierLoginApi = new CourierLoginApi();
            DeleteCourierApi deleteCourierApi = new DeleteCourierApi();

            deleteCourierApi.deleteCourier(courier);
        }
    }

    @Test
    @DisplayName("Создание курьера, позитивный сценарий")
    @Severity(SeverityLevel.CRITICAL)
    public void responseIsCorrectWhenCourierIsCreated() {

        courier = new Courier(true);
        needsAfterStep = true;

        Response response = api.createCourier(courier);

        response
                .then()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));

    }

    @Test
    @DisplayName("Сообщение об ошибке, если курьер с таким логином уже создан")
    @Severity(SeverityLevel.NORMAL)
    public void errorMessageIfCourierAlreadyExists() {
        courier = new Courier(true);
        api.createCourier(courier);
        String login = courier.getLogin();
        Courier courier2 = new Courier(true);
        courier2.setLogin(login);
        needsAfterStep = true;

        Response response = api.createCourier(courier2);

        response
                .then()
                .statusCode(409)
                .and()
                .body("message", containsString(COURIER_ALREADY_EXISTS_ERROR));

    }

    @Test
    @DisplayName("Сообщение об ошибке, если в запросе не передан login")
    @Severity(SeverityLevel.NORMAL)
    public void errorMessageIfFieldLoginMissing() {
        Courier requestBody = new Courier();
        requestBody.setRandomPassword();
        requestBody.setRandomName();

        Response response = api.createCourier(requestBody);

        response
                .then()
                .statusCode(400)
                .and()
                .body("message", containsString(INSUFFICIENT_DATA_ERROR));
    }

    @Test
    @DisplayName("Сообщение об ошибке, если в запросе не передан password")
    @Severity(SeverityLevel.NORMAL)
    public void errorMessageIfFieldPasswordMissing() {
        Courier requestBody = new Courier();
        requestBody.setRandomLogin();
        requestBody.setRandomName();

        Response response = api.createCourier(requestBody);

        response
                .then()
                .statusCode(400)
                .and()
                .body("message", containsString(INSUFFICIENT_DATA_ERROR));

    }

    @Test
    @DisplayName("Сообщение об ошибке, если в запросе не передан firstName")
    @Severity(SeverityLevel.NORMAL)
    public void errorMessageIfFieldFirstNameMissing() {
        Courier requestBody = new Courier();
        requestBody.setRandomLogin();
        requestBody.setRandomPassword();

        Response response = api.createCourier(requestBody);

        response
                .then()
                .statusCode(400)
                .and()
                .body("message", containsString(INSUFFICIENT_DATA_ERROR));

    }
}
