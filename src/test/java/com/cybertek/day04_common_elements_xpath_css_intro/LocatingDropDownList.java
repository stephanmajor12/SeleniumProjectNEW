package com.cybertek.day04_common_elements_xpath_css_intro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatingDropDownList {

    public static void main(String[] args) {

        // open chrome and navigate to dropdown practice url
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://practice.cybertekschool.com/dropdown");

    }

}
