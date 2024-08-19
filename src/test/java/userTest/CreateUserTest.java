package userTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import praktikum.user.*;
import org.junit.After;

public class CreateUserTest {

    private String token;
    private CreateUser createUser;
    private final UserSteps userSteps = new UserSteps();
    private final UserPull pulls = new UserPull();

    @Test
    @DisplayName("Создание пользователя")
    @Description("Позитивная проверка")
    public void createUser() {
        createUser = CreateUser.creater();
        ValidatableResponse responseCreateUser = userSteps.createUser(createUser);
        LoginUser user = LoginUser.from(createUser);
        token = userSteps.loginUser(user).extract().path("accessToken");
        pulls.checkCreateOk(responseCreateUser);
    }

    @Test
    @DisplayName("Запрос с повторяющимся email")
    @Description("Негативная проверка")
    public void createUserDuplicate() {
        createUser = new CreateUser("andreypraktikum@yandex.ru", "Saskeqwerty", "Андрей");
        ValidatableResponse responseCreate = userSteps.createUser(createUser);
        pulls.checkCreateWithDuplicate(responseCreate );
    }

    @Test
    @DisplayName("Запрос без пароля")
    @Description("Негативная проверка")
    public void createUserWithoutPassword() {
        createUser = CreateUser.creater();
        CreateUser userWithoutPassword = CreateUser.from(createUser);
        userWithoutPassword.setPassword(null);

        ValidatableResponse responseCreate = userSteps.createUser(userWithoutPassword);
        pulls.checkCreateWithoutMandatoryData(responseCreate );

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
