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

@Tag("web")
@Feature("Мероприятие")
public class EventTestsWeb extends TestBaseWeb {
    TestData testData = new TestData();
    @WithLogin
    @Test
    @DisplayName("Создание быстрой встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createFastMeetingTest() {
       // authorizationPage.openPage().setEmail().setPassword().clickSubmit();
        eventPage.openPage()
                .clickStartFastMeetingButton()
                .clickJoinMeetingButton()
                .checkVCSVisible();
    }
/*
    @Test
    @DisplayName("Создание запланированной встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createScheduleMeetingTest() {
        authorizationPage.openPage()
                .setEmail()
                .setPassword()
                .clickSubmit();
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .clickGoToEventButton()
                .clickStartMeetingButton()
                .clickJoinMeetingButton()
                .checkVCSVisible();
    }

    @Test
    @DisplayName("Создание постоянной встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createRegularMeetingTest() {
        authorizationPage.openPage()
                .setEmail()
                .setPassword()
                .clickSubmit();
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleEndlessMeetingButton()
                .clickGoToEventButton()
                .clickJoinMeetingButton()
                .checkVCSVisible();
    }

    @Test
    @DisplayName("Редактирование мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void editMeetingTest() {
        String newNameMeeting = "test edit name";
        authorizationPage.openPage()
                .setEmail()
                .setPassword()
                .clickSubmit();
        eventPage.openPage()
                .openNameEvent("Новое мероприятие сегодня")
                .editNameMeeting(newNameMeeting)
                .clickSaveEventButton();
        back();
        eventPage.checkEditNameMeeting(newNameMeeting);
    }

    @Test
    @DisplayName("Удаление мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void deleteMeetingTest() {
        authorizationPage.openPage()
                .setEmail()
                .setPassword()
                .clickSubmit();
        eventPage.openPage()
                .openNameEvent("Новое мероприятие сегодня")
                .clickEditorTopbarMenuButton()
                .clickDeleteButton()
                .clickDeleteMeetingButton()
                .checkDeletedNameMeetingIsNotVisisble("Новое мероприятие сегодня");
    }*/
}
