package pages.components;

import static com.codeborne.selenide.Selenide.*;

public class CalendarComponent {

    public void setDate(String day, String month, String year) {
        $(".DayPicker_day-select").selectOption(month);
        $x(".DayPicker_year-select").selectOption(year);
        $$x("//div[@class='DayPicker_body__n3BTD']//div[contains(@class, 'DayPicker_day')][contains(text(), '%s')]".formatted(day)).first().click();
    }
}