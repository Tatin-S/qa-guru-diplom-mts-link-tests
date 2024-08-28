package tests.web;

import api.extensions.WithLogin;
import common.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static common.helpers.ConverterUrl.getEventSessionIdFromUrl;

@Tag("web")
@Feature("Мероприятие")
public class EventTestsWeb extends TestBaseWeb {
    TestData testData = new TestData();

/*    @WithLogin
    @Test
    @DisplayName("Создание быстрой встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createFastMeetingTest() {
        eventPage.openPage()
                .clickStartFastMeetingButton()
                .clickJoinMeetingButton()
                .checkVCSVisible()
                .clickLeaveButton()
                .clickFinishMeetingButton();
    }*/

/*    @WithLogin
    @Test
    @DisplayName("Создание запланированной встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createScheduleMeetingTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .clickGoToEventButton()
                .clickStartMeetingButton()
                .clickJoinMeetingButton()
                .checkVCSVisible()
                .clickLeaveButton()
                .clickFinishMeetingButton();
    }*/

    @WithLogin
    @Test
    @DisplayName("Создание постоянной встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createRegularMeetingTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleEndlessMeetingButton()
                .clickGoToEventButton()
                .clickJoinMeetingButton()
                .checkVCSVisible();
        String eventSessionId = getEventSessionIdFromUrl(getWebDriver().getCurrentUrl());
        eventPage.clickLeaveButton();
      //  back();
        sleep(15000);
        eventPage.deleteEvent(eventSessionId);

    }

/*    @WithLogin
    @Test
    @DisplayName("Редактирование мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void editMeetingTest() {
        String newNameMeeting = "test edit name";
        eventPage.createEventApi(testData.eventName)
                .openPage()
                .openNameEvent(testData.eventName)
                .editNameMeeting(newNameMeeting)
                .clickSaveEventButton();
        back();
        eventPage.checkEditNameMeeting(newNameMeeting);
    }*/

/*    @WithLogin
    @Test
    @DisplayName("Удаление мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void deleteMeetingTest() {
        eventPage.createEventApi(testData.eventName)
                .openPage()
                .openNameEvent(testData.eventName)
                //.clickEditorTopbarMenuButton()
                .clickDeleteButton()
                .clickDeleteMeetingButton()
                .checkDeletedNameMeetingIsNotVisisble(testData.eventName);
    }*/
}
