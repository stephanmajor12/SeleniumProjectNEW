# Selenium Learning Project 

## [Class Notes](https://ubiquitous-system-377131ac.pages.github.io/) :

* [x] [Day 01 Note](./src/test/java/com/cybertek/day01_navigation_locators/ReadMe.md) 
* [x] [Day 02 Note](./src/test/java/com/cybertek/day02_locators_practice/ReadMe.md)
* [x] [Day 03 Note](./src/test/java/com/cybertek/day03_locate_by_tag_class_getatt/ReadMe.md)
* [x] [Day 04 Note](./src/test/java/com/cybertek/day04_common_elements_xpath_css_intro/ReadMe.md)
* [x] [Day 05 Morning Note](./src/test/java/com/cybertek/day05_css_xpath_junit5//ReadMe.md)
* [x] [Day 05 Afternoon Note](./src/test/java/com/cybertek/tests/day05_css_xpath_junit5/ReadMe.md)
* [x] [Day 06 Note](./src/test/java/com/cybertek/tests/day06_driver_utility_testbase_alerts_tables/ReadMe.md)
* [x] [Day 07 Note](./src/test/java/com/cybertek/tests/day07_iframe/ReadMe.md)
* [x] [Day 08 Note](./src/test/java/com/cybertek/tests/day08_upload_actions_window_js/ReadMe.md)
* [x] [Day 09 Note](./src/test/java/com/cybertek/tests/day09_explict_wait_methods_singleton_driver/ReadMe.md)
* [x] [Day 10 Note](./src/test/java/com/cybertek/tests/day10_driver_method_practice_properties/ReadMe.md)
* [x] [Day 11 Note](./src/test/java/com/cybertek/tests/day11_property_driver_faker_pom/ReadMe.md)
* [x] [Day 12 Note](./src/test/java/com/cybertek/tests/day12_pom_practice_review/ReadMe.md)

---
---
<img width="200" alt="selenium_icom" src="https://user-images.githubusercontent.com/59104509/134586246-189cfb25-e1e9-412e-8fb4-1bbbacaaeb75.png">

[Selenium](https://selenium.dev) is an open source java library for automating browser. 

>Selenium automates browsers. That's it!
What you do with that power is entirely up to you.
 
Selenium Consists of 3 part 
- Selenium IDE (browser plugin for record playback)
- **Selenium WebDriver** (programmatic access)
- **Selenium Grid** (running distributed test in multiple machines) 

Selenium Clients and WebDriver Language Bindings
In order to create scripts that interact with browser, 
you need to make use of language-specific client drivers.

Selenium has support for Java , C# , Ruby , Python and JavaScript...

We will be working with Java, so we need Selenium Java (current version  3.141.59)

Selenium works on all major operating systems like mac , windows and linux. 

Selenium support automation of all major browsers by communicating the browser specific driver to automate browser actions. 
Each browser vendors like chrome , firefox , safari and edge so on have their own specific driver. 

For example : 
 - Google Chrome has [ChromeDriver](https://chromedriver.chromium.org/) 
 - Mozilla Firefox has [GeckoDriver](https://github.com/mozilla/geckodriver)  
 - Microsoft Edge has [EdgeDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)

At its minimum, 
WebDriver talks to a browser through a driver. 
Communication is two-way: 
- WebDriver passes commands to the browser through the driver, and receives information back via the same route.
- ![](https://www.selenium.dev/images/documentation/webdriver/basic_comms.png)
- We will be using it to automate browser 
- Additionally, we will use testing framework like Junit to make assertions


# Apache Maven 
[Apache Maven ](https://maven.apache.org/) 
is a tool for Java Project management and comprehension.
Based on the concept of a project object model (POM), 
Maven can manage a project's build, reporting and documentation from a central piece of information.

It has a lot of [features](https://maven.apache.org/maven-features.html) that developer heavily rely on.

We will focus on one of few core feature that relevant to us to get start easily with selenium. 
- Simple project setup that follows best practices

```
├── pom.xml
└── src
    ├── main                
    │ ├── java              // dev use this
    │ └── resources
    └── test                
        ├── java            // test goes here
        └── resources (optionally)
```

- **Dependency management**: Maven encourages the use of a [central repository](https://mvnrepository.com/) of JARs and other dependencies. Maven comes with a mechanism that your project's clients can use to download any JARs required for building your project from a central JAR repository.

>In Maven, dependency is another archive—JAR, ZIP, and so on—which your current project needs in order to compile, build, test, and/or to run. The dependencies are gathered in the pom.
If they are not present there, then Maven will download them from a remote repository and store them in the local repository

In order to work with selenium we will need `selenium-java` dependency.
In order to easy manage browser specific drivers we need `WebDriverManager` dependency. 
These dependencies can be added into `pom.xml` file in the project we are about to create so it can download it from maven [central repository](https://mvnrepository.com/).

# Setting up Maven Project in IntelliJ 
>IntelliJ already come with maven bundled so no separate download or set up needed at this moment for easy start.

1. From top menu , `File`--> `New` --> `Project`
2. Select **Maven** from left tab and select Java 8 ![Select Maven](https://user-images.githubusercontent.com/59104509/131440646-9b9c533d-d7fc-4857-a8f3-4870530e254e.png)
3. Provide project name and select default project location 
4. Provide group id, usually reverse domain name
   - in this case `com.cybertek` and it will also be base package we create later.
5. Provide artifact id , same as project name and keep the version tab as is ![Project Detail](https://user-images.githubusercontent.com/59104509/131440859-8de38b61-6cb2-477d-8ecf-09362b9f622f.png)
6. Click `Finish` to create project , you will be present with `pom.xml` file
7. You will see content below 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>

    <groupId>com.cybertek</groupId>
    <artifactId>SeleniumProject</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>
```
8. Now it's time to add dependencies for `selenium-java` and WebDriver manager.
9. Open a section right under `</properties>` and right before `</project>`
    
```
   <dependencies> 
   
   <dependencies>
```

10. Copy and paste the [selenium dependency](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.141.5) 
and the [WebDriverManager Dependency](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager/4.4.3) in between. 
11. Here is the full `pom.xml`
   
```xml
<?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
   
       <groupId>com.cybertek</groupId>
       <artifactId>SeleniumProject</artifactId>
       <version>1.0-SNAPSHOT</version>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
       </properties>
   
       <dependencies>
   
   <!--        This is the dependency for selenium java binding -->
           <dependency>
               <groupId>org.seleniumhq.selenium</groupId>
               <artifactId>selenium-java</artifactId>
               <version>3.141.59</version>
           </dependency>
   
   <!--        This is WebDriverManager dependency to help us set up Browser driver automatically-->
           <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
           <dependency>
               <groupId>io.github.bonigarcia</groupId>
               <artifactId>webdrivermanager</artifactId>
               <version>4.4.3</version>
           </dependency>
   
   
       </dependencies>
   
   
   </project>
```

12. Create a package under `src/test/java` with name `com.cybertek` to match group id you added when creating project.
13. Create a class called `SeleniumDryRun` and add a main method to test things out.
    ![Folder Structure](https://user-images.githubusercontent.com/59104509/131441768-f6c396db-b5f7-40ba-972a-19cbd60381e6.png)
14. Add below code and run , The Chrome browser should open and navigate to google.com

```java
package com.cybertek;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDryRun {
    public static void main(String[] args) {
        //1. setup chrome driver using webdriver manager
            // so selenium can send interaction request
        WebDriverManager.chromedriver().setup();
        //2. create webdriver instance using Chrome Driver object
        WebDriver driver = new ChromeDriver();
        //3. navigate to https://google.com
        driver.get("https://google.com");
    }
}
```

15. You are all set.
