package pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.web.TestBaseWeb;

import static com.codeborne.selenide.Condition.value;
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

    @Step("Указать имя")
    public ProfilePage setName(String name) {
        nameInput.clear();
        nameInput.setValue(name);
        return this;
    }

    @Step("Указать фамилию")
    public ProfilePage setSecondName(String secondName) {
        secondNameInput.clear();
        secondNameInput.setValue(secondName);
        return this;
    }

    @Step("Указать никнейм")
    public ProfilePage setNickname(String nickname) {
        nicknameInput.clear();
        nicknameInput.setValue(nickname);
        return this;
    }

    @Step("Указать телефон")
    public ProfilePage setPhone(String phone) {
        phoneInput.clear();
        phoneInput.setValue(phone);
        return this;
    }

    @Step("Указать организацию")
    public ProfilePage setOrganization(String organization) {
        organizationInput.clear();
        organizationInput.setValue(organization);
        return this;
    }

    @Step("Указать должность")
    public ProfilePage setPosition(String position) {
        positionInput.clear();
        positionInput.setValue(position);
        return this;
    }

    @Step("Указать информацию о себе")
    public ProfilePage setDescription(String description) {
        descriptionInput.clear();
        descriptionInput.setValue(description);
        return this;
    }

    @Step("Нажать кнопку Сохранить")
    public void clickSave() {
        saveButton.click();
    }

    @Step("Проверить имя")
    public ProfilePage checkName(String name) {
        nameInput.shouldHave(value(name));
        return this;
    }

    @Step("Проверить фамилию")
    public ProfilePage checkSecondName(String secondName) {
        secondNameInput.shouldHave(value(secondName));
        return this;
    }

    @Step("Проверить никнейм")
    public ProfilePage checkNickname(String nickname) {
        nicknameInput.shouldHave(value(nickname));
        return this;
    }

    @Step("Проверить телефон")
    public ProfilePage checkPhone(String phone) {
        phoneInput.shouldHave(value(phone));
        return this;
    }

    @Step("Проверить организацию")
    public ProfilePage checkOrganization(String organization) {
        organizationInput.shouldHave(value(organization));
        return this;
    }

    @Step("Проверить должность")
    public ProfilePage checkPosition(String position) {
        positionInput.shouldHave(value(position));
        return this;
    }

    @Step("Проверить описание о себе")
    public ProfilePage checkDescription(String description) {
        descriptionInput.shouldHave(value(description));
        return this;
    }
}
