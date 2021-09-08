package com.cybertek.officeHours.Practice_09_08_2021.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class P5_LinkText_Vs_PartialText {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver=new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com");


        /**
         * LINKTEXT
         *
         */
        driver.findElement(By.linkText("Multiple Buttons")).click();
        WebElement homeLink = driver.findElement(By.linkText("Home"));
        System.out.println("homeLink.getAttribute(\"class\") = " + homeLink.getAttribute("class"));
        homeLink.click();



        /**
         * PARTIALLINKTEXT
         *
         */


        WebElement multiple = driver.findElement(By.partialLinkText("Multiple"));
        System.out.println("multiple.getText() = " + multiple.getText());

        List<WebElement> multipleLinks = driver.findElements(By.partialLinkText("Multiple"));

        for (WebElement multipleLink : multipleLinks) {
            System.out.println("multipleLink.getText() = " + multipleLink.getText());
        }


        driver.quit();
    }
}
