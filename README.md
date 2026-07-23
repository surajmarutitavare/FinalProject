# Practo Test Automation 🏥

## What is This?

A tool that **automatically tests** the Practo website (hospital/health app). Instead of clicking manually, this tool tests features on its own.

--------------------------------------------------------------------------------------------------------------------------------------------------------

## What Does It Test? ✅

1. **Hospital Search** - Find hospitals, check ratings & parking
2. **Lab Tests** - Get list of cities offering lab tests
3. **Wellness Form** - Fill forms and check validations
4. **Reports** - Generate test results in HTML

---

## What You Need to Install

1. **Java 21** - [Download here](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
2. **Maven** - [Download here](https://maven.apache.org/download.cgi)

Check if installed:
```bash
java -version
mvn -version
```

---

## How to Setup (3 Steps)

### Step 1: Download Project
```bash
git clone https://github.com/your-repo/FinalProject.git
cd FinalProject
```

### Step 2: Install Libraries
```bash
mvn clean install
```

### Step 3: Update Test Data
Edit `src/main/resources/config.properties`:
```properties
browser=chrome
url=https://www.practo.com/
name=Your Name
organization=Your Org
contactNumber=1234567890
emailId=your@email.com
```

---

## Where Are Results?

After tests finish, open:
**Reports/PractoReport.html** - Shows pass/fail results with screenshots

---

## Folder Structure

```
FinalProject/
├── src/main/java/
│   ├── pages/              → Website interactions (SearchPage, etc)
│   ├── utils/              → Helper tools (DriverFactory, ConfigReader, etc)
│   └── reports/            → Report management
├── src/test/java/
│   ├── base/BaseTest.java  → Base setup for all tests
│   └── tests/PractoTest.java → Actual tests to run
├── src/main/resources/
│   ├── config.properties   → Test settings
│   └── log4j2.xml          → Logging settings
├── logs/                   → Test logs
└── Reports/                → Test results in HTML
```

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Test Details

Tests run in this order:

| Order | Test | Does What |
|-------|------|-----------|
| 1 | verifyUrl() | Check website loads |
| 2 | verifyHospitalSearch() | Search hospitals |
| 3 | verifyTopCities() | Get cities list |
| 4 | validateCorporateWellnessForm() | Check form validation |

---

## Main Files Explained

| File | Purpose |
|------|---------|
| **BaseTest.java** | Setup before each test, cleanup after |
| **PractoTest.java** | Contains all test methods |
| **DriverFactory.java** | Creates browser instance |
| **ConfigReader.java** | Reads test data from config file |
| **ExcelUtility.java** | Writes results to Excel |
| **SearchPage.java** | Hospital search logic |
| **LabTestPage.java** | Lab tests logic |
| **CorporateWellness.java** | Form handling logic |

---


## Run Your First Test

```bash
# 1. Go to project folder
cd FinalProject

# 2. Run tests
mvn test

# 3. View results
Open: Reports/PractoReport.html
```

---

**Last Updated:** July 23, 2026 
**Happy Testing! 🚀**
