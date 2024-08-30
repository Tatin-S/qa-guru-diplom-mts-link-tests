package tests.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;

@Owner("Stulova Tatiana")
public class TestApiBase {
    TestSteps testSteps = new TestSteps();

    @BeforeEach
    void setUp() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}
