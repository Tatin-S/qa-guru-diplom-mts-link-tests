package tests.api;

import api.authorization.AuthorizationApi;
import api.models.account.LoginRequestModel;
import api.models.event.CreateEventTemplateRequestModel;
import api.models.event.CreateEventTemplateResponseModel;
import common.data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.sessionId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;
public class EventTests extends TestBaseApi{
    TestData testData = new TestData();
   // LoginRequestModel loginRequest = new LoginRequestModel(testData.email, testData.password, true);
    String sessionId = "993cca74680d9256eb49196815a89634";

    @Test
    @DisplayName("Создание шаблона для мероприятия")
    @Severity(SeverityLevel.BLOCKER)
    void createEventTemplateTest() {
        CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
        CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel(testData.eventName, accessSettings);
        CreateEventTemplateResponseModel response = step("Создать шаблон для мероприятия", () ->
                given(requestSpecEvent)
                        .contentType("application/json")
                        .body(createEventTemplateRequest)
                        .when()
                        .post("/events")
                        .then()
                        .spec(responseSpecStatusCode201))
                .extract().as(CreateEventTemplateResponseModel.class);

        step("Проверяем, что EventId не пустой", () ->
                assertThat(response.getEventId(), notNullValue()));
        step("Проверяем, что Link содержит EventId", () -> {
            Assertions.assertThat(response.getLink()).contains("https://my.mts-link.ru/j/106104753/"+response.getEventId());
        });

    }
}
