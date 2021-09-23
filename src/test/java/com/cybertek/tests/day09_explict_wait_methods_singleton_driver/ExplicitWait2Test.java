package com.cybertek.tests.day09_explict_wait_methods_singleton_driver;

import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait2Test extends TestBase {

    @Test
    public void waitUntilElementInteractive(){

        driver.get("http://practice.cybertekschool.com/dynamic_controls");

        // 	elementToBeClickable(By locator)
        // elementToBeClickable(WebElement element)
        // An expectation for checking an element is visible and enabled such that you can click it.


        WebElement inputField = driver.findElement(By.cssSelector("form#input-example>input"));
        System.out.println("inputField.isEnabled() = " + inputField.isEnabled());

        // click on enable button and wait until the inputb=Box is enabled clickable
        driver.findElement(By.xpath("//button[.='Enable']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 7);
        // this will wait for 7 second max for input element located by cssSelector("form#input-example>input")
        // enabled , interactive , visible
        //wait.until( elementToBeClickable(By.cssSelector("form#input-example>input") )  );
        wait.until( elementToBeClickable(inputField) ) ;

        // now enter something into the input filed
        inputField.sendKeys("HELLO WORLD");

        BrowserUtil.waitFor(5);


    }


}
