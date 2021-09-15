package com.cybertek.officeHours.Practice_09_15_2021;

import com.cybertek.utility.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class P3_moneyGaming extends TestBase {
    @Test
    public void moneyGaming() {


         /*
        1. Navigate to: https://moneygaming.qa.gameaccount.com/

        2. Click the JOIN NOW button to open the registration page

        3. Select a title value from the dropdown

        4. Enter your first name and surname in the form

        5. Check the tickbox with text 'I accept the Terms and Conditions and certify that I am over the age of 18.'

        6. Submit the form by clicking the JOIN NOW button

        7. Validate that a validation message with text ‘ This field is required’ appears under the date of birth box
         */


        //1. Navigate to: https://moneygaming.qa.gameaccount.com/
        driver.get("https://moneygaming.qa.gameaccount.com/");


        //2.Click the JOIN NOW button to open the registration page
        WebElement joinNow = driver.findElement(By.xpath("//a[@class='newUser green']"));
        //WebElement joinNow = driver.findElement(By.cssSelector(".newUser.green"));
        joinNow.click();


        // 3. Select a title value from the dropdown
            /*
            id --> title
            //select[@id='title']
            //select[@name='map(title)']
            css -- > #title
            //select[@class='title required']
            css -- >
             */

        WebElement titleDropdown = driver.findElement(By.id("title"));

        Select dropdown = new Select(titleDropdown);
        dropdown.selectByVisibleText("Mr");
        // dropdown.deselectByVisibleText("Mr");
        // NOTE : You may only deselect options of a multi-select


        // 4. Enter your first name and surname in the form
        /*
        //input[@id='forename']
        //input[@name='map(firstName)']
        css -- > input[name='map(firstName)']

         */
        WebElement firstName = driver.findElement(By.cssSelector("input[name='map(firstName)']"));
        firstName.sendKeys("Mike");

        WebElement lastName = driver.findElement(By.cssSelector("input[name='map(lastName)']"));
        lastName.sendKeys("Smith");

        // 5. Check the tickbox with text 'I accept the Terms and Conditions and certify that I am over the age of 18.'
        WebElement termBox = driver.findElement(By.xpath("//input[@name='map(terms)']"));
        termBox.click();

        //6. Submit the form by clicking the JOIN NOW button
        /*
        xpath -- > //input[@id='form']
        css --> .promoReg.green
                #form
         */
        WebElement submitButton = driver.findElement(By.cssSelector(".promoReg.green"));
        submitButton.click();

        // 7. Validate that a validation message with text ‘ This field is required’ appears under the date of birth box

        WebElement actualResult = driver.findElement(By.xpath("//label[@for='dob']"));
        System.out.println("actualResult.getText() = " + actualResult.getText());

        System.out.println("TEST PASSED");

    }
}
