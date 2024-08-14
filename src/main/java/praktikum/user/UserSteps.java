package praktikum.user;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static praktikum.CONSTANT.*;


public class UserSteps {

    public static RequestSpecification requestSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(HOST);
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(CreateUser createUser) {
        return requestSpec()
                .body(createUser)
                .when()
                .post(CREATE_USER)
                .then().log().all();
    }

    @Step("Логин пользователя в системе")
    public ValidatableResponse loginUser(LoginUser loginUser) {
        return requestSpec()
                .body(loginUser)
                .when()
                .post(LOGIN_USER)
                .then().log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(DeleteUser deleteUser) {
        return requestSpec()
                .headers(
                        "Authorization", deleteUser.getUserToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(deleteUser)
                .when()
                .delete(DELETE_USER)
                .then().log().all();
    }

    @Step("Изменение данных пользователя c авторизацией")
    public ValidatableResponse editUserWithAuth(EditUser editUser, String token) {
        return requestSpec()
                .headers(
                        "Authorization", token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(editUser)
                .when()
                .patch(EDIT_USER)
                .then().log().all();
    }

    @Step("Изменение данных пользователя без авторизации")
    public ValidatableResponse editUserWithoutAuth(EditUser editUser) {
        return requestSpec()
                .body(editUser)
                .when()
                .patch(EDIT_USER)
                .then().log().all();
    }
}
