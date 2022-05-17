package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_11_Button_RadioButton {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	By logintab = By.cssSelector("li.popup-login-tab-login");
	By loginButton = By.cssSelector("button.fhs-btn-login");
	By email = By.id("login_username");
	By password = By.id("login_password");
	By errorMessageEmail = By.xpath("//div[@class='fhs-input-box checked-error']/div[@class='fhs-input-alert']");
	By errorMessagePass = By.xpath("//div[@class='fhs-input-box fhs-input-display checked-error']/div[@class='fhs-input-alert']");
	
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
		driver.get("https://www.fahasa.com/customer/account/create");
	}
	
	
	@Test
	public void TC_01_() {
		// Cach 1: goi ham click cua webelement 
		//driver.findElement(loginButton).click();
		// Cach 2: viet ham JS script
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(logintab));
		
		// verify login button disable 
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		//input username and password and verify login button enable
		driver.findElement(email).sendKeys("nhukhanhle@gmail.com");
		driver.findElement(password).sendKeys("12345678");
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		// Verify background color is RED
		String backgroundColorRGB = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println ("RGB color = " + backgroundColorRGB);
		
		//Assert.assertEquals(backgroundColorRGB, "rgb(201, 33, 39");
		
		//convert to Hexa
		String backgroundColorHexa = Color.fromString(backgroundColorRGB).asHex();
		System.out.println ("Hexa color = " + backgroundColorHexa);
		
		Assert.assertEquals(backgroundColorHexa.toUpperCase(), "#C92127");
		
		sleepInSecond(2);
		
		driver.navigate().refresh();
		driver.findElement(logintab).click();
		
		//remove attribute by JS script 
		jsExecutor.executeScript("document.querySelector('button.fhs-btn-login').removeAttribute('disabled')");
		
		driver.findElement(loginButton).click();
		
		// verify error message 
		Assert.assertEquals(driver.findElement(errorMessageEmail).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(errorMessagePass).getText(), "Thông tin này không thể để trống");
		
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



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
