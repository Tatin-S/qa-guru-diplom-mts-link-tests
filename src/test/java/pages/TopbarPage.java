package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TopbarPage {
    private final SelenideElement
            pageTopbarUser = $x("//div[contains(@class, 'MuiAvatar-root UserAvatar_root')]"),
            pageTopbarUserProfile = $x("//li[contains(@class, 'MuiButtonBase-root MuiListItem-roo')][text()='Профиль']");

    @Step("Нажимаем на аватар пользователя")
    public TopbarPage clickPageTopbarUser(){
        pageTopbarUser.shouldBe(visible).click();
        return this;
    }

    @Step("Нажимаем на кнопку Профиль")
    public void clickPageTopbarUserProfile() {
        pageTopbarUserProfile.shouldBe(visible).click();
    }
}
