package tests.api;

import api.models.event.CreateEventTemplateResponseModel;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTests extends TestBaseApi{
    TestSteps testSteps = new TestSteps();

    @Test
    @DisplayName("Создание шаблона для мероприятия")
    @Severity(SeverityLevel.BLOCKER)
    void createEventTemplateTest() {
        CreateEventTemplateResponseModel response = testSteps.createEventTemplate();
testSteps.checkEventId(response);
testSteps.checkLinkContainsEventId(response);
    }
}
