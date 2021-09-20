package com.cybertek.tests.day08_upload_actions_window_js;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

public class WindowTest extends TestBase {

    @Test
    public void testWindowOrTab(){

        // selenium see window or tab with no difference
        // everything tab is new window for selenium
        driver.get("http://practice.cybertekschool.com/windows");

        // save windowHandle of this tab into currentHandle variable
        String currentHandle = driver.getWindowHandle();
        System.out.println("currentHandle = " + currentHandle);

        // try to use getWindowHandles() method and print it out
        Set<String> allHandles = driver.getWindowHandles();
        System.out.println("allHandles = " + allHandles);

        // click on the link to generate new tab|window
        WebElement clickHereLnk = driver.findElement(By.linkText("Click Here"));
        clickHereLnk.click(); // this will open new tab, so now you have 2 tabs

        // now print out current handle
        // it should be the same as whatever we got previously
        System.out.println("After clicking driver.getWindowHandle() = "
                + driver.getWindowHandle());

        // print all window handles
        // after you click now you have 2 tabs
        // in order to get all of them , you can call  driver.getWindowHandles()
        // and we can print it out
        System.out.println("After clicking driver.getWindowHandles() = "
                + driver.getWindowHandles());

        // our objective is to iterate over each and every handles
        // and use switch method to switch to the window
        // print out its title

        // this is reassigning the variable to new handles
        allHandles = driver.getWindowHandles(); // this will give us latest all handle

        // iterate over Collection , you can use for each loop
        for(String eachHandle : allHandles  ){

            System.out.println("eachHandle = " + eachHandle);
            // THIS IS HOW YOU SWITCH TO DIFFERENT WINDOW|tab
            driver.switchTo().window( eachHandle ) ;
            // now we can print out the title so we know what tab we are in right now
            System.out.println("driver.getTitle() = " + driver.getTitle());
        }

        // at the end of execution we observed
        // last switched window was window with the title "New Window"
        // now we want to go back to original tab so we can continue to work on it
        System.out.println("SWITCHING BACK TO ORIGINAL TAB ");
        driver.switchTo().window(currentHandle ) ;
        // assert the title is now "Practice"
        System.out.println("current tab title = " + driver.getTitle());
        assertEquals("Practice" , driver.getTitle() );


    }

}
