# Day 8

## Recap on [Day 7:](../day07_iframe/ReadMe.md)
- `iFrame` created using `<iframe>` tag
- used to embed one document inside another
- it can be nested iframe1>iframe2>iframe3
- Switch to the frame In order to findElement(s) inside frame
  - `driver.switchTo().frame(frameIndexNumber);`
  - `driver.switchTo().frame(frameIdOrName);`
  - `driver.switchTo().frame(iframeWebElement);`
- Once done with the frame, need to switch out of it
  - one level up :
    - `driver.switchTo().parentFrame();`
  - go to all the way top :
    - `driver.switchTo().defaultContent();`
    
## File Upload
Uploading a file in selenium is very straightforward. </br>
Unlike doing it manually where we have to click to open window and select the file,
In selenium , we just need to 
- identify the `input` element with `type` attribute `file`
  ```html
  <input type="file" id="file-upload">
  ```
- `sendKeys` the file location on your computer
  - On Mac, 
    - right-click on the file you want to upload 
    - press `option` key on your keyboard, 
    - it will show `copy file path option` for copying full path.
  - On Windows,
    - right-click on the file you want to upload
    - select last option properties
    - select `security tab`
    - copy down the full path displayed there
- The just hit upload button.

[Here is the full code](FileUploadTest.java) we did in class.
```java
driver.get("http://practice.cybertekschool.com/upload");
// locate the input filed :
WebElement fileInputField = driver.findElement(By.id("file-upload"));
String filePath = "/Users/training/Desktop/TheCoolPic.jpg" ;
fileInputField.sendKeys(filePath);
// click upload button with id file-submit
driver.findElement(By.id("file-submit")).click();
```
## Browser Utility class
We have been using `Thread.sleep(3000)` in couple places to slow down the execution.

However, everytime we use it , we are forced to handle checked exception.

So we decided to wrap it up into a method and handle the checked exception inside the method.

Here is the [BrowserUtil](../../utility/BrowserUtil.java) we created.
```java
public class BrowserUtil {
    /**
     * A method to pause the thread certain seconds
     * @param seconds
     */
    public static void waitFor(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
Now, whenever we have to wait we can just call as below 
```java
BrowserUtil.waitFor(2); // wait for 2 seconds no need to handle checked exception
```

## Actions Class 
**Actions** Class in Selenium is a built-in feature provided by the selenium <br>
for handling keyboard and mouse events. 

Here is the 
<a target="_blank" href="https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html">Actions Class Java Doc.</a>

These operations from the action class are performed using the advanced user interaction API in Selenium Webdriver

### Common Actions Class Operations
- drag and drop
- hover over ,move to element
- double click
- right click
- click and hold
- release
- and more

### Creating Actions Object
Actions class is coming from `org.openqa.selenium.interactions.Actions` <br>

First we need to create Action object by passing WebDriver instance
```java
Actions actions = new Actions(driver);
```
**Actions** class use a common design pattern known as builder pattern 
to compose one or more actions together then perform them as one unit. 

### Actions Class Hover over Demo

You can hover over using `moveToElement` method 
and pass WebElement object you have located.

Once done composing actions , 
**WE MUST END IT WITH `.perform()`** 
or it will not trigger the actions.

```java
driver.get("http://practice.cybertekschool.com/hovers");
//locating first the images from the page
WebElement img1 = driver.findElement(By.xpath("(//img)[1]"));
//create instance of Actions class' object to be able to use its method
Actions actions = new Actions(driver);
//Hover over to first image
actions.moveToElement(img1).perform();
```

### Actions Class Drag and Drop Demo

We used [this website](https://demos.telerik.com/kendo-ui/dragdrop/index) 
to practice how we can drag an element into another element. 

Drag and Drop is actually consists of few steps below : 
- Move to the draggable element (hover over)
- Click and hold 
- Move to the target element (hover)
- Release the mouse hold

Actions class method `dragAndDrop` method that access two WebElements as arguments made it easy.

It will drag the first element you passed and drop it second element.

Here is the example we did in class. 
```java 
//navigate to demo site
driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
Thread.sleep(2000);
WebElement smallCircle  = driver.findElement(By.id("draggable"));
WebElement biggerCircle = driver.findElement(By.id("droptarget"));
// create Actions object
Actions actions = new Actions(driver);
// drag left box and drop ip into right box
actions.dragAndDrop(smallCircle,biggerCircle).perform();
```

You have option to pause in between multiple actions in the chain.

It can be done using `pause(time in milleseconds)`.

This can be also done by breaking down into smaller steps then perform.
```java
actions.moveToElement(smallCircle).pause(1000)
        .clickAndHold().pause(1000)
        .moveToElement(biggerCircle).pause(1000)
        .release().perform();
```
Method `dragAndDrop` is actually doing it in one shot.

### Actions Class Keyboard actions Demo

You can perform common keyboard operations like combo keypress.

Here is simple example for `SHIFT`+`abc` then type `efg`: 
  - Hold down to `SHIFT` using `keyDown` method and pass `Keys.SHIFT`
    - ```java
       actions.keyDown(Keys.SHIFT)
      ```
    - `keyDown` method is meant for special modifier keys like 
      - **SHIFT**
      - **CONTROL**
      - **COMMAND**
      - **ALT** (for windows) **OPTIONS** for mac
  - Press any other keys using `sendKeys` method of `Actions` class
      - ```java
          actions.keyDown(Keys.SHIFT).sendKeys("abc")
        ``` 
    - since we are holding down to `SHIFT` key , it will actually enter `ABC`
  - Releasing the key that you are holding down to using `keyUp`
    - ```java
       actions.keyDown(Keys.SHIFT).sendKeys("abc").keyUp(Keys.SHIFT)
      ```
    - just like we would do manually, once done, we will need to stop holding down to `SHIFT`
    - if you enter more text now using `sendKeys`, it will now enter lowercase.
    - ```java
       actions.keyDown(Keys.SHIFT).sendKeys("abc").keyUp(Keys.SHIFT).sendKeys("efg")
      ```
    - the result if `perform()` is called : `ABCefg`
  - You can pause in between actions using `pause` method
    - `pause` method exclusively used for pausing in between action chain
    - it can not be used anywhere else and not replacement for sleep method
    - ```java
      actions.keyDown(Keys.SHIFT).sendKeys("abc").pause(1000).keyUp(Keys.SHIFT)
      ```
  - Once we are done combining actions, we can end with `perform()` for trigger.
    - ```java
      actions.keyDown(Keys.SHIFT).sendKeys("abc").pause(1000).keyUp(Keys.SHIFT).sendKeys("efg").perform();`
      ```
      - or write it in multiple like for readability as below : 
      ```java
        actions.keyDown(Keys.SHIFT)            // hold down shift
                .sendKeys("abc").pause(1000)   // enter abc and pasue 1 second
                .keyUp(Keys.SHIFT).            // release shift now
                 sendKeys("efg").pause(1000)   // enter efg and pasue 1 second
                 perform();                 // trigger it and get result "ABCefg"
      ```
    
Here is the [full code demo](ActionsClassTest.java) for Actions class. 

## Tab and Windows
- Selenium sees new tab and new windows exactly the same with no difference
- Every tab/window is a separate window
- Selenium can focus & control one window at a time
- We can switch to window using window handles 

### Window Handle 
A randomly generated unique alpha-numeric string that assigned to the tab or window when it's open for the first time.

For example : `CDwindow-289A5E491DD3D41196B5372DFF19885F`

Every tab and window get their own unique handle value like above. <BR>
**Selenium can use it to switch to certain tab or window.**

Two methods available to get window handle in selenium
- `driver.getWindowHandle()`
    - return String handle value of current window or tab
    - `String currentHandle = driver.getWindowHandle();`
- `driver.getWindowHandles()`
    - return all window handles in Set<String>
    - `Set<String> allHandles = driver.getWindowHandles();`
      
All selenium need for switching tab or window is the **window handle**.


### Switching Window using Window Handle

Here is how code look like :
```java
driver.switchTo().window(theWindowHandleGoesHere);
```

Here is the simple code demo to demonstrate switching to all windows available and printing out titles
```java
// get current handle
String currentHandle = driver.getWindowHandle();
// click some links to generate few more windows 
// omitted here
Set<String> allHandles = driver.getWindowHandles();
```

Iterate over switching each windows using their window handle
```java
for(String eachHandle: allHandles){
    // swicth to it using handle
    driver.switchTo().window(eachHandle);
    // print title
    System.out.println("driver.getTitle() = " + driver.getTitle());
}
```
Switch back to original window using its handle we stored
```java
driver.switchTo().window(currentHandle);
System.out.println("driver.getTitle() = " + driver.getTitle());
```
Here is the [full code demo](WindowTest.java) we did in class. 


## Running Javascript Code
There will be time we need to rely on 
running browser specific javascript 
to perform certain actions like 
- scrolling the window 
- scrolling the element into the view 
- and anything that work on browser

### `JavaScriptExecutor` Interface
The Selenium WebDriver API provides the
ability to execute JavaScript code with the
browser window. 

This is a very useful feature
when tests need to interact with the page
using JavaScript.

Here is the class diagram to show relationship between classes and interfaces in Selenium
![WebDriver_UML](https://user-images.githubusercontent.com/59104509/134089768-ebdac1fc-c388-44fb-b86c-6307cfd13ffa.png)
- `RemoteWebDriver` inherit `WebDriver`
- `RemoteWebDriver` inherit `JavaScriptExecutioner` Interface.
- `JavaScriptExecutioner` has methods to `executeScript`
- `ChromeDriver` inherit `RemoteWebDriver` interface

`JavaScriptExecutor` Interface from Selenium interface can help to achieve that with 2 methods
- `executeScript` 
- `executeAsyncScript` 

Since `JavaScriptExecutioner` has the method `executeScript`, </br>
in order to call the method the driver object need to have `JavaScriptExecutioner` reference as below by casting.
```java
JavaScriptExecutioner jse = (JavaScriptExecutioner) driver;
```
Now we can execute browser javascript as below
```java
jse.executeScript("window.scrollBy(0, 1000)");
```
`window.scrollBy(0, 1000)` is javascript code for scrolling the window down by 1000 pixel.

In order to see the scrolling effect clearly, 
We can go to practice site infinite scroll page 
and scroll down 10 times by 1000 pixel. 
```java
driver.get("http://practice.cybertekschool.com/infinite_scroll");
BrowserUtil.waitFor(2);
// get JavascriptExecutor reference from driver variable
// so we can run this js code windows.scrollBy(0,1000)
JavascriptExecutor jse = (JavascriptExecutor) driver;
// it uses execute method to run javascript , it can accept parameter
jse.executeScript("window.scrollBy(0,1000)") ;

// scrolling once is not obvious, lets loop and scroll 10 times and wait 1 secs
for (int i = 0; i < 10 ; i++) {
    jse.executeScript("window.scrollBy(0,1000)") ;
    BrowserUtil.waitFor(1);
}
```
### Passing Java variables into JavaScript code
It's possible to pass variable into javascript code using `arguments[indexHere]`.

For example, if we wanted to pass variable into `window.scrollBy(0,1000)` method, 
we could do this:
```java
   int myScrollNumber = 1000 ;
   jse.executeScript("window.scrollBy(0, arguments[0]  )" ,  myScrollNumber  )   ;
```
`myScrollNumber` will replace the `arguments[0]` since it's first argument after the javascript call.

If you want to pass more than one value , you can just add more at the end as below.
```java
   int scrollX = 0 ;  
   int scrollY = 1000 ;
   jse.executeScript("window.scrollBy(arguments[0], arguments[1]  )" ,  scrollX,scrollY  )   ;
```
I have renamed the variable with shorter name `scrollX`, `scrollY`
Here is how it's actually passed :
![Passing arguments](https://user-images.githubusercontent.com/59104509/134091493-502cdb7b-930e-4f30-8ed7-c9c23dc08610.png)



The reason we might want to scroll the window is 
to avoid certain element is not intractable 
because it's not in the view either it's down in the page 
or it's temporarily blocked by other elements.

Javascript can easily scroll the element into the view using below call.
```javascript
theTargetElement.scrollIntoView(true);
```
Let's navigate to [practice site](http://practice.cybertekschool.com/large)
First step we need to take is to locate the element to be scrolled into view. 
```java
driver.get("http://practice.cybertekschool.com/large");
// get JavascriptExecutor reference out of driver
JavascriptExecutor jse = (JavascriptExecutor) driver;
// locate the element
WebElement cybetekSchoolLnk = driver.findElement(By.linkText("Cybertek School"));
// pass this element as argument to javascript
jse.executeScript("arguments[0].scrollIntoView(true)" ,  cybetekSchoolLnk  );
```
As we saw from previous example , 
`arguments[0]` will be replaced by the element `cybetekSchoolLnk`
and it will be scrolled into the view wherever it is. 




