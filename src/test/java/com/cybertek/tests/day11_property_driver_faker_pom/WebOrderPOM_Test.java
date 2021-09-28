package com.cybertek.tests.day11_property_driver_faker_pom;

import com.cybertek.pages.WAllOrderPage;
import com.cybertek.pages.WLoginPage;
import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.Driver;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebOrderPOM_Test extends TestBase {


    @Test
    public void testWithPOM_for_login(){


        WLoginPage loginPage = new WLoginPage();

        loginPage.goTo();

//        loginPage.userNameField.sendKeys("Tester");
//        loginPage.passwordField.sendKeys("test");
//        loginPage.loginButton.click();
        loginPage.login("BLA" ,"test");

        assertTrue ( loginPage.loginErrorMsgPresent()  );


        BrowserUtil.waitFor(4);

    }

    @Test
    public void testAllOrderPage(){

        WLoginPage loginPage = new WLoginPage();
        loginPage.goTo();
        loginPage.login("Tester","test");
        // --- here we logged in

        // now we are at all order page
        WAllOrderPage allOrderPage = new WAllOrderPage();

        // assert the header element is displayed
        assertTrue( allOrderPage.header.isDisplayed()  );

        allOrderPage.checkAllButton.click();

        BrowserUtil.waitFor(2);

        allOrderPage.unCheckAllButton.click();

        BrowserUtil.waitFor(2);

    }


}
