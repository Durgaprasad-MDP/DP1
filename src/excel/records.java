package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class records {

	
		public static void main(String[] args) throws InterruptedException, IOException {
	        // Set the path to your ChromeDriver executable
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\durga\\Downloads\\chromedriver-win32 (5)\\chromedriver-win32\\chromedriver.exe");

	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--disable-notifications");
	        WebDriver driver = new ChromeDriver(options);
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
	 
	        // Navigate to Salesforce login page
	        driver.get("https://login.salesforce.com/");
	 
	        // Log in to Salesforce
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).sendKeys("salesforecdp@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("Prasad@1111");
	        driver.findElement(By.id("Login")).click();
	        driver.manage().window().maximize();
	 
	        // Load Excel file
	        FileInputStream fis = new FileInputStream(new File("C:\\Users\\durga\\OneDrive\\Documents\\excelrecords.xlsx"));
	        Workbook workbook = new XSSFWorkbook(fis);
	        Sheet sheet = workbook.getSheetAt(0);
	        
	        // Loop through rows and retrieve data
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            if (row != null) {
	                String firstName = row.getCell(0).getStringCellValue();
	                String lastName = row.getCell(1).getStringCellValue();
	                //String company = row.getCell(2).getStringCellValue();
	 
	                // Navigate to Leads
	                // Your existing Selenium code for navigating to Leads goes here
	 
	                // Click on the New button to create a new lead
	                WebElement newButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='New']")));
	                newButton.click();
	                Thread.sleep(6000);
	 
	                // Enter first name
	                WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='firstName']")));
	                firstNameField.click();
	                firstNameField.sendKeys(firstName);
	 
	                // Enter last name
	                WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='lastName']")));
	                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", lastNameField);
	                lastNameField.click();
	                lastNameField.sendKeys(lastName);
	 
	                WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='SaveAndNew']")));
	                saveButton.click();
	                Thread.sleep(1000);
	            }
	        }
	 
	        // Close the workbook and browser after creating leads
	        workbook.close();
	        driver.quit();
	    }
	 
	 
	}
	          