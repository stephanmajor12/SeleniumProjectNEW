package com.cybertek.officeHours.Practice_09_08_2021.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class P4_ClassName {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver=new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com");


        /**
         * CLASSNAME
         * Classes are unique.we will use to take more than one elements
         * we will use it findElements
         */

        driver.findElement(By.linkText("Multiple Buttons")).click();

        WebElement multipleButtons = driver.findElement(By.className("h3"));
        System.out.println("multipleButtons.getText() = " + multipleButtons.getText());
        driver.navigate().back();
        driver.quit();


    }
}
