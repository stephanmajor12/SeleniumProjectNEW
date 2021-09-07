package com.cybertek.day04_common_elements_xpath_css_intro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementByCssSelector {

    public static void main(String[] args) {

        // open chrome navigate here https://search.yahoo.com/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver() ;
        driver.get("https://search.yahoo.com/");

        // TODO : Identify searcbox using css selector

        //<input type="text" class="sbq" id="yschsp" name="p" value=""  role="combobox">

        // try different way , by id , by name
//        WebElement searchbox = driver.findElement(By.cssSelector("#yschsp"));
//        WebElement searchbox = driver.findElement(By.cssSelector("input[name='p']"));
//        WebElement searchbox = driver.findElement(By.cssSelector("input[id='yschsp']"));
        WebElement searchbox = driver.findElement(By.cssSelector("input[name='p'][type='text'] "));
        searchbox.sendKeys("CSS SELECTOR");



        driver.quit();



    }

}
