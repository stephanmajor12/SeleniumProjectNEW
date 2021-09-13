package com.cybertek.day05_css_xpath_junit5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class XPathPractice1 {

    public static void main(String[] args) {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/forgot_password");

        // locate header element using xpath
        WebElement forgetPasswordHeader = driver.findElement(By.xpath("//h2[ text() = 'Forgot Password']"));
        System.out.println("forgetPasswordHeader.getText() = " + forgetPasswordHeader.getText());
        // locate input box element using xpath
        WebElement emailBox = driver.findElement(By.xpath("//input[ @name='email' and @type='text']")) ;
        emailBox.sendKeys("someone@somewhere.com");

        // locate button element using xpath
        WebElement retrieveBtn = driver.findElement(By.xpath("//button[@id='form_submit' and @class='radius']"));
        retrieveBtn.click();

        // use xpath to identify confirmation message
        //Your e-mail's been sent!
        /**
         * Couple xpath we can try to get this confirmation header element 
         *  //h4[@name='confirmation_message' and text()='Your e-mail's been sent!']
         *  //h4[@name='confirmation_message']
         *  //h4[text()='Your e-mail's been sent!']
         *  //h4[.='Your e-mail's been sent!']
         *  
         */
        String confirmationLocatorStr = "//h4[text()=\"Your e-mail's been sent!\"]" ;
        System.out.println("confirmationLocatorStr = " + confirmationLocatorStr);


        WebElement confirmationMsg = driver.findElement(By.xpath( confirmationLocatorStr ) ) ;
        System.out.println("confirmationMsg.getText() = " + confirmationMsg.getText());

//WebElement confirmationMsg = driver.findElement(By.xpath( "//h4[text()=\"Your e-mail's been sent!\"]" ) ) ;
        
//        WebElement confirmationMsg = driver.findElement(By.xpath("//h4[@name='confirmation_message' and text()=\"Your e-mails been sent!\"]")) ;
//        System.out.println("confirmationMsg.getText() = " + confirmationMsg.getText());

        driver.quit();
    }

}
