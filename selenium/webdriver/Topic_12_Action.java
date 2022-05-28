package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_12_Action {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explixitWait;
	Actions action;
	String osname = System.getProperty("os.name").toLowerCase();
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		action = new Actions (driver);
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


	
	public void TC_01_Hover () {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement yourAgeTextBox = driver.findElement(By.id("age"));
		action.moveToElement(yourAgeTextBox).perform();
		sleepInSecond(3);
		// verify hover text
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
	
	}
	
	
	public void TC_02_Hover () {
		driver.get("http://www.myntra.com/");
		WebElement kids = driver.findElement(By.xpath("//header//a[text()=\"Kids\"]"));
		action.moveToElement(kids).perform();
		sleepInSecond(3);
		action.click(driver.findElement(By.xpath("//a[text()=\"Home & Bath\"]"))).perform();
		// action.click khac voi .click nhu the nao
		// action.click la ham cua thu vien action - no se move to element roi click
		//driver.findElement(By.xpath("//a[text()=\"Home & Bath\"]")).click();
		// verify
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()=\"Kids Home Bath\"]")).isDisplayed());
	}
	

	public void TC_04_ClickAndHold () {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List <WebElement> listNumber = driver.findElements(By.cssSelector("li.ui-state-default.ui-selectee"));
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(3)).release().perform();
		sleepInSecond(3);
		
		String colorRGB = driver.findElement(By.xpath("//li[text()='1']")).getCssValue("background-color");
		System.out.println ("color: " + colorRGB);
		//convert to Hexa
		String backgroundColorHexa = Color.fromString(colorRGB).asHex();
		System.out.println ("Hexa color = " + backgroundColorHexa);
				
		Assert.assertEquals(backgroundColorHexa.toUpperCase(), "#F39A14");
	}
	
	@Test 
	public void TC_04_ClickAndHold_Random () {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List <WebElement> listNumber = driver.findElements(By.cssSelector("li.ui-state-default.ui-selectee"));
		Keys control;
		if (osname.contains("win")) {
			control = Keys.CONTROL;
		}
		else
			control = Keys.COMMAND;
		// 1 and 5 and 11 
		// Send ctrl 
		// click 1
		//click 5
		// click 11
		// run command
		// key up ctrl
		
		action.keyDown(control).perform();
		action.click(listNumber.get(2)).click(listNumber.get(6)).click(listNumber.get(10)).perform();
		action.keyUp(control).perform();
		sleepInSecond(3);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	} 
}
