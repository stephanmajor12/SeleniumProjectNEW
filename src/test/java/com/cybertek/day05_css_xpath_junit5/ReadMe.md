# Day 5 Morning: 

## Recap on [Day 4](../day04_common_elements_xpath_css_intro/ReadMe.md) 
- Working with common form elements 
  - **Checkboxes**
  - **RadioButtons**
  - **Dropdown List** (`Select` class in Selenium)
    - Single item dropdown
    - Multiple item dropdown
    - Dropdown without select tag
- Intro to Locating by **CSS Selector**
- Intro to Locating by **XPath** 

## Locating Element(s) By CssSelector
In CSS, selectors are patterns used to select the element(s) you want to style.

Selenium can use this to locate the elements on the page to interact with them. 

Compared to other locators we learned so far(other than xpath), Strength of Css Selectors are we are not limited to just tag name , id , class attribute.

We can use any other attributes and also define parent child relationship. 

CSS Selector also allow you to define partial attribute value logic using: 
- Attribute `^=` value  : attribute starts with value
- Attribute `$=` value  : attribute end with value
- Attribute `*=` value  : contains with value

Here is the few different ways we practiced identifying the elements for [forget passport page](http://practice.cybertekschool.com/forgot_password)

for email box : 
```java
/**
  input[name='email']
  form input[name='email']
  form#forgot_password input[name='email']
 #forgot_password input[name='email']
*/
 WebElement emailBox = driver.findElement(By.cssSelector("input[name='email']"));
 emailBox.sendKeys("someone@somewhere.com");
```

for submit button : 
```java
/**
 * #form_submit
 * button#form_submit
 * button#form_submit.radius
 * button[id='form_submit'][class='radius']
 */
 WebElement retrieveBtn = driver.findElement(By.cssSelector("#form_submit"));
 retrieveBtn.click();
```
for success message
```java
 // identify the success message and print out the text
/**
 * div#content > h4[name='confirmation_message']
 * div > h4[name='confirmation_message']
 * h4[name='confirmation_message']
 */
WebElement confirmationMsg = driver.findElement(By.cssSelector("h4[name='confirmation_message']"));
System.out.println("confirmationMsg.getText() = " + confirmationMsg.getText());
```

Here is the [full code example ](CssSelectorPractice1.java) . 

## Locating Element(s) By CssSelector
XPath uses path expressions to select element or elements in an XML or html document.

The node | element is selected by following a path or steps.

Types of XPath Expression : 

- Absolute (full) path
  - `/html/body/div/div[1]/form/input[2]`
- Relative Path
  - `//form/input[2]`

### Absolute full path
- It starts with single forward slash `/` .
- For example : `/html/body/div/div[1]/form/input[2]`
- `1` and `[2]` here indicate index
- Attributes can be specified using `elementTag[@attributeName='attributeValue']` syntax.
- Code example : `driver.findElement(By.xpath("/html/body/div/div[1]/form/input[2]`"));`
- It's fragile because the entire hierarchy must exactly match, or it will break.

  - It starts with double forward slash `//` .
  - For example : `//input[@id='username']` or simply `//*[@id='username']`
  - `//*[@id='username']` : `*` means any element tag here
  - Attributes can be specified using `elementTag[@attributeName='attributeValue']` syntax.
  - You can specify multiple hierarchy like this `//form//input[@id='username']`
    - an `input` element with `id` value `username` **any level** under `form` element
  - Code example : `driver.findElement(By.xpath("//input[@id='username']"));`
  - It's flexible because as long as the relative part match , it does not care about parent elements hierarchy.

> We will be using relative path literally at all times

#### Testing in browser
You can test out your XPath before running your code directly in browser just like you did for `CSS Selector`

<img width="1272" alt="Testing XPath" src="https://user-images.githubusercontent.com/59104509/132450039-43b3acf1-2736-4730-ae4e-3cf5aa941692.png">

### XPath Syntax
Element with Attributes can be specified using <br>
`//elementTag[@attributeName='attributeValue']` syntax.

```java
WebElement elm = driver.findElement(By.xpath("//input[@id='username']"));
// or 
WebElement elm1 = driver.findElement(By.xpath("//*[@id='username']"));
```


[Here is this summary document](XpathCssSummary.md) that summarize most of the common situations

It compares the CSS-Selector and XPath for same element identification. 

Use it as guide for all future , ideally print it out to review often.