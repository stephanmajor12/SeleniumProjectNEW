# Day 6 

## Recap on [Day 5](../day05_css_xpath_junit5/ReadMe.md) : 

Introduction to **JUnit5** (**JUnit Jupiter**)
   * Java Unit Testing Framework.
   * Can be used for all layers of testing
   * Setting it up by adding **Maven Dependency**
      ```xml
      <dependency>
          <groupId>org.junit.jupiter</groupId>
          <artifactId>junit-jupiter</artifactId>
          <version>5.8.0</version>
      </dependency>
      ```
   * **Annotations** to put special meaning on methods
       - `@Test`
       - `@BeforeAll`
       - `@AfterAll`
       - `@BeforeEach`
       - `@AfterEach`
       - `and much more later`
   * **Assertions** to test expected outcome
     - **assertEquals**
       - `assertEquals(expectedResult, actualResult)`
     - **assertTrue**
          - `assertTrue(expected_outcome_as_boolean) `
          - `assertTrue(10>9) `
     - **assertFalse**
          - `assertFalse(expected_outcome_as_boolean) `
          - `assertFalse(10<9) `
     - We can use them directly by doing static import as below:
       - `import static org.junit.jupiter.api.Assertions.*;`
     - and much more [here](https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations)
     

## Writing Selenium Test
Writing selenium test is nothing more than taking some action on browser and asserting actual result against expected result just like any other type of test. 

Here is what we did on [day5 Yahoo Practice](../day05_css_xpath_junit5/YahooSearchPageTest.java);

We started with Simple yahoo home page test to check title:
```java
@Test
public void testYahooSearchHomePageTitle(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://search.yahoo.com/");
    String expectedTitle = "Yahoo Search - Web Search" ;
    String actualTitle = driver.getTitle();
    // do static import, so you can do this
    assertEquals(expectedTitle,actualTitle ) ;
}
```
Then we start adding more test like this to check search result title:
```java
@Test
public void testYahooSearchResultPageTitle(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://search.yahoo.com/");
    // identify search box and enter selenium , and hit Enter key on keyboard
    WebElement searchBox = driver.findElement(By.xpath("//input[@name='p']"));
    // we can simulate keystroke using Keys.SELECT_ONE_OF_THE_OPTION
    // In this case we are typing selenium and hitter enter
    searchBox.sendKeys("Selenium" + Keys.ENTER);
    String actualTitle = driver.getTitle() ;
    // assert the title starts with Selenium
    assertTrue( actualTitle.startsWith("Selenium")  );
}
```

### Using Lifecycle Annotation to Reduce duplicate
As we observed in two tests above , even though it works , the problem already started emerging.

There are already many lines of codes are repeating.
like this one
```java
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
```
and After each test we are closing the browser using 
```java
    driver.quit();
```
If we have to write more tests we still have to repeat all `WebDriver` setup in all tests and close the browser at the end of each test ourselves.

As we learned lifecycle annotations in **JUnit5** , 
We can run certain code once before all test or each test , after all test or each test.

So we moved driver setup code into `@BeforeEach` (optionally move `WebDriverManager` set up into `@BeforeAll`)

Optionally, we also added `implicitWait` to instruct driver to wait maximum 10 seconds before throwing `NoSuchElement` Exception.
```java
@BeforeEach
public void setupWebDriver(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    //set maximum wait time for driver to find element
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
}
```

Added Closing browser part of the code into `@AfterEach` method
```java
@AfterEach
public void closeBrowser(){
    driver.quit();
}
```

Since now `driver` need to accessible in all instance methods :
```java
public class YahooSearchTest {
    WebDriver driver;
    // the rest omitted.
}
```
We have moved `WebDriver driver` declaration from inside method to Instance variable on the top, so it can be accessible in all instance methods.  

Now When we add new @Test method , we will not have to repeat the setup part in same class.

### Checking if Element `isDisplayed()`
[Here in this class](IsDisplayedPracticeTest.java),
we have used this to simplify our Test Class

Some Elements on the page can be hidden from the display on the page by using common css properties like 
- `hidden` attribute on element 
- `style="display:none;`

Elements with these attributes are not visible.

Selenium `WebElement` have a method called `isDisplayed()` to check if an element is visible or not.

Here is the code we tried for error validation elements on [registration form page](http://practice.cybertekschool.com/registration_form)
```java
driver.get("http://practice.cybertekschool.com/registration_form");
String xpathForErrorMsg = "//small[.='first name must be more than 2 and less than 64 characters long']" ;
// this element exists in html but it's hidden not displayed on the screen
// it will only display when there is an input validation error like less 2 or more than 64 chars in this case
// so isDisplayed method will return false
WebElement strLengthErrorElement = driver.findElement(By.xpath(xpathForErrorMsg)) ;
System.out.println("strLengthErrorElement.isDisplayed() = " 
                    + strLengthErrorElement.isDisplayed());
//assertTrue( ! strLengthErrorElement.isDisplayed() ) ;
assertFalse(  strLengthErrorElement.isDisplayed() );

// now we are entering something invalid : less than 2 character
WebElement firstNameField = driver.findElement(By.name("firstname")) ;
firstNameField.sendKeys("a");
// now the error message should show up
System.out.println("strLengthErrorElement.isDisplayed() = " 
                        + strLengthErrorElement.isDisplayed());
assertTrue( strLengthErrorElement.isDisplayed() );
```


### Using Inheritance to further Reduce Duplicate

Our Test methods has improved a lot, we do not have to repeat setup in same class.

However, whenever we add new class for new tests, we still have to set up the driver and close the browser.

So we extracted out the common part to `TestBase` class so all selenium tests can inherit from
```java
public abstract class TestBase {
    // we want only subclasses of TestBase have access to this.
    protected WebDriver driver ;
    // setting up all driver here directly in @BeforeEach method
    @BeforeEach
    public void setupWebDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
    }
    // quit browser after each test
    @AfterEach
    public void closeBrowser(){
        driver.quit();
    }
}
```

So now whenever we start new Test class , we can simply extend `TestBase` to have all setup and teardown of driver. 

Our [YahooSearchTest](YahooSearchTest.java) class become much simpler: 
```java
package com.cybertek.tests.day06_driver_utility_testbase_alerts_tables;

import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.junit.jupiter.api.Assertions.*;

public class YahooSearchTest extends TestBase {
    
    @Test
    public void testYahooSearchHomePage(){
        driver.get("https://search.yahoo.com") ;
        String expectedTitle = "Yahoo Search - Web Search" ;
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testYahooSearchResultPage() throws InterruptedException {
        // navigate to yahoo search and enter selenium and hit enter
        driver.get("https://search.yahoo.com") ;
        driver.findElement(By.name("p")).sendKeys("selenium" + Keys.ENTER);
//        assert title starts with selenium
        Thread.sleep(1000); // quick 1 second wait before getting title

        System.out.println("driver.getTitle() = " + driver.getTitle());
        assertTrue(  driver.getTitle().startsWith("selenium") );
    }
}

```

### Creating Utility Class [WebDriverFactory](../../utility/WebDriverFactory.java)

Everything to write test easily for next test class has been established , all we have to do to write new Test class is just extending **TestBase**.

So now It's time to think cross browser. 
How would I run my test in **FireFox** browser?

Do we need to change driver setup to Firefox in **TestBase** class?

Yes, however , wouldn't it be nice if we just have one utility method we can call by passing browser name and let it automatically get correct driver setup for us?

We can create : 
- utility class under new package `com.cybertek.utility` 
- give a name `WebDriverFactory` 
- create a single method called getDriver that accept browser name as parameter.
- so we can get driver easily by doing as below
  - get chrome setup 
    - `driver = WebDriverFactory.getDriver("chrome");`
  - get firefox setup
    - `driver = WebDriverFactory.getDriver("firefox");`
  - get Microsoft Edge setup (optionally)
    - `driver = WebDriverFactory.getDriver("edge");`

[Here](../../utility/WebDriverFactory.java) is what we did : 
```java
public class WebDriverFactory {
    /**
     * A method to return WebDriver object according to browserName
     * @param browserName
     * @return WebDriver object of browser type
     */
    public static WebDriver getDriver(String browserName){

        WebDriver driver ;
        // for case-insensitive check we checked for lowercase
        switch (browserName.toLowerCase() ){
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
                // other browsers omitted
            default:
                driver = null ;
                System.out.println("UNKNOWN BROWSER TYPE!!! " + browserName);
        }
        // can be moved inside case to avoid NullPointerException when wrong browser type provided
        driver.manage().window().maximize();

        return driver ;
    }

}
```

Now we can simply call the method in `TestBase` as below
```java
public abstract class TestBase {
    // we want only subclasses of TestBase have access to this.
    protected WebDriver driver ;
    // setting up all driver stuff here directly in @BeforeEach method
    @BeforeEach
    public void setupWebDriver(){
        driver = WebDriverFactory.getDriver("firefox");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
    }
    @AfterEach
    public void closeBrowser(){
        driver.quit();
    }
}
```

### Synchronization (waiting for certain elements)

Synchronization is an important subject in Selenium where we wait until certain element appear or certain condition is met.

There are 3 ways we can achieve this: 
- Thread.sleep(8000)
  - wait 8 seconds no matter what 
- **implicitWait**
  - `driver.manage().timeouts().implicitWait(8,TimeUnit.SECONDS)`
  - instruct driver to wait maximum 8 seconds before throwing exception
  - it will move on if element is found earlier than that
  - better strategy than `Thread.sleep(8000)`
- **ExplicitWait**
  - more on this later.

Here is the example code we did in class for element that show up slower to cause driver to `NoSuchElementException`.

We have initially tried to use `Thread.sleep(5000)` to fix it. 

Then added `driver.manage().timeouts().implicitWait(10,TimeUnit.SECONDS)` 
to instruct driver to wait max 10 seconds before timeout and the test has passed.

### Alert (Popups)
There are two types of pop-ups used in websites

- HTML pop-ups :
    - created with regular html elements
    - you can inspect element and identify as usual
    - [example here](https://getbootstrap.com/docs/5.0/components/modal/#live-demo)


- JavaScript pop-ups :
    - result of javascript code
    - It is not in html and can not be inspected
    - can not use find element methods
    - [example here](http://practice.cybertekschool.com/javascript_alerts)


Here are 3 types of JavaScript alerts which can be troublesome for automation<br>
Selenium have special interface called Alert to handle javascript popups
- alert box (only option is `OK` button)
  -   ![JSAlert](https://user-images.githubusercontent.com/59104509/133842768-4aa022f4-1144-4dfc-b20a-5ab714f89e7a.jpg)
- confirmation box (2 options : `OK` button and `Cancel` Button)
  -   ![JSConfirmation](https://user-images.githubusercontent.com/59104509/133842769-6f9d810d-0613-46f1-b26c-e6720ce65ec6.jpg)
- prompt (3 options : `Enter text`, `OK` button and `Cancel` Button)
  - ![JSPrompt](https://user-images.githubusercontent.com/59104509/133842771-3fde0927-1d6d-40fa-a467-938293af8afd.jpg)

First we need to get a hold of that alert by called `driver.switchTo().alert()` method.

It will return `Alert` instance that point to current popup.

You can optionally save it as below
```java
Alert alertObj = driver.switchTo().alert();
```
Or call 4 available methods directly according to what type of popup:
- `accept()`
- `dismiss()`
- `sendKeys("hello")`
- `getText()`

Here is how it works :  

- Click `Ok` button
    - `driver.switchTo().alert().accept()`
    - or `alertObj.accept();`
- Click `Cancel` button
    - `driver.switchTo().alert().dismiss()`
    - or `alertObj.dismiss();`
- Enter text and Click `ok` button
    - `driver.switchTo().alert().sendKeys("hello")`
    - `driver.switchTo().alert().accept()`
- Get the `text` of the alert box
    - `driver.switchTo().alert().getText()`

Here is the [full code example](AlertPracticeTest.java) we have tried in class on [practice site](http://practice.cybertekschool.com/javascript_alerts).
```java
driver.get("http://practice.cybertekschool.com/javascript_alerts");
// Alert interface from Selenium is used to deal with alters
// your driver object can switchTo the alert and take action on it
        
driver.findElement(By.xpath("//button[.='Click for JS Alert']")).click();

System.out.println("getting the text of alert" 
                    + driver.switchTo().alert().getText()   );
driver.switchTo().alert().accept();
//        Alert alertObj =  driver.switchTo().alert() ;
//        alertObj.accept();

driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();
// this will click on dismiss button on the confirmation box
//driver.switchTo().alert().dismiss();
// this will click on dismiss button on the confirmation box
driver.switchTo().alert().accept(); 

driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();
// you will not see it's being entered like regular textbox but it's actually being entered.
// this is how we can enter text into prompt
driver.switchTo().alert().sendKeys("Hello"); 
driver.switchTo().alert().accept(); // click ok
```

### XPath Practice with Index
There will be times we want to get the element by its position index when we have similar items. 

Perfect example for this is web table with rows and columns 

Here is the practice we did.
```java
driver.get("http://practice.cybertekschool.com/tables");
 //table[@id='table1']//tr[4]/td[2]
// print the text of 4th column and second row
WebElement cell42 = driver.findElement(By.xpath("//table[@id='table1']//tr[4]/td[2]"));
System.out.println("cell42.getText() = " + cell42.getText());
assertEquals("Tim" ,cell42.getText() ) ;
//table[@id='table1']//td[ text()='Jason']
WebElement cellWithTextJason = driver.findElement(By.xpath("//table[@id='table1']//td[ text()='Jason']"));
System.out.println("cellWithTextJason text = " + cellWithTextJason.getText());
assertEquals("Jason" ,cellWithTextJason.getText() ) ;
```