package com.cybertek.day04_common_elements_xpath_css_intro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatingRadioButtons {

    // radio buttons can be checked and usually come in group
    //  That's the only attribute special about the element
    // we can use additional method called isSelected() to determine if it's checked

    // open chrome navigate here http://practice.cybertekschool.com/radiobuttons

    public static void main(String[] args) {
        // open chrome navigate here http://practice.cybertekschool.com/radiobuttons
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver() ;
        driver.get("http://practice.cybertekschool.com/radiobuttons");


    }

}
