package tests.web;

import api.extensions.WithLogin;
import common.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AuthorizationPage;
import pages.EventPage;
import pages.ProfilePage;
import pages.TopbarPage;

@Feature("Авторизация пользователя")
@Tag("web")
public class AccountWebTests extends TestBaseWeb {
    TestData testData = new TestData();
    EventPage eventPage = new EventPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    TopbarPage topbarPage = new TopbarPage();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Успешная авторизация пользователя по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void authorizationTest() {
        authorizationPage.openPage()
                .setEmail()
                .setPassword()
                .clickSubmit()
                .checkSuccessfulAuthorization();
    }

    @Test
    @DisplayName("Неуспешная авторизация пользователя с некорректной почтой")
    @Severity(SeverityLevel.CRITICAL)
    void authorizationBadEmailTest() {
        authorizationPage.openPage()
                .setBadEmail()
                .setPassword()
                .clickSubmit()
                .checkWrongEmail();
    }

    @Test
    @DisplayName("Неуспешная авторизация пользователя с некорректным паролем")
    @Severity(SeverityLevel.CRITICAL)
    void authorizationBadPasswordTest() {
        authorizationPage.openPage()
                .setEmail()
                .setBadPassword()
                .clickSubmit()
                .checkWrongPassword();
    }

    @WithLogin
    @Test
    @DisplayName("Редактирование профиля пользователя")
    @Severity(SeverityLevel.CRITICAL)
    void editProfileUserTest() {
        eventPage.openPage();
        topbarPage.clickPageTopbarUser()
                .clickPageTopbarUserProfile();
        profilePage.setName(testData.name)
                .setSecondName(testData.secondName)
                .setNickname(testData.nickname)
                .setPhone(testData.phone)
                .setOrganization(testData.organization)
                .setPosition(testData.position)
                .setNewPhoto(testData.pictures)
                .setDescription(testData.description)
                .clickSave();

        topbarPage.clickPageTopbarUser()
                .clickPageTopbarUserProfile();

        profilePage.checkName(testData.name)
                .checkSecondName(testData.secondName)
                .checkNickname(testData.nickname)
                .checkPhone(testData.phone)
                .checkOrganization(testData.organization)
                .checkPosition(testData.position)
                .checkDescription(testData.description);
    }
}

