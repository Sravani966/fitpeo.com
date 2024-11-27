package datadriventesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Fitpeo_1 {
	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "./softwares/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Apply implicit wait for all elements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Step 1: Navigate to the FitPeo Homepage
		driver.get("https://fitpeo.com"); // Replace with the actual website URL
		System.out.println("Navigated to FitPeo homepage.");

		// Add sleep to ensure the page has fully loaded
		Thread.sleep(2000); // 2 seconds pause

		// Step 2: Click on the "Revenue Calculator" Link
		WebElement calculatorLink = driver.findElement(By.linkText("Revenue Calculator")); // Adjust the locator as
																							// needed
		calculatorLink.click();
		System.out.println("Clicked on the Revenue Calculator link.");

		// Step 3: Wait for the "slider" element to load
		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement slider = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@min='0' and @max='2000'])[1]")));
		System.out.println("Slider is visible on the page.");
		
		Actions actions = new Actions(driver);
		actions.clickAndHold(slider).moveByOffset(-200,0).release().perform();

		actions.clickAndHold(slider).moveByOffset(124,0).release().perform();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = arguments[1];", slider, 820); // Set exact value


		// Verify the slider value
		String sliderValue = slider.getAttribute("value");
		if (!sliderValue.equals("820")) {

			throw new Exception("Slider value not set to 820. Current value: " + sliderValue);
		}

		System.out.println("Slider value updated to 820.");

		// Step 5: Update the Text Field to 560
		WebElement textField = driver.findElement(By.xpath("//input[@type='number']")); // Adjust the selector
		textField.click();
		textField.sendKeys(Keys.CONTROL + "a");
		textField.sendKeys(Keys.CONTROL + "x");
		textField.sendKeys("560");
		Thread.sleep(1000); // Pause to allow field update

		// Validate the updated text field value
		String updatedTextFieldValue = textField.getAttribute("value");
		if (!updatedTextFieldValue.equals("560")) {
			throw new Exception("Text field value not updated to 560. Current value: " + updatedTextFieldValue);
		}
		System.out.println("Text field value updated to 560.");
		Thread.sleep(1000);

		// Step 6: Perform click actions on checkboxes
		WebElement cd = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
		cd.click();
		WebElement cd1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
		cd1.click();
		WebElement cd2 = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]"));
		cd2.click();
		WebElement cd3 = driver.findElement(By.xpath("(//input[@type='checkbox'])[8]"));
		cd3.click();

		Thread.sleep(2000);

		// Step 7: Validate Total Recurring Reimbursement
		WebElement dollarSymbol = driver
				.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-12bch19'])[3]"));

		Thread.sleep(2000);

		// Get the actual value from the page
		String actualValue = dollarSymbol.getText(); // Combines "$" + "110700"

		// Expected value
		String expectedValue = "$110700";

		// Validation
		if (actualValue.equals(expectedValue)) {
			System.out.println("Total Recurring Reimbursement is correct: " + actualValue);
		} else {
			System.err.println("Total Recurring Reimbursement is incorrect. Found: " + actualValue);
		}
		driver.quit();
	}
}


