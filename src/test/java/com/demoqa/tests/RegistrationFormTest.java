package com.demoqa.tests;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.utils.RandomTestData;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class RegistrationFormTest extends TestBase{

    private final SelenideElement buttonSubmit = $("#submit");
    RandomTestData randomTestData = new RandomTestData();
    Faker faker = new Faker();

//    @BeforeEach
//    void prepareTestData() {
//        String[] rndDate = randomTestData.getRandomDateParsed();
//        String firstName = faker.name().firstName(),
//                lastName = faker.name().lastName(),
//                email = randomTestData.getRandomEmail(firstName, lastName),
//                gender = "Male",
//                mobile = faker.phoneNumber().subscriberNumber(10),
//                userBDay = rndDate[0],
//                userBMonth = rndDate[1],
//                userBYear = rndDate[2],
//                subjects = "English",
//                hobby = "Reading",
//                currentAddress = faker.address().fullAddress(),
//                state = "Haryana",
//                city = "Panipat",
//                expectedStudentName = format("%s %s", firstName, lastName),
//                expectedBDay = format("%s %s,%s",userBDay, userBMonth, userBYear),
//                expectedStateCity = format("%s %s",state, city);
//    }

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
                .checkResult("Student Name", expectedStudentName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", expectedBDay);

    }
}
