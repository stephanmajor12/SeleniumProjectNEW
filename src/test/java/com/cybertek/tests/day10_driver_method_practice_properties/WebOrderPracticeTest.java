package com.cybertek.tests.day10_driver_method_practice_properties;

import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.Driver;
import com.cybertek.utility.TestBase;
import com.cybertek.utility.WebOrderUtil;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

// This is new Test class we are using for day 10
public class WebOrderPracticeTest extends TestBase {

    @Test
    public void testDriverPractice(){

        WebOrderUtil.openWebOrderApp();
        // assert the page title "WebOrder Login" to ensure you are at the login page
//        Driver.getDriver().getTitle() same as below in test class
         //    driver.getTitle()
        assertEquals("Web Orders Login" ,  driver.getTitle() ) ;

        //WebOrderUtil.login();
        WebOrderUtil.login("Tester","test");



        BrowserUtil.waitFor(3);
    }

    @Test
    public void testInvalidCredentials(){

        WebOrderUtil.openWebOrderApp();

        WebOrderUtil.login("abc","efg");

        // locate the error message element
        // with text "Invalid Login or Password."  id ctl00_MainContent_status
        //span[@id='ctl00_MainContent_status']
        //span[. ='Invalid Login or Password.']
//        WebElement errorMsgElm = driver.findElement(By.xpath("//span[. ='blablanvalid Login or Password.']"));
//        assertTrue( errorMsgElm.isDisplayed()  );
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver() ,2  ) ;
//        // check of visibility of the errorMsgElm in 2 seconds
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[. ='Invalid Login or Password.']")));
//        }catch(TimeoutException e){
//            //System.out.println("The error message is = " + e.getMessage()  );
//            System.out.println("WE DID NOT SEE THE ERROR MESSAGE ELEMENT ");
//        }

//        // check for element exists using the browser util we just created
//        boolean elementFound =
//                BrowserUtil.checkVisibilityOfElement(By.xpath("//span[. ='Invalid Login or Password.']"),2);
//        // wrap above code into method inside WebOrderUtil
//        // loginErrorMsgVisible method that return above line

        assertTrue(    WebOrderUtil.loginErrorMsgVisible()       );

    }

}
