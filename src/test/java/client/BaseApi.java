package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    public static final String BASE_ENDPOINT = "https://qa-scooter.praktikum-services.ru";

    public static final RequestSpecification spec = new RequestSpecBuilder()
            .addHeader("Content-Type", "application/json")
            .setBaseUri(BASE_ENDPOINT)
            .build();

}
