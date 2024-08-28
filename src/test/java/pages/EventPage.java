package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Tag;
import tests.api.TestSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Feature("Мероприятие")
@Tag("web")
public class EventPage {
    TestSteps testSteps = new TestSteps();
    private final SelenideElement
            startFastMeetingButton = $(byAttribute("data-testid", "Meetings.Toolbar.startMeeting")),
            scheduleButton = $(byAttribute("data-testid", "Meetings.Toolbar.schedule")),
            scheduleMeetingButton = $(byAttribute("data-testid", "Meetings.Toolbar.scheduleMeeting")),
            scheduleEndlessMeetingButton = $(byAttribute("data-testid", "Meetings.Toolbar.scheduleEndlessMeeting")),
            goToEventButton = $(byAttribute("data-testid", "MeetingsEditor.Topbar.goToEvent")),
            startMeetingButton = $(byText("Начать встречу")),
            nameMeetingInput = $(byAttribute("data-testid", "MeetingsEditor.Hero.Body.Title.textarea")),
            dateMeetingInput = $(byAttribute("data-testid", "Meetings.Editor.SettingsSidebar.Date.date")),
            timeMeetingInput = $(byAttribute("data-testid", "Meetings.Editor.SettingsSidebar.Date.time")),
            saveEventButton = $(byAttribute("data-testid", "MeetingsEditor.Topbar.saveEvent")),
            joinMeetingButton = $(byText("Присоединиться к встрече")),
            vcs = $(byAttribute("data-testid", "Stream.Vcs.MyConference")),
            meetingsEditorTopbarMenu = $(byAttribute("data-testid", "MeetingsEditor.Topbar.menu")),
            deleteButton = $(byText("Удалить")),
            deleteMeetingButton = $(byText("Удалить встречу"));

    @Step("Открываем страницу")
    public EventPage openPage() {
        open("/meetings");
        return this;
    }

    @Step("Нажимаем кнопку Быстрая встреча")
    public EventPage clickStartFastMeetingButton() {
        startFastMeetingButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Присоединиться к встрече")
    public EventPage clickJoinMeetingButton() {
        joinMeetingButton.click();
        return this;
    }

    @Step("Проверяем отображение ВКС пользователя")
    public EventPage checkVCSVisible() {
        vcs.shouldHave(visible);
        return this;
    }

    @Step("Открываем мероприятие по названию в списке запланированных")
    public EventPage openNameEvent(String eventName) {
        $(byText(eventName)).click();
        return this;
    }

    @Step("Проверяем, что изменилось название мероприятия в списке запланированных")
    public EventPage checkEditNameMeeting(String newNameMeeting) {
        $(".MuiList-root", 0).$$("li").findBy(Condition.text(newNameMeeting)).shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что удаленное мероприятие не отображается в списке запланированных")
    public EventPage checkDeletedNameMeetingIsNotVisisble(String newNameMeeting) {
        $(".MuiList-root", 0).$$("li").findBy(Condition.text(newNameMeeting)).shouldNotBe(visible);
        return this;
    }

    @Step("Нажимаем кнопку Запланировать")
    public EventPage clickScheduleButton() {
        scheduleButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Встречу")
    public EventPage clickScheduleMeetingButton() {
        scheduleMeetingButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Перейти к встрече")
    public EventPage clickGoToEventButton() {
        goToEventButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Начать встречу")
    public EventPage clickStartMeetingButton() {
        startMeetingButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Постоянную встречу")
    public EventPage clickScheduleEndlessMeetingButton() {
        scheduleEndlessMeetingButton.click();
        return this;
    }

    @Step("Редактируем название встречи")
    public EventPage editNameMeeting(String newNameMeeting) {
        nameMeetingInput.setValue(newNameMeeting);
        return this;
    }

    @Step("Нажимаем кнопку Сохранить")
    public EventPage clickSaveEventButton() {
        saveEventButton.click();
        $(byId("notistack-snackbar")).shouldHave(visible).shouldHave(text("Изменения сохранены"));
        return this;
    }

    @Step("Нажимаем кнопку Удалить")
    public EventPage clickDeleteButton() {
        deleteButton.click();
        return this;
    }

    @Step("Нажимаем кнопку меню на странице редактирования мероприятия")
    public EventPage clickEditorTopbarMenuButton() {
        meetingsEditorTopbarMenu.click();
        return this;
    }

    @Step("Нажимаем кнопку Удалить встречу")
    public EventPage clickDeleteMeetingButton() {
        deleteMeetingButton.click();
        return this;
    }

    @Step("Удаляем встречу с помощью API")
    public void deleteEvent(String EventSessionId) {
        testSteps.DeleteEvent(EventSessionId);
    }
}
