package userTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.user.*;

public class LoginUserTest {

    private String token;
    private CreateUser createUser;
    private final UserSteps userSteps = new UserSteps();
    private final UserPull pulls = new UserPull();

    @Before
    @DisplayName("Создание пользователя")
    public void createUser() {
        createUser = CreateUser.creater();
        userSteps.createUser(createUser);
    }

    @Test
    @DisplayName("Логин пользователя")
    @Description("Позитивная проверка")
    public void loginUser() {
        LoginUser user = LoginUser.from(createUser);
        ValidatableResponse responseLoginUser = userSteps.loginUser(user);
        token = userSteps.loginUser(user).extract().path("accessToken");
        pulls.checkLoginOk(responseLoginUser);
    }

    @Test
    @DisplayName("Логин пользователя c несуществующей парой логин-пароль")
    @Description("Позитивная проверка")
    public void loginUserWithNonExistentData() {
        LoginUser user = new LoginUser("jngadsfngjnasd;jbh@yandex.ru", "jnasfjnfjk");
        ValidatableResponse responseLoginUser = userSteps.loginUser(user);
        pulls.checkLoginWithNonExistentData(responseLoginUser);
    }

    @After
    @DisplayName("Удаления пользователя")
    @Description("Очистка данных")
    public void deleteUser() {
        if (token != null) {
            DeleteUser deleteUser = new DeleteUser(token);

            ValidatableResponse removalResponse = userSteps.deleteUser(deleteUser);
            pulls.checkRemoveOk(removalResponse);
        }
    }
}
