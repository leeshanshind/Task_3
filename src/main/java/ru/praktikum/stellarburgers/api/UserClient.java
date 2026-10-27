package ru.praktikum.stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Клиент API для подготовки тестовых данных: создание и удаление пользователя.
 */
public class UserClient {
    private static final String BASE_URI = "https://qa-stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String USER_PATH = "/api/auth/user";
    private static final String AUTHORIZATION = "Authorization";

    @Step("Создать пользователя через API")
    public ValidatableResponse register(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then();
    }

    @Step("Залогиниться пользователем через API")
    public ValidatableResponse login(String email, String password) {
        return given()
                .spec(getSpec())
                .body(Map.of("email", email, "password", password))
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    @Step("Удалить пользователя через API")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getSpec())
                .header(AUTHORIZATION, accessToken)
                .when()
                .delete(USER_PATH)
                .then();
    }

    private RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }
}
