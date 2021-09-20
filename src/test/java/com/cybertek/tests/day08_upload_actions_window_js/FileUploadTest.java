package com.cybertek.tests.day08_upload_actions_window_js;

import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUploadTest extends TestBase {

    @Test
    public void testUpload(){

        driver.get("http://practice.cybertekschool.com/upload");

        // locate the input filed :
        /**
         * By.id("file-upload")
         * By.xpath("//input[@id='file-upload']")  Abbos
         * By.cssSelector("input[id='file-upload']") Hande #file-upload
         *
          */

        WebElement fileInputField = driver.findElement(By.id("file-upload"));
        // find the full path of your file in your computer and send key into above input filed
        // you can copy the full path of file by
        // right clicking and holddown to option key and select -- copy "yourfilename" as path
        // it will copy the full path of your file
        String filePath = "/Users/training/Desktop/TheCoolPic.jpg" ;
        fileInputField.sendKeys(filePath);

        /**
         * #file-submit
         * //input[@id='file-submit'] mucahit
         */
        driver.findElement(By.id("file-submit")).click();
        // TODO: Optionally Assert "File Uploaded!" success message showed up
       // Thread.sleep(2000);
        BrowserUtil.waitFor(2);

        // Success message page :  //h3[.='File Uploaded!']
        WebElement successMsg = driver.findElement(By.xpath("//h3[.='File Uploaded!']"));
        assertTrue( successMsg.isDisplayed() );


        // Thread.sleep(2000); is needed sometimes , but we are forced to deal with checked exception
        // objective : have a human-readable utility method so we can call like this
        //  BrowserUtil.waitFor(2) ; === >> no exception handling just wait for 2 seconds
        //  Create a class called BrowserUtil under utility package
        //  create a static void method called waitFor accept int as second to wait

    }

}
