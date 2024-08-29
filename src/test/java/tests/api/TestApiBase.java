package tests.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import common.data.TestData;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;

public class TestApiBase {
    TestSteps testSteps = new TestSteps();

    @BeforeEach
    void setUp() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}
