package common.helpers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

public class Tools {
    public static String getEventSessionIdFromUrl(String url) {
        return url.split("/")[7];
    }

    public static String getEventSessionIdFromUrlForEdit(String url) {
        return url.split("/")[6];
    }

    public void cleanField(SelenideElement field) {
        field.sendKeys(Keys.LEFT_CONTROL, "a");
        field.sendKeys(Keys.BACK_SPACE);
    }
}
