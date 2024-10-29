# LinkedIn Job Scraper

A Java application that automates the process of scraping job listings from LinkedIn and saves the data to an Excel file.

## Design Overview

### Architecture

The application follows a straightforward architecture:

1. **User Interface (UI):**
   - No graphical UI; it runs as a console application.
   - User inputs (LinkedIn credentials) are specified directly in the code.

2. **Web Scraping Module:**
   - Uses Selenium to automate browser interactions.
   - Scrapes job listings based on predefined job titles.

3. **Data Management:**
   - Extracted data is structured and written to an Excel file using Apache POI.

4. **Logging:**
   - Outputs relevant information and errors to the console.

### Flow Diagram

```plaintext
+---------------------+
| Start Application   |
+---------------------+
          |
          v
+---------------------+
| Set Up WebDriver    |
+---------------------+
          |
          v
+---------------------+
| Log into LinkedIn   |
+---------------------+
          |
          v
+---------------------+
| Search for Job      |
| Titles              |
+---------------------+
          |
          v
+---------------------+
| Scrape Job Details  |
+---------------------+
          |
          v
+---------------------+
| Write to Excel      |
+---------------------+
          |
          v
+---------------------+
| Close Application    |
+---------------------+

```plaintext

Components
Main Class: LinkedInJobScraper:

Initializes the WebDriver.
Handles the login process.
Manages job title searches and data extraction.
Data Structure:

Each job listing is saved in an Excel sheet with the following columns:
Index
Job Title
Job Link
Date Posted
Company Name
Job Description
Prerequisites
Java Development Kit (JDK) 21 or higher
Maven
A LinkedIn account
Getting Started
Clone the repository:

bash

git clone <repository-url>
cd linkedin_job_scraper
Update LinkedIn Credentials: Replace username@gmail.com and Password_Example in the code with your LinkedIn login credentials.

Build the Project: Run the following command to build the project and install dependencies:

bash

mvn clean install
Run the Application: You can run the application using:

bash

mvn exec:java -Dexec.mainClass="linkedin_job_scraper.LinkedInJobScraper"
Check the Output: After execution, you will find the LinkedIn_Job_Listings.xlsx file in the specified path (e.g., E:/).

Dependencies
This project uses the following dependencies:

Selenium Java for web automation
WebDriverManager for managing browser drivers
Apache POI for reading/writing Excel files
pom.xml
Here's a snippet of the pom.xml file containing the necessary dependencies:

xml

<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.25.0</version>
    </dependency>
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.9.2</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.2.3</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.3</version>
    </dependency>
</dependencies>
Important Notes
Ensure compliance with LinkedIn's Terms of Service when using this tool.
The code may need adjustments if LinkedIn changes its page structure.
Use at your own risk, as scraping may result in account restrictions.
License
This project is open-source and available under the MIT License.

Acknowledgments
Selenium
Apache POI
WebDriverManager
Feel free to contribute by submitting issues or pull requests!


### Instructions
1. Replace `<repository-url>` with the actual URL of your GitHub repository.
2. Save the file as `README.md` in the root directory of your project.

This README provides a comprehensive overview of your project, including design, installation instructions, and important notes.

Let me know if you need any further changes!

Happy Coding
