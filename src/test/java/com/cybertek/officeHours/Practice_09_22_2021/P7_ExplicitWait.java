package com.cybertek.officeHours.Practice_09_22_2021;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class P7_ExplicitWait extends TestBase {
    @Test
    public void explicitWaits(){
        driver.get("https://testpages.herokuapp.com/styled/dynamic-buttons-disabled.html");
        WebDriverWait wait=new WebDriverWait(driver, 10);
        WebElement start = driver.findElement(By.id("button00"));
        System.out.println("start.isEnabled() = " + start.isEnabled());

        WebElement one = driver.findElement(By.id("button01"));
        System.out.println("one.isEnabled() = " + one.isEnabled());

        WebElement two = driver.findElement(By.id("button02"));
        System.out.println("two.isEnabled() = " + two.isEnabled());

        WebElement three = driver.findElement(By.id("button03"));
        System.out.println("three.isEnabled() = " + three.isEnabled());

        start.click();
        wait.until(ExpectedConditions.elementToBeClickable(one));

        one.click();
        wait.until(ExpectedConditions.elementToBeClickable(two));

        two.click();
        wait.until(ExpectedConditions.elementToBeClickable(three));

        three.click();

        String expectedResult="All Buttons Clicked";
        String actualResult = driver.findElement(By.cssSelector("#buttonmessage")).getText();
        Assertions.assertEquals(expectedResult, actualResult);

        wait.until(ExpectedConditions.textToBe(By.cssSelector("#buttonmessage"),"Clickable Buttons"));
        expectedResult="Clickable Buttons";
        actualResult = driver.findElement(By.cssSelector("#buttonmessage")).getText();
        Assertions.assertEquals(expectedResult, actualResult);

        wait.until(ExpectedConditions.textToBe(By.cssSelector("#buttonmessage"),"Click Buttons In Order"));
        expectedResult="Click Buttons In Order";
        actualResult = driver.findElement(By.cssSelector("#buttonmessage")).getText();
        Assertions.assertEquals(expectedResult, actualResult);
    }

}
