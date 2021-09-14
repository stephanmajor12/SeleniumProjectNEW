package com.cybertek.tests.day06_driver_utility_testbase_alerts_tables;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableXPathPracticeTest extends TestBase {


    @Test
    public void testTableData(){

        driver.get("http://practice.cybertekschool.com/tables");

         //table[@id='table1']//tr[4]/td[2]
        // print the text of 4th column and second row
        WebElement cell42 = driver.findElement(By.xpath("//table[@id='table1']//tr[4]/td[2]"));
        System.out.println("cell42.getText() = " + cell42.getText());

        assertEquals("Tim" ,cell42.getText() ) ;

        //table[@id='table1']//td[ text()='Jason']
        WebElement cellWithTextJason = driver.findElement(By.xpath("//table[@id='table1']//td[ text()='Jason']"));
        System.out.println("cellWithTextJason text = " + cellWithTextJason.getText());

        assertEquals("Jason" ,cellWithTextJason.getText() ) ;


    }



}
