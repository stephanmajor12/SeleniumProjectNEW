# Day 5 Afternoon

## JUnit5 (JUnit Jupiter)

**JUnit** is a simple framework to write repeatable tests.
<br> It is an instance of the xUnit architecture for unit testing frameworks.

It's a default choice when it comes to writing tests in Java. 

### Features
- Used by developers to write unit test for their code to ensure quality
- Not limited to just unit testing, can be used for other type of testing
- Provides annotations to identify test methods.
- Provides assertions for testing expected results.
- Provides test runners for running tests
- Easy integration with build tools like Maven

Here is the [official documentation page](ttps://junit.org/junit5/docs/current/user-guide/) as reference. 

### Setting up JUnit

JUnit5 or JUnit Jupiter is the latest version.
Currently, the specific version is `5.8.0`.

It's quite easy to set up JUnit5 in Maven project just by adding dependency in `dependencies` section of `pom.xml`

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.0</version>
</dependency>
```

This will give us most of the components needed for our UI tests or other type of test later. 

### Writing Test

Here is simple test example. 

JUnit use annotation (a word start with @) to mark special meaning to a method. 

In order to create a test method :
- Create a class
  - naming convention is `SomethingTest`
- Create a void method with no parameters
  - naming convention is `testSomethingSomething`
- Put `@Test` annotation on top of the method
- Now it will be recognized as test method and can be run.

> From this point on, You will not be using main method!

```java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @Test
    public void testAddition(){
        int num1 = 2 ;
        int num2 = 3 ;
        int expectedResult = 5 ; 
        Assertions.assertEquals( expectedResult, num1+num2);
    }
}
```

### Writing Assertions

**Automated test**  is a test that give you immediate result of pass fail after executing.

And automatic assertions will help to compare expected result with actual result <br>after the test to determine it's pass or fail. 

**Assertions** methods are coming from package <br >`org.junit.jupiter.api.Assertions`

You can do static import to directly use those methods :

`import static org.junit.jupiter.api.Assertions.*`

Common Assertions methods are :
- `assertEquals`
    - `assertEquals(expectedResult, actualResult)`
- `assertTrue`
    - `assertTrue(expected_outcome_as_boolean) `
    - `assertTrue(10>9) `
- `assertFalse`
   - `assertFalse(expected_outcome_as_boolean) `
   - `assertFalse(10<9) `
- and much more

## LifeCycle Annotations

It's possible to run certain code run once before and after all test or each test.

These are the annotations available : 
- `@BeforeAll` run once before all test
- `@BeforeEach` run before each test
- `@AfterEach` run after each test
- `@AfterAll` run once after all test

```java
  @BeforeAll
  public static void init(){
      System.out.println("@Before All Run once right before all tests");
  }
  @AfterAll
  public static void cleanup(){
      System.out.println("@AfterAll All Run once right after all tests");
  }

  @BeforeEach
  public void setup(){
      System.out.println("@BeforeEach All Run once right before all tests");
  }
  @AfterEach
  public void teardown(){
      System.out.println("@AfterEach All Run once right after all tests");
  }

  @Test
  public void test1(){
      System.out.println("This is test method 1 ");
  }
```
<br>

- `@BeforeAll` run once before all test
- `@BeforeEach` run before each test
- `test1` is running
- `@AfterEach` run after each test
- `@BeforeEach` run before each test
- `test2` is running
- `@AfterEach` run after each test
- `@AfterAll` run once after all test

[Here is the Full code](LifecycleAnnotationDemoTest.java) for lifecycle annotations. 
```java
package com.cybertek.tests.day05_css_xpath_junit5;

import org.junit.jupiter.api.*;

public class LifecycleAnnotationDemoTest {

    @BeforeAll
    public static void init(){
        System.out.println("@BeforeAll run once before all test");
    }

    @BeforeEach
    public void setup(){
        System.out.println("@BeforeEach run before each and every test");
    }

    @Test
    public void test1(){
        System.out.println("test1 method is running");
    }

    @Test
    public void test2(){
        System.out.println("test2 method is running");
    }

    @AfterEach
    public void teardown(){
        System.out.println("@AfterEach run after each and every test");
    }

    @AfterAll
    public static void cleanup(){
        System.out.println("@AfterAll run once after all test");
    }

}
```
[Here is the Full code](YahooSearchPageTest.java) for trying out running your first Selenium JUnit5 Test. 
```java
package com.cybertek.tests.day05_css_xpath_junit5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class YahooSearchPageTest {

    WebDriver driver ;

    @BeforeAll
    public static void setUpDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupWebDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void closeBrowser(){
        driver.quit();
    }

    @AfterAll
    public static void teardown(){
        System.out.println("@After all , nothing to do here");
    }

    // write 2 tests :
    // testYahooSearchHomePageTitle
    //
    //    test when you navigate to yahoo search page
    //        the title should be "Yahoo Search - Web Search"

    @Test
    public void testYahooSearchHomePageTitle(){

        driver.get("https://search.yahoo.com/");

        String expectedTitle = "Yahoo Search - Web Search" ;
        String actualTitle = driver.getTitle();

        // do static import, so you can do this
        // import static org.junit.jupiter.api.Assertions.*;
        assertEquals(expectedTitle,actualTitle ) ;


    }

    // test method name : testYahooSearchResultPageTitle
    // test navigate to yahoo page
    // and search for Selenium
    // the page title should start with selenium
    @Test
    public void testYahooSearchResultPageTitle(){

        driver.get("https://search.yahoo.com/");

        // identify search box and enter selenium , and hit Enter key on keyboard
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='p']"));
        // we can simulate keystroke using Keys.SELECT_ONE_OF_THE_OPTION
        // In this case we are typing selenium and hitter enter
        searchBox.sendKeys("Selenium" + Keys.ENTER);

//        String expectedTitleStartWith = "Selenium" ;
        String actualTitle = driver.getTitle() ;

        // assert the title starts with Selenium
        assertTrue( actualTitle.startsWith("Selenium")  );
        
    }
}
```
