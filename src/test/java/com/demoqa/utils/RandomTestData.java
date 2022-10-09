package com.demoqa.utils;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.String.format;

public class RandomTestData {
    Faker faker = new Faker();

//    public String getRandomDate() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM,yyyy", Locale.ENGLISH);
//
//        return sdf.format(faker.date().birthday());
//    }

    public String[] getRandomDateParsed(){
        String[] dateParsed = new String[3],
                month = {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"};

            int rndMonth = faker.number().numberBetween(0,11);

            dateParsed[0] = String.valueOf(faker.number().numberBetween(1,31));
            dateParsed[1] = month[rndMonth];
            dateParsed[2] = String.valueOf(faker.number().numberBetween(1940,2012));

        return dateParsed;
    }

    public String getRandomEmail (String firstName, String lastName) {
       return format("%s.%s@%s.%s",
               firstName,
               lastName,
               faker.internet().domainWord(),
               faker.internet().domainSuffix());
    }
//    @Test
//    void main() throws ParseException {
//        String[] rndDate = new String[3];
//        for(int i = 0; i < 10; i++) {
//            rndDate = getRandomDateParsed();
//            System.out.println(format("%s %s,%s", rndDate[0], rndDate[1], rndDate[2]));
//        }
//    }
}
