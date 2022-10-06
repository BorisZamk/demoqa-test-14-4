package com.demoqa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
//        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        String name = "Борис";
        String email = "boris@mail.ru";
        String cadress = "Где то в Москве, на какой то улице, дом 3";
        String padress = "какой то другой адрес с другой улицей, дом 30";



        //Fill out the form
        open("/text-box");
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(cadress);
        $("#permanentAddress").setValue(padress);
        $("#submit").click();

        //Check the results
        $("#output #name").shouldHave(text(name));
        $("#output #email").shouldHave(text(email));
        $("#output #currentAddress").shouldHave(text(cadress));
        $("#output #permanentAddress").shouldHave(text(padress));
    }
}
