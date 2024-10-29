package linkedin_job_scraper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LinkedInJobScraper
{
    public static void main(String[] args) {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();
        driver.get("https://www.linkedin.com/login");

        // Wait for the login elements to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        // Enter your LinkedIn credentials
        WebElement emailField = driver.findElement(By.id("username"));
        emailField.sendKeys("username@gmail.com"); // Replace with your LinkedIn email
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Password_Example"); // Replace with your LinkedIn password
        passwordField.submit();

        // Wait for the LinkedIn homepage to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, 'Search')]")));

        // Navigate to LinkedIn Jobs
        // driver.get("https://www.linkedin.com/jobs/");

        // Wait for the search box to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"global-nav-typeahead\"]/input")));

        // Define job titles to search for
        String[] jobTitles = {"QA Engineer", "QC Engineer", "Software Tester", "Software Engineer",
                "Front End Engineer", "Back End Engineer", "Full Stack Engineer",
                "Fullstack Web Developer", "Fullstack Web Developer"};

        // Create a workbook for Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("LinkedIn Job Listings");

            // Create header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Index");
            header.createCell(1).setCellValue("Job Title");
            header.createCell(2).setCellValue("Job Link");
            header.createCell(3).setCellValue("Date Posted");
            header.createCell(4).setCellValue("Company Name");
            header.createCell(5).setCellValue("Job Description");

            int index = 1;

            // Loop through each job title
            for (String jobTitle : jobTitles) {
                // Search for jobs
                WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"global-nav-typeahead\"]/input"));
                searchBox.clear(); // Clear previous input
                searchBox.sendKeys(jobTitle);
                //searchBox.submit();
                // Simulate pressing the Enter key
                searchBox.sendKeys(Keys.ENTER);

                // Wait for results to load
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-reusables__filters-bar\"]/ul/li[1]/button")));

                // Wait for the login elements to load
                //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-reusables__filters-bar\"]/ul/li[1]/button")));

                WebElement linkElement = driver.findElement(By.xpath("//*[@id=\"search-reusables__filters-bar\"]/ul/li[1]/button"));
                linkElement.click();

                // Wait for results to load
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jobs-search-results")));

                // Scrape job details
                List<WebElement> jobElements = driver.findElements(By.cssSelector(".job-card-container"));

                for (WebElement jobElement : jobElements)
                {
                    try {
                        String title = jobElement.findElement(By.cssSelector("h3")).getText();
                        String link = jobElement.findElement(By.cssSelector("a")).getAttribute("href");
                        String datePosted = jobElement.findElement(By.cssSelector("time")).getText(); // Adjust based on the HTML structure
                        String companyName = jobElement.findElement(By.cssSelector(".job-card-container__company-name")).getText();
                        String jobDescription = jobElement.findElement(By.cssSelector(".job-card-list__description")).getText();

                        // Write to Excel
                        Row row = sheet.createRow(index++);
                        row.createCell(0).setCellValue(index - 1); // Index
                        row.createCell(1).setCellValue(title); // Job Title
                        row.createCell(2).setCellValue(link); // Job Link
                        row.createCell(3).setCellValue(datePosted); // Date Posted
                        row.createCell(4).setCellValue(companyName); // Company Name
                        row.createCell(5).setCellValue(jobDescription); // Job Description

                        System.out.println((index - 1) + ": " + title + " - " + link); // Print for verification
                    } catch (Exception e) {
                        System.out.println("Error parsing job details: " + e.getMessage());
                    }
                }
            }

            // Write to Excel file
            try (FileOutputStream outputStream = new FileOutputStream("E:/LinkedIn_Job_Listings.xlsx")) {
                workbook.write(outputStream);
            }
            System.out.println("Data has been written to Excel file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit(); // Close the browser
        }
    }
}
