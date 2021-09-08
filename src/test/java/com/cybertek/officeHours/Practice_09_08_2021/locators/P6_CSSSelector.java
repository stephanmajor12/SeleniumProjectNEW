package com.cybertek.officeHours.Practice_09_08_2021.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class P6_CSSSelector {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver=new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com");


        /***
         * 7. CSS Selector
         *
         *  ID  : #idOfElement
         *
         *  CLASS : .ClassName.   if we have more than one class (Spaces means different classes)
         *                              class="abcd def";
         *
         *                              .abcd.def

                tagName > tagName[attribute='value] OR tagName[attribute='value] OR  [attribute='value]
         */


        WebElement checkBoxPage = driver.findElement(By.cssSelector("[href='/checkboxes']"));
        checkBoxPage.click();

        WebElement cbox1 = driver.findElement(By.cssSelector("#box1"));
        System.out.println("cbox1.isSelected() = " + cbox1.isSelected());
        cbox1.click();
        System.out.println("cbox1.isSelected() = " + cbox1.isSelected());
        System.out.println("cbox1.isEnabled() = " + cbox1.isEnabled());
        System.out.println("cbox1.isDisplayed() = " + cbox1.isDisplayed());

        //Another Ex

        driver.navigate().to("http://practice.cybertekschool.com/multiple_buttons");

        WebElement dontClick = driver.findElement(By.cssSelector("#disappearing_button"));
        System.out.println("disButton.getText() = " + dontClick.getText());
        dontClick.click();

        // WebElement result = driver.findElement(By.cssSelector("#result"));
        WebElement result = driver.findElement(By.cssSelector("p[id='result']"));
        System.out.println("result.getText() = " + result.getText());


        driver.quit();
    }
}
