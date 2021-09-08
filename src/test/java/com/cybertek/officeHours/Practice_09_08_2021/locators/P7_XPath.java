package com.cybertek.officeHours.Practice_09_08_2021.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class P7_XPath {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver=new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com");


        /***
         * 8. XPath
         *
         *  1- ABSOLUTE : Full PATH and it start from html
         *      if there any changes , Xpath will fail
         *
         *                  div/label/span/.....
         *
         *  2- RELATIVE :  // it looks anywhere in the page
         *
         *
         * LOCATING ELEMENTS KNOWN ATTRIBUTE & VALUE
         *
         * // TagName[@attribute='value']
         * // TagName[@*='value']
         * // TagName[text()='textValue']
         * // *[text()='textValue']
         *
         */

          driver.findElement(By.xpath("//a[text()='Radio Buttons']"))  .click();

        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@name='sport']"));
        System.out.println("radioButtons.size() = " + radioButtons.size());


        for (WebElement radioButton : radioButtons) {
            System.out.println("radioButton.isDisplayed() = " + radioButton.isDisplayed());
            System.out.println("radioButton.isEnabled() = " + radioButton.isEnabled());
            System.out.println("radioButton.isSelected() = " + radioButton.isSelected());
            System.out.println("radioButton.getText() = " + radioButton.getText());
            System.out.println("radioButton.getAttribute(\"id\") = " + radioButton.getAttribute("id"));
            System.out.println("=====================");
        }

        WebElement football = driver.findElement(By.xpath("//label[@for='football']"));
        System.out.println("football.getText() = " + football.getText());
        driver.quit();

    }
}
