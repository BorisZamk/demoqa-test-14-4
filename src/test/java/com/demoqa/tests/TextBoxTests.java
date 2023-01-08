package com.demoqa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

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
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем страницу с формой", () -> {
            open("/text-box");
        });
        step("Заполняем поле name", () -> {
            $("#userName").setValue(name);
        });
        step("Заполняем поле email", () -> {
            $("#userEmail").setValue(email);
        });
        step("Заполняем поле current address", () -> {
            $("#currentAddress").setValue(cadress);
        });
        step("Заполняем поле permanent address", () -> {
            $("#permanentAddress").setValue(padress);
        });
        step("Нажимаем кнопку Submit", () -> {
            $("#submit").click();
        });
        step("Проверяем, что поле name содержит ранее введеное значение", () -> {
            $("#output #name").shouldHave(text(name));
        });
        step("Проверяем, что поле email содержит ранее введеное значение", () -> {
            $("#output #email").shouldHave(text(email));
        });
        step("Проверяем, что поле current address содержит ранее введеное значение", () -> {
            $("#output #currentAddress").shouldHave(text(cadress));
        });
        step("Проверяем, что поле permanent address содержит ранее введеное значение", () -> {
            $("#output #permanentAddress").shouldHave(text(padress));
        });
    }
}
