package com.cybertek.officeHours.Practice_09_08_2021.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class P3_TagName {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver=new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com");


        /**
         * TAGNAME
         * if the tag is top of the page OR if we have ONLY ONE
         */

        WebElement firstTag = driver.findElement(By.tagName("a"));
        System.out.println("firstTag.getText() = " + firstTag.getText());
        driver.quit();


    }
}
