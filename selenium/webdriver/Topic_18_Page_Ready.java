package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_18_Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		//driver = new FirefoxDriver ();

		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		driver.manage().window().maximize();

	}
	
	@BeforeMethod 
	public void beforeMethod () {
	}


	public void TC01_OrangeHRM_Implicit() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://api.orangehrm.com/");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");

	}

	public void TC01_OrangeHRM_Explicit() {
		//driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		// neu nhu khong set implicit = 0 thi dong 43 se anh huong toi tcs nay - vi nguyen tac cua implicit
		driver.get("https://api.orangehrm.com/");
	 // Wait until spinner invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
	}

	public void TC01_OrangeHRM_Page_Ready() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.get("https://api.orangehrm.com/");
		// Wait until page load success/ ready
		Assert.assertTrue(isJQueryAndPageLoadedSuccess());
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
	}

	public void TC_01_OrangeHRM_UI_Page_Ready () {
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();

		// c1: Wait page ready
		Assert.assertTrue(isJQueryAndPageLoadedSuccess());

		// c2: Wait cho element invisible
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.loadmask"))));
	}

	@Test
	public void TC_02_TestProject_Page_Ready () {
		driver.get("https://blog.testproject.io/");
		// Handle popup
		if (driver.findElement(By.cssSelector("div#mailch-bg")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}

		// hover to search textbox
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		Assert.assertTrue(isJQueryAndPageLoadedSuccess());

		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();

		Assert.assertTrue(isJQueryAndPageLoadedSuccess());

		//ham verify search result correctly?
		List<WebElement>  listResult = driver.findElements(By.cssSelector("h3.post-title a"));
		for (WebElement a: listResult) {
			Assert.assertTrue(a.getText().contains("Selenium"));
		}
	}
	// ham page ready only apply for JQuery
	public boolean isJQueryLoadedSuccess () {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active ===0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	// JQuery + Javascript
	public boolean isJQueryAndPageLoadedSuccess () {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active ===0);");
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
