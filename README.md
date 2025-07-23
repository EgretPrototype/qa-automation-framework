
# QA Automation Framework

## Overview
This is a Java-based test automation framework built with Selenium WebDriver, TestNG, Gradle, and Allure reporting.  
It follows the Page Object Model (POM) pattern to ensure maintainable and scalable test code.

---

## Tech Stack
- Java 21+
- Selenium WebDriver 4.x
- TestNG 7.x
- Gradle build system
- Allure Reports for test reporting
- SLF4J + Log4j for logging

---

## Features
- Modular Page Object Model architecture
- Configurable browser setup via properties file
- Soft and hard assertions with TestNG
- Automated screenshot capture on test failure (via Allure listener)
- Allure reporting integrated with Gradle tasks
- Logging with SLF4J
- Sample test cases for category navigation and page title validation

---

## Getting Started

### Prerequisites
- Java JDK 17 or higher installed
- Gradle installed (or use Gradle wrapper `./gradlew`)
- Chrome browser (or any configured browser)

### Setup & Run Tests

1. Clone the repo:
   ```
   git clone https://github.com/YOUR-USERNAME/qa-automation-framework.git
   cd qa-automation-framework
   
2. Run tests with Gradle:
    ```
   ./gradlew clean test
   
3. Generate and serve Allure reports:
   ```
   ./gradlew allureReport
   ./gradlew allureServe

## Project Structure
```src/
├── main/
│    └── java/
│         ├── base/          # Base classes (BaseTest, BasePage)
│         ├── pageObjects/   # Page Object Model classes
│         └── utils/         # Utility classes (e.g., Allure listener)
└── test/
└── java/
└── tests/         # TestNG test classes
```

## Contact
- Javier Rodriguez
- Email: jr0019@gmail.com
- LinkedIn: https://www.linkedin.com/in/javier-rodriguez-40929620/

## License
This project is licensed under the MIT License - see the LICENSE file for details.