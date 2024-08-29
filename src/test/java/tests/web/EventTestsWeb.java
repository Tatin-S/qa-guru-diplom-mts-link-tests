package tests.web;

import api.extensions.WithLogin;
import com.codeborne.selenide.SelenideElement;
import common.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AuthorizationPage;
import tests.api.TestSteps;

import java.io.IOException;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static common.helpers.ConverterUrl.getEventSessionIdFromUrl;

@Tag("web")
@Feature("Мероприятие")
public class EventTestsWeb extends TestBaseWeb {
    TestData testData = new TestData();

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
                .editNameMeeting("Создание запланированной встречи")
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
                .editNameMeeting("Создание постоянной встречи")
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
        String eventSessionId = getEventSessionIdFromUrl(getWebDriver().getCurrentUrl());
        back();
        eventPage.checkEditNameMeeting(newNameMeeting)
                .deleteEvent(eventSessionId);

    }

    @WithLogin
    @Test
    @DisplayName("Удаление мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void deleteMeetingTest(){
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .editNameMeeting("Удаление мероприятия")
                .clickSaveEventButton();
        back();
        eventPage.openNameEvent("Удаление мероприятия")
              //  .clickEditorTopbarMenuButton()
                .clickDeleteButton()
                .clickDeleteMeetingButton();
               /* .clickEndlessMeetingCardMenu()
                .clickEndlessMeetingButton()
                .clickEndlessMeetingButtonInWindow()*/
               // sleep(5000);
        eventPage.checkDeletedNameMeetingIsNotVisisble("Удаление мероприятия");
    }
}
