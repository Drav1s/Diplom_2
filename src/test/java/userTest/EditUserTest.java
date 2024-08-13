package userTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.user.*;

public class EditUserTest {

    private String token;
    private CreateUser createUser;
    private final UserSteps userSteps = new UserSteps();
    private final UserPull pulls = new UserPull();

    @Before
    @DisplayName("Создание  и логин пользователя")
    public void createUser() {
        createUser = CreateUser.creater();
        userSteps.createUser(createUser);
        LoginUser user = LoginUser.from(createUser);
        token = userSteps.loginUser(user).extract().path("accessToken");
    }

    @Test
    @DisplayName("Изменение данных пользователя с авторизацией")
    @Description("Позитивная проверка")
    public void editUserWithAuth() {
        EditUser editUser = EditUser.editer();
        ValidatableResponse responseEditUser = userSteps.editUserWithAuth(editUser, token);
        pulls.checkEditUserWithLogin(responseEditUser);
    }

    @Test
    @DisplayName("Изменение данных пользователя без авторизации")
    @Description("Позитивная проверка")
    public void editUserWithoutAuth() {
        EditUser editUser = EditUser.editer();
        ValidatableResponse responseEditUser = userSteps.editUserWithoutAuth(editUser);
        pulls.checkEditUserWithoutLogin(responseEditUser);
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
