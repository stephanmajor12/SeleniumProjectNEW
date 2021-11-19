package com.cybertek.pages;

import com.cybertek.utility.ConfigReader;
import com.cybertek.utility.Driver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class GojiLoginPage {
    @FindBy(xpath="//*[@id=\"root\"]/div/button")
    private WebElement googleSignin;
    @FindBy(id="boardSubmitButton")
    private WebElement verifyProv_login;
    @FindBy(name="userName")
    private WebElement usernameBox;
    @FindBy(name="password")
    private WebElement passwordBox;
    @FindBy(name="pin")
    private WebElement pinBox;
    @FindBy(xpath="//*[@id=\"root\"]/div/div[2]/button")
    private WebElement loginButton;

    public GojiLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void goTo() {
        Driver.getDriver().get(ConfigReader.read("goji.login"));
    }

    public void login(String username, String password, String pin) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        googleSignin.click();
        TimeUnit.SECONDS.sleep(4);
        verifyProv_login.click();
        TimeUnit.SECONDS.sleep(2);
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        pinBox.sendKeys(pin);
        loginButton.click();
    }
}