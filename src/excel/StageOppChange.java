package excel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StageOppChange {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\durga\\Downloads\\chromedriver-win32 (5)\\chromedriver-win32\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));

        // Navigate to Salesforce login page
        driver.get("https://login.salesforce.com/");

        // Log in to Salesforce
        wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).sendKeys("salesforcedp@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Prasad@1111");
        driver.findElement(By.id("Login")).click();
        driver.manage().window().maximize();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-target-selection-name=\"181c7013df09423d8f755b49c067f6f3\"]"))).click();
        Thread.sleep(6000);
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Search apps and items')]")));
        searchInput.sendKeys("Opportunities");
        Thread.sleep(6000);
        WebElement searchResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@data-label,'Opportunities')]//*[@class='slds-truncate']")));
        searchResult.click();
        Thread.sleep(6000);
        
        WebElement opportunity = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title=\"Example2\"]")));
        opportunity.click();
        Thread.sleep(6000);

        // Ensure the "Value Proposition" stage is visible
        WebElement valuePropositionStage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title slds-path__title' and text()='Closed']")));
        
        // Use Actions to move to the element and click
        Actions actions = new Actions(driver);
        actions.moveToElement(valuePropositionStage).click().perform();

        // Perform further actions here if needed
        
        WebElement closedStageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='slds-button slds-button--brand slds-path__mark-complete stepAction active uiButton' and .//span[text()='Select Closed Stage']]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closedStageButton);

        
        
        
        WebElement searchResult1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"stepAction required-field select\"]")));
        searchResult1.click();
        Thread.sleep(2000);
        
      
        WebElement selectElement2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@label=\"Closed Won\"]")));
        selectElement2.click();    
        
        
        
        WebElement Save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Save']")));
        Save.click();
		
	}

}
