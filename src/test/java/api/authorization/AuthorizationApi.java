/*
package api.authorization;

import api.models.account.LoginResponseModel;
import com.codeborne.selenide.WebDriverRunner;
import common.config.AuthDataConfig;
import io.qameta.allure.Step;
import api.models.account.LoginRequestModel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;*/
/**//*


import static api.specs.Specs.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {
    static final AuthDataConfig AUTH_DATA_CONFIG = ConfigFactory.create(AuthDataConfig.class, System.getProperties());

    @Step("Авторизация на сайте")
    public static LoginResponseModel login() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail(AUTH_DATA_CONFIG.email());
        loginData.setPassword(AUTH_DATA_CONFIG.password());
        loginData.setRememberMe(AUTH_DATA_CONFIG.rememberMe());
        return given(requestSpecAuth)
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .body(loginData)
                .when()
                .post("/login")
                .then()
                .spec(responseSpecStatusCode200)
                .extract().as(LoginResponseModel.class);
    }
    @Step("Авторизация пользователя")
    public static void setCookiesInBrowser(LoginResponseModel loginResponse) {
        open("https://my.mts-link.ru/logo.11686b809df6f35e676a1aff1162064807ef6bbd1fda8d66b90a7e0f724d6874.svg");
        getWebDriver().manage().addCookie(new Cookie("sessionId", loginResponse.getSessionId()));
    }
    public static String extactValueFromCookieString(String value) {
        String cookieValue = String.valueOf(getWebDriver().manage().getCookieNamed(value));
        return cookieValue;
    }
}
*/
