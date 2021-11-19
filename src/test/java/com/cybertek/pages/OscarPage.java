package com.cybertek.pages;

import com.cybertek.utility.ConfigReader;
import com.cybertek.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OscarPage {
    @FindBy(name="keyword")
    private WebElement searchBox;
    @FindBy()
    private WebElement searchButton;

    public OscarPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    public void goTo_home() {
        Driver.getDriver().get(ConfigReader.read("oscar.patientScreen"));
    }
    public void searchPatient(String patientName) {
        searchBox.sendKeys(patientName);
    }
    public void goTo_notes() {
        Driver.getDriver().findElement(By.linkText("E-Chart")).click();
        Driver.getDriver().switchTo().window("win7305");
    }
    public void goTo_appointments() {
        Driver.getDriver().findElement(By.linkText("Appointment History")).click();
    }
}
