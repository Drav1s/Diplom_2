package orderTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.order.OrderPull;
import praktikum.order.OrderSteps;
import praktikum.user.*;


public class GetOrderTest {

    private String token;
    private CreateUser createUser;
    private final UserSteps userSteps = new UserSteps();
    private final UserPull userPulls= new UserPull();
    private final OrderSteps orderSteps = new OrderSteps();
    private final OrderPull orderPulls= new OrderPull();

    @Before
    @DisplayName("Создание  и логин пользователя")
    public void createUser() {
        createUser = CreateUser.creater();
        userSteps.createUser(createUser);
        LoginUser user = LoginUser.from(createUser);
        token = userSteps.loginUser(user).extract().path("accessToken");
    }

    @Test
    @DisplayName("Получений данных о заказе с авторизацией")
    @Description("Позитивная проверка")
    public void getOrderWithAuth() {
        ValidatableResponse responseCreateOrder = orderSteps.getOrderWithAuth(token);

        orderPulls.getUserOrdersWithLogin(responseCreateOrder);
    }

    @Test
    @DisplayName("Получений данных о заказе с авторизацией")
    @Description("Негативная проверка")
    public void getOrderWithoutAuth() {
        ValidatableResponse responseCreateOrder = orderSteps.getOrderWithoutAuth();

        orderPulls.getUserOrdersWithoutLogin(responseCreateOrder);
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
