package api.extensions;

import api.authorization.AuthorizationApi;
import api.models.account.LoginRequestModel;
import common.config.AuthDataConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    public String sessionIdU;
    static final AuthDataConfig AUTH_DATA_CONFIG = ConfigFactory.create(AuthDataConfig.class, System.getProperties());
    @Override
    public void beforeEach(ExtensionContext context) {

        AuthorizationApi authorizationApi = new AuthorizationApi();
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail(AUTH_DATA_CONFIG.email());
        loginData.setPassword(AUTH_DATA_CONFIG.password());
        loginData.setRememberMe(AUTH_DATA_CONFIG.rememberMe());
        sessionIdU = authorizationApi.getSessionId(loginData);

        open("logo.11686b809df6f35e676a1aff1162064807ef6bbd1fda8d66b90a7e0f724d6874.svg");
        getWebDriver().manage().addCookie(new Cookie("sessionId", sessionIdU));
    }
}