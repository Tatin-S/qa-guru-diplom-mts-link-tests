package tests.api;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.config.ApiConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseApi {
    static final ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = apiConfig.baseURI();
        RestAssured.basePath = apiConfig.basePath();
    }
}
