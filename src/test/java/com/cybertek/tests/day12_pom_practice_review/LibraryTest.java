package com.cybertek.tests.day12_pom_practice_review;

import com.cybertek.pages.DashboardPage;
import com.cybertek.pages.LibLoginPage;
import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

public class LibraryTest extends TestBase {

    @Test
    public void testLogin(){

        LibLoginPage loginPage = new LibLoginPage();
        loginPage.goTo();
        loginPage.login("librarian52@library","Sdet2022*" );

        BrowserUtil.waitFor(3);

        DashboardPage dashboardPage = new DashboardPage() ;

        System.out.println("dashboardPage.getBookCountText() = "
                + dashboardPage.getBookCountText());
        System.out.println("dashboardPage.getUserCountText() = "
                + dashboardPage.getUserCountText());
        System.out.println("dashboardPage.getBorrowedBookCountText() = "
                + dashboardPage.getBorrowedBookCountText());
    }


}
