package com.cybertek.officeHours.Practice_09_08_2021.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class P1_ID {
    public static void main(String[] args) {


        WebDriverManager.chromedriver().setup();

        WebDriver driver=new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com");

        /**
         * LOCATORS are milestone of Selenium
         */

        /**
         * ID
         * Always look first for ID.They are UNIQUE.(It can be dynamic)
         */

        driver.findElement(By.linkText("Multiple Buttons")).click();

        WebElement disappearing_button = driver.findElement(By.id("disappearing_button"));
        System.out.println("disappearing_button.getText() = " + disappearing_button.getText());
        disappearing_button.click();

        WebElement result = driver.findElement(By.id("result"));
        System.out.println("result.getText() = " + result.getText());

        driver.navigate().back();

        driver.quit();


    }
}
