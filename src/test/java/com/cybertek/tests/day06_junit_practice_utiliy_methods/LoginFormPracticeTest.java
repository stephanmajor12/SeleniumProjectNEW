package com.cybertek.tests.day06_junit_practice_utiliy_methods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginFormPracticeTest {

    WebDriver driver ;

    @BeforeEach
    public void setupWebDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void testLogin(){

        driver.get("http://practice.cybertekschool.com/login");

        //h2[ .='Login Page' ]
        WebElement header = driver.findElement(By.xpath("//h2[ .='Login Page' ]"));
        System.out.println("header.getText() = " + header.getText());

        //   css selector :   input[name='username']
        // @Hande Tek Karsi
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("tomsmith");
        //   css selector :   input[name='password']
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("SuperSecretPassword");

        // wooden_spoon
        driver.findElement(By.id("wooden_spoon")).click();

         //div[@id='flash']
        WebElement successMsg = driver.findElement(By.xpath(" //div[@id='flash']"));
        // assertion
        String expectedResult = "You logged into a secure area!" ;
        assertEquals(expectedResult , successMsg.getText() );

    }


}
