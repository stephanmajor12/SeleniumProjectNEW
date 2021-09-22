package com.cybertek.officeHours.Practice_09_22_2021;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class P6_JSExecutor extends TestBase {

    @Test
    public void name() {
        driver.get("http://automationpractice.com/index.php");

        WebElement topTrends = driver.findElement(By.xpath("(//img[@class='item-img'])[3]"));

        JavascriptExecutor jse=(JavascriptExecutor) driver;

        for (int i = 0; i < 5; i++) {           //x  y
            jse.executeScript("window.scrollBy(0,250)");
        }

        for (int i = 0; i < 5; i++) {
            jse.executeScript("window.scrollBy(0,-250)");
        }

        jse.executeScript("arguments[0].scrollIntoView(true)", topTrends);

        topTrends.click();



    }
}
