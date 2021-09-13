package com.cybertek.tests.day05_css_xpath_junit5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class YahooSearchPageTest {


    // write 2 tests :
    // testYahooSearchHomePageTitle
    //
    //    test when you navigate to yahoo search page
    //        the title should be "Yahoo Search - Web Search"

    @Test
    public void testYahooSearchHomePageTitle(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://search.yahoo.com/");

        String expectedTitle = "Yahoo Search - Web Search" ;
        String actualTitle = driver.getTitle();

        // quit the browser here , because we already saved the title
        driver.quit();
        // do static import, so you can do this
        // import static org.junit.jupiter.api.Assertions.*;
        assertEquals(expectedTitle,actualTitle ) ;


    }

    // test method name : testYahooSearchResultPageTitle
    // test navigate to yahoo page
    // and search for Selenium
    // the page title should start with selenium
    @Test
    public void testYahooSearchResultPageTitle(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://search.yahoo.com/");

        // identify search box and enter selenium , and hit Enter key on keyboard
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='p']"));
        // we can simulate keystroke usinh Keys.SELECT_ONE_OF_THE_OPTION
        // In this case we are typing selenium and hitter enter
        searchBox.sendKeys("Selenium" + Keys.ENTER);

//        String expectedTitleStartWith = "Selenium" ;
        String actualTitle = driver.getTitle() ;

        // quit the browser
        driver.quit();
        // assert the title starts with Selenium
        assertTrue( actualTitle.startsWith("Selenium")  );



    }



}
