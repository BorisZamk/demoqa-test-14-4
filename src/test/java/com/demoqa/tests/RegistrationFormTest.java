package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormTest {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    private SelenideElement buttonSubmit = $("#submit");
    String firstName = "Борис",
            lastName = "Зам",
            email = "boris@mail.ru",
            gender = "Male",
            mobile = "9191919191",
            userBDay = "23",
            userBMonth = "October",
            userBYear = "1984",
            subjects = "English",
            hobby = "Reading",
            currentAddress = "Где то в Москве, на какой то улице, дом 3",
            state = "Haryana",
            city = "Panipat";

    @BeforeAll
    static void configure() {

        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.holdBrowserOpen = true;
//        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
    }

    @Test
    void fillRegistrationFormTest() {

        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile)
                .setBDay(userBYear, userBMonth, userBDay)
                .setSubject(subjects)
                .setHobby(hobby)
                .uploadPictures()
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city);


//
        buttonSubmit.click();
        registrationFormPage.resultTableIsVisible()
                .checkResult("Student Name", firstName + ' ' + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", userBDay + ' ' + userBMonth + "," + userBYear);

    }
    @Test
    void fillRegistrationFormWithMinimumData() {
        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setMobile(mobile)
                .setBDay(userBYear, userBMonth, userBDay);

        buttonSubmit.click();
        registrationFormPage.resultTableIsVisible()
                .checkResult("Student Name", firstName + ' ' + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", userBDay + ' ' + userBMonth + "," + userBYear);

    }
}
