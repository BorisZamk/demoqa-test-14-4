package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormTest {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    String firstName = "Борис";
    String lastName = "Зам";
    String email = "boris@mail.ru";
    String gender = "Male";
    String mobile = "9191919191";
    String userBDay = "23";
    String userBMonth = "October";
    String userBYear = "1984";
    String subjects = "English";
    String hobby = "Reading";
    String currentAddress = "Где то в Москве, на какой то улице, дом 3";
    String state = "Haryana";
    String city = "Panipat";

    @BeforeAll
    static void configure() {

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
//        Configuration.headless = true;
    }

    @Test
    void fillRegistrationFormTest() {

        registrationFormPage.openPage();

        //Fill out the form
        registrationFormPage.setFirstName(firstName)
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
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldBe(visible);

    }
    @Test
    void FillRegistrationFormWithMinimumData() {
        registrationFormPage.openPage();
        registrationFormPage.setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setMobile(mobile)
                .setBDay(userBYear, userBMonth, userBDay);

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldBe(visible);
    }
}
