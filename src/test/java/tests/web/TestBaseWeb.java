package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.Selenide;
import common.config.AuthDataConfig;
import common.config.WebConfig;
import common.helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.AuthorizationPage;
import pages.EventPage;
import tests.api.TestSteps;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;

public class TestBaseWeb {
    EventPage eventPage = new EventPage();

    @BeforeAll
    static void beforeAll() {
        AuthDataConfig authConfig = ConfigFactory.create(AuthDataConfig.class, System.getProperties());
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = "https://my.mts-link.ru";
        Configuration.browser = webConfig.browser();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 15000;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");

        if (System.getProperty("browserHost", "selenoid").equals("selenoid")) {
            Configuration.browserVersion = webConfig.browserVersion();
            Configuration.remote = authConfig.selenoidUrl();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            Configuration.browserCapabilities = capabilities;
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        if (System.getProperty("browserHost", "selenoid").equals("selenoid")) {
            Attachments.addVideo();
        }
        Selenide.closeWebDriver();
    }
}
