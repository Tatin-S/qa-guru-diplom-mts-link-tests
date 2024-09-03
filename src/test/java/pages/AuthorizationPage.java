package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.config.AuthDataConfig;
import common.data.Language;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationPage {

    static final AuthDataConfig AUTH_DATA_CONFIG = ConfigFactory.create(AuthDataConfig.class, System.getProperties());
    private final SelenideElement
            emailInput = $(byName("email")),
            passwordInput = $(byName("password")),
            submitButton = $(byAttribute("data-testid", "SignIn.action.submit")),
            pageTopbarUser = $x("//div[contains(@class, 'MuiAvatar-root UserAvatar_root')]"),
            errorBadEmailMessage = $(byText("Wrong email address")),
            errorBadPasswordMessage = $(byText("The login and password contain errors")),
            languageMenuButton = $x("//div[contains(@class, 'AuthLayout_lang')]"),
            languageRuButton = $(byAttribute("data-testid", "AuthLayout.language.ru")),
            languageEnButton = $(byAttribute("data-testid", "AuthLayout.language.en"));
    private ElementsCollection fieldsAuthorizationText = $$x("//div[contains(@class, 'AuthContent_root')]");

    @Step("Открываем страницу")
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
        submitButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверяем, что пользователь авторизован")
    public void checkSuccessfulAuthorization() {
        pageTopbarUser.shouldBe(visible).shouldBe(visible);
    }

    @Step("Проверяем, что отображается ошибка Wrong email address")
    public void checkWrongEmail() {
        errorBadEmailMessage.shouldBe(visible);
    }

    @Step("Проверяем, что отображается ошибка The login and password contain errors")
    public void checkWrongPassword() {
        errorBadPasswordMessage.shouldBe(visible);
    }

    public void checkFieldsAuthorizationOnLanguage(Language language, List<String> listFieldsAuthorization) {
        languageMenuButton.click();
        if (language.language.equals("RU")) {
            languageRuButton.click();
            fieldsAuthorizationText.filter(visible).shouldHave(texts(listFieldsAuthorization));
        } else {
            languageEnButton.click();
            fieldsAuthorizationText.filter(visible).shouldHave(texts(listFieldsAuthorization));
        }
    }
}
