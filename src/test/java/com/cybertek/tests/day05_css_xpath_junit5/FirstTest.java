package com.cybertek.tests.day05_css_xpath_junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class name should be sth like this
 * //  SomethingTest.java
 * // TestSomething.java
 * // SomethingSouldDoThatTest.java
 *
 * // Test method  :  testAddition()
 *                      additionTest()
 *
 */

public class FirstTest {
    // Test method is annotated with @Test annotation
    // It's a void method with no param
    @Test
    public void testAddition(){

        int num1 = 10 ;
        int num2 = 7 ;
        int expectedResult = 17 ;
        Assertions.assertEquals(expectedResult , num1+num2);

    }

    @Test
    public void testString(){

        String msg = "I love Selenium";
        // assert this msg equal "I love Selenium"
        // below assertEquals method accept 2 parameters
        Assertions.assertEquals("I love Selenium" ,  msg);

        // assert this msg starts with "I love"
//        below assertTure method accept 1 parameter as boolean
        Assertions.assertTrue(   msg.startsWith("I love")   );

    }




}
