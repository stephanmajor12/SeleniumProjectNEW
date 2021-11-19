package com.cybertek.pages;

import com.cybertek.utility.ConfigReader;
import com.cybertek.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class GoogleLoginPage {
    @FindBy(id="identifierId")
    private WebElement usernameBox;
    @FindBy(id="identifierNext")
    private WebElement nextButton_u;
    @FindBy(xpath="//input[@class='whsOnd zHQkBf']")
    private WebElement passwordBox;
    @FindBy(id="passwordNext")
    private WebElement nextButton_p;

    public GoogleLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    public void goTo() {
        Driver.getDriver().get(ConfigReader.read("google.login"));
    }
    public void login(String username, String password) throws InterruptedException {
        usernameBox.sendKeys(username);
        nextButton_u.click();
        TimeUnit.SECONDS.sleep(2);
        passwordBox.sendKeys(password);
        nextButton_p.click();
    }

}
