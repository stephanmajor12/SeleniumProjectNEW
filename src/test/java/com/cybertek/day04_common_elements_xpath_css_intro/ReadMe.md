# Day 4 Note

## Quick Recap on [Day3](../day03_locate_by_tag_class_getatt/ReadMe.md)
- Getting Attributes of Element `getAttrubute("attrubute name")`
- Finding Element(s) by tag name , class name
- `findElement` vs `findElements` 
- |   	        |  `findElement` 	            |   `findElements`	|
  |---	        |---	                        |---	            |
  |0 Matches   	|  `NoSuchElement` Exception 	|  return empty `List<WebElement>`    	            |
  |1 Matches   	|   return `WebElement` object	|  return `List<WebElement>` with `size()` 1 	|
  |2+ Matches   	|   return first`WebElement` object	| return  `List<WebElement>` 	|

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


### Working with Radio Buttons

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
List<&#60;>WebElement> radioBtnGroup = driver.findElements(By.name("color")) ;
// count how many radio button in radio group with name color

System.out.println("radioBtnGroup.size() = " + radioBtnGroup.size());  

radioBtnGroup.get(2).click() // click on radio button at index 2
```

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
- `Command` key on mac
- `Control` key on windows
- and select items

```java
WebElement multiDropdownElm = driver.findElement(By.name("Languages"));
Select multiSelectObj = new Select(multiDropdownElm);
multiSelectObj.selectByIndex(1);
multiSelectObj.selectByValue("java");
multiSelectObj.selectByVisibleText("JavaScript");
```

> Additional methods Methods available in Select class
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

## Find Element By CssSelector


## Find Element By XPath
