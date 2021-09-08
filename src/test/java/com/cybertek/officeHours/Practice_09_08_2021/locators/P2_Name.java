package com.cybertek.officeHours.Practice_09_08_2021.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class P2_Name {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver=new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com");


        /**
         * NAME
         * NOt always unique.(Be careful for radioButtons)
         */

        WebElement signUpPage = driver.findElement(By.partialLinkText("Sign"));
        signUpPage.click();

        WebElement name = driver.findElement(By.name("full_name"));
        name.sendKeys("Mike Smith");
        System.out.println("name.getAttribute(\"type\") = " + name.getAttribute("type"));

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("mike@cybertek.com");
        System.out.println("name.getAttribute(\"name\") = " + name.getAttribute("name"));

        WebElement signUpButton = driver.findElement(By.name("wooden_spoon"));
        signUpButton.click();

        /*driver.navigate().back();
        driver.navigate().back();

         */
        driver.navigate().to("http://practice.cybertekschool.com");

        signUpPage= driver.findElement(By.partialLinkText("Sign"));
        signUpPage.click();



        driver.quit();

    }
}
