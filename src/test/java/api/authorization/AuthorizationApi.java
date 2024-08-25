package api.authorization;

import com.codeborne.selenide.WebDriverRunner;
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
                .body(loginRequest)
                .when()
                .post("https://my.mts-link.ru/api/login")
                .then()
                .spec(responseSpecStatusCode200)
                .extract().cookie("sessionId");
    }

    public static String extactValueFromCookieString(String value) {
        String cookieValue = String.valueOf(WebDriverRunner.getWebDriver().manage().getCookieNamed(value));
        return cookieValue;
    }
}
