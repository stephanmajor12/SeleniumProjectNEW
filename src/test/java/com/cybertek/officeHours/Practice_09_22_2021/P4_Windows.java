package com.cybertek.officeHours.Practice_09_22_2021;

import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;

public class P4_Windows extends TestBase {
    @Test
    public void multipleWindows() {

        driver.get("https://www.amazon.com");

        JavascriptExecutor jse=(JavascriptExecutor) driver;

        jse.executeScript("window.open('http://google.com','_blank');");
        BrowserUtil.waitFor(1);

        jse.executeScript("window.open('http://etsy.com','_blank');");
        BrowserUtil.waitFor(1);

        jse.executeScript("window.open('https://www.target.com/','_blank');");
        BrowserUtil.waitFor(1);

        String currentHandle = driver.getWindowHandle();
        String expectedResult=driver.getTitle();


        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            System.out.println("driver.getTitle() = " + driver.getTitle()+" handle "+windowHandle);

            if(driver.getTitle().contains("Etsy")){
                Assertions.assertTrue(driver.getTitle().contains("Etsy"));
                break;
            }

        }

        BrowserUtil.waitFor(3);

        System.out.println("driver.getWindowHandle() = " + driver.getWindowHandle());

        driver.switchTo().window(currentHandle);
        String actualResult=driver.getTitle();
        System.out.println("actualResult = " + actualResult);

        Assertions.assertEquals(expectedResult, actualResult);


    }
}
