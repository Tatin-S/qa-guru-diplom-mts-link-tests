package tests.api;

import api.models.account.ErrorResponseModel;
import api.models.event.CreateEventResponseModel;
import api.models.event.CreateEventTemplateRequestModel;
import api.models.event.CreateEventTemplateResponseModel;
import api.models.event.GetEventResponseModel;
import common.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Tag("api")
@Feature("Создание мероприятия")
public class EventTests extends TestBaseApi {
    TestData testData = new TestData();
    CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
    CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel(testData.eventName, accessSettings);
    TestSteps testSteps = new TestSteps();

    @Test
    @DisplayName("Создание шаблона для мероприятия")
    @Severity(SeverityLevel.BLOCKER)
    void createEventTemplateTest() {
        CreateEventTemplateResponseModel response = testSteps.createEventTemplate(createEventTemplateRequest);
        testSteps.checkEventId(response);
        testSteps.checkLinkContainsEventId(response);
    }

    @Test
    @DisplayName("Создание мероприятия по шаблону")
    @Severity(SeverityLevel.BLOCKER)
    void createEventTest() throws IOException {
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        CreateEventResponseModel responseEvent = testSteps.createEvent(responseTemplate.getEventId());
        testSteps.checkEventSessionId(responseEvent);
        testSteps.checkLinkContainsEventSessionId(responseEvent, responseTemplate);
    }

    @Test
    @DisplayName("Создание мероприятия по шаблону без параметров")
    @Severity(SeverityLevel.CRITICAL)
    void createEventWithEmptyBodyTest(){
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        CreateEventResponseModel responseEvent = testSteps.createEventWithEmptyBody(responseTemplate.getEventId());
        testSteps.checkEventSessionId(responseEvent);
        testSteps.checkLinkContainsEventSessionId(responseEvent, responseTemplate);
    }

    @Test
    @DisplayName("Создание мероприятия по шаблону c невалидным запросом")
    @Severity(SeverityLevel.NORMAL)
    void createEventWithInvalidBodyTest(){
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        ErrorResponseModel responseErrorEvent = testSteps.createEventWithInvalidBody(responseTemplate.getEventId());
        testSteps.checkJsonIsNotValid(responseErrorEvent);
    }

    @Test
    @DisplayName("Проверка параметров мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void checkSettingsEventTest(){
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        CreateEventResponseModel responseEvent = testSteps.createEventWithEmptyBody(responseTemplate.getEventId());
        GetEventResponseModel getResponseEvent = testSteps.GetEvent(responseEvent.getEventSessionId());
        testSteps.checkEventSettings(getResponseEvent, responseEvent.getEventSessionId(), createEventTemplateRequest);
    }
}
