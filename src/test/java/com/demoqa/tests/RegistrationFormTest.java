package com.demoqa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.utils.RandomTestData;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

@DisplayName("Testing the student registration form")
public class RegistrationFormTest extends TestBase{
    private final SelenideElement buttonSubmit = $("#submit");
    RandomTestData randomTestData = new RandomTestData();
    Faker faker = new Faker();

    String[] rndDate = randomTestData.getRandomDateParsed();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = randomTestData.getRandomEmail(firstName, lastName),
            gender = "Male",
            mobile = faker.phoneNumber().subscriberNumber(10),
            userBDay = rndDate[0],
            userBMonth = rndDate[1],
            userBYear = rndDate[2],
            subjects = "English",
            hobby = "Reading",
            currentAddress = faker.address().fullAddress(),
            state = "Haryana",
            city = "Panipat",
            expectedStudentName = format("%s %s", firstName, lastName),
            expectedBDay = format("%s %s,%s",userBDay, userBMonth, userBYear),
            expectedStateCity = format("%s %s",state, city);


    @Test
    @DisplayName("Fill in all fields of registration form and check results table")
    void fillRegistrationFormTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

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

        buttonSubmit.click();

        registrationFormPage.resultTableIsVisible()
                .checkResult("Student Name", expectedStudentName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", expectedBDay)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", "1.png")
                .checkResult("Address", currentAddress)
                .checkResult("State and City",expectedStateCity);

    }
    @Test
    @DisplayName("Fill in only required fields and check results table")
    void fillRegistrationFormWithMinimumData() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setMobile(mobile)
                .setBDay(userBYear, userBMonth, userBDay);

        buttonSubmit.click();
        registrationFormPage.resultTableIsVisible()
                .checkResult("Student Name", expectedStudentName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", expectedBDay);

    }

//  8. JUnit 5. Дмитрий Тучс
//  1. Написать свои варианты параметризованных dеб-тестов (не на поиск в яндексе или гугле)
//
//  1.1. Попробовать разные варианты датапровайдеров (аннотаций), минимум 3
    enum Genders {
        MALE, FEMALE, OTHER, TRANS
    }
    @DisplayName("Check of choosing different genders via @EnumSource annotation. \"Trans\"-test must be failed")
    @ParameterizedTest(name = "Check of choosing \"{0}\" gender")
    @EnumSource(Genders.class)
    void fillFormWithDifferentGendersParamTestWithEnumSource(Genders gender) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(String.valueOf(gender))
                .setMobile(mobile);

        buttonSubmit.click();
        registrationFormPage.resultTableIsVisible()
                .checkResult("Student Name", expectedStudentName)
                .checkResult("Gender", String.valueOf(gender))
                .checkResult("Mobile", mobile);

    }

    @DisplayName("Check of choosing different hobbies via @ValueSource annotation")
    @ValueSource(strings = {"Sports", "Reading", "Music"})
    @ParameterizedTest(name = "Check of choosing \"{0}\"")
    void fillFormWithDifferentHobbyParamTestWithValueSource(String hobby) {

        SelenideLogger.addListener("allure", new AllureSelenide());

        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(String.valueOf(gender))
                .setMobile(mobile)
                .setBDay(userBYear, userBMonth, userBDay)
                .setHobby(hobby);

        buttonSubmit.click();
        registrationFormPage.resultTableIsVisible()
                .checkResult("Student Name", expectedStudentName)
                .checkResult("Gender", String.valueOf(gender))
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", expectedBDay)
                .checkResult("Hobbies", hobby);

    }

    static Stream<Arguments> fillFormWithoutRequiredFieldParamTestWithMethodSource() {
        return Stream.of(
                Arguments.of("#firstName","", "Lastname","1234567890"),
                Arguments.of("#lastName","Firstname", "","1234567890"),
                Arguments.of("#userNumber","Firstname", "Lastname","")
        );
    }
    @DisplayName("Check warning \"the required field is not filled in\"  via @MethodSource annotation")
    @MethodSource()
    @ParameterizedTest(name = "Field \"{0}\" isn't filled")
    void fillFormWithoutRequiredFieldParamTestWithMethodSource(String element, String firstName, String lastName,String mobile) {

        SelenideLogger.addListener("allure", new AllureSelenide());

        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(String.valueOf(gender))
                .setMobile(mobile);

        buttonSubmit.click();
        $(".modal-dialog").shouldNotBe(visible);
        $(element).shouldBe(empty);
    }

    @Disabled("Для отчета Allure потом будет нужен disabled тест")
    @DisplayName("Disabled test for allure report")
    @Test
    void disabledTest() {
        registrationFormPage.openPage();
    }
}
