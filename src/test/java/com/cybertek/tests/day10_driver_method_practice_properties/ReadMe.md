# Day 10 Note :

## Recap on [Day 9](../day09_explict_wait_methods_singleton_driver/ReadMe.md)
- Recap on `Actions` class `JavascriptExecutor`
- **Synchronization** in selenium
  - **Fixed time**
    - `Thread.sleep(x)`
  - **Global timeout setting** : **Implicit Wait**
    ```java
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    ```
  - **Wait with Custom condition** : **Explicit Wait**
    ```java
    WebDriverWait wait = new WebDriverWait(driver, 6);
    wait.until( ExpectedConditions.titleIs("Dynamic title") );
    ```
    - It will not be affected by `Implicit Wait` and used only once for that specific situation.
    - Here is the [java doc for ExpectedConditions](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) with all pre-defined conditions ready to be used.

- Creating methods for re-usability.
  - Web Order Practice site
- Creating `Driver` utility that generate single driver (inspired by singleton pattern)
  - Create `Driver` class
  - Add `private` no arg constructor
  - Add `private static WebDriver obj` field
  - Add `public static WebDriver getDriver()` getter method
    - check if static field is `null`
    - if `null` -> create new `WebDriver` instance and assign to static field
    - if not -> return same `WebDriver` instance : `ChromeDriver` in this case.

## Singleton [`Driver`](../../utility/Driver.java) Utility Practice
We have created the `Driver` utility class that only generate single `WebDriver` instance.
It's time we put it into use.

### Update [`TestBase`](../../utility/TestBase.java) class to use [`Driver`](../../utility/Driver.java) utility method
For minimum code change to ensure existing test classes work ,
We can simply update `TestBase` class to use `Driver` utility method
instead of `WebDriverFacory` method.

```java
package com.cybertek.utility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
  // we want only subclasses of TestBase have access to this.
  protected WebDriver driver;
  // setting up all driver stuff here directly in @BeforeEach method
  @BeforeEach
  public void setupWebDriver() {
    driver = Driver.getDriver(); //WebDriverFactory.getDriver("chrome");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterEach
  public void closeBrowser() {
    driver.quit();
  }

}
```

### Add `closeBrowser` Method to `Driver` Utility. 

We have created Driver utility to 
provide single WebDriver instances if it's not `null` 
to ensure use same driver in one test and utilities. 

However, after each test we call `quit` method on driver. 
In selenium , we can not reuse a WebDriver instance that is already closed and quited.

Since our `getDriver` method only fire up new WebDriver if it's not null, 
It's necessary to set the value of `driver` to `null` after it quit. 

And we can create a method called `closeDriver` in `Driver` utility class 
to quit the browser and make it null if it's not already null.

Here is the code we added

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

Now, we can call this method in `@AfterEach` section of test base 
to ensure making `driver` to null after quitting. 

```java
  @AfterEach
  public void closeBrowser(){ // you can call it anything you want
      //driver.quit();
      // quit the browser + make it null, so we can get new one when ask for it again.
      Driver.closeBrowser();
  }
```


### Create New [`WebOrderUtil`](../../utility/WebOrderUtil.java) class to use `Driver` utility method

```java
package com.cybertek.utility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebOrderUtil {

  public static void login(){
    // think about removing duplicate by calling second method
    Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
    Driver.getDriver().findElement(By.id("ctl00_MainContent_password")) .sendKeys("test");
    Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();
  }

  public static void login(String username ,String password){

    Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
    Driver.getDriver().findElement(By.id("ctl00_MainContent_password")) .sendKeys(password);
    Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();
  }

  public static void logout(){
    Driver.getDriver().findElement(By.id("ctl00_logout")).click();
  }

  public static boolean isAtOrderPage(){
    boolean result = false ;
    try{
      WebDriverWait wait = new WebDriverWait(Driver.getDriver(),2);
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='List of All Orders']"))) ;
      System.out.println("ELEMENT WAS IDENTIFIED ");
      result = true ;
    }catch (TimeoutException e){
      System.out.println("NO Such element! you are not at the right page");
    }
    return result ;
  }
  // Think about creating a utility method under BrowserUtil to wait for element using above technique
}

```

## Using External storage for Test Data
Any data we used in our code like username ,passwords, urls or any application specific data we used to pass into our tests.

We have been using such data directly in our code , which means test data is directly in the soure code , this is also known as `hard-coded` values.

So in a nutshell, `hard-coding` means using values directly in the source code. It can have few downside
- changing data will require us to go to source code to change
- changing source code means it needs to be re-compiled
- it can be harder to maintain since we have to go to many places to change source code
- since it require changes in many places , it will increase maintenance effort.
- simply running same code against different environment
  - like `qa1`,`qa2`, `uat`
  - (usually have different url) can be big code change everywhere.

As describe above, it's better to externalize such data in code and store it in external files.

Few common type of files
- **text** file : `testData.txt`
- **excel** file : `testData.xlsx`
- **csv**(comma separated value) file : `testData.csv`
  - can be opened by excel tools , lightweight plain text
  - columns are separated by `,`(comma)
  - first row usually serve as column name
    - ```csv
      name , batch, gender
      Nick , b22  ,  Male
      Maria, b23  , Female
      Aysu , b23  , Female
      ```
- **properties** file : `testData.properties`
  - key value pair separated by `=` (equal sign) or `:` (colon) or space ` `
  - one key value pair per line
  - ```properties
    hello=world
    url=https://google.com
    weborder_url=http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx
    weborder_username=Tester
    weborder_password=test
    browser=chrome
    ```
    
- **`YAML` or `YML`** file : `something.yml`

## Working with Properties File

In this section ,
we will learn how to work with properties file.

### **Creating properties file**
The properties file is nothing more than a file with `.properties` extension.

In IntelliJ, you can simply create new properties file by
`right-click`ing on your project name -> `New`->`File`->`config.properties` .

![Creating_PropertiesFile](https://user-images.githubusercontent.com/59104509/134741742-03222d3b-e772-48b4-8bdd-e4a3fd24bf12.gif)


As mentioned above, it contains:
- key value pair separated by
  - `=`(equal sign) or
  - `:` (colon) or just space
  - It's recommended not to mix and match , always use `=`.
  - one key value pair per line , **SPACE MATTER!!**
    ```properties
    hello=world
    url=https://google.com
    weborder_url=http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx
    weborder_username=Tester
    weborder_password=test
    browser=chrome
    ```

### **Reading from the file**
In order to read from properties file,
we need to first specify the location of the file
by using a java type for working with files `FileInputStream` from `java.io` package.
It will create a `FileInputStream` opening a connection to an actual file in that path.

```java
FileInputStream in = new FileInputStream("/path/to/the/file/config.properties")
```

If you created the file directly under root directory(right under your project folder) , you can simply use file name instead of full path.

```java
FileInputStream in = new FileInputStream("config.properties")
```

Any File related operation has checked exception
so add `throws IOException` into method signature for now to move on.

Next step to read it as properties file is
by using Java built-in type for specifically working with properties : `Properties` class.

1. Create `Properties` Object from `Properties` class from `java.util.Properties`
    ```java
    Properties properties = new Properties();
    ```
2. Use the `load` method from `Properties` class to load the properties file you referred in `FileInputStream`
    ```java
    // load method expect FileInputStream object as parameter and declare to throw IOException
    properties.load(in) ; 
    ```
3. Use the `getProperty` method from `Properties` class to get the value by providing the key
    ```java
    String appURL = properties.getProperty("weborder_url") ; 
    ```   
4. `FileInputStream` is a resource need to be closed once it's being used, so it's good to close the resource right after you load into properties file.
    ```java
   // close method declare to throw IOException 
    in.close(); 
    ```   

Assuming that you have created a properties file called `config.properties` ,
Here is how full working code look like.

```java
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesTest {

  @Test
  public void testRead() throws IOException {

    FileInputStream in = new FileInputStream("config.properties");
    Properties properties = new Properties();
    properties.load(in);
    in.close();
    properties.list(System.out);
    String helloValue = properties.getProperty("hello") ;
    System.out.println("helloValue = " + helloValue);
  }
}
```

We can handle the checked exception using try catch as below example.

```java
    @Test
    public void testReadTryCatch(){
          // moving Properties' declaration here so it can be accessible outside try catch
          Properties properties = new Properties();
        try {
            // all 3 lines of code declare to throw IOException 
            // so wrapping them in try catch block together. 
            FileInputStream in = new FileInputStream("config.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            String helloValue = properties.getProperty("hello") ;
            System.out.println("helloValue = " + helloValue);
    }
```

### Creating Simple Utility Class
Since **we decided** to store most configuration data into properties file, 
lets create a utility to read from `config.properties` file.

> You can read any properties file , here we just decided to read from one properties file we decided to use for configuration data.

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

  //In this class we will implement the repeated steps of reading
  // from config.properties file
  //#1- Create the object of Properties
  private static Properties properties = new Properties();

  //#2 using static block to only load it one time.
  static {
    //#3- Get the path and open the file
    try {
      FileInputStream in = new FileInputStream("config.properties");
      //#4- Load the opened file into properties object
      properties.load(in);
      //closing the file in JVM Memory
      in.close();

    } catch (IOException e) {
      System.out.println("Error occurred while reading config.properties " + e.getMessage()) ;
    }
  }

  //#5- Use the object to read from the config.properties file
  public static String read(String key) {
    return properties.getProperty(key);
  }
}
```

Now we can simply use the utility class everywhere.

```java
  @Test
public void testUsingConfigReaderUtility(){
    String webOrderUrl = ConfigReader.read("weborder_url");
    String username = ConfigReader.read("weborder_username");
    String password = ConfigReader.read("weborder_password");
    Driver.getDriver().get( webOrderUrl );
    // you can use login function with param and use above username passwords
}
```

Here is the [full code](PropertyFileReadTest.java) we did in class. 

Here is [another exercise](SeleniumWithPropertiesTest.java) we did to include selenium.  

### Updating `Driver` Utility Class to use Property File
We have been holding out on using browser parameter into our `Driver` utility.
So we can now read the browser type from the `config.properties` file.
It will allow us to change browser type in one place and apply everywhere we used `Driver.getDriver`.

Here is how new [`Driver`](../../utility/Driver.java) utility class look like.

```java
public class Driver {

  private static WebDriver obj ;

  private Driver(){ }

  public static WebDriver getDriver(){
    // reading browser type from config.properties file using utility
    String browserName = ConfigReader.read("browser") ;

    if(obj == null){

      switch (browserName.toLowerCase() ){
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

}
```


### Java System Properties (OPTIONAL)
The Java platform itself uses a Properties object to maintain its own configuration.
The `System` class maintains a `Properties` object that describes the configuration of the current working environment.

System properties include information about the current user, the current version of the Java runtime, and the character used to separate components of a file path name.

List of important system properties keys [can be found here.](https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html#PageContent)

It's relatively easy to access system properties by using static method provided by `System` class `getProperty("path.separator");`

```java
@Test
public void testReadingSystemProperties(){
    // java home path
    System.out.println("System.getProperty(\"java.home\") = "
    + System.getProperty("java.home"));
    // User working directory
    System.out.println("System.getProperty(\"user.dir\") = "
    + System.getProperty("user.dir"));
    // Operating system name
    System.out.println("System.getProperty(\"os.name\") = "
    + System.getProperty("os.name"));
    // User account name
    System.out.println("System.getProperty(\"user.name\") = "
    + System.getProperty("user.name"));
    // User's home directory
    System.out.println("System.getProperty(\"user.home\") = "
    + System.getProperty("user.home"));
}
```



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

