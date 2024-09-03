package tests.web;

import api.extensions.WithLogin;
import common.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.EventPage;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static common.helpers.Tools.getEventSessionIdFromUrl;
import static common.helpers.Tools.getEventSessionIdFromUrlForEdit;

@Owner("Stulova Tatiana")
@Tag("webEvents")
@Feature("Мероприятие")
public class EventWebTests extends TestBaseWeb {
    TestData testData = new TestData();
    EventPage eventPage = new EventPage();

    @WithLogin
    @Test
    @DisplayName("Проверка успешного создания быстрой встречи")
    @Severity(SeverityLevel.BLOCKER)
    void checkSuccessfullyCreatedFastMeetingTest() {
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
    @DisplayName("Проверка успешного создания запланированной встречи")
    @Severity(SeverityLevel.BLOCKER)
    void checkSuccessfullyCreatedScheduleMeetingTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .editNameMeeting(testData.eventName)
                .clickGoToEventButton()
                .clickStartMeetingButton()
                .clickJoinMeetingButton()
                .checkVCSVisible();
        eventPage.clickLeaveButton()
                .clickFinishMeetingButton();
    }

    @WithLogin
    @Test
    @DisplayName("Проверка успешного создания постоянной встречи")
    @Severity(SeverityLevel.BLOCKER)
    void checkSuccessfullyCreateRegularMeetingTest() {
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
    @DisplayName("Проверка успешного редактирования мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void checkSuccessfullyEditedMeetingTest() {
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
    @DisplayName("Проверка успешного удаления мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void checkSuccessfullyDeletedMeetingTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .editNameMeeting(testData.eventName)
                .clickSaveEventButton();
        back();
        eventPage.openNameEvent(testData.eventName)
                .clickEditorTopbarMenuButton()
                .clickDeleteButton()
                .clickDeleteMeetingButton()
                .checkDeletedNameMeetingIsNotVisisble(testData.eventName);
    }
}
