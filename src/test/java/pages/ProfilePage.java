package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import tests.web.TestBaseWeb;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.*;
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
            saveButton = $(byText("Сохранить")),
            newPhotoButton = $(byAttribute("data-testid", "ProfileForm.field.photo.newPhotoButton"));

    @Step("Указываем имя")
    public ProfilePage setName(String name) {
        nameInput.sendKeys(Keys.BACK_SPACE);
        nameInput.setValue(name);
        return this;
    }

    @Step("Указываем фамилию")
    public ProfilePage setSecondName(String secondName) {
        secondNameInput.sendKeys(Keys.BACK_SPACE);
        secondNameInput.setValue(secondName);
        return this;
    }

    @Step("Указываем никнейм")
    public ProfilePage setNickname(String nickname) {
        nicknameInput.sendKeys(Keys.BACK_SPACE);
        nicknameInput.setValue(nickname);
        return this;
    }

    @Step("Указываем телефон")
    public ProfilePage setPhone(String phone) {
        phoneInput.sendKeys(Keys.BACK_SPACE);
        phoneInput.setValue(phone);
        return this;
    }

    @Step("Указываем организацию")
    public ProfilePage setOrganization(String organization) {
        organizationInput.sendKeys(Keys.BACK_SPACE);
        organizationInput.setValue(organization);
        return this;
    }

    @Step("Указываем должность")
    public ProfilePage setPosition(String position) {
        positionInput.sendKeys(Keys.BACK_SPACE);
        positionInput.setValue(position);
        return this;
    }

    @Step("Указываем информацию о себе")
    public ProfilePage setDescription(String description) {
        descriptionInput.sendKeys(Keys.BACK_SPACE);
        descriptionInput.setValue(description);
        return this;
    }

    @Step("Нажимаем кнопку Сохранить")
    public void clickSave() {
        saveButton.click();
    }

    @Step("Проверяем имя")
    public ProfilePage checkName(String name) {
        nameInput.shouldHave(value(name));
        return this;
    }

    @Step("Проверяем фамилию")
    public ProfilePage checkSecondName(String secondName) {
        secondNameInput.shouldHave(value(secondName));
        return this;
    }

    @Step("Проверяем никнейм")
    public ProfilePage checkNickname(String nickname) {
        nicknameInput.shouldHave(value(nickname));
        return this;
    }

    @Step("Проверяем телефон")
    public ProfilePage checkPhone(String phone) {
        phoneInput.shouldHave(value(phone));
        return this;
    }

    @Step("Проверяем организацию")
    public ProfilePage checkOrganization(String organization) {
        organizationInput.shouldHave(value(organization));
        return this;
    }

    @Step("Проверяем должность")
    public ProfilePage checkPosition(String position) {
        positionInput.shouldHave(value(position));
        return this;
    }

    @Step("Проверяем описание о себе")
    public ProfilePage checkDescription(String description) {
        descriptionInput.shouldHave(value(description));
        return this;
    }

    @Step("Загружаем фотографию пользователя")
    public ProfilePage setNewPhoto(String newPhoto) {
        newPhotoButton.uploadFromClasspath(newPhoto);
        return this;
    }
}
