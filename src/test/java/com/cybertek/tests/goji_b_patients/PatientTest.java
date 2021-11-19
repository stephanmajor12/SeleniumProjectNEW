package com.cybertek.tests.goji_b_patients;

import com.cybertek.pages.GojiHomePage;
import com.cybertek.pages.SelectPatient;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

public class PatientTest extends TestBase {
    @Test
    public void testSwitchPatient() throws InterruptedException {
        GojiHomePage myGojiHome = new GojiHomePage();
        myGojiHome.goTo();

        SelectPatient myPatient1 = new SelectPatient();
        myPatient1.goTo();

        myPatient1.SwitchPatient("wren","davids");
    }
    @Test
    public void testCheckPatient() throws InterruptedException {
        GojiHomePage myGojiHome = new GojiHomePage();
        myGojiHome.goTo();

        SelectPatient myPatient1 = new SelectPatient();
        myPatient1.goTo();

        myPatient1.SelectPatient("wren");
        myPatient1.CheckPatient("AGE","43");
    }
}

