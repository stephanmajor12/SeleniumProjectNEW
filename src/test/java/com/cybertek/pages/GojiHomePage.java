package com.cybertek.pages;

import com.cybertek.utility.ConfigReader;
import com.cybertek.utility.Driver;

public class GojiHomePage {
    public void goTo() {
        Driver.getDriver().get(ConfigReader.read("goji.dashboard"));
    }
}
