package api.authorization;

import io.qameta.allure.Step;
import api.models.account.LoginRequestModel;

import static io.restassured.RestAssured.given;
import static api.specs.Specs.requestSpec;
import static api.specs.Specs.responseSpecStatusCode200;

public class AuthorizationApi {

    @Step("Авторизация на сайте")
    public String getSessionId(LoginRequestModel loginRequest) {
        return given(requestSpec)
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .body(loginRequest.loginBodyModel())
                .when()
                .post("/login")
                .then()
                .spec(responseSpecStatusCode200)
                .extract().cookie("sessionId");
    }
}
