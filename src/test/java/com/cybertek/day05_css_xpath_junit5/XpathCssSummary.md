# Summary of commonly used XPath CssSelectors

This document is meant to give you the idea and comparison of css selector and xpath syntax.

You can use it as a reference for most of the scenarios that you will encounter in your automation.

Most of the time it does not get more complex than this. you might be just able to identify most elements just by
fraction of these listed below.

> E here represent any element tag

## General

| Requirement                          | Css Selector   | Xpath                    |
| ------------------------------------ | -------------- | ------------------------ |
| Refer the while page                 | `html`         | `/html`                  |
| Whole Web Page body                  | `body`         | `/html/body`             |
| Element `<E>` by absolute reference  | `body>…>…>…>E` | `/html/body/…/.../.../E` |
| Element `<E>` by relative reference  | `E`            | `//E`  for example `//p` |
| Image Elements by relative reference | `Img`          | `//img`                  |

## ID and class Attributes

| Requirement                                                    | Css Selector                              | Xpath                             |
| -------------------------------------------------------------- | ----------------------------------------- | --------------------------------- |
| Any Element with <br>`ID` attribute value `abc`                | `#abc` or `[id='abc']`                    | `//*[@id = 'abc']`                |
| Element `<E>` with <br>`ID` attribute value `abc`              | `E#abc` or `E[id='abc']`                  | `//E[@id = 'abc']`                |
| Any Element with <br>`class` attribute value `efg`             | `.efg` or  `[class='efg']`                | `//*[@class='efg']`               |
| Element `<E>` with <br>`class` attribute value `efg`           | `E.efg` or  `E[class='efg']`              | `//E[@class='efg']`               |
| Element `<E>` with <br>`id`of `abc` or `zzz`                   | `E#abc , E#zzz`                           | `//E[@id='abc' or @id='zzz']`     |
| Element `<E>` with <br>`id`value `abc` and `class` value `efg` | `E.efg#abc` or `E[id='abc'][class='efg']` | `//E[@id='abc' and @class='efg']` |

## Any Other Attributes

| Requirement                                                 | Css Selector                   | Xpath                                 |
| ----------------------------------------------------------- | ------------------------------ | ------------------------------------- |
| Element `<E>` with <br> `name` attribute value `cool`       | `E[name='cool']`               | `//E[@name='cool']`                   |
| Element `<E>` with attribute `A` (exists)                   | `E[A]`                         | `//E[@A]`                             |
| Element `<span>` with attribute `title`                     | `span[title]`                  | `//span[@title]`                      |
| Element `<span>` with <br>`title` attribute  value `search` | `span[title='search']`         | `//span[@title='search']`             |
| Element `<span>` with <br>any attribute value `search`      | N/A                            | `//span[@*='search']`                 |
| Element `<a>` with <br> `href` attribute  value `some url`  | `a[href='some url']`           | `//a[@href='some url']`               |
| Element `<input>` with two Attributes                       | `input[name='p'][type='text']` | `//input[@name='p' and @type='text']` |

## Partial Attribute Value

| Requirement                                                                     | Css Selector                                         | Xpath                                                      |
| ------------------------------------------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------------- |
| Element `<Button>` with <br>attribute `id` starts with `cool`                   | `button[ id^= 'cool']`                               | `//button[starts-with(@id, 'cool')]`                       |
| Element `<Button>` with <br>attribute `id` ends with `cool`                     | `button[ id$= 'cool']`                               | `//button[ends-with(@id, 'cool')]`                         |
| Element `<Button>` with <br>attribute `id` contains `cool`                      | `button[ id*= 'cool']` <br>or `button[ id~= 'cool']` | `//button[contains(@id, 'cool')]`                          |
| Element `<li>` with attribute <br>`class` starts with `abc`                     | `li[ class^= 'abc']`                                 | `//li[starts-with(@class, 'cool')]`                        |
| Element `<li>` with attribute <br>`class` ends with `abc`                       | `li[ class$= 'abc']`                                 | `//li[ends-with(@class, 'cool')]`                          |
| Element `<li>` with attribute <br>`class` contains `abc`                        | `li[ class*= 'abc']` <br>or `li[ class~= 'abc']`     | `//li[contains(@class, 'cool')]`                           |
| Element `<button>` with <br>id starts with `abc` <br>and `class` contains `efg` | `li[ id^= 'abc'][class*='efg'] `                     | `//li[starts-with(@id,'abc') and contains(@class, 'efg')]` |

## Element with Text

| Requirement                                           | Css Selector | Xpath                                                                      |
| ----------------------------------------------------- | ------------ | -------------------------------------------------------------------------- |
| Any Element with <br>Text value `something`           | N/A          | `//*[text()='something']`                                                  |
| Any Element with <br>Text value `something` shorthand | N/A          | `//*[.='something']`                                                       |
| Element `<Button>` <br>with Text value `Sign In`      | N/A          | `//button[text()='Sign In']` or<br> `//button[.='Sign In']`                |
| Element `<A>` with <br>Text value `Click Me`          | N/A          | `//a[text()='Click Me']` or<br> `//a[.='Click Me']`                        |
| Element `<A>` with <br>Text value starts with `Click` | N/A          | `//a[starts-with(text(), 'Click')]` or<br> `//a[starts-with(. , 'Click')]` |
| Element `<A>` with <br>Text value ends with `Click`   | N/A          | `//a[ends-with(text(), 'Click')]` or<br> `//a[ends-with(. , 'Click')]`     |
| Element `<A>` with <br>Text value contains `Click`    | N/A          | `//a[contains(text(), 'Click')]` or<br> `//a[contains(. , 'Click')]`       |
|                                                       |              |                                                                            |
|                                                       |              |                                                                            |

## Index (starts with 1)

| Requirement                                                   | Css Selector                                            | Xpath                               |
| ------------------------------------------------------------- | ------------------------------------------------------- | ----------------------------------- |
| First `<E>` Child Element                                     | `E:first-child` or `E:first-of-type`                    | `//E[1]`                            |
| First `<a>` Child Element                                     | `a:first-child` or `a:first-of-type`                    | `//a[1]`                            |
| Last `<a>` Child Element                                      | `a:last-child` or `a:last-of-type`                      | `//a[ last() ]`                     |
| Third `<li>` Element anywhere                                 | `li:nth-child(3)` or `li:nth-of-type(3)`                | `//li[3]`                           |
| Third `<li>` Child Element <br>with `class`=`list-group-item` | `li.list-group-item:nth-child(3)`                       | `//li[@class='list-group-item'][3]` |
| Second-to-last `<li>` child                                   | `li:nth-last-child(2)` or<br>  `li:nth-last-of-type(2)` | `//li[last()-1]`                    |

## **Parent Child**

| Requirement                                                           | Css Selector | Xpath                              |
| --------------------------------------------------------------------- | ------------ | ---------------------------------- |
| `<Input>` child Element directly Under `<div>`                        | `div>input`  | `//div/input`                      |
| `<Input>` Element <br>any level Under (Descendant) `<div>`            | `div input`  | `//div//input`                     |
| Parent of element `<li>`                                              | N/A          | `//li/..`                          |
| Descendant `<input>` of element <br>with id `abc` using relative path | `#abc input` | `//*[@id='abc']//input`            |
| Element `<E1>` following some sibling `<E2>`                          | `E2~E1`      | `//E2/following-sibling::E1`       |
| Sibling Element immediately following `<E>`                           | `E + *`      | `//E/following-sibling::*`         |
| Sibling Element immediately following `<Label>`                       | `Label + *`  | `//Label/following-sibling::*`     |
| Element `<E1>` preceding some sibling `<E2>`                          | N/A          | `//E2/preceding-sibling::E1`       |
| Element `<input>` preceding a sibling `<label>`                       | N/A          | `//input/preceding-sibling::label` |

## **Table Cell**

| Requirement                                                 | Css Selector                              | Xpath                                                     |
| ----------------------------------------------------------- | ----------------------------------------- | --------------------------------------------------------- |
| `Table` with `ID` value `"table1"`                          | `#table1`                                 | `//table[@id='table1']`                                   |
| Get 3rd row, 2nd column from above table                    | `#table1 tr:nth-child(3) td:nth-child(2)` | `//*[@id='TestTable']//tr[3]//td[2]`                      |
| Cell Have exact text value of `"Tim"`                       | N/A                                       | `//table[@id='table1']//td[.='Tim']`                      |
| Cell immediately following <br>cell containing text `"Tim"` | N/A                                       | `//table[@id='table1']//td[following-sibling::td='Tim']`  |
| Cell before the cell <br>containing text `"Tim"`            | N/A                                       | `//table[@id='table1']//td[preceeding-sibling::td='Tim']` |
