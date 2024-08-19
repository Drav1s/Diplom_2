package praktikum.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;

public class UserPull {

    @Step("Успешное создание учётной записи")
    public void checkCreateOk(ValidatableResponse response) {
        response
                .statusCode(200)
                .assertThat().body("success", Matchers.equalTo(true));
    }

    @Step("Запрос без логина или пароля")
    public void checkCreateWithoutMandatoryData(ValidatableResponse response) {
        response
                .statusCode(403)
                .assertThat().body("message", Matchers.equalTo("Email, password and name are required fields"));
    }

    @Step("Запрос с повторяющимся логином")
    public void checkCreateWithDuplicate(ValidatableResponse response) {
        response
                .statusCode(403)
                .assertThat().body("message", Matchers.equalTo("User already exists"));
    }

    @Step("Изменение данных с авторизацией")
    public void checkEditUserWithLogin(ValidatableResponse response) {
        response
                .statusCode(200)
                .assertThat().body("success", Matchers.equalTo(true));
    }


    @Step("Изменение данных без авторизацией")
    public void checkEditUserWithoutLogin(ValidatableResponse response) {
        response
                .statusCode(401)
                .assertThat().body("message", Matchers.equalTo("You should be authorised"));
    }

    @Step("Успешный логин")
    public String checkLoginOk(ValidatableResponse response) {
        return response
                .statusCode(200)
                .assertThat().body("success", Matchers.equalTo(true))
                .extract()
                .path("accessToken");
    }


    @Step("Запрос c несуществующей парой логин-пароль")
    public void checkLoginWithNonExistentData(ValidatableResponse response) {
        response
                .statusCode(401)
                .assertThat().body("message", Matchers.equalTo("email or password are incorrect"));
    }

    @Step("Успешное удаление")
    public void checkRemoveOk(ValidatableResponse response) {
        response
                .statusCode(202)
                .assertThat().body("success", Matchers.equalTo(true));
    }
}
