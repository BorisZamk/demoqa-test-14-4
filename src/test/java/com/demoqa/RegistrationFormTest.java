package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class RegistrationFormTest {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillRegistrationFormTest() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";

        String fname = "Борис";
        String lname = "Зам";
        String email = "boris@mail.ru";
        String gender = "Male";
        String mobile = "9191919191";
        String userBDay = "23";
        String userBMonth = "October";
        String userBYear = "1984";
        String subjects = "English";
        String hobbie = "Reading";
        String cadress = "Где то в Москве, на какой то улице, дом 3";
        String state = "Haryana";
        String city = "Panipat";

        open("/automation-practice-form");

        //Fill out the form
        $("#firstName").setValue(fname);
        $("#lastName").setValue(lname);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobile);

        //Выбор даты рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(userBYear);
        $(".react-datepicker__month-select").selectOption(userBMonth);
        if (Integer.parseInt(userBDay) <= 9) {
            $(format(".react-datepicker__day--00%s", userBDay)).click();
        } else {
            $(format(".react-datepicker__day--0%s", userBDay)).click();
        }

        //Если в данном месте добавить .pressEnter(), то форма реагирует на это, как на нажатие на кнопку Submit.
        //И далее все остальные шаги начинают отрабатываться некорректно.
        //Данное поле я не смог заполнить даже вручну. Вероятно это какой то заложенный баг
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbie)).click();
        $("#currentAddress").setValue(cadress);

       //Выбор State и City из выпадающих списков
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldBe(visible);

    }
}
