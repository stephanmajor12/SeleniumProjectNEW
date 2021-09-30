# Day 11 Note :

## Recap on [Day 10](../day10_driver_method_practice_properties/ReadMe.md)
- Using `Driver` utility class
  - `Driver.getDriver()` to get single Driver instance
  - Use it in `TestBase` class to ensure same `driver` used everywhere
  - Use it in `WebOrderUtil` class to remove unnecessary method parameter
- Adding `closeBrowser` method into `Driver` class
  - `Driver.getDriver()` method only return new instance when driver is null
  - Selenium can not reuse a `driver` already `quited`
  - `@Afrer` each method of `TestBase` quit the browser after each test
  - In order to open new browser window it's necessary to make driver = null after each test.
  - We added `closeDriver()` method 
    ```java
     public static void closeBrowser(){
        // check if we have WebDriver instance or not
        // basically checking if obj is null or not
        // if not null
            // quit the browser
            // make it null , because once quit it can not be used
        if(obj != null ){
            obj.quit();
            // so when ask for it again , it gives us not quited fresh driver
            obj = null ;
        }
     }
    ```
  - Add this method call to `TestBase` class `@AfterEach`
     ```java
     @AfterEach
     public void closeBrowser(){ // you can call it anything you want

        //driver.quit();
        // quit the browser + make it null, so we can get new one when ask for it again
        Driver.closeBrowser();
     }
     ```
  - Now, we will not have any issue running multiple tests together 
  - each test will run in its own browser window.  
- Externalizing Test Data 
  - any data we used in the source code are known as test data
  - there are important data like url,credentials , browser type and so on
  - it's better to separate such data from source code
  - so it can be easy to maintain and re-used.
  - Storing data into `properties` file and reading from it.
    - a file with `.properties` extension
    - simple create new file with any name for example `config.properties`
    - store key=value pair per line separated by `=` or `:` or ` `
    - ```properties
      hello=world
      url=https://google.com
      weborder_url=http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx
      weborder_username=Tester
      weborder_password=test
      browser=chrome
      ```
    - We can load this file content using `java.util.Properties` class
      ```java
      Properties properties = new Properties();
      ```
    - It has `load` method that accept `FileInputStream`
    - `FileInputStream` (from `java.io` package) can be used to open connection to properties file.
      ```java
      FileInputStream in = new FileInputStream("config.properties");
      // then load it into Properties object
      properties.load(in);
      // close the connection after load 
      in.close()
      ```
    - all above method calls declare to throw `IOException` so wrap it around with `try catch` block
    - Read property value by key using `getProperties("key here")` method
      ```java
      String theValue = properties.getProperty("key goes here")
      ```
  - creating Utility class for reading from `config.properties`
    - instead of repeating above steps for each read, we can create a utility
    - We have created [`ConfigReader`](../../utility/ConfigReader.java)
      - It will load the `config.properties` file into `Properties` object once
      - It provide access to property value using method `read("key here")`
      - ```java
         String url = ConfigReader.read("weborder_url") ;
         String username = ConfigReader.read("weborder_username") ;
         String password = ConfigReader.read("weborder_password") ;
        ```
        
## Updating `Driver` Class to get browser type from `config.properties`

Till this moment , we have been holding out on providing option to use different browser.

Now we can use `ConfigReader` utility to get browser name directly from `config.properties`.
It will require zero code change and allow us to 
run from different browser just by changing browser property value from properties file.

```java
    /**
     * Return obj with only one WebDriver instance
     * @return same WebDriver if exists , new one if null
     */
    public static WebDriver getDriver(){
        // read the browser type you want to launch from properties file
        String browserName = ConfigReader.read("browser") ;

        if(obj == null){
            // according to browser type set up driver correctly
            switch (browserName ){
                case "chrome" :
                    WebDriverManager.chromedriver().setup();
                    obj = new ChromeDriver();
                    break;
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    obj = new FirefoxDriver();
                    break;
                // other browsers omitted
                default:
                    obj = null ;
                    System.out.println("UNKNOWN BROWSER TYPE!!! " + browserName);
            }
            obj.manage().window().maximize();
            return obj ;
        }else{
//            System.out.println("You have it just use existing one");
            return obj ;
        }
    }
```

Now if we want to run any test in `firefox` browser , 
we can just change below browser property in `config.properties` as below 
```properties
browser=firefox
```

**Neat !** 

## Java Faker Library for Random Fake Data
[JavaFaker](https://github.com/DiUS/java-faker) is
a library aim to provide random fake data of different kinds and domains
for development or testing or any other purposes.

Sometimes, our tests need some data that does not really need to be stored.

In the meantime, it's also not very ideal to just use same data all the time.
We can use javafaker library for generating random test data.

In order to get started with javafaker we need to first add maven dependency.

```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
```

Then in java code ,
```java
Faker faker = new Faker();

String name = faker.name().fullName(); // Miss Samanta Schmidt
String firstName = faker.name().firstName(); // Emory
String lastName = faker.name().lastName(); // Barton

String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449

System.out.println("faker.book().title() = " + faker.book().title());
System.out.println("faker.book() = " + faker.book().publisher());
System.out.println("faker.gameOfThrones().character() = " 
                        + faker.gameOfThrones().character());
System.out.println("faker.phoneNumber().phoneNumber() = " 
                        + faker.phoneNumber().cellPhone());
System.out.println("faker.idNumber().ssnValid() = " 
                        + faker.idNumber().ssnValid());
System.out.println("faker.number().numberBetween(1000000000L,5000000000L) = " 
                        + faker.number().numberBetween(1000000000L, 5000000000L));
System.out.println("faker.numerify(\"###-###-####\") = " 
                        + faker.numerify("###-###-####"));
System.out.println("faker.chuckNorris().fact() = " 
                        + faker.chuckNorris().fact());

```

It will generate random fake data in each method call.

This is the sample result from above code

```text
faker.book().title() = The Wind's Twelve Quarters
faker.book().author() = Chad Corkery
faker.gameOfThrones().character() = Ayra Stark
faker.phoneNumber().phoneNumber() = 1-899-529-8120
faker.idNumber().ssnValid() = 006-77-7959
faker.number().numberBetween(1000000000L,5000000000L) = 1834322790
faker.numerify("###-###-####") = 950-169-9832
faker.chuckNorris().fact() = Chuck Norris doesn't need a debugger, he just stares down the bug until the code confesses.

```


Here is the [full list of items](https://github.com/DiUS/java-faker#fakers) you can generate according to the documentation. 


    
 

## Page Object Model and `PageFactory`

Page Object is a Design Pattern which has become popular in test automation 
for enhancing test maintenance and reducing code duplication.

![Page Object Model](https://user-images.githubusercontent.com/59104509/135579454-b72117c6-821b-4d09-9f14-530541874b8d.png)

A page object is an object-oriented class that serves as an interface to a page of your AUT. 
The tests then use the methods of this page object class 
whenever they need to interact with the UI of that page. 
The benefit is that if the UI changes for the page, 
the tests themselves don’t need to change, 
only the code within the page object needs to change. 
Subsequently, all changes to support that new UI are located in one place.

The Page Object Design Pattern provides the following advantages:

- There is a clean separation between test code and page specific code 
  - such as locators (or their use if you’re using a UI Map) and layout.
- There is a single repository for the operations offered by the page 
  - rather than having these codes scattered throughout the tests.

In Page Object Model, 
we can model web application by page and add all locators and methods related to that page into its own corresponding Page class.

For example :
- For Login page we can create LoginPage class
  - add all locators for username , password , login button , error messages and so on
  - add login method(s)
  - error checking methods
- For All order page , we can create AllOrderPage class
- Sometimes multiple pages share common areas like nav bar , side bar , footers
  - We can create separate class to reflect those common area instead of repeating in each page class

We have built `WebOrderUtil` class and created methods for re-usability and maintainability 
and yet it can be organized in Page Object Model way to organize elements and methods into their own page class.
Finding elements can be simpler using annotations and `PageFactory` class.

`PageFactory` is a selenium class 
that support the Page Object Model along with `@FindBy` `@FindBys` `@FindAll` annotations.

Here is the simple example of login page class. 

```java
package com.cybertek.pages;
import com.cybertek.utility.ConfigReader;
import com.cybertek.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WLoginPage {
    // instructing selenium to find using id
    @FindBy(id = "ctl00_MainContent_username")
    public WebElement usernameField;
    // instructing selenium to find using name
    @FindBy(name = "ctl00$MainContent$login_button")
    public WebElement passwordField;
    // instructing selenium to find using css selector
    @FindBy(css = "#ctl00_MainContent_login_button")
    public WebElement loginButton;
    // instructing selenium to find using xpath
    @FindBy(xpath = "//span[.='Invalid Login or Password.']")
    public WebElement errorMsg ;
    // Now we need instruct selenium to start looking for elements
    // when this constructor is called
    // This is no arg constructor of this class
    public WLoginPage(){
       // PageFactory is a selenium class that support Page Object Model
        // It has method called initElements
        // once it's called , it will locate all the element
        // specified using @FindBy annotation with locator
        // initElements accept 2 arguments ,
        // WebDriver instance and Page class instance (this) means current instance of this class.
        PageFactory.initElements(Driver.getDriver() , this );
    }
    /**
     * Create a method to goTo
     * accept no param just navigate to login page
     * use config.properties for url
     */
    public void goTo(){
        Driver.getDriver().navigate().to( ConfigReader.read("weborder_url")    );
    }
    /**
     * Login with parameters
     * @param username username
     * @param password password
     */
    public void login(String username, String password ){
        // you can access directly using userNameField or this.userNameField
        this.userNameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.loginButton.click();
    }
    /**
     * Check error message is present if wrong credentials provided
     * loginErrorMsgPresent  simple return the result of isDisplayed method call
     */
     public boolean loginErrorMsgPresent(){
         return  this.errorMsg.isDisplayed()  ;
     }
}
```

Now in test class we can simply use it as below 

```java
public class WebOrderPageObjectModelTest extends TestBase {
    @Test
    public void testLoginWithWrongCredentials(){
        // instantiating WLoginPage class
        WLoginPage loginPage = new WLoginPage();
        // use goTo method to navigate to the app login page
        loginPage.goTo();
        // use login method
        loginPage.login("Bla", "test");
        // assert the error message displayed
        loginPage.login("BLA" ,"test");
    }
}
```

As you can see above, 
the test code is much more readable and locators and methods are inside Page class.

**PageFactory** is a selenium class that support Page Object Model.

We have used `PageFactory` method `initElements` that accept 2 parameters
- `WebDriver` instance
- Page class instance using `this` keyword (quick java refreshed below)
  - outside class, you call your object `loginPage` or anything
  - inside class, you refer the object using `this` keyword

Once it's called , 
it will locate all the elements specified using `@FindBy` annotation with locators.

```java
    PageFactory.initElements(Driver.getDriver() , this );
```

We will use it in constructor of each and every Page classes we create in the future. 

As practice, we have created 3 more Page classes 
- [`WAllOrderPage`](../../pages/WAllOrderPage.java) for all order page 
- [`WCommonArea`](../../pages/WCommonArea.java) for areas that common for all the pages
- [`WOrderPage`](../../pages/WOrderPage.java) for Order page with form

Common steps we followed are : 
1. Create page class according to the current page you are working on
2. Add instance variables with type `WebElement` with good name to reflect element
   ```java
   public WebElement header ;
   ```
3. Add instance variable with type `List<WebElement>` if you wanted to get more than 1 element
   ```java
     public List<WebElement> allHeaderCells ;
   ```
4. Add `@FindBy` annotations on top of instance filed and use 8 locators we learned so far
   1. `@FindBy(id= "someId")`
   2. `@FindBy(name= "someName")`
   3. `@FindBy(tagName= "someTag")`
   4. `@FindBy(className= "someClassName")`
   5. `@FindBy(linkText= "someLinkText")`
   6. `@FindBy(partiallinkText= "someLinkText")`
   7. `@FindBy(css= "someCSS")`
   8. `@FindBy(xpath= "someXpath")`
5. Add No Arg Constructor and call `PageFactory` method `initElements`
   ```java
    public WAllOrderPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
   ```
6. Create re-usable methods by using all the elements located above.
7. Use it in Test class by instantiating Page classes
    ```java
     @Test
      public void testAllOrderPage(){
      
        WLoginPage loginPage = new WLoginPage();
        loginPage.goTo();
        loginPage.login("Tester","test");
        // --- here we logged in
      
        // now we are at all order page
        WAllOrderPage allOrderPage = new WAllOrderPage();
        // assert the header element is displayed
        assertTrue( allOrderPage.header.isDisplayed()  );
      
        allOrderPage.checkAllButton.click();
        BrowserUtil.waitFor(2);
      
        allOrderPage.unCheckAllButton.click();
        BrowserUtil.waitFor(2);
      
        // print out the size of headerCells
        System.out.println("allOrderPage.headerCells.size() = "
              + allOrderPage.headerCells.size());
        assertEquals(13, allOrderPage.headerCells.size() );

      }
    ```
8. Repeat the process according to the test cases you are working on. 


## Homework 
Redo WebOrder util homework by following page object model, use java faker along the way for random data. 

