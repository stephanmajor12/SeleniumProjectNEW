package com.cybertek.tests.goji_a_auto;

import com.cybertek.pages.GoogleLoginPage;
import com.cybertek.utility.TestBase;
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

public class a_GoogleLoginTest extends TestBase {
    // Test method is annotated with @Test annotation
    // It's a void method with no param
    @Test
    public void testLogin_google() throws InterruptedException {
        String userName_g = "gojitester@gmail.com";
        String passWord_g = "Goj1tech!";

        GoogleLoginPage loginPage = new GoogleLoginPage();
        loginPage.goTo();
        loginPage.login(userName_g,passWord_g);
    }
}
