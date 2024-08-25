package tests.api;

import api.models.event.CreateEventTemplateRequestModel;
import api.models.event.CreateEventTemplateResponseModel;
import common.data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.specs.Specs.requestSpec;
import static api.specs.Specs.responseSpecStatusCode201;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.sessionId;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;
public class EventTests extends TestBaseApi{
    TestData testData = new TestData();

    @Test
    @DisplayName("Создание шаблона для мероприятия")
    @Severity(SeverityLevel.BLOCKER)
    void createEventTemplateTest() {
        CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
        CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel(testData.eventName, accessSettings);

        CreateEventTemplateResponseModel response = step("Создать шаблон для мероприятия", () ->
                given(requestSpec)
                        .cookie("sessionId", sessionId)
                        .contentType("application/json")
                        .body(createEventTemplateRequest)
                        .when()
                        .post("/event")
                        .then()
                        .spec(responseSpecStatusCode201))
                .extract().as(CreateEventTemplateResponseModel.class);

        step("Проверить данные в ответе", () -> {
            assertEquals(testData.eventName, response.getName());
            assertEquals("meeting", response.getType());
        });
    }
}
