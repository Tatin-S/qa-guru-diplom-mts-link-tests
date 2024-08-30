package tests.api;

import api.models.account.ErrorResponseModel;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("Stulova Tatiana")
@Feature("Авторизация пользователя")
@Tag("api")
public class AccountApiTests extends TestApiBase {
    @Test
    @DisplayName("Успешная авторизация по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void authorizationTest() {
        testSteps.getSuccessfulAuthorization();
    }

    @Test
    @DisplayName("Авторизация по некорректным почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void checkWrongLoginTest() {
        ErrorResponseModel response = testSteps.getBadAuthorization();
        testSteps.checkWrongCredentials(response);
    }
}
