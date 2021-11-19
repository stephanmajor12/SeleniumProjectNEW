package com.cybertek.tests.goji_c_backend;

import com.cybertek.pages.GojiHomePage;
import com.cybertek.pages.OscarLoginPage;
import com.cybertek.pages.OscarPage;
import com.cybertek.pages.SelectPatient;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

public class BackendTest extends TestBase {
    @Test
    public void testLoginOscar() throws InterruptedException {
        String userName_o = "autotest";
        String passWord_o = "Goj1tech!";
        String pin_o = "1234";

        OscarLoginPage oscarLogin = new OscarLoginPage();
        oscarLogin.login(userName_o,passWord_o,pin_o);

        OscarPage oscarPage = new OscarPage();
        oscarPage.goTo_home();
        oscarPage.goTo_notes();
    }
}
