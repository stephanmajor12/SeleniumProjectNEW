package com.cybertek.day04_common_elements_xpath_css_intro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectingFromFakeDropdown {

    public static void main(String[] args) {

        // open chrome and navigate to dropdown practice url
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/dropdown");

        // the only way that Select class we learned work is , if the element tag is select

        // identify the fake dropdown , it's actually a link
        WebElement fakeDropdownElm = driver.findElement(By.id("dropdownMenuLink"));
        fakeDropdownElm.click();
        // and "select" the options -- it's actually another link
        // identify option Google and click on it
        driver.findElement(By.linkText("Google")).click();


        driver.quit();

    }

}
