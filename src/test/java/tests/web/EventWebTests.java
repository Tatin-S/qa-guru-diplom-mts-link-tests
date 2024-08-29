package tests.web;

import api.extensions.WithLogin;
import common.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.EventPage;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static common.helpers.ConverterUrl.getEventSessionIdFromUrl;
import static common.helpers.ConverterUrl.getEventSessionIdFromUrlForEdit;

@Tag("web")
@Feature("Мероприятие")
public class EventWebTests extends TestBaseWeb {
    TestData testData = new TestData();
    EventPage eventPage = new EventPage();

    @WithLogin
    @Test
    @DisplayName("Создание быстрой встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createFastMeetingTest() {
        eventPage.openPage()
                .clickStartFastMeetingButton()
                .clickJoinMeetingButton()
                .checkVCSVisible();
        String eventSessionId = getEventSessionIdFromUrl(getWebDriver().getCurrentUrl());
        eventPage.clickLeaveButton()
                .clickFinishMeetingButton()
                .deleteEvent(eventSessionId);
    }

    @WithLogin
    @Test
    @DisplayName("Создание запланированной встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createScheduleMeetingTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .editNameMeeting(testData.eventName)
                .clickGoToEventButton()
                .clickStartMeetingButton()
                .clickJoinMeetingButton()
                .checkVCSVisible();
        String eventSessionId = getEventSessionIdFromUrl(getWebDriver().getCurrentUrl());
        eventPage.clickLeaveButton()
                .clickFinishMeetingButton()
                .deleteEvent(eventSessionId);
    }

    @WithLogin
    @Test
    @DisplayName("Создание постоянной встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createRegularMeetingTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleEndlessMeetingButton()
                .editNameMeeting(testData.eventName)
                .clickGoToEventButton()
                .clickJoinMeetingButton()
                .checkVCSVisible()
                .clickLeaveButton();
    }

    @WithLogin
    @Test
    @DisplayName("Редактирование мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void editMeetingTest() {
        String newNameMeeting = "test edit name";
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .editNameMeeting(newNameMeeting)
                .clickSaveEventButton();
        String eventSessionId = getEventSessionIdFromUrlForEdit(getWebDriver().getCurrentUrl());
        back();
        eventPage.checkEditNameMeeting(newNameMeeting)
                .deleteEvent(eventSessionId);

    }

    @WithLogin
    @Test
    @DisplayName("Удаление мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void deleteMeetingTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .editNameMeeting(testData.eventName)
                .clickSaveEventButton();
        back();
        eventPage.openNameEvent(testData.eventName)
                .clickEditorTopbarMenuButton()
                .clickDeleteButton()
                .clickDeleteMeetingButton();
        eventPage.checkDeletedNameMeetingIsNotVisisble(testData.eventName);
    }
}
