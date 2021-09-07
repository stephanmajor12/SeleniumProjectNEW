package com.cybertek.day04_common_elements_xpath_css_intro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatingCheckBoxes {

    public static void main(String[] args) {

        // checkboxes can be checked and unchecked
        //  That's the only attribute special about the element
        // we can use additional method called isSelected() to determine if it's checked

        // open chrome navigate here http://practice.cybertekschool.com/checkboxes

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver() ;
        driver.get("http://practice.cybertekschool.com/checkboxes");

        // identify first checkbox
        WebElement checkbox1 = driver.findElement(By.id("box1")) ;
        System.out.println("checkbox1.isSelected() = " + checkbox1.isSelected());
        // get the value of checked attribute  , checkbox1.getAttribute("checked").soutv
        System.out.println("checkbox1.getAttribute(\"checked\") = "
                + checkbox1.getAttribute("checked"));

        WebElement checkbox2 = driver.findElement(By.id("box2"));
        System.out.println("checkbox2.isSelected() = " + checkbox2.isSelected());
        System.out.println("checkbox2.getAttribute(\"checked\") = "
                + checkbox2.getAttribute("checked"));

        checkbox1.click();  // currently, it's unchecked so clicking will check it
        checkbox2.click();  // currently, it's checked so clicking will uncheck it

        // check if it's not already checked then click on it
        if( ! checkbox1.isSelected() ){
            checkbox1.click();
        }else{
            System.out.println("take it easy , already checked");
        }

        if( ! checkbox2.isSelected() ){
            checkbox2.click();
        }else{
            System.out.println("take it easy , already checked");
        }






        // close browser
        driver.quit();


    }

}
