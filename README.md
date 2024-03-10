
# Selenium Java Testing Demo

## Introduction
This project demonstrates UI & functionality testing using Selenium, TestNG, and Allure frameworks. The project is built using the Page Object Model (POM) design pattern for maintainable and scalable test automation.

The framework includes the following layers:

* Pages - Contains page object classes that represent web pages under test. Encapsulates access to page elements.

* Tests - Contains test classes with test methods to validate functionality.

* Extensions - Contains reusable classes for common test actions and assertions.

* Utilities - Contains helper classes for setup, driver management, listeners etc.

* Workflows - Contains methods to execute common user workflows.

The framework verifies UI elements, texts & network calls to validate functionality and business logic.

## Setup
1. **Prerequisites**: Ensure you have [Java JDK 8+](https://www.oracle.com/il-en/java/technologies/downloads/#jdk21-windows), [Apache Maven 3.6+](https://maven.apache.org/download.cgi) & **Optional** [Allure Report](https://github.com/allure-framework/allure2/releases) properly installed on your machine. Ensure Environment variables are set up for the following:
    * JAVA_HOME - `path/to/java/jdk`
    * Path - `path/to/apache-maven/bin` & **Optional** `path/to/allure/bin`
2. **Clone the repository**: Clone this repository to your local machine using `git clone https://github.com/Zapkid/Vicarius-Selenium-Java-Demo.git`.
3. **Install dependencies & Run tests**: Run `mvn clean install -P Web` to install all necessary dependencies to your local machine & run the Web tests suite.

## Running Tests
Every test is independent of each other, and can be executed independently.
Run `mvn test -P Web` to run the Web tests suite.
Once the browser opens, tests will execute across the Vicarius.io webpages under test, Logs will be recorded to the terminal & test results will be recorded in the `allure-results` folder.
More test suites can be added to the project by creating new profiles in the `pom.xml` file, and configuring the test suite in a new TestNG xml file.

## Reporting
The project uses Allure reports, providing a clear and comprehensive representation of test execution output. 
Run `allure generate --clean` in the terminal to generate a detailed HTML report after test execution in `allure-report` folder.

Screenshots and video recordings of failed tests are also available to help with debugging. Additional reports can be found in the `/target/surefire-reports` folder, including jUnit reports with test duration and status.

## Test Coverage
#### X Tests:
* 


##### TODOs:
* Fix - asserting on network traffic on different browsers than chrome

  
#### Bugs found:


## Future Improvements
* Integrate with CI/CD pipeline for automated testing
* Report summary sent to Slack on a dedicated channel
* Implement integration to link automated tests with test cases in a test management platform


## Contributing

Contributions to the project are welcome! To contribute:

1. Fork the repository
2. Create a new branch
3. Make changes and test
4. Submit a pull request

### Estimated time worked on the project (hrs):
~  hrs
