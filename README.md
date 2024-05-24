# Test Automation Demo project
Java 17 - Gradle - TestNG - Cucumber - RestAssured - Selenium WebDriver - Extent Report

# About this project
This is a Demo Test Automation framework that represents API and UI auto-testing approaches using some popular technologies:
 - Selenium WebDriver for UI testing
 - RestAssured for API testing
 - Cucumber as BDD framework that makes testing scenarios more user-friendly
 - Extent report as a tool for test results representation and analysis
 - Gradle as a Java build tool
 - And all this works on Java 17 basis

All the scenarios are aimed to test https://thinking-tester-contact-list.herokuapp.com/ both API and UI.
API scenarios are implemented on https://documenter.getpostman.com/view/4012288/TzK2bEa8 documentation.

The project uses the best coding practises (SOLID, DRY, KISS) and patterns (such as PageObject, Singleton, Factory).  
Project structure includes modules for:
 - API functionality (containing API steps, API clients, Authorization and POJO modules)
 - Configuration and Driver management (Driver initialization, Browser factory, Setting up config data)
 - Page objects (UI methods and locators)
 - Step definitions (Cucumber steps implementations)
 - Test runners (TestNG, Cucumber & Extent report plugin integration)
 - Utility module (Helper classes to read from file, deserialize JSON etc.)
 - Resources (BDD scenarios, Test data and property files)



