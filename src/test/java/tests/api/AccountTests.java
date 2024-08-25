package tests.api;
import api.models.account.ErrorResponseModel;
import api.models.account.LoginRequestModel;
import com.github.javafaker.Faker;
import common.config.AuthDataConfig;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTests extends TestBaseApi {
    static final AuthDataConfig AUTH_DATA_CONFIG = ConfigFactory.create(AuthDataConfig.class, System.getProperties());

    @Test
    @DisplayName("Авторизация по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void authorizationTest() {
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

    @Test
    @DisplayName("Авторизация по некорректным почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void checkWrongLoginTest() {
        Faker faker = new Faker();
        String testEmail = faker.internet().emailAddress();
        String testPassword = faker.internet().password(6, 10, true, true, true);
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail(testEmail);
        loginData.setPassword(testPassword);
        loginData.setRememberMe(AUTH_DATA_CONFIG.rememberMe());
        ErrorResponseModel response = step("Авторизуемся по некорректным почте и паролю", () ->
                given(requestSpecAuth)
                        .contentType("application/json")
                        .body(loginData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpecStatusCode404)
                .extract().as(ErrorResponseModel.class));
        step("Проверяем текст об ошибке", () ->
                assertThat(response.getError().getMessage()).isEqualTo("Wrong credentials"));
    }
}
