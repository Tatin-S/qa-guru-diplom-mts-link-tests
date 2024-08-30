package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class TopbarPage {
    private final SelenideElement
            pageTopbarUser = $(byAttribute("data-testid", "Meetings.PageTopbar.User")),
            pageTopbarUserProfile = $(byAttribute("data-testid", "Meetings.PageTopbar.User.profile"));

    @Step("Нажимаем на аватар пользователя")
    public TopbarPage clickPageTopbarUser() throws InterruptedException {
        pageTopbarUser.wait();
        pageTopbarUser.click();
        return this;
    }

    @Step("Нажимаем на кнопку Профиль")
    public void clickPageTopbarUserProfile() {
        pageTopbarUserProfile.click();
    }
}
