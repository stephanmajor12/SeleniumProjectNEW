package com.cybertek.tests.day06_driver_utility_testbase_alerts_tables;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.junit.jupiter.api.Assertions.*;

public class YahooSearchTest extends TestBase {


    @Test
    public void testYahooSearchHomePage(){
        driver.get("https://search.yahoo.com") ;
        String expectedTitle = "Yahoo Search - Web Search" ;
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testYahooSearchResultPage() throws InterruptedException {
        // navigate to yahoo search and enter selenium and hit enter
        driver.get("https://search.yahoo.com") ;
        driver.findElement(By.name("p")).sendKeys("selenium" + Keys.ENTER);
//        assert title starts with selenium
        Thread.sleep(1000); // quick 1 second wait before getting title

        System.out.println("driver.getTitle() = " + driver.getTitle());
        assertTrue(  driver.getTitle().startsWith("selenium") );
    }
}
