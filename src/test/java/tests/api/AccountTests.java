package tests.api;
import api.models.account.ErrorResponseModel;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Авторизация пользователя")
@Tag("api")
public class AccountTests extends TestBaseApi {
/*    TestSteps testSteps = new TestSteps();
    @Test
    @DisplayName("Успешная авторизация по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void authorizationTest() {
        testSteps.getSuccessAuthorization();
    }

    @Test
    @DisplayName("Авторизация по некорректным почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void checkWrongLoginTest() {
        ErrorResponseModel response = testSteps.getBadAuthorization();
        testSteps.checkWrongCredentials(response);
    }*/
}
