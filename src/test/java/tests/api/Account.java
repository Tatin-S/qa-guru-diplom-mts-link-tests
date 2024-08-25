package tests.api;
import api.models.account.LoginRequestModel;
import api.models.account.LoginResponseModel;
import common.config.AuthDataConfig;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;
public class Account extends TestBaseApi {
    static final AuthDataConfig AUTH_DATA_CONFIG = ConfigFactory.create(AuthDataConfig.class, System.getProperties());

    @Test
    @DisplayName("Авторизация по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void authorizationTest() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail(AUTH_DATA_CONFIG.email());
        loginData.setPassword(AUTH_DATA_CONFIG.password());
        loginData.setRememberMe(AUTH_DATA_CONFIG.rememberMe());
        LoginResponseModel response = step("Авторизоваться по почте и паролю", () ->
                given(requestSpecAuth)
                        .contentType("application/x-www-form-urlencoded; charset=utf-8")
                        .body(loginData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpecStatusCode200))
                .extract().as(LoginResponseModel.class);

        step("Проверяем, что SessionId содержит не 1 символ и цифро-буквенные значения", () -> {
            assertThat(response.getSessionId()).isAlphanumeric();
            assertThat(response.getSessionId()).hasSizeGreaterThan(1);
        });
    }
}
