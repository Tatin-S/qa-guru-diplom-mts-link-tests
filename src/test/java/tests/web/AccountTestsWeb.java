/*
package tests.web;

import org.junit.jupiter.api.*;
import io.qameta.allure.*;

@Feature("Авторизация пользователя")
@Tag("web")
public class AccountTestsWeb extends TestBaseWeb{
    @Test
    @DisplayName("Успешная авторизация пользователя по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void authorizationTest() {
        authorizationPage.openPage()
                .setEmail()
                .setPassword()
                .clickSubmit()
                .checkSuccessfulAuthorization();
    }

    @Test
    @DisplayName("Неуспешная авторизация пользователя с некорректной почтой")
    @Severity(SeverityLevel.CRITICAL)
    void authorizationBadEmailTest() {
        authorizationPage.openPage()
                .setBadEmail()
                .setPassword()
                .clickSubmit()
                .checkWrongEmail();
    }

    @Test
    @DisplayName("Неуспешная авторизация пользователя с некорректным паролем")
    @Severity(SeverityLevel.CRITICAL)
    void authorizationBadPasswordTest() {
        authorizationPage.openPage()
                .setEmail()
                .setBadPassword()
                .clickSubmit()
                .checkWrongPassword();
    }
}
*/
