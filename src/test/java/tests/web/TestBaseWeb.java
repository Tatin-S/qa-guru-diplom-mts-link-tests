package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.config.WebConfig;
import common.helpers.Attachments;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

@Owner("Stulova Tatiana")
public class TestBaseWeb {
    @BeforeAll
    static void beforeAll() {
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 15000;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");

        if (webConfig.isRemote()) {
            Configuration.remote = "https://" + webConfig.selenoidUser() + ":"
                    + webConfig.selenoidPass() +
                    "@" + webConfig.remoteUrl()
                    + "/wd/hub";
        }

        Configuration.pageLoadStrategy = "eager";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
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
        Attachments.addVideo();
        Selenide.closeWebDriver();
    }
}
