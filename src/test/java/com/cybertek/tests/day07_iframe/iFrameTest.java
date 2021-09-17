package com.cybertek.tests.day07_iframe;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class iFrameTest extends TestBase {

    @Test
    public void testSimpleIFrame(){

        driver.get("http://practice.cybertekschool.com/iframe");

        // locate the <p> element inside iframe
        /**
          first we need to switch to the iFrame
          -- by index (index in java starts with 0, we are calling java method here)
            driver.switchTo().frame(0);
          -- by name or id
            driver.switchTo().frame("mce_0_ifr");
          -- by passing iframe WebElement object
             locate the iframe element by it's title  "Rich Text Area. Press ALT-0 for help."
            css selector locator :
              iframe[title='Rich Text Area. Press ALT-0 for help.']
              iframe[title^='Rich Text Area']
           */
        WebElement iframeElm = driver.findElement(By.cssSelector("iframe[title^='Rich Text Area']"));
        driver.switchTo().frame(iframeElm);

        WebElement pTagInsideFrame = driver.findElement(By.tagName("p"));

        System.out.println("pTagInsideFrame.getText() = " + pTagInsideFrame.getText());

        // 2 ways to get out of the frame
//        // jump one level out of the frame
//        driver.switchTo().parentFrame();
        // jump all the way to the main content , this will get you out of any level deep inside the frame
          driver.switchTo().defaultContent();
        // click on the Home link on top left corner
        driver.findElement(By.linkText("Home")).click();

        System.out.println("THE END");
    }

}
