package pages;

import com.codeborne.selenide.SelenideElement;
import common.config.AuthDataConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {

    static final AuthDataConfig AUTH_DATA_CONFIG = ConfigFactory.create(AuthDataConfig.class, System.getProperties());
    private final SelenideElement
            emailInput = $(byName("email")),
            passwordInput = $(byName("password")),
            submitButton = $(byAttribute("data-testid", "SignIn.action.submit")),
            profileImage = $(byAttribute("data-testid", "Meetings.PageTopbar.User")),
            errorBadEmailMessage = $(byText("The login and password contain errors")),
            errorBadPasswordMessage = $(byText("The login and password contain errors"));

    @Step("Открыаем страницу")
    public AuthorizationPage openPage() {
        open("/signin");
        return this;
    }

    @Step("Вводим email пользователя")
    public AuthorizationPage setEmail() {
        emailInput.sendKeys(AUTH_DATA_CONFIG.email());
        return this;
    }

    @Step("Вводим некорректный email пользователя")
    public AuthorizationPage setBadEmail() {
        emailInput.sendKeys("test");
        return this;
    }

    @Step("Вводим некорректный пароль пользователя")
    public AuthorizationPage setBadPassword() {
        passwordInput.sendKeys("test");
        return this;
    }

    @Step("Вводим пароль пользователя")
    public AuthorizationPage setPassword() {
        passwordInput.sendKeys(AUTH_DATA_CONFIG.password());
        return this;
    }

    @Step("Нажимаем кнопку Войти")
    public AuthorizationPage clickSubmit() {
        submitButton.click();
        return this;
    }

    @Step("Проверяем, что пользователь авторизован")
    public void checkSuccessfulAuthorization() {
        profileImage.shouldHave(visible);
    }

    @Step("Проверяем, что отображается ошибка Неверный адрес электронной почты")
    public void checkWrongEmail() {

        errorBadEmailMessage.shouldHave(visible);
    }

    @Step("Проверяем, что отображается ошибка Логин или пароль содержит ошибки")
    public void checkWrongPassword() {
        errorBadPasswordMessage.shouldHave(visible);
    }
}
