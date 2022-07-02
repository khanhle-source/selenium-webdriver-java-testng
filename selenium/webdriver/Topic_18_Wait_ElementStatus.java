package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_18_Wait_ElementStatus {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		//driver = new FirefoxDriver ();

		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://www.facebook.com/");
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


	public void TC_01_Visible () {
		// Visible: co tren UI va co trong DOM/HTML
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

	}


	public void TC_02_Invisible_In_DOM () {
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		//Invisible: Ko co tren UI, co trong DOM
		//dong registation form lai thi no khong con tren UI nua
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);

		//chay mat 15s : bi anh huong boi timeout cua explicitWait
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		// Khong hien thi webelement nen driver se wait cho den het timeout > FAILED > 20s
		// vi dieu kien bat buoc cua findElement la fai co trong DOM
		// neu khong tim dc, no se tim di tim lai cho den khi het 20s
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
	}

	public void TC_03_Presence () {
		// Presence: co trong DOM va co tren UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		//Presence: co trong DOM va khong co tren UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

	}

	@Test
	public void TC_04_Staleness () {
		//Open registation form
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		//tai thoi diem nay element dang co trong DOM
		// luu gia tri webelement nay lai
		WebElement confirmationEmailAddressTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));

		//dong registation form
		// thi webelement nay khong con co tren DOM va UI nua
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

		// Wait cho confirmation email address textbox khong con trong DOM nua
		explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmailAddressTextbox));

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
