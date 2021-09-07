package com.cybertek.day04_common_elements_xpath_css_intro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LocatingAndSelectingMutiDropdown {

    public static void main(String[] args) {

        // open chrome and navigate to dropdown practice url
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/dropdown");

        // TODO : Identify language multi select dropdown
        WebElement langDropdownElm = driver.findElement(By.name("Languages"));
        // TODO: Wrap it up using Select class so we can use easy methods
        Select langSelectObj = new Select(langDropdownElm) ;
        // TODO : call all available methods

        System.out.println("langSelectObj.isMultiple() = " + langSelectObj.isMultiple());

        langSelectObj.selectByIndex(2);
        langSelectObj.selectByValue("c");
        langSelectObj.selectByVisibleText("Java");
        // at this moment 3 options are selected at the same time

        // now deselect
        langSelectObj.deselectByIndex(2);
        langSelectObj.deselectByValue("c");
        langSelectObj.deselectByVisibleText("Java");

        // deselect all
        langSelectObj.deselectAll();

        driver.quit();

    }

}
