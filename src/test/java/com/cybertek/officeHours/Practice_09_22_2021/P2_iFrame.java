package com.cybertek.officeHours.Practice_09_22_2021;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class P2_iFrame extends TestBase {

    @Test
    public void toolQa() {
        driver.get("https://demoqa.com/frames");
        // css - #sampleHeading

        driver.switchTo().frame("frame1");

        System.out.println("driver.findElement(By.cssSelector(\"#sampleHeading\")).getText() = " + driver.findElement(By.cssSelector("#sampleHeading")).getText());

    }

    @Test
    public void nestedFrames() {

        driver.get("http://practice.cybertekschool.com/nested_frames");

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        // css for MIDDLE
        System.out.println(driver.findElement(By.cssSelector("#content")).getText());

        driver.switchTo().parentFrame();   // frame-top
        driver.switchTo().frame(2);

        System.out.println(driver.findElement(By.tagName("body")).getText());
        /*
        driver.switchTo().parentFrame();   // frame-top
        driver.switchTo().parentFrame();   // frame-top

         */

        driver.switchTo().defaultContent();
        driver.switchTo().frame(1);
        System.out.println(driver.findElement(By.tagName("body")).getText());




    }
}
