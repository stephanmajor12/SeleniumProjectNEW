package com.cybertek.pages;

import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.ConfigReader;
import com.cybertek.utility.Driver;
import net.bytebuddy.matcher.ElementMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import sun.jvm.hotspot.debugger.Page;

import java.util.concurrent.TimeUnit;

import static net.bytebuddy.matcher.ElementMatchers.is;

public class SelectPatient {
    @FindBy(id="profileButton")
    private WebElement profButton;
    @FindBy(id="inputBox_voiceArea")
    private WebElement inputBox_va;
    @FindBy(id="patientSelect1")
    private WebElement patientSelect1;
    @FindBy(id="switchPatients")
    private WebElement switchPatientID;

    public SelectPatient() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void goTo(){
        Driver.getDriver().get(ConfigReader.read("gojiProfile.url"));
    }

    public void SelectPatient(String patientName) throws InterruptedException {
        Driver.getDriver().get(ConfigReader.read("gojiProfile.url"));
        BrowserUtil.waitFor(5);
        inputBox_va.click();
        inputBox_va.sendKeys(patientName);
        TimeUnit.SECONDS.sleep(2);
        patientSelect1.click();
        TimeUnit.SECONDS.sleep(1);
    }
    public void CheckPatient(String check,String item) throws InterruptedException {
        Driver.getDriver().findElement(By.id("controlled-tab-example-tab-profile")).click();
        BrowserUtil.waitFor(10);
        assertThat(Driver.getDriver().findElement(By.id(check)).getText(), is(item));
        System.out.println(check + " " + item);
    }
    public void SwitchPatient(String patientName1,String patientName2) throws InterruptedException {
        BrowserUtil.waitFor(2);
        SelectPatient(patientName1);
        switchPatientID.click();
        Driver.getDriver().get(ConfigReader.read("gojiProfile.url"));
        BrowserUtil.waitFor(2);
        SelectPatient(patientName2);
        switchPatientID.click();
    }
    public void deSelectPatient() throws InterruptedException {
        BrowserUtil.waitFor(2);
        switchPatientID.click();
        Driver.getDriver().get(ConfigReader.read("goji.dashboard"));
    }

    private void assertThat(String text, ElementMatcher.Junction<Object> objectJunction) {
        text = objectJunction.toString();
    }
}
