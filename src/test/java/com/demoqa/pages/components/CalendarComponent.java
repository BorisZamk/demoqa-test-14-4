package com.demoqa.pages.components;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CalendarComponent {
    public CalendarComponent setDate (String year, String month, String day) {
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        if (Integer.parseInt(day) <= 9) {
            $(format(".react-datepicker__day--00%s", day)).click();
        } else {
            $(format(".react-datepicker__day--0%s", day)).click();
        }

        return this;
    }
}
