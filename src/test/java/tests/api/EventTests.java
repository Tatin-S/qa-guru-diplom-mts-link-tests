package tests.api;

import api.models.account.ErrorResponseModel;
import api.models.event.CreateEventResponseModel;
import api.models.event.CreateEventTemplateResponseModel;
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
    TestSteps testSteps = new TestSteps();

/*    @Test
    @DisplayName("Создание шаблона для мероприятия")
    @Severity(SeverityLevel.BLOCKER)
    void createEventTemplateTest() {
        CreateEventTemplateResponseModel response = testSteps.createEventTemplate();
        testSteps.checkEventId(response);
        testSteps.checkLinkContainsEventId(response);
    }*/

/*    @Test
    @DisplayName("Создание мероприятия по шаблону")
    @Severity(SeverityLevel.BLOCKER)
    void createEventTest() throws IOException {
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate();
        String eventId = responseTemplate.getEventId();
        CreateEventResponseModel responseEvent = testSteps.createEvent(eventId);
       // testSteps.checkEventSessionId(responseEvent);
      //  testSteps.checkLinkContainsEventSessionId(responseEvent, responseTemplate);
    }*/

    @Test
    @DisplayName("Создание мероприятия по шаблону без параметров")
    @Severity(SeverityLevel.CRITICAL)
    void createEventWithEmptyBodyTest(){
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate();
        CreateEventResponseModel responseEvent = testSteps.createEventWithEmptyBody(responseTemplate.getEventId());
        testSteps.checkEventSessionId(responseEvent);
        testSteps.checkLinkContainsEventSessionId(responseEvent, responseTemplate);
    }

    @Test
    @DisplayName("Создание мероприятия по шаблону c невалидным запросом")
    @Severity(SeverityLevel.NORMAL)
    void createEventWithInvalidBodyTest(){
        CreateEventTemplateResponseModel responseTemplate = testSteps.createEventTemplate();
        ErrorResponseModel responseErrorEvent = testSteps.createEventWithInvalidBody(responseTemplate.getEventId());
        testSteps.checkJsonIsNotValid(responseErrorEvent);
    }
}
