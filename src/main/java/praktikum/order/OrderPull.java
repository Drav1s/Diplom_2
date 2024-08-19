package praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;

public class OrderPull {

    @Step("Создание заказа с авторизацией")
    public void checkCreateOrderWithLoginOK(ValidatableResponse response) {
        response
                .statusCode(200)
                .assertThat().body("success", Matchers.equalTo(true));
    }


    @Step("Создание заказа без авторизации")
    public void checkCreateOrderWithoutLogin(ValidatableResponse response) {
        response
                .statusCode(401)
                .assertThat().body("message", Matchers.equalTo("You should be authorised"));
    }

    @Step("Создание заказа с ингредиентами")
    public void createOrderWithIngredient(ValidatableResponse response) {
        response
                .statusCode(200)
                .assertThat().body("success", Matchers.equalTo(true));
    }


    @Step("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredient(ValidatableResponse response) {
        response
                .statusCode(400)
                .assertThat().body("message", Matchers.equalTo("Ingredient ids must be provided"));
    }

    @Step("Создание заказа с неверным хешем ингредиентов")
    public void createOrderWithNonExistentHashOfIngredient(ValidatableResponse response) {
        response
                .statusCode(500);
    }

    @Step("Получение заказов конкретного пользователя с авторизацией")
    public void getUserOrdersWithLogin(ValidatableResponse response) {
        response
                .statusCode(200)
                .assertThat().body("success", Matchers.equalTo(true));
    }


    @Step("Получение заказов конкретного пользователя без авторизацией")
    public void getUserOrdersWithoutLogin(ValidatableResponse response) {
        response
                .statusCode(401)
                .assertThat().body("message", Matchers.equalTo("You should be authorised"));
    }
}
