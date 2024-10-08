package pages;

import com.codeborne.selenide.SelenideElement;
import common.helpers.Tools;
import io.qameta.allure.Step;
import tests.web.TestBaseWeb;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends TestBaseWeb {

    private final SelenideElement
            nameInput = $(byName("name")),
            secondNameInput = $(byName("secondName")),
            nicknameInput = $(byName("nickname")),
            phoneInput = $(byName("phone")),
            organizationInput = $(byName("organization")),
            positionInput = $(byName("position")),
            descriptionInput = $(byName("description")),
            saveButton = $(byText("Сохранить"));
    Tools tools = new Tools();

    @Step("Указываем имя")
    public ProfilePage setName(String name) {
        tools.cleanField(nameInput);
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Указываем фамилию")
    public ProfilePage setSecondName(String secondName) {
        tools.cleanField(secondNameInput);
        secondNameInput.sendKeys(secondName);
        return this;
    }

    @Step("Указываем никнейм")
    public ProfilePage setNickname(String nickname) {
        tools.cleanField(nicknameInput);
        nicknameInput.sendKeys(nickname);
        return this;
    }

    @Step("Указываем телефон")
    public ProfilePage setPhone(String phone) {
        tools.cleanField(phoneInput);
        phoneInput.sendKeys(phone);
        return this;
    }

    @Step("Указываем организацию")
    public ProfilePage setOrganization(String organization) {
        tools.cleanField(organizationInput);
        organizationInput.sendKeys(organization);
        return this;
    }

    @Step("Указываем должность")
    public ProfilePage setPosition(String position) {
        tools.cleanField(positionInput);
        positionInput.sendKeys(position);
        return this;
    }

    @Step("Указываем информацию о себе")
    public ProfilePage setDescription(String description) {
        tools.cleanField(descriptionInput);
        descriptionInput.sendKeys(description);
        return this;
    }

    @Step("Нажимаем кнопку Сохранить")
    public void clickSave() {
        saveButton.shouldBe(visible).click();
    }

    @Step("Проверяем имя")
    public ProfilePage checkName(String name) {
        nameInput.shouldBe(visible).shouldHave(value(name));
        return this;
    }

    @Step("Проверяем фамилию")
    public ProfilePage checkSecondName(String secondName) {
        secondNameInput.shouldBe(visible).shouldHave(value(secondName));
        return this;
    }

    @Step("Проверяем никнейм")
    public ProfilePage checkNickname(String nickname) {
        nicknameInput.shouldBe(visible).shouldHave(value(nickname));
        return this;
    }

    @Step("Проверяем телефон")
    public ProfilePage checkPhone(String phone) {
        phoneInput.shouldBe(visible).shouldHave(value(phone));
        return this;
    }

    @Step("Проверяем организацию")
    public ProfilePage checkOrganization(String organization) {
        organizationInput.shouldBe(visible).shouldHave(value(organization));
        return this;
    }

    @Step("Проверяем должность")
    public ProfilePage checkPosition(String position) {
        positionInput.shouldBe(visible).shouldHave(value(position));
        return this;
    }

    @Step("Проверяем описание о себе")
    public void checkDescription(String description) {
        descriptionInput.shouldBe(visible).shouldHave(value(description));
    }
}
