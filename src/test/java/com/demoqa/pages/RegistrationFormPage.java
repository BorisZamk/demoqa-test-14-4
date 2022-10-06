package com.demoqa.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class RegistrationFormPage {
    private final static String TITLE_TEXT = "Student Registration Form";

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public RegistrationFormPage setFirstName(String firstName) {
        $("#firstName").setValue(firstName);

        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        $("#lastName").setValue(lastName);

        return this;
    }

    public RegistrationFormPage setEmail(String email) {
        $("#userEmail").setValue(email);

        return this;
    }

    public RegistrationFormPage setGender(String gender) {
        $(byText(gender)).click();

        return this;
    }

    public RegistrationFormPage setMobile(String mobile) {
        $("#userNumber").setValue(mobile);

        return this;
    }

    public RegistrationFormPage setBDay(String userBYear, String userBMonth, String userBDay) {

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(userBYear);
        $(".react-datepicker__month-select").selectOption(userBMonth);
        if (Integer.parseInt(userBDay) <= 9) {
            $(format(".react-datepicker__day--00%s", userBDay)).click();
        } else {
            $(format(".react-datepicker__day--0%s", userBDay)).click();
        }

        return this;
    }

    public RegistrationFormPage setSubject (String subjects) {
        $("#subjectsInput").setValue(subjects).pressEnter();

        return this;
    }

    public RegistrationFormPage setHobbie (String hobbie) {
        $("#hobbiesWrapper").$(byText(hobbie)).click();

        return this;
    }

    public RegistrationFormPage setCurrentAddress (String currentAddress) {
        $("#currentAddress").setValue(currentAddress);

        return this;
    }

    public RegistrationFormPage setState (String state) {
        $("#state").click();
        $(byText(state)).click();

        return this;
    }

    public RegistrationFormPage setCity (String city) {
        $("#city").click();
        $(byText(city)).click();

        return this;
    }

    public RegistrationFormPage uploadPictures () {
        $("#uploadPicture").uploadFromClasspath("img/1.png");

        return this;
    }

}
