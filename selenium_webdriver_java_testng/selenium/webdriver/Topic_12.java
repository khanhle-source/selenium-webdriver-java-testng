package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_12 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explixitWait;
	By dualZoneCheckbox = By.cssSelector("input#eq5");
	
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod 
	public void beforeMethod () {
	
	}
	
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public int random () {
		Random rnd = new Random();
		return rnd.nextInt(999);
	}

	public void CheckAllCheckbox (List <WebElement> checkboxes) {
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
			Assert.assertTrue(checkbox.isSelected());
		}
	}
	
	public void UnCheckAllCheckbox (List <WebElement> checkboxes) {
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", checkbox);
				checkbox.click();
				sleepInSecond(2);
			}
			Assert.assertFalse(checkbox.isSelected());
		}
	}
	
	public boolean isElementSelected (By by) {
		if (driver.findElement(by).isSelected()) {
		return true;
		}
		else
		return false;
	}

	@Test
	public void TC_01_Default_Checkbox() {
		// the input co kich thuoc (4 duong ke tao thanh o vuong) thi duoc goi la default 
		
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(3);
		
		// accept cookie
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
		sleepInSecond(3);
		
		//select
		driver.findElement(dualZoneCheckbox).click();
		Assert.assertTrue(isElementSelected(dualZoneCheckbox));
		
		// de-select
		driver.findElement(dualZoneCheckbox).click();
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
		
	}
	
	
	@Test
	public void TC_02_Multiple_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		sleepInSecond(5);
		
		//select All
		List<WebElement> checkboxesGithub = driver.findElements(By.cssSelector("input[type='checkbox']"));
		CheckAllCheckbox(checkboxesGithub);
		UnCheckAllCheckbox(checkboxesGithub);
	}
	



	@AfterClass
	public void afterClass() {
		driver.quit();
	} 
}
