package tests.api;

import api.models.account.ErrorResponseModel;
import api.models.account.LoginRequestModel;
import api.models.event.CreateEventRequestModel;
import api.models.event.CreateEventResponseModel;
import api.models.event.CreateEventTemplateRequestModel;
import api.models.event.CreateEventTemplateResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.config.AuthDataConfig;
import common.data.TestData;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.IOException;

import static api.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TestSteps {
    TestData testData = new TestData();
    static final AuthDataConfig AUTH_DATA_CONFIG = ConfigFactory.create(AuthDataConfig.class, System.getProperties());

    @Step("Создаем шаблон для мероприятия")
    public CreateEventTemplateResponseModel createEventTemplate(CreateEventTemplateRequestModel createEventTemplateRequest) {
        return given(requestSpecEvent)
                .contentType("application/json")
                .body(createEventTemplateRequest)
                .when()
                .post("/events")
                .then()
                .spec(responseSpecStatusCode201)
                .extract().as(CreateEventTemplateResponseModel.class);
    }

    @Step("Проверяем, что eventId содержит не 1 символ и цифровые значения")
    public void checkEventId(CreateEventTemplateResponseModel response) {
        assertThat(response.getEventId()).isAlphanumeric();
        assertThat(response.getEventId()).hasSizeGreaterThan(1);
    }

    @Step("Проверяем, что link содержит eventId")
    public void checkLinkContainsEventId(CreateEventTemplateResponseModel response) {
        assertThat(response.getLink()).contains("https://my.mts-link.ru/j/106400841/" + response.getEventId());
    }

    @Step("Авторизуемся c валидными почтой и паролем")
    public void getSuccessfulAuthorization() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail(AUTH_DATA_CONFIG.email());
        loginData.setPassword(AUTH_DATA_CONFIG.password());
        loginData.setRememberMe(AUTH_DATA_CONFIG.rememberMe());
        step("Авторизуемся по почте и паролю", () ->
                given(requestSpecAuth)
                        .contentType("application/json")
                        .body(loginData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpecStatusCode200));
    }

    @Step("Авторизуемся c невалидными почтой и паролем")
    public ErrorResponseModel getBadAuthorization() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail(testData.testEmail);
        loginData.setPassword(testData.testPassword);
        loginData.setRememberMe(AUTH_DATA_CONFIG.rememberMe());
        return given(requestSpecAuth)
                .contentType("application/json")
                .body(loginData)
                .when()
                .post("/login")
                .then()
                .spec(responseSpecStatusCode404)
                .extract().as(ErrorResponseModel.class);
    }

    @Step("Проверяем текст об ошибке Wrong credentials")
    public void checkWrongCredentials(ErrorResponseModel response) {
        assertThat(response.getError().getMessage()).isEqualTo("Wrong credentials");
    }

    @Step("Создаем мероприятие по шаблону")
    public CreateEventResponseModel createEvent(String eventId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateEventRequestModel request = objectMapper.readValue(
                new File("src/test/resources/data/CreateEventRequestBody.json"),
                CreateEventRequestModel.class
        );
        return given(requestSpecEvent)
                .contentType("application/json")
                .body(request)
                .when()
                .post("/events/" + eventId + "/sessions")
                .then()
                .spec(responseSpecStatusCode201)
                .extract().as(CreateEventResponseModel.class);
    }

    @Step("Проверяем, что eventSessionId содержит не 1 символ и цифровые значения")
    public void checkEventSessionId(CreateEventResponseModel response) {
        assertThat(response.getEventSessionId()).isAlphanumeric();
        assertThat(response.getEventSessionId()).hasSizeGreaterThan(1);
    }

    @Step("Проверяем, что link содержит eventId и eventSessionId")
    public void checkLinkContainsEventSessionId(CreateEventResponseModel responseEvent, CreateEventTemplateResponseModel responseTemplate) {
        assertThat(responseEvent.getLink()).contains("https://my.mts-link.ru/j/106400841/" + responseTemplate.getEventId()
                + "/session/" + responseEvent.getEventSessionId());
    }

    @Step("Создаем мероприятие по шаблону без параметров")
    public CreateEventResponseModel createEventWithEmptyBody(String eventId) {
        return given(requestSpecEvent)
                .contentType("application/json")
                .body("{}")
                .when()
                .post("/events/" + eventId + "/sessions")
                .then()
                .spec(responseSpecStatusCode201)
                .extract().as(CreateEventResponseModel.class);
    }

    @Step("Создаем мероприятие по шаблону с невалидным телом")
    public ErrorResponseModel createEventWithInvalidBody(String eventId) {
        return given(requestSpecEvent)
                .contentType("application/json")
                .body("")
                .when()
                .post("/events/" + eventId + "/sessions")
                .then()
                .spec(responseSpecStatusCode400)
                .extract().as(ErrorResponseModel.class);
    }

    @Step("Проверяем текст об ошибке \"json is not valid\"")
    public void checkJsonIsNotValid(ErrorResponseModel response) {
        assertThat(response.getError().getMessage()).isEqualTo("json is not valid");
    }

    @Step("Получаем данные мероприятия")
    public void getEvent(String eventSessionId) {
        given(requestSpecEvent)
                .contentType("application/json")
                .body("{}")
                .when()
                .get("/eventsessions/" + eventSessionId)
                .then()
                .spec(responseSpecStatusCode200);
    }

    @Step("Удаляем мероприятие")
    public void deleteEvent(String eventSessionId) {
        given(requestSpecEvent)
                .contentType("application/json")
                .body("{}")
                .when()
                .delete("/eventsessions/" + eventSessionId)
                .then()
                .spec(responseSpecStatusCode204);
    }
}
