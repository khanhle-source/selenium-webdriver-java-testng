package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_13_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	
	@BeforeMethod 
	public void beforeMethod () {
	}
	
	public int random () {
		Random rnd = new Random();
		return rnd.nextInt(999);
	}

	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void TC_01_FixedPopup() {
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(3);

		By popupLogin = By.cssSelector("div#modal-login-v1");
		Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());

		By loginButton = By.cssSelector("button.login_");
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(popupLogin).isDisplayed());

		By closeButton = By.cssSelector("div.modal.fade.in button.close");
		driver.findElement(closeButton).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());

	}





	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
