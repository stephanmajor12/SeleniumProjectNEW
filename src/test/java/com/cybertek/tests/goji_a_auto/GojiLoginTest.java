package com.cybertek.tests.goji_a_auto;

import com.cybertek.pages.GojiLoginPage;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

public class GojiLoginTest extends TestBase {
    @Test
    public void testLogin_goji() throws InterruptedException {
        String userName_o = "autotest";
        String passWord_o = "Goj1tech!";
        String pin_o = "1234";

        GojiLoginPage loginPage_goji = new GojiLoginPage();
        loginPage_goji.goTo();
        loginPage_goji.login(userName_o,passWord_o,pin_o);
    }
}
