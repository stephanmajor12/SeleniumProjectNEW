package com.cybertek.officeHours.Practice_09_22_2021;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class P3_FileUpload extends TestBase {
    @Test
    public void fileUpload() {
        // path of test pdf     /Users/soyturk/Desktop/test.pdf

        // path choose file     #fileinput
        // message              .explanation

        driver.get("https://testpages.herokuapp.com/styled/file-upload-test.html");

        String path="/Users/soyturk/Desktop/test.pdf";

        WebElement chooseFile = driver.findElement(By.cssSelector("#fileinput"));

        chooseFile.sendKeys(path);
        chooseFile.submit();

        /*
        WebElement uploadButton = driver.findElement(By.cssSelector("input[type='submit']"));
        uploadButton.click();

         */

        String expectedResult= "You uploaded a file. This is the result.";
        String actualResult= driver.findElement(By.cssSelector(".explanation")).getText();

        Assertions.assertEquals(expectedResult, actualResult);




    }
}
