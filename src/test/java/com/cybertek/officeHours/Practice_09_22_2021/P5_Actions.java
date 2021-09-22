package com.cybertek.officeHours.Practice_09_22_2021;

import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class P5_Actions extends TestBase {
    @Test
    public void hoverOver() {
        Actions actions=new Actions(driver);
        driver.get("http://automationpractice.com/index.php");

        // Dresses (//a[@title='Dresses'])[2]
        WebElement dresses = driver.findElement(By.xpath("(//a[@title='Dresses'])[2]"));

        // subCategories (//a[@title='Dresses'])[2]/..//li
        List<WebElement> subCategories = driver.findElements(By.xpath("(//a[@title='Dresses'])[2]/..//li"));

        for (WebElement subCategory : subCategories) {
            System.out.println(subCategory.isDisplayed());
            Assertions.assertFalse(subCategory.isDisplayed());
        }

        actions.moveToElement(dresses).perform();

        BrowserUtil.waitFor(3);

        for (WebElement subCategory : subCategories) {
            System.out.println(subCategory.isDisplayed());
            Assertions.assertTrue(subCategory.isDisplayed());
        }







    }


    @Test
    public void dragAndDrop() {
        Actions actions=new Actions(driver);
        driver.get("https://www.globalsqa.com/demo-site/draganddrop/");

        WebElement frame = driver.findElement(By.cssSelector(".demo-frame.lazyloaded"));
        driver.switchTo().frame(frame);


        // first  //h5[.='High Tatras']
        WebElement first = driver.findElement(By.xpath("//h5[.='High Tatras']"));

        //second //h5[.='High Tatras 2']
        WebElement second = driver.findElement(By.xpath("//h5[.='High Tatras 2']"));

        // trash    #trash
        WebElement trash = driver.findElement(By.cssSelector("#trash"));

        // first
        actions.dragAndDrop(first, trash).perform();

        BrowserUtil.waitFor(3);


        // second
        actions.moveToElement(second).clickAndHold().moveToElement(trash).pause(3000).release().perform();

        BrowserUtil.waitFor(3);


        //verfiication //div[@id='trash']//li

        List<WebElement> trashedPicture = driver.findElements(By.xpath("//div[@id='trash']//li"));
        Assertions.assertTrue(trashedPicture.size()==2);


    }

    @Test
    public void keyboardAction() {
        Actions actions=new Actions(driver);

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");

        WebElement username = driver.findElement(By.id("ctl00_MainContent_username"));
        // enter password
        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        // click login
        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));


        actions.moveToElement(username).click()
                .keyDown(Keys.SHIFT)
                .sendKeys("test")
                .keyUp(Keys.SHIFT)
               // .doubleClick()    // choose all text
                .keyDown(Keys.COMMAND).sendKeys("A")
                .keyUp(Keys.COMMAND)
                .pause(3000)
                .keyDown(Keys.COMMAND).sendKeys("C")
                .keyUp(Keys.COMMAND)
                .pause(3000)
                .sendKeys(Keys.TAB)
                .keyDown(Keys.COMMAND).sendKeys("V")
                .keyUp(Keys.COMMAND)
                .pause(3000)
                .moveToElement(loginButton)
                .click()
                .perform();


        String actualText = driver.findElement(By.cssSelector(".error")).getText();

        String expextedText="Invalid Login or Password.";

        Assertions.assertEquals(expextedText, actualText);


    }
}
