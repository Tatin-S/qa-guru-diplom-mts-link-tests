package tests.api;

import api.models.account.ErrorResponseModel;
import api.models.event.CreateEventResponseModel;
import api.models.event.CreateEventTemplateRequestModel;
import api.models.event.CreateEventTemplateResponseModel;
import common.data.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Owner("Stulova Tatiana")
@Tag("apiEvents")
@Feature("Мероприятие")
public class EventApiTests extends TestApiBase {
    TestData testData = new TestData();
    CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
    CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel(testData.eventName, accessSettings);

    @Test
    @DisplayName("Проверка успешного создания шаблона для мероприятия")
    @Severity(SeverityLevel.BLOCKER)
    void checkSuccessfullyCreatedEventTemplateTest() {
        CreateEventTemplateResponseModel response = testSteps.createEventTemplate(createEventTemplateRequest);
        testSteps.checkEventId(response);
        testSteps.checkLinkContainsEventId(response);
    }

    @Test
    @DisplayName("Проверка успешного создания мероприятия по шаблону")
    @Severity(SeverityLevel.BLOCKER)
    void checkSuccessfullyCreatedEventTest() throws IOException {
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        CreateEventResponseModel responseEvent = testSteps.createEvent(responseTemplate.getEventId());
        testSteps.checkEventSessionId(responseEvent);
        testSteps.checkLinkContainsEventSessionId(responseEvent, responseTemplate);
        testSteps.deleteEvent(responseEvent.getEventSessionId());
    }

    @Test
    @DisplayName("Проверка успешного создания мероприятия по шаблону без параметров")
    @Severity(SeverityLevel.CRITICAL)
    void checkSuccessfullyCreatedEventWithEmptyBodyTest() {
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        CreateEventResponseModel responseEvent = testSteps.createEventWithEmptyBody(responseTemplate.getEventId());
        testSteps.checkEventSessionId(responseEvent);
        testSteps.checkLinkContainsEventSessionId(responseEvent, responseTemplate);
        testSteps.deleteEvent(responseEvent.getEventSessionId());
    }

    @Test
    @DisplayName("Проверка неуспешного создания мероприятия по шаблону c невалидным запросом")
    @Severity(SeverityLevel.NORMAL)
    void checkCreatedEventWithInvalidBodyTest() {
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        ErrorResponseModel responseErrorEvent = testSteps.createEventWithInvalidBody(responseTemplate.getEventId());
        testSteps.checkJsonIsNotValid(responseErrorEvent);
    }

    @Test
    @DisplayName("Проверка успешного получения данных мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void checkSuccessfullyGetEventTest() {
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        CreateEventResponseModel responseEvent = testSteps.createEventWithEmptyBody(responseTemplate.getEventId());
        testSteps.getEvent(responseEvent.getEventSessionId());
    }

    @Test
    @DisplayName("Проверка успешного удаления мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void checkSuccessfullyDeletedEventTest() {
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate(createEventTemplateRequest);
        CreateEventResponseModel responseEvent = testSteps.createEventWithEmptyBody(responseTemplate.getEventId());
        testSteps.deleteEvent(responseEvent.getEventSessionId());
    }
}
