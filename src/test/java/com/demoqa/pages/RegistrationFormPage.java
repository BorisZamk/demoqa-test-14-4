package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class RegistrationFormPage {

    private final static String TITLE_TEXT = "Student Registration Form";
    private CalendarComponent calendarComponent = new CalendarComponent();
    private ResultTableComponent resultTableComponent = new ResultTableComponent();
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            mobileInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            uploadPictureInput = $("#uploadPicture");



    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public RegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationFormPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    public RegistrationFormPage setGender(String gender) {
        genderInput.$(byText(gender)).click();

        return this;
    }

    public RegistrationFormPage setMobile(String mobile) {
        mobileInput.setValue(mobile);

        return this;
    }

    public RegistrationFormPage setBDay(String year, String month, String day) {

        calendarInput.click();
        calendarComponent.setDate(year, month, day);

        return this;
    }

    public RegistrationFormPage setSubject (String subjects) {
        subjectInput.setValue(subjects).pressEnter();

        return this;
    }

    public RegistrationFormPage setHobby(String hobby) {
        hobbiesInput.$(byText(hobby)).click();

        return this;
    }

    public RegistrationFormPage setCurrentAddress (String currentAddress) {
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    public RegistrationFormPage setState (String state) {
        stateInput.click();
        $(byText(state)).click();

        return this;
    }

    public RegistrationFormPage setCity (String city) {
        cityInput.click();
        $(byText(city)).click();

        return this;
    }

    public RegistrationFormPage uploadPictures () {
        uploadPictureInput.uploadFromClasspath("img/1.png");

        return this;
    }

    public RegistrationFormPage resultTableIsVisible() {
        resultTableComponent.checkVisibility();

        return this;
    }

    public RegistrationFormPage checkResult (String key, String value) {
        resultTableComponent.checkResult(key,value);

        return this;
    }

}
