package com.cybertek.officeHours.Practice_09_15_2021;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class P5_Alerts extends TestBase {

    @Test
    public void htmlAlerts() {

    driver.get("https://www.primefaces.org/showcase/ui/overlay/confirmDialog.xhtml?jfwid=5f126");

    // confirm  //span[.='Confirm']
    // yes       //span[.='Yes']
    //   message div[@class='ui-growl-message']/p

        WebElement confirmButton = driver.findElement(By.xpath("//span[.='Confirm']"));
        confirmButton.click();
        WebElement yesButton = driver.findElement(By.xpath("//span[.='Yes']"));
        yesButton.click();

        String expectedResult="You have accepted";
        String actualResult = driver.findElement(By.xpath("//div[@class='ui-growl-message']/p")).getText();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void JSAlert() {

        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");

        // INFOMATION
        // clcik me (//button[text()='Click me!'])[1]

        driver.findElement(By.xpath("(//button[text()='Click me!'])[1]")).click();

        Alert alert=driver.switchTo().alert();

        System.out.println("alert.getText() = " + alert.getText());
        alert.accept();


        // CONFIRMATION
        driver.findElement(By.xpath("(//button[text()='Click me!'])[2]")).click();
        System.out.println("alert.getText() = " + alert.getText());
        alert.dismiss();

        // PROMPT
        // //button[text()='Click for Prompt Box']
        driver.findElement(By.xpath("//button[text()='Click for Prompt Box']")).click();
        System.out.println("alert.getText() = " + alert.getText());
        alert.sendKeys("Test");
        alert.accept();






    }
}
