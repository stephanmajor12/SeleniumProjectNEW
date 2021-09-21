package com.cybertek.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebOrderUtility {


    public static void login(WebDriver driver){

        // enter username
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        // enter password
        driver.findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
        // click login
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

    }

}
