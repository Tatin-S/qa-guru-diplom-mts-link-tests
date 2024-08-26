package tests.api;

import api.models.account.*;
import api.models.event.*;
import com.github.javafaker.Faker;
import common.config.AuthDataConfig;
import common.data.TestData;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.File;

import static api.specs.Specs.*;
import static api.specs.Specs.responseSpecStatusCode200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TestSteps {
    TestData testData = new TestData();
    CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
    CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel(testData.eventName, accessSettings);
    static final AuthDataConfig AUTH_DATA_CONFIG = ConfigFactory.create(AuthDataConfig.class, System.getProperties());
    Faker faker = new Faker();
    String testEmail = faker.internet().emailAddress();
    String testPassword = faker.internet().password(6, 10, true, true, true);

    @Step("Создаем шаблон для мероприятия")
    public CreateEventTemplateResponseModel createEventTemplate (){
        return given(requestSpecEvent)
                        .contentType("application/json")
                        .body(createEventTemplateRequest)
                        .when()
                        .post("")
                        .then()
                        .spec(responseSpecStatusCode201)
                .extract().as(CreateEventTemplateResponseModel.class);
    }
    @Step("Проверяем, что eventId содержит не 1 символ и цифро-буквенные значения")
    public void checkEventId(CreateEventTemplateResponseModel response){
        assertThat(response.getEventId()).isAlphanumeric();
        assertThat(response.getEventId()).hasSizeGreaterThan(1);
    }

    @Step("Проверяем, что link содержит eventId")
    public void checkLinkContainsEventId(CreateEventTemplateResponseModel response){
        assertThat(response.getLink()).contains("https://my.mts-link.ru/j/106104753/" + response.getEventId());
    }
    @Step("Авторизуемся c валидными почтой и паролем")
    public void getSuccessAuthorization() {
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
    public ErrorResponseModel getBadAuthorization(){
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail(testEmail);
        loginData.setPassword(testPassword);
        loginData.setRememberMe(AUTH_DATA_CONFIG.rememberMe());
        return   given(requestSpecAuth)
                        .contentType("application/json")
                        .body(loginData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpecStatusCode404)
                        .extract().as(ErrorResponseModel.class);
    }

    @Step("Проверяем текст об ошибке \"Wrong credentials\"")
    public void checkWrongCredentials(ErrorResponseModel response){
        assertThat(response.getError().getMessage()).isEqualTo("Wrong credentials");
    }
    @Step("Создаем мероприятие по шаблону")
    public CreateEventResponseModel createEvent (String eventId) throws IOException {
  /*      ObjectMapper objectMapper = new ObjectMapper();
        CreateEventRequestModel[] request = objectMapper.readValue(
                new File("src/test/resources/data/CreateEventRequestBody.json"),
                CreateEventRequestModel[].class
        );*/
        return given(requestSpecEvent)
                .contentType("application/json")
                .body("{}")
                .when()
                .post("/" + eventId + "/sessions")
                .then()
                .spec(responseSpecStatusCode201)
                .extract().as(CreateEventResponseModel.class);
    }

    @Step("Проверяем, что eventSessionId содержит не 1 символ и цифро-буквенные значения")
    public void checkEventSessionId(CreateEventResponseModel response){
        assertThat(response.getEventSessionId()).isAlphanumeric();
        assertThat(response.getEventSessionId()).hasSizeGreaterThan(1);
    }

    @Step("Проверяем, что link содержит eventId и eventSessionId")
    public void checkLinkContainsEventSessionId(CreateEventResponseModel responseEvent, CreateEventTemplateResponseModel responseTemplate){
        assertThat(responseEvent.getLink()).contains("https://my.mts-link.ru/j/106104753/" + responseTemplate.getEventId()
                + "/session/" +  responseEvent.getEventSessionId());
    }
}
