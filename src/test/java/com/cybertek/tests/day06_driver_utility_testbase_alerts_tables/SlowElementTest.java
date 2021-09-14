package com.cybertek.tests.day06_driver_utility_testbase_alerts_tables;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SlowElementTest extends TestBase {

    @Test
    public void testWait() throws InterruptedException {


        // we can instruct WebDriver to wait maximum amount of time
        // before throwing NoSuchElement Exception by using implicit wait
        // If element is not available , it will wait for maximum of 10second
        // If element is found before 10s for example 1 second - > It will move on to next step

        driver.navigate().to("http://practice.cybertekschool.com/dynamic_controls");
        driver.findElement(By.xpath("//button[ .='Remove']")).click();

//        Thread.sleep(5000);

        WebElement itsGoneElm = driver.findElement(By.id("message")) ;
        System.out.println("itsGoneElm.getText() = " + itsGoneElm.getText());
        System.out.println("THE END ");

    }

}
