package com.cybertek.tests.goji_d_create;

import com.cybertek.pages.GojiHomePage;
import com.cybertek.pages.SelectPatient;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

public class createTest extends TestBase {
    @Test
    public void testSwitchPatient() throws InterruptedException {
        GojiHomePage myGojiHome = new GojiHomePage();
        myGojiHome.goTo();

        SelectPatient myPatient1 = new SelectPatient();
        myPatient1.goTo();

        myPatient1.deSelectPatient();
        myPatient1.SelectPatient("wren");

    }
}
