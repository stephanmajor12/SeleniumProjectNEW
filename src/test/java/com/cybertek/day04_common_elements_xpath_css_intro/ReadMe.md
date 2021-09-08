# Day 4 Note

## Quick Recap on [Day3](../day03_locate_by_tag_class_getatt/ReadMe.md)
- Getting Attributes of Element `getAttrubute("attrubute name")`
- Finding Element(s) by tag name , class name
- `findElement` vs `findElements` 
- |   	        |  `findElement` 	            |   `findElements`	|
  |---	        |---	                        |---	            |
  |0 Matches   	|  `NoSuchElement` Exception 	|  return empty `List<WebElement>`    	            |
  |1 Matches   	|   return `WebElement` object	|  return `List<WebElement>` with `size()` 1 	|
  |2+ Matches   |   return first`WebElement` object	| return  `List<WebElement>` 	|

--- 

## Common Elements

### Working with Checkboxes
Here is how it looks like
```html
<label for="martial_status">Married ? </label>
<input type="checkbox" name="martial_status" id="martial_status">
<label for="vaccinated"> Vaccinated? </label>
<input type="checkbox"  name="got_vaccinated" id="vaccinated">
```
```java
WebElement checkbox1 = driver.findElement(By.id("box1")) ;
System.out.println("Before click checkbox1.isSelected() = "
        + checkbox1.isSelected() );
checkbox1.click();
if( ! checkbox1.isSelected() ){
    checkbox1.click();
}else{
    System.out.println("checkbox 1 is already selected");
}
```
[Here](LocatingCheckBoxes.java) is the full code.


### Working with Radio Buttons

Radio buttons created using `input` tag and attribute `type="radio"`

They can be grouped by `name` attribute and only one option can be selected per group at a time.

Assuming that we have below radio button groups
```html
<input type="radio" id="red" name="color" checked>
<label for="red">Red</label>
<input type="radio" id="blue" name="color" checked="">
<label for="blue">Blue</label>
<input type="radio" id="black" name="color" checked="">
<label for="black">Green</label>
<input type="radio" id="green" name="color" disabled>
<label for="green">Green</label>
```
```java
WebElement blueRadioBtn = driver.findElement(By.id("blue")) ;
// check if it's already selected or not
System.out.println("blueRadioBtn.isSelected() = " + blueRadioBtn.isSelected());  
// check if it's disabled or not
System.out.println("blueRadioBtn.isDisabled() = " + blueRadioBtn.isDisabled());  
blueRadioBtn.click(); // click it
```


```java
List<WebElement> radioBtnGroup = driver.findElements(By.name("color")) ;
// count how many radio button in radio group with name color

System.out.println("radioBtnGroup.size() = " + radioBtnGroup.size());  

radioBtnGroup.get(2).click() // click on radio button at index 2
```

[Here](LocatingRadioButtons.java) is the full code.


### Working with Dropdown List Single Item 
Assuming that this is an `<Select>` element with below options

```html
<select id="dropdown">
      <option value="1">Option 1</option>
      <option value="2">Option 2</option>
</select>
```
Selenium has special support for selecting dropdown list options using `Select` Class.

First we need to identify the parent `select` element by it's ID value `dropdown` as below.

Then we can use `Select` class provided by Selenium to create Object by enclosing the element So that we can use the easy methods provided to select items

```java
WebElement dropdownElm = driver.findElement(By.id("dropdown") ) ;
Select selectObj = new Select(dropdownElm) ; 
```

> Methods available in Select class
- Select by
    - `Index` : `selectObj.selectByIndex(1);`
    - `value` : `selectObj.selectByValue("2");`
    - `VisibleText` : `selectObj.selectByVisibleText("Option 1");`

[Here](LocatingDropDownList.java) is the full code.



### Working with Dropdown List Multiple Items

![Multi Select List](https://user-images.githubusercontent.com/59104509/132386514-51ec9623-f966-4cfa-ba56-8d974e4271d8.png)

Assuming that this is an `<Select>` element with below options

  ```html
  <select name="Languages" multiple="">
      <option value="java">Java</option>
      <option value="js">JavaScript</option>
      <option value="c#">C#</option>
      <option value="python">Python</option>
  </select>
  ```
You can select more than one item in this type of dropdown by holding down to
- `Command` key on Mac
- `Control` key on Windows
- and select items

```java
WebElement multiDropdownElm = driver.findElement(By.name("Languages"));
Select multiSelectObj = new Select(multiDropdownElm);
multiSelectObj.selectByIndex(1);
multiSelectObj.selectByValue("java");
multiSelectObj.selectByVisibleText("JavaScript");
```

> Additional Methods available in Select class
- deselect by
    - `index` : `multiSelectObj.deselectByIndex(1);`
    - `value` : `multiSelectObj.deselectByValue("2");`
    - `VisibleText` : `multiSelectObj.deselectByVisibleText("C#");`
    - `all` : `multiSelectObj.deselectAll()`

Here is few examples : 
```java
WebElement multiDropdownElm = driver.findElement(By.name("Languages"));
Select multiSelectObj = new Select(multiDropdownElm);
multiSelectObj.selectByIndex(1);
multiSelectObj.selectByValue("java");
multiSelectObj.selectByVisibleText("Python");
multiSelectObj.deselectByIndex(2);
multiSelectObj.deselectByValue("java");
multiSelectObj.deselectAll();
```
[Here](LocatingAndSelectingMutiDropdown.java) is the full code. 

### Working with "Unusual" Dropdown

![Unusual Dropdown](https://user-images.githubusercontent.com/59104509/132386400-9ab67dcd-d585-424d-b5e7-1cdc9fa10a64.png)
```html
<div class="dropdown">
    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Dropdown link
    </a>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 38px, 0px);">
        <a class="dropdown-item" href="https://www.google.com/">Google</a>
        <a class="dropdown-item" href="https://www.amazon.com/">Amazon</a>
        <a class="dropdown-item" href="https://www.yahoo.com/">Yahoo</a>
        <a class="dropdown-item" href="https://www.facebook.com/">Facebook</a>
        <a class="dropdown-item" href="https://www.etsy.com/">Etsy</a>
    </div>
</div>
```
[Here](SelectingFromFakeDropdown.java) is how we dealt with above 
so-called "dropdown" that actually created from `select` tag just using regular element identification. 


## Locating Element(s) By CssSelector
In CSS, selectors are patterns used to select the element(s) you want to style.

  <a target="_blank" href="https://www.w3schools.com/cssref/css_selectors.asp">CSS Selector Reference</a> </br>
  <a target="_blank" href="https://www.w3schools.com/cssref/trysel.asp">CSS Selector Tester</a>

### Few examples of Css Selector
- Selecting `tags`
  - `h1` :  all `h1` elements
  - `h1, input, select` : all `<h1>` `<input>` `<select>` elements
  - `div > button` : all buttons directly under parent `<div>` element
  - `div button` : all `<button>` elements inside `<div>`
  - `ul + p` : all `<p>` elements next to `<ul>`
  - `ul ~ table` : all `<table>` elements that are siblings of a `<ul>` element
- Hash `#` for ID
  - `#LastName` : element with `ID` attribute value = `LastName`
- Dot `.` for class
  - `.group` : element with `class` attribute value = `group`
  - `p.cool` : `<p>` element with `class` attribute value = `cool`
  - `.class1.class2` : element with `class` attribute value = `class1 class2`
- Combining with comma `,`
  - `#LastName , .group` : element with `ID` value = `LastName` and `class` attribute value = `group`

### Few examples of Css Selector using Attributes 
- Explicitly specifying attributes
  - `[id]` : all elements with an `id` attribute.
  - `[id='pretty-cool']` : all elements with an `id` attribute = `pretty-cool`. (same as `#pretty-cool`)
  - `[title='cool stuff']` : all elements with `title` attribute = `cool stuff`
  - `[title^='cool']` : all elements with `title` attribute start with `cool`
  - `[title$='stuff']` : all elements with `title` attribute end with `stuff`
  - `[title~='stuff']` : all elements with `title` attribute contains `stuff`

  - `h1[title='cool stuff']` : all `<h1>` elements with `title` attribute = `cool stuff`
  - `input[type='text']` : all `<input>` element with `type` attribute = `text`
  - `input[type='checkbox'][name='vehicle1']` : `<input>` element with `type` attribute = `checkbox` and `name` attribute = `vehicle1`
- and more

### Testing out directly in Browser
You may inspect any element to open dev tab in chrome and activate test box
- `Command` + `F` on Mac 
- `Control` + `F` on Windows

  <img width="992" alt="The test box in chrome" src="https://user-images.githubusercontent.com/59104509/132441910-8a4e1ef4-e1c5-4750-8c4a-426a93e2c2b5.png">

It will give you 3 option to search 
- Just String (useless in our case)
- Css Selector 
- Xpath

  ![Open_Css_Xpath_Test_box_in_chrome](https://user-images.githubusercontent.com/59104509/132441561-6d2dfcfb-84c4-454f-b53c-00c8c1f89477.gif)



### Code Example 
Given the html snippet from [Swag Lab Practice site](https://www.saucedemo.com/),

```html
<div class="form_group">
  <input class="input_error form_input" 
         placeholder="Username" type="text" 
         data-test="username" id="user-name" 
         name="user-name" value="standard_user">
</div>
```
Here is few ways we can identify element(s) using `Css Selector`

- Any element with `id` value of `user-name` 
  ```java
  WebElement usernameBox1 = driver.findElement(By.cssSelector("#user-name"))
  ```
- input element with id attribute username in explicit way
  ```java
  WebElement usernameBox2 = driver.findElement(By.cssSelector("input[id='user-name']"))
  ```
- input element with `name` attribute value `user-name` 
  ```java
  WebElement usernameBox3 = driver.findElement(By.cssSelector("input[name='user-name']"))
  ```
- input element with `data-test` attribute value `username`
  ```java
  WebElement usernameBox4 = driver.findElement(By.cssSelector("input[data-test='username']"))
  ```
- input element with `id` value `user-name` right under div parent
  ```java
  WebElement usernameBox5 = driver.findElement(By.cssSelector("div>input#user-name"))
  ```
- input element with `type` attribute value `text` and `placeholder` attribute value `data-test`
  ```java
  WebElement usernameBox6 = driver.findElement(By.cssSelector("input[type='text'][placeholder='Username']"))
  ```

[Here](FindElementByCssSelector.java) is another example we did in class 

The Html for search box and clear(x) icon
```html
<div id="sbq-wrap" class="sbq-w">
  <input type="text" class="sbq" id="yschsp" name="p" value="" autocomplete="off">
  <button id="sbq-clear" type="button" class="sbq-x sa-hidden">
    <span class="sprite"></span>
  </button>
  <span class="sep"></span>
</div>
```
>Search box Try different way , by id , by name and parent child relationship 
```java
// few ways to identify search box with css selector
WebElement searchbox = driver.findElement(By.cssSelector("#yschsp"));
WebElement searchbox = driver.findElement(By.cssSelector("input[name='p']"));
WebElement searchbox = driver.findElement(By.cssSelector("input[id='yschsp']"));
WebElement searchbox = driver.findElement(By.cssSelector("input[name='p'][type='text']"));
// EXTRAS   : an element with id value yschsp under another element with id sbq-wrap
WebElement searchbox = driver.findElement(By.cssSelector("#sbq-wrap>#yschsp"));
// EXTRAS   : an element with id value yschsp under another div element with class sbq-w
WebElement searchbox = driver.findElement(By.cssSelector("div.sbq-w>#yschsp"));

searchbox.sendKeys("CSS SELECTOR");
```
> Clear icon (span): Try different way , by id , by name and parent child relationship
```java
// few ways to identify clear icon with css selector
// span element with class attribute sprite
WebElement clearIcon = driver.findElement(By.cssSelector("span.sprite")); // risky not so unique
WebElement clearIcon = driver.findElement(By.cssSelector("span[class='sprite']")); // risky not so unique
// span element under an element with id value of sbq-clear
WebElement clearIcon = driver.findElement(By.cssSelector("#sbq-clear>span"));
WebElement clearIcon = driver.findElement(By.cssSelector("div[id='sbq-clear']>span"));

// span element under an button element with id value of sbq-clear
WebElement clearIcon = driver.findElement(By.cssSelector("button#sbq-clear>span"));
// span element with class attribute sprite under an button element with id value of sbq-clear
WebElement clearIcon = driver.findElement(By.cssSelector("button#sbq-clear>span.sprite"));

//more specific : 
// any span element with class attribute sprite anywhere under parent div with id value sbq-wrap
// for direct child we use > and for child or grand child use space
WebElement clearIcon = driver.findElement(By.cssSelector("div#sbq-wrap span.sprite"));

// even more specific : using 3 level hierarchy
WebElement clearIcon = driver.findElement(By.cssSelector("div#sbq-wrap>button#sbq-clear>span.sprite"));
//and so on
clearIcon.click();
```
The html for search icon 
```html
<button class="sbb" type="submit" tabindex="2">
  <span role="button" title="Search" class="ico-syc mag-glass"></span>
</button>
```
Here is the few examples of using attributes and parent child relationship
```java
WebElement searchIcon = driver.findElement(By.cssSelector("span[title='Search']"));
// EXTRAS :  span with title value search and class value ico-syc mag-glass
WebElement searchIcon = driver.findElement(By.cssSelector("span[title='Search'][class='ico-syc mag-glass']"));
WebElement searchIcon = driver.findElement(By.cssSelector("span.ico-syc.mag-glass[title='Search']"));
// span with title attribute value "Search" directly under a button with class value "sbb"
WebElement searchIcon = driver.findElement(By.cssSelector("button.sbb>span[title='Search']"));
// span with title attribute value "Search" directly under an element with type attribute value "submit"
WebElement searchIcon = driver.findElement(By.cssSelector("[type='submit']>span[title='Search']"));
```

  
## Locating Element(s) By XPath

**XPath** stands for XML Path Language. 

XPath can be used to navigate through elements and attributes in an HTML or XML document.

Here is [full doc](https://www.w3schools.com/xml/xpath_intro.asp). 
It uses XML and yet it works the same for html. 

XPath is advanced locating technic with lots of powers.

Given Below HTML
```html
<html>
<body>
<div id="content">
  <div>
    <form id="cool_form">
      <label for="username">Enter username</label>
      <input id="username"type="text">
      <label for="passwd">Enter username</label>
      <input id="passwd"type="password">
      <button type="submit">Sign In</button>
    </form>
  </div>
  <div>
    This is second div under parent div
  </div>
</div>
</body>
</html>
```

### Types of XPath : 
- **Absolute (full) XPath**
  - similar to the idea of full file path in computer : `C:\Users\You\Desktop\Selenium\Something.java`
  - full path for username box : `/html/body/div/div[0]/form/input[0]`
    - It starts with single forward slash `/` . 
    - It's fragile because the entire hierarchy must exactly match, or it will break.
    - `0` here indicate index
    - Attributes can be specified using `elementTag[@attributeName='attributeValue']` syntax.
    - Code example : `driver.findElement(By.xpath("/html/body/div/div[0]/form/input[0]"));`

- **Relative XPath**
  - similar to short file path `\\Desktop\Selenium\Something.java`
  - relative path for username box : `//input[@id='username']` or simply `//*[@id='username']`
    - It starts with double forward slash `//` . 
    - It's flexible because as long as the relative part match , it does not care about parent elements hierarchy.
    - `//*[@id='username']` : `*` means any element tag here 
    - Attributes can be specified using `elementTag[@attributeName='attributeValue']` syntax.
    - You can specify multiple hierarchy like this `//form//input[@id='username']`
      - an `input` element with `id` value `username` **any level** under `form` element 
    - Code example : `driver.findElement(By.xpath("//input[@id='username']"));`
 

You can test out your XPath before running your code directly in browser just like you did for `CSS Selector`

<img width="1272" alt="Testing XPath" src="https://user-images.githubusercontent.com/59104509/132450039-43b3acf1-2736-4730-ae4e-3cf5aa941692.png">


### Using XPath functions
XPath has useful function to use `text` value 
which can not be done using `css selector` or any other findElement technic (other than linkText). 

Text is not attribute of element, so unlike attribute where we use `//elementTag[@attributeName='attributeValue']`,

This is the syntax for `text` function : `//elementTag[text()='The Black Element Text']`

For example 
- in order to get an `<span>` element with text value `Test Automatin Practice`, 
- You can use below this XPath : `//span[text()='Test Automatin Practice']`
- Here is how code look like : 
  ```java
  WebElement spanElm = driver.findElement(By.xpath("//span[text()='Test Automation Practice']"))
  ```
- If we want to be more specific and say it's under a `<h1>` tag here is how it looks like. 
  ```java
  WebElement spanElm = driver.findElement(By.xpath("//h1/span[text()='Test Automation Practice']"))
  ```

There are more useful functions like `contains`, `starts-with` , `ends-with` and so on

[Here](FindElementByXPath_Intro.java) is the full practice we did in class. 

XPath can do literally anything that all other locators can do. 

We will continue to learn more about XPath and Css Selector in upcoming classes. 

[Here](Update_XPath_Css_CheatSheet.pdf) is the full document you can use as reference. 

## Homework 

Try redoing all the in class tasks and homework tasks using `CSS Selector` and `XPath`