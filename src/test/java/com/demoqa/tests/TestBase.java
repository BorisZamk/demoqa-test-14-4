package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.utils.RandomTestData;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    RandomTestData randomTestData = new RandomTestData();
    @BeforeAll
    static void configure (){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
//        Configuration.browserSize = "1920x1080";
//        Configuration.headless = true;
    }
}
