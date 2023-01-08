package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultTableComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTextCaseInsensitive;
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


    @Step("Open Registration Form page")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));

        return this;
    }

    @Step("Fill in the first name field")
    public RegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    @Step("Fill in the last name field")
    public RegistrationFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    @Step("Fill in the e-mail field")
    public RegistrationFormPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    @Step("Select {gender} in gender check-box")
    public RegistrationFormPage setGender(String gender) {
        genderInput.$(byTextCaseInsensitive(gender)).click();
        return this;
    }

    @Step("Fill in the mobile field")
    public RegistrationFormPage setMobile(String mobile) {
        mobileInput.setValue(mobile);

        return this;
    }

    @Step("Select birthday in calendar")
    public RegistrationFormPage setBDay(String year, String month, String day) {

        calendarInput.click();
        calendarComponent.setDate(year, month, day);

        return this;
    }

    @Step("Fill in the subject field")
    public RegistrationFormPage setSubject (String subjects) {
        subjectInput.setValue(subjects).pressEnter();

        return this;
    }

    @Step("Select hobby in the hobby check-boxes")
    public RegistrationFormPage setHobby(String hobby) {
        hobbiesInput.$(byText(hobby)).click();

        return this;
    }

    @Step("Fill in the current address field")
    public RegistrationFormPage setCurrentAddress (String currentAddress) {
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    @Step("Find and select the state  in the state field")
    public RegistrationFormPage setState (String state) {
        stateInput.click();
        $(byText(state)).click();

        return this;
    }

    @Step("Find and select the city  in the city field")
    public RegistrationFormPage setCity (String city) {
        cityInput.click();
        $(byText(city)).click();

        return this;
    }

    @Step("Upload a picture")
    public RegistrationFormPage uploadPictures () {
        uploadPictureInput.uploadFromClasspath("img/1.png");

        return this;
    }

    @Step("Check that the Result table is visible")
    public RegistrationFormPage resultTableIsVisible() {
        resultTableComponent.checkVisibility();

        return this;
    }

    @Step("Check that {key} field contains correct value in the Result table")
    public RegistrationFormPage checkResult (String key, String value) {
        resultTableComponent.checkResult(key,value);

        return this;
    }



}
