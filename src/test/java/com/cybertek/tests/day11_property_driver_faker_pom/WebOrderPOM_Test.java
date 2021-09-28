package com.cybertek.tests.day11_property_driver_faker_pom;

import com.cybertek.pages.WLoginPage;
import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.Driver;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

public class WebOrderPOM_Test extends TestBase {


    @Test
    public void testWithPOM_for_login(){


        WLoginPage loginPage = new WLoginPage();

        loginPage.goTo();

//        loginPage.userNameField.sendKeys("Tester");
//        loginPage.passwordField.sendKeys("test");
//        loginPage.loginButton.click();
        loginPage.login("Tester" ,"test");

        BrowserUtil.waitFor(4);

    }

}
