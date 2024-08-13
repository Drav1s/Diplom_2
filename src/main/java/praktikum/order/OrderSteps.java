package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static praktikum.CONSTANT.*;

public class OrderSteps {

    public static RequestSpecification requestSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(HOST);
    }

    @Step("Создание заказа с авторизацией")
    public ValidatableResponse createOrderWithAuth(CreateOrder createOrder, String token) {
        return requestSpec()
                .headers(
                        "Authorization", token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(createOrder)
                .when()
                .post(CREATE_ORDER)
                .then().log().all();
    }

    @Step("Создание заказа без авторизации")
    public ValidatableResponse createOrderWithoutAuth(CreateOrder createOrder) {
        return requestSpec()
                .body(createOrder)
                .when()
                .post(CREATE_ORDER)
                .then().log().all();
    }

    @Step("Получние данных о заказе с авторизацией")
    public ValidatableResponse getOrderWithAuth(String token) {
        return requestSpec()
                .headers(
                        "Authorization", token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .get(GET_ORDERS)
                .then().log().all();
    }

    @Step("Получние данных о заказе без авторизации")
    public ValidatableResponse getOrderWithoutAuth() {
        return requestSpec()

                .when()
                .get(GET_ORDERS)
                .then().log().all();
    }

    @Step("Создание заказа без авторизации")
    public ValidatableResponse getIngredients() {
        return requestSpec()
                .when()
                .get(GET_INGREDIENTS)
                .then().log().all();
    }
}
