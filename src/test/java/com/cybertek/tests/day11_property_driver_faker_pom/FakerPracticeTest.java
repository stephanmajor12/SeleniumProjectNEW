package com.cybertek.tests.day11_property_driver_faker_pom;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class FakerPracticeTest {

    @Test
    public void testPrintFakeData(){

        Faker faker = new Faker();

        // faker.YourCategory().WhateverAvailable() ;
        System.out.println("faker.name().fullName() = "
                        + faker.name().fullName());

        // fake book info
        System.out.println("faker.book().title() = "
                + faker.book().title());

        // fake phone number
        System.out.println("faker.phoneNumber().cellPhone() = "
                + faker.phoneNumber().cellPhone());

        System.out.println("faker.chuckNorris().fact() = "
                + faker.chuckNorris().fact());


        // what if you want random numbers in specific format like phone number
        // 111-111-1111
        System.out.println("faker.numerify(\"###-###-####\") = "
                + faker.numerify("###-###-####"));

        System.out.println("faker.finance().creditCard() = "
                + faker.finance().creditCard());

        // AS a homework , use random data to fill up practice site registration form
        // use random data to fill up the address and payment info in WebOrder site

    }


}
