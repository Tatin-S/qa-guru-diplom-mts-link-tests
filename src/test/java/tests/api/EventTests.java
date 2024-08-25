package tests.api;

import api.models.event.CreateEventTemplateRequestModel;
import api.models.event.CreateEventTemplateResponseModel;
import common.data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
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
                given(requestSpecEvent)
                        .contentType("application/json")
                        .body(createEventTemplateRequest)
                        .when()
                        .post("/events")
                        .then()
                        .spec(responseSpecStatusCode201))
                .extract().as(CreateEventTemplateResponseModel.class);

        step("Проверяем, что EventId содержит не 1 символ и цифро-буквенные значения", () ->{
            assertThat(response.getEventId()).isAlphanumeric();
            assertThat(response.getEventId()).hasSizeGreaterThan(1);
    });
        step("Проверяем, что Link содержит EventId", () -> {
            assertThat(response.getLink()).contains("https://my.mts-link.ru/j/106104753/"+response.getEventId());
        });
    }
}
