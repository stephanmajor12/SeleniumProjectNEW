# Day 12 Morning Note :

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

## In-class Practice
We practice Page object model with Google Search and Library app: 
- Created [GoogleHomePage](../../pages/GoogleHomePage.java) class
  - WebElement Fields : 
    - `searchBox`
    - `searchBtn`
  - Methods : 
    - `void goTo()`
    - `void searchKeyword( String keyword)`
    - `boolean isAt()`
- Created [GoogleSearchPage](../../pages/GoogleResultPage.java) class
  - WebElement Fields :
    - `WebElement searchResultCount`
    - `List<WebElement> resultLinks`
  - Methods :
      - ` String getResultCountText()`
      - `void printAllSearchResultLinks()`
- Created [LibLoginPage](../../pages/LibLoginPage.java) class
    - WebElement Fields :
        - `usernameBox`
        - `passwordBox`
        - `loginButton`
    - Methods :
        - `void goTo()`
        - `void login(String username, String password)`

Here is Google [Search Test class](GoogleSearchTest.java) we practiced 

```java
package com.cybertek.tests.day12_pom_practice_review;

import com.cybertek.pages.GoogleHomePage;
import com.cybertek.pages.GoogleResultPage;
import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.Driver;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest extends TestBase {
    
    @Test
    public void testGoogleSearch() {

        GoogleHomePage homePage = new GoogleHomePage();
        homePage.goTo();
        // assert you are at the homepage
        assertTrue(   homePage.isAt()     );

        homePage.searchKeyword("SDET JOB");
        // assert the title starts with SDET JOB
        assertTrue( driver.getTitle().startsWith("SDET JOB")    ) ;
//        assertTrue( Driver.getDriver().getTitle().startsWith("SDET JOB")    ) ;
        // possibly wait little here
        GoogleResultPage resultPage = new GoogleResultPage();

        String resultText = resultPage.getResultCountText() ;
        System.out.println("resultText = " + resultText);

        resultPage.printAllSearchResultLinks();
        BrowserUtil.waitFor(4);
    }
}

```

And Here is [Library Test class](LibraryTest.java) we practiced : 

```java
package com.cybertek.tests.day12_pom_practice_review;

import com.cybertek.pages.DashboardPage;
import com.cybertek.pages.LibLoginPage;
import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

public class LibraryTest extends TestBase {

    @Test
    public void testLogin(){

        LibLoginPage loginPage = new LibLoginPage();
        loginPage.goTo();
        loginPage.login("librarian52@library","Sdet2022*" );

        BrowserUtil.waitFor(3);

        DashboardPage dashboardPage = new DashboardPage() ;

        System.out.println("dashboardPage.getBookCountText() = "
                + dashboardPage.getBookCountText());
        System.out.println("dashboardPage.getUserCountText() = "
                + dashboardPage.getUserCountText());
        System.out.println("dashboardPage.getBorrowedBookCountText() = "
                + dashboardPage.getBorrowedBookCountText());
    }


}
```