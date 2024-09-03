package tests.web;

import api.extensions.WithLogin;
import common.data.Language;
import common.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.AuthorizationPage;
import pages.EventPage;
import pages.ProfilePage;
import pages.TopbarPage;

import java.util.List;
import java.util.stream.Stream;

@Owner("Stulova Tatiana")
@Feature("Авторизация пользователя")
@Tag("webAccount")
public class AccountWebTests extends TestBaseWeb {
    static TestData testData = new TestData();
    EventPage eventPage = new EventPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    TopbarPage topbarPage = new TopbarPage();
    ProfilePage profilePage = new ProfilePage();

    static Stream<Arguments> fieldsAuthorization() {
        return Stream.of(
                Arguments.of(
                        Language.RU,
                        testData.listFieldsAuthorizationRu
                ),
                Arguments.of(
                        Language.EN,
                        testData.listFieldsAuthorizationEn
                )
        );
    }

    @Test
    @DisplayName("Проверка успешной авторизации пользователя по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void checkSuccessfullyAuthorizationTest() {
        authorizationPage.openPage()
                .setEmail()
                .setPassword()
                .clickSubmit()
                .checkSuccessfulAuthorization();
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации пользователя с некорректной почтой")
    @Severity(SeverityLevel.CRITICAL)
    void checkAuthorizationBadEmailTest() {
        authorizationPage.openPage()
                .setBadEmail()
                .setPassword()
                .clickSubmit()
                .checkWrongEmail();
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации пользователя с некорректным паролем")
    @Severity(SeverityLevel.CRITICAL)
    void checkAuthorizationBadPasswordTest() {
        authorizationPage.openPage()
                .setEmail()
                .setBadPassword()
                .clickSubmit()
                .checkWrongPassword();
    }

    @WithLogin
    @Test
    @DisplayName("Проверка успешного редактирования профиля пользователя")
    @Severity(SeverityLevel.CRITICAL)
    void checkEditProfileUserTest() {
        eventPage.openPage();
        topbarPage.clickPageTopbarUser()
                .clickPageTopbarUserProfile();
        profilePage.setName(testData.name)
                .setSecondName(testData.secondName)
                .setNickname(testData.nickname)
                .setPhone(testData.phone)
                .setOrganization(testData.organization)
                .setPosition(testData.position)
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

    @MethodSource("fieldsAuthorization")
    @ParameterizedTest(name = "Для языка {0} должны отображаться кнопки на соответствующем языке")
    @DisplayName("Проверка отображения полей авторизации на разных языках")
    @Severity(SeverityLevel.CRITICAL)
    void checkFieldsAuthorizationOnLanguageTests(Language language, List<String> listFieldsAuthorization) {
        authorizationPage.openPage()
                .checkFieldsAuthorizationOnLanguage(language, listFieldsAuthorization);
    }
}

