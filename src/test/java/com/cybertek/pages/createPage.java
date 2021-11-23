package com.cybertek.pages;

import com.cybertek.utility.BrowserUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class createPage {
    //create appointment id
    @FindBy(id="newAppointmentButton")
    private WebElement appointmentButton;
    @FindBy(id="checkButton")
    private WebElement checkButton;
    @FindBy(id="0")
    private WebElement timeClick;

    //create prescription / create note
    @FindBy(id="noteButton")
    private WebElement noteButton;
    @FindBy(id="prescription-input")
    private WebElement prescriptionInput;
    @FindBy(id="prescriptionButton")
    private WebElement prescriptionButton;
    @FindBy(xpath="/html/body/div/div[2]/div/div[2]/div[2]/div/button/span")
    private WebElement notSureXpath;

    public void createAppointment() {
        appointmentButton.click();
        BrowserUtil.waitFor(5);
        timeClick.click();
        BrowserUtil.waitFor(2);
        checkButton.click();
    }
    public void createNotes() {
        noteButton.click();
        prescriptionInput.sendKeys("Test Note");
        prescriptionInput.sendKeys(Keys.ENTER);
        BrowserUtil.waitFor(1);
        prescriptionInput.sendKeys("Objective");
        prescriptionInput.sendKeys(Keys.ENTER);
        BrowserUtil.waitFor(1);
        prescriptionInput.sendKeys("Assessment");
        prescriptionInput.sendKeys(Keys.ENTER);
        BrowserUtil.waitFor(1);
        prescriptionInput.sendKeys("Plan");
        prescriptionInput.sendKeys(Keys.ENTER);
        BrowserUtil.waitFor(1);
        prescriptionInput.sendKeys("Summary");
        prescriptionInput.sendKeys(Keys.ENTER);
        BrowserUtil.waitFor(1);
        notSureXpath.click();
    }
    public void createPrescription() {
        prescriptionButton.click();
        BrowserUtil.waitFor(1);
        prescriptionInput.click();
        BrowserUtil.waitFor(1);
        prescriptionInput.sendKeys("");
        prescriptionInput.sendKeys(Keys.ENTER);
        prescriptionInput.sendKeys("");
        prescriptionInput.sendKeys(Keys.ENTER);
        BrowserUtil.waitFor(3);
    }
}

