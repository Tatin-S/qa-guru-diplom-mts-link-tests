package api.extensions;

import api.authorization.AuthorizationApi;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        AuthorizationApi authorizationApi = new AuthorizationApi();
        String sessionId = authorizationApi.getSessionId();

        open("/logo.73c0c1c921744210f1b3babdc1e1aa60.svg");
        getWebDriver().manage().addCookie(new Cookie("sessionId", sessionId));
    }
}
