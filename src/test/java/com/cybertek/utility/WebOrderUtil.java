package com.cybertek.utility;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebOrderUtil {


    /**
     * Simply navigate to WebOrderApp
     */
    public static void openWebOrderApp(){

        Driver.getDriver().get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx") ;

    }

    /**
     * A method for logging into Web Order practice site from login page
     */
    public static void login(){

        // enter username
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        // enter password
        Driver.getDriver().findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
        // click login
        Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();

    }


}
