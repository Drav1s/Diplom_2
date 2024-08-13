package orderTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.order.CreateOrder;
import praktikum.order.OrderPull;
import praktikum.order.OrderSteps;
import praktikum.user.*;

import java.util.List;

public class CreateOrderTest {
    private String token;
    private CreateUser createUser;
    private final UserSteps userSteps = new UserSteps();
    private final UserPull userPulls= new UserPull();
    private CreateOrder order;
    private final OrderSteps orderSteps = new OrderSteps();
    private final OrderPull orderPulls= new OrderPull();
    private List<String> orderWithIngredient;
    private List<String> orderWithoutIngredient;
    private List<String> orderWithNonExistentIngredient;

    @Before
    @DisplayName("Создание  и логин пользователя")
    public void createUser() {
        createUser = CreateUser.creater();
        userSteps.createUser(createUser);
        LoginUser user = LoginUser.from(createUser);
        token = userSteps.loginUser(user).extract().path("accessToken");
        orderWithIngredient = orderSteps.getIngredients().extract().path("data._id");
    }

    @Test
    @DisplayName("Создание заказа с авторизацией и ингредиентами")
    @Description("Позитивная проверка")
    public void createOrderWithAuth() {
        order = new CreateOrder(orderWithIngredient);
        ValidatableResponse responseCreateOrder = orderSteps.createOrderWithAuth(order, token);

        orderPulls.checkCreateOrderWithLoginOK(responseCreateOrder);
    }

    @Test
    @DisplayName("Создание заказа без авторизации и ингредиентами")
    @Description("Негативная проверка")
    public void createOrderWithoutAuth() {
        order = new CreateOrder(orderWithIngredient);
        ValidatableResponse responseCreateOrder = orderSteps.createOrderWithoutAuth(order);

        orderPulls.checkCreateOrderWithoutLogin(responseCreateOrder);
    }

    @Test
    @DisplayName("Создание заказа c авторизацией и без ингредиентов")
    @Description("Негативная проверка")
    public void createOrderWithoutIngredient() {
        orderWithoutIngredient = List.of();
        order = new CreateOrder(orderWithoutIngredient);
        ValidatableResponse responseCreateOrder = orderSteps.createOrderWithAuth(order, token);

        orderPulls.createOrderWithoutIngredient(responseCreateOrder);
    }

    @Test
    @DisplayName("Создание заказа c авторизацией и неверным хэшем ингредиентов")
    @Description("Негативная проверка")
    public void createOrderWithNonExistentHashOfIngredient() {
        orderWithNonExistentIngredient = List.of("61cjw6a71d1j42001bdaaa79");
        order = new CreateOrder(orderWithNonExistentIngredient);
        ValidatableResponse responseCreateOrder = orderSteps.createOrderWithAuth(order, token);

        orderPulls.createOrderWithNonExistentHashOfIngredient(responseCreateOrder);
    }

    @After
    @DisplayName("Удаления пользователя")
    @Description("Очистка данных")
    public void deleteUser() {
        if (token != null) {
            DeleteUser deleteUser = new DeleteUser(token);

            ValidatableResponse removalResponse = userSteps.deleteUser(deleteUser);
            userPulls.checkRemoveOk(removalResponse);
        }
    }
}
