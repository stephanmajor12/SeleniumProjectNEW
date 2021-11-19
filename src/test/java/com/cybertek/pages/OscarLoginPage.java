package com.cybertek.pages;

import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.ConfigReader;
import com.cybertek.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class OscarLoginPage {
    @FindBy(id="username")
    private WebElement userName_o;
    @FindBy(id="password")
    private WebElement passWord_o;
    @FindBy(id="pin")
    private WebElement pin_o;
    @FindBy(name="submit")
    private WebElement submitButton;

    public OscarLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void login(String username,String password, String pin) throws InterruptedException {
        Driver.getDriver().get(ConfigReader.read("goji.oscar"));
        TimeUnit.SECONDS.sleep(1);
        userName_o.sendKeys(username);
        passWord_o.sendKeys(password);
        pin_o.click();
        pin_o.sendKeys(pin);
        BrowserUtil.waitFor(2);
        submitButton.click();
    }

}
