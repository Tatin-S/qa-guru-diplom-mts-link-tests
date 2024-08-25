package tests.api;

import api.models.event.CreateEventTemplateRequestModel;
import api.models.event.CreateEventTemplateResponseModel;
import common.data.TestData;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;

import static api.specs.Specs.requestSpecEvent;
import static api.specs.Specs.responseSpecStatusCode201;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TestSteps {
    TestData testData = new TestData();
    CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
    CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel(testData.eventName, accessSettings);

    @Step("Создаем шаблон для мероприятия")
    public CreateEventTemplateResponseModel createEventTemplate (){
        return given(requestSpecEvent)
                        .contentType("application/json")
                        .body(createEventTemplateRequest)
                        .when()
                        .post("/events")
                        .then()
                        .spec(responseSpecStatusCode201)
                .extract().as(CreateEventTemplateResponseModel.class);
    }
    @Step("Проверяем, что EventId содержит не 1 символ и цифро-буквенные значения")
    public void checkEventId(CreateEventTemplateResponseModel response){
        assertThat(response.getEventId()).isAlphanumeric();
        assertThat(response.getEventId()).hasSizeGreaterThan(1);
    }

    @Step("Проверяем, что Link содержит EventId")
    public void checkLinkContainsEventId(CreateEventTemplateResponseModel response){
        assertThat(response.getLink()).contains("https://my.mts-link.ru/j/106104753/" + response.getEventId());
    }
}
