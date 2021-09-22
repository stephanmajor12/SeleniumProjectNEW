package com.cybertek.officeHours.Practice_09_22_2021;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P1_WebTables extends TestBase {
    @Test
    public void getHeaders() {
        driver.get("http://practice.cybertekschool.com/tables");

        //headers --//table[@id='table1']//th
        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='table1']//th"));
        System.out.println("headers.size() = " + headers.size());     // 6

        for (WebElement header : headers) {
            System.out.println("header.getText() = " + header.getText());
        }

    }

    @Test
    public void getRow() {
        driver.get("http://practice.cybertekschool.com/tables");
        // row 2 -- xpath //table[@id='table1']//tr[2]

        WebElement row = driver.findElement(By.xpath("//table[@id='table1']//tr[2]"));
        System.out.println("row.getText() = " + row.getText());

        List<WebElement> allRow = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"));
        System.out.println("allRow.size() = " + allRow.size());   //4

        for (int i = 1; i <= allRow.size(); i++) {
            String path="//table[@id='table1']//tr["+i+"]";

            WebElement eachRow = driver.findElement(By.xpath(path));
            System.out.println("eachRow.getText() = " + eachRow.getText());

        }


    }

    @Test
    public void getInRelation() {
        driver.get("http://practice.cybertekschool.com/tables");
        String firstname="Frank";

        // xpath -- //table[@id='table1']//td[.='Jason']/../td[3]
        String path="//table[@id='table1']//td[.='"+firstname+"']/../td[3]";

        WebElement email = driver.findElement(By.xpath(path));
        System.out.println("email.getText() = " + email.getText());
    }
}
