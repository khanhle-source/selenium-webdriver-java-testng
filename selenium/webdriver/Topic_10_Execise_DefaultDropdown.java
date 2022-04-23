package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_10_Execise_DefaultDropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select selectCountry;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	
	@BeforeMethod 
	public void beforeMethod () {
		driver.get("https://www.rode.com/wheretobuy");
	}
	
	public int random () {
		Random rnd = new Random();
		return rnd.nextInt(999);
	}

	@Test
	public void TC_01() {
		selectCountry = new Select(driver.findElement(By.id("country")));
		
		//Verify is multiple select or not
		Assert.assertFalse(selectCountry.isMultiple());
		
		// select value vietnam in dropdown list
		selectCountry.selectByVisibleText("Vietnam");
		// Verify vietnam is selected
		Assert.assertEquals(selectCountry.getFirstSelectedOption().getText(), "Vietnam");
		
		// Click search button
		driver.findElement(By.xpath("//button[@class=\'btn btn-default\']")).click();

		
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
