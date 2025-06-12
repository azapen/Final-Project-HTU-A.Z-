# Booking.com QA Automation Framework

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-Automation-brightgreen)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-Testing-red)](https://testng.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)](LICENSE)

---

## Project Summary

This project presents a complete end-to-end automated testing framework for Booking.com, designed and implemented as part of the Upskilling Program at Al Hussein Technical University (HTU). The framework is built using Java, Selenium WebDriver, and TestNG, with a strong emphasis on multilingual testing (English and Arabic), UI responsiveness, form validation, and payment process simulations.

It adheres to best practices in QA automation, including the Page Object Model (POM) design pattern, modular test case development, and robust exception handling. The purpose is to reduce manual testing efforts, improve regression cycles, and ensure high-quality user experiences on one of the most trafficked travel platforms.

---

## Repository Structure

BookingAutomation/
├── src/
│ ├── main/java/
│ │ ├── pages/ # Page Object classes
│ │ ├── utils/ # Utilities and test data generators
│ ├── test/java/
│ │ └── tests/ # TestNG test cases
├── testng.xml # Test suite configuration
├── pom.xml # Maven build file
├── README.md # Project documentation
└── reports/ # TestNG HTML reports (auto-generated)

yaml
Copy
Edit

---

## Automated Test Coverage

The following features and flows are validated through automated tests:

### Functional Areas Covered

- Language & Currency Selection (English and Arabic support)
- Hotel Search Functionality with city, date, and guest filters
- Booking Form validation (positive and negative scenarios)
- Payment Page iframe simulation and input interaction
- Email Subscription & Pop-up Handling
- Edge Case Testing (e.g., invalid dates, overbooking)

### Sample Test Cases

| Test Case ID | Description                                         | Status |
|--------------|-----------------------------------------------------|--------|
| TC01         | Verify language toggle (English ↔ Arabic)           | Pass   |
| TC04         | Booking confirmation popup validation               | Pass   |
| TC07         | Invalid date entry triggers validation message      | Pass   |
| TC10         | Newsletter subscription with invalid email format   | Pass   |

---

## Technologies Used

| Category         | Tool/Technology        |
|------------------|------------------------|
| Language         | Java (JDK 17)          |
| Automation Tool  | Selenium WebDriver     |
| Test Framework   | TestNG                 |
| Build Tool       | Maven                  |
| Design Pattern   | Page Object Model (POM)|
| IDE              | Eclipse / IntelliJ     |
| Browser Tested   | Google Chrome (v>95)   |
| Reporting        | TestNG HTML reports    |

---

## Getting Started

### Prerequisites

- Java JDK 17+
- Maven 3.6+
- ChromeDriver (matching your Chrome version)
- Eclipse / IntelliJ IDEA
- Git (optional for version control)

### Installation & Execution

1. Clone the repository
   ```bash
   git clone https://github.com/azapen/Final-Project-HTU-A.Z-.git
Navigate to the project directory

bash
Copy
Edit
cd Final-Project-HTU-A.Z-
Run the test suite via TestNG

Open the project in Eclipse or IntelliJ

Locate testng.xml

Right-click > Run As > TestNG Suite

View Reports

Navigate to /test-output/index.html to view the results in your browser.

Future Enhancements
Integrate with CI/CD pipelines (e.g., Jenkins, GitHub Actions)

Implement Allure or ExtentReports for detailed reporting

Add data-driven testing using Excel/JSON inputs

Expand to mobile/responsive layout testing (e.g., Appium, Selenium Grid)

Capture screenshots on test failure for better debugging

Contribution Guidelines
While this is a final academic project, contributions or suggestions are welcome.

Fork the repository

Create a new branch: git checkout -b feature-name

Commit your changes: git commit -m "Added feature"

Push to the branch: git push origin feature-name

Create a pull request

References
Refer to the full project report here (if uploaded) for detailed documentation, design approach, test strategy, and screenshots.

License
This project is licensed under the MIT License.

Author
Ahmad Zapen
Upskilling Program - Al Hussein Technical University (HTU)

Supervised by:

Eng. Abdulraheem Alaska

Dr. Basheer Mufleh

Coach. Madonna Samawi
