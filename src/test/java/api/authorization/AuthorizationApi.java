package api.authorization;

import common.config.AuthDataConfig;
import io.qameta.allure.Step;
import api.models.account.LoginRequestModel;
import org.aeonbits.owner.ConfigFactory;

import static api.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {
    static final AuthDataConfig AUTH_DATA_CONFIG = ConfigFactory.create(AuthDataConfig.class, System.getProperties());

    @Step("Авторизация на сайте")
    public static void getSuccessfulAuthorization() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail(AUTH_DATA_CONFIG.email());
        loginData.setPassword(AUTH_DATA_CONFIG.password());
        loginData.setRememberMe(AUTH_DATA_CONFIG.rememberMe());
        step("Авторизуемся по почте и паролю", () ->
                given(requestSpecAuth)
                        .contentType("application/json")
                        .body(loginData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpecStatusCode200));
    }
}
