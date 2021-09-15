package com.cybertek.officeHours.Practice_09_15_2021;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P1_Annotations extends TestBase {

    @Test
    public void test1() {
        System.out.println("Yahoo");
        driver.get("https://search.yahoo.com");

        String actualResult=driver.getTitle();
        String expectedResult="Yahoo Search - Web Search";

        Assertions.assertEquals(expectedResult, actualResult);


    }

    @Test
    public void test2() {
        System.out.println("Amazon");
        driver.get("https://amazon.com");

        String actualResult=driver.getTitle();
        String expectedResult="Amazon.com. Spend less. Smile more.";

        Assertions.assertEquals(expectedResult, actualResult);


    }
}
