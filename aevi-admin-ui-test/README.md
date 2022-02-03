# Introduction
Automated tests project for UI testing of Aevi Pay Admin. Project is using Selenium, Cucumber and TestNG.

## Installation
Install following plugins inside IntelliJ:
* JUnit
* TestNG
* Create TestNG XML
* Cucuber for Java
* Gherkin

Edit settings.xml file inside root folder:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
    <entry key="webpage_url">https://localhost:8443/SMCAdmin/</entry>
    <entry key="chrome_driver_location">\target\chromedriver.exe</entry>
</properties>
```

Change keys inside settings.xml accordingly:
```properties
webpage_url = point to Aevi Pay Admin URL
chrome_driver_location = point to location inside project where you will hold chrome driver file (chromedriver.exe) for testing
```

Run automated tests using UiTestRunner.java or inside terminal using:
```bash
mvn test
```
