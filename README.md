
# QA Automation Framework

## Overview
A modular, scalable UI test automation framework built using **Java**, **Selenium**, and **JUnit 5**, with support for **Selenium Grid**, **Docker**, and **GitHub Actions CI**.

---

## Tech Stack
- **Language**: Java 21
- **Build Tool**: Gradle
- **Test Framework**: JUnit 5
- **Browser Automation**: Selenium WebDriver
- **Remote Execution**: Selenium Grid (via Docker)
- **CI/CD**: GitHub Actions
- **Logging**: SLF4J + Logback
- **Reporting**: Allure Reports

---

## Getting Started

### Prerequisites
- Java JDK 17 or higher installed
- Gradle installed (or use Gradle wrapper `./gradlew`)
- Chrome browser (or any configured browser)

### Setup & Run Tests

1. Clone the repo:
   ```
   git clone https://github.com/EgretPrototype/qa-automation-framework.git
   cd qa-automation-framework
   
2. Run tests with Gradle:
    ```
   ./gradlew clean test
   
3. Generate and serve Allure reports:
   ```
   ./gradlew allureReport
   ./gradlew allureServe
   
4. Update config.properties to set:
   ```
   browser=chrome
   remote=false
   remoteUrl=http://localhost:4444/wd/hub
   
## Remote Execution with Selenium Grid

1. Start Grid using Docker:
   ```
   docker-compose -f docker-compose.yml up -d\
2. Update config.properties:
   ```
   remote=true
   browser=chrome
   remoteUrl=http://localhost:4444/wd/hub
3. Run your tests:
   ```
   ./gradlew clean test
## Docker Compose Grid
```
# docker-compose.yml

services:
  selenium-hub:
    image: selenium/hub:4.21.0
    ports:
      - "4444:4444"

  chrome:
    image: selenium/node-chrome:4.21.0
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  firefox:
    image: selenium/node-firefox:4.21.0
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
```
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