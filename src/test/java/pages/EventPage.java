package pages;

import api.models.event.CreateEventTemplateRequestModel;
import api.models.event.CreateEventTemplateResponseModel;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Tag;
import tests.api.TestSteps;

import java.io.IOException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@Feature("Мероприятие")
@Tag("web")
public class EventPage {
    private final SelenideElement
            startFastMeetingButton = $(byAttribute("data-testid", "Meetings.Toolbar.startMeeting")),
            scheduleButton = $(byAttribute("data-testid", "Meetings.Toolbar.schedule")),
            scheduleMeetingButton = $(byAttribute("data-testid", "Meetings.Toolbar.scheduleMeeting")),
            scheduleEndlessMeetingButton = $(byAttribute("data-testid", "Meetings.Toolbar.scheduleEndlessMeeting")),
            goToEventButton = $(byAttribute("data-testid", "MeetingsEditor.Topbar.goToEvent")),
            startMeetingButton = $(byText("Начать встречу")),
            nameMeetingInput = $(byAttribute("data-testid", "MeetingsEditor.Hero.Body.Title.textarea")),
           // nameMeetingInput = $(".name-field.textarea"),
            saveEventButton = $(byAttribute("data-testid", "MeetingsEditor.Topbar.saveEvent")),
            joinMeetingButton = $(byText("Присоединиться к встрече")),
            vcs = $(byAttribute("data-testid", "Stream.Vcs.MyConference")),
            meetingsEditorTopbarMenu = $(byAttribute("data-testid", "MeetingsEditor.Topbar.menu")),
            leaveButton = $(byAttribute("data-testid", "LeaveButtonDropdown.LeaveButton.Button")),
            finishMeetingButton = $(byAttribute("data-testid", "LeaveButton.FinishMeeting")),
          //  deleteButton = $(byText("Удалить")),
         //   deleteMeetingButton = $(byText("Удалить встречу")),
            deleteButton = $(byText("Удалить мероприятие")),
            deleteMeetingButton = $(".popover delete open");
           // endlessMeetingCardMenu = $(byAttribute("data-testid", "EndlessMeetingCard.menu")),
           // deleteEndlessMeetingButton = $(byAttribute("data-testid", "EndlessMeetingCard.menu.delete")),
          //  deleteEndlessMeetingButtonInWindow = $(byText("Удалить"));
    TestSteps testSteps = new TestSteps();

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
        $$(byText(eventName)).first().click();
        return this;
    }

    @Step("Проверяем, что изменилось название мероприятия в списке запланированных")
    public EventPage checkEditNameMeeting(String newNameMeeting) {
        $$(byText(newNameMeeting)).first().shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что удаленное мероприятие не отображается в списке запланированных")
    public EventPage checkDeletedNameMeetingIsNotVisisble(String newNameMeeting) {
        $(byText(newNameMeeting)).shouldBe(disappear);
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

/*    @Step("Редактируем название встречи")
    public EventPage editNameMeeting(String newNameMeeting) {
        nameMeetingInput.setValue(newNameMeeting);
        return this;
    }*/

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

    @Step("Нажимаем кнопку Удалить мероприятие")
    public EventPage clickDeleteButton() {
        deleteButton.click();
        return this;
    }

/*    @Step("Нажимаем кнопку меню мероприятия")
    public EventPage clickEndlessMeetingCardMenu() {
        endlessMeetingCardMenu.click();
        return this;
    }

    @Step("Нажимаем кнопку Удалить в меню")
    public EventPage clickEndlessMeetingButton() {
        deleteEndlessMeetingButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Удалить в модальном окне")
    public EventPage clickEndlessMeetingButtonInWindow() {
        deleteEndlessMeetingButtonInWindow.click();
        return this;
    }*/

    @Step("Нажимаем кнопку меню на странице редактирования мероприятия")
    public EventPage clickEditorTopbarMenuButton() {
        meetingsEditorTopbarMenu.click();
        return this;
    }

    @Step("Нажимаем кнопку Удалить мероприятие")
    public EventPage clickDeleteMeetingButton() {
        deleteMeetingButton.click();
        return this;
    }

    @Step("Удаляем встречу с помощью API")
    public void deleteEvent(String eventSessionId) {
        testSteps.deleteEvent(eventSessionId);
    }

    @Step("Создаем встречу с помощью API")
    public EventPage createEventApi(String nameEvent){
        CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
        CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel(nameEvent, accessSettings);
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        testSteps.createEventWithEmptyBody(responseTemplate.getEventId());
        return this;
    }

/*    @Step("Изменяем статус встречи с помощью API")
    public EventPage editEventStatusApi(String eventSessionId) {
        testSteps.editEvent(eventSessionId);
        return this;
    }*/

    @Step("Нажимаем кнопку Завершить встречу")
    public EventPage clickLeaveButton() {
        leaveButton.click();
        return this;
    }

    @Step("Нажимаем кнопку Завершить встречу для всех")
    public EventPage clickFinishMeetingButton() {
        finishMeetingButton.click();
        return this;
    }

}
