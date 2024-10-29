<h1>LinkedIn Job Scraper</h1>

A simple Java application that automates the process of scraping job listings from LinkedIn and exports the data to an Excel file. This tool is built using Selenium for web automation and Apache POI for handling Excel files.

Features
Automates login to LinkedIn.
Searches for multiple job titles.
Scrapes job details such as title, link, date posted, company name, and job description.
Exports the scraped data to an Excel file.
Prerequisites
Java Development Kit (JDK): Version 21 or higher.
Maven: For managing project dependencies.
LinkedIn Account: Valid LinkedIn credentials for scraping job listings.
Dependencies
This project uses the following dependencies, which are defined in the pom.xml file:

Selenium Java: For web automation.
WebDriverManager: To manage the WebDriver binaries automatically.
Apache POI: For reading and writing Excel files.
SLF4J: For logging (optional).
Log4j: For advanced logging (optional).
Installation
Clone the repository:

bash
Copy code
git clone <repository-url>
cd linkedin_job_scraper
Build the project:

bash
Copy code
mvn clean install
Update LinkedIn Credentials: In LinkedInJobScraper.java, replace the placeholders with your LinkedIn email and password:

java
Copy code
emailField.sendKeys("username@gmail.com"); // Replace with your LinkedIn email
passwordField.sendKeys("Password_Example"); // Replace with your LinkedIn password
Usage
Open the command line or terminal.

Navigate to the project directory.

Run the application:

bash
Copy code
mvn exec:java -Dexec.mainClass="linkedin_job_scraper.LinkedInJobScraper"
Wait for the script to finish execution. The scraped job listings will be saved in E:/LinkedIn_Job_Listings.xlsx.

Code Overview
The main class LinkedInJobScraper handles the web scraping process.
It utilizes Selenium to control the Chrome browser, logging in to LinkedIn, and searching for job listings.
The job details are collected and written to an Excel file using Apache POI.
Important Notes
Ensure that you comply with LinkedIn's terms of service when scraping data.
Consider adding exception handling and logging for better error tracking.
Modify the job titles in the jobTitles array as needed to customize your job search.
License
This project is open-source and available under the MIT License.

Support
For any issues or feature requests, please open an issue in the repository.
