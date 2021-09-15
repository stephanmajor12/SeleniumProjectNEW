package com.cybertek.officeHours.Practice_09_15_2021;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class P4_LocatingDynamicElements extends TestBase {


    @Test
    public void index() {
        // 5 --//button[@class='btn btn-primary'][5]

        // result -- //p[starts-with(text(),'Clicked')]
        driver.get("http://practice.cybertekschool.com/multiple_buttons");

        WebElement buttonFive = driver.findElement(By.xpath("//button[@class='btn btn-primary'][5]"));
        buttonFive.click();
        System.out.println("buttonFive.getText() = " + buttonFive.getText());

        String expectedResult="Clicked on button five!";
        String actualResutl = driver.findElement(By.xpath("//p[starts-with(text(),'Clicked')]")).getText();

       assertEquals(expectedResult, actualResutl);


    }

    @Test
    public void startswith() {

    // 3 -- xpath //button[ starts-with(@id,'button')]
    // 3 -- css button[id^='button']
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        WebElement buttonThree = driver.findElement(By.xpath("//button[ starts-with(@id,'button')]"));
        buttonThree=driver.findElement(By.cssSelector("button[id^='button']"));
        buttonThree.click();

        String expectedResult="Clicked on button three!";
        String actualResutl = driver.findElement(By.xpath("//p[starts-with(text(),'Clicked')]")).getText();
        assertEquals(expectedResult, actualResutl);

    }

    @Test
    public void contains() {

        // 4 -- xpath //button[contains(@id,'button')][2]
        // 4 -- css button[id*='button'][onclick='button4()']
        driver.get("http://practice.cybertekschool.com/multiple_buttons");

        WebElement buttonFour = driver.findElement(By.xpath("//button[contains(@id,'button')][2]"));
         //buttonFour=driver.findElement(By.cssSelector("button[id*='button'][onclick='button4()']"));
        buttonFour.click();


        String expectedResult="Clicked on button four!";
        String actualResutl = driver.findElement(By.xpath("//p[starts-with(text(),'Clicked')]")).getText();
        assertEquals(expectedResult, actualResutl);

    }

    @Test
    public void endswith() {

        // 4 -- xpath //button[ends-with(@id,'button')]   is not working with Chrome
        // 4 -- css button[id$='button'][onclick='button4()']
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        WebElement buttonFour = driver.findElement(By.cssSelector("button[id$='button'][onclick='button4()']"));
        buttonFour.click();


        String expectedResult="Clicked on button four!";
        String actualResutl = driver.findElement(By.xpath("//p[starts-with(text(),'Clicked')]")).getText();
        assertEquals(expectedResult, actualResutl);

    }
}
