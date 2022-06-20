package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_15_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
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

	// ham nay se dung duoc khi swicth 2 tab
	public void switchToWindowByID (String parentID) {
		Set <String> listWindowID = driver.getWindowHandles();
		for (String ID: listWindowID) {
			if (!ID.equals(parentID)) {
				driver.switchTo().window(ID);
				break;
			}
		}
	}

	//ham nay se dung duoc khi switch nhieu hon 2 tab
	public void switchToWindowByTitle (String expectedPageTitle) {
		Set <String> listWindowID = driver.getWindowHandles();
		for (String ID: listWindowID
			 ) {
			driver.switchTo().window(ID);
			if (driver.getTitle().equals(expectedPageTitle))
			{
				break;
			}

		}
	}

	//ham close all tab/window
	public void closeAllWindowWithoutParent (String parentWindow) {
		Set <String> listWindowIDs = driver.getWindowHandles();
		for (String ID: listWindowIDs
		) {
			if (!ID.equals(parentWindow))
			{
				driver.switchTo().window(ID);
				driver.close();

			}
		}
		//van con lai parent
		driver.switchTo().window(parentWindow);
	}

	@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);

		//get window ID
		String formTabID = driver.getWindowHandle();
		System.out.println("Window ID " + formTabID);


		// click into google hyperlink
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		//Set khac voi List o diem List cho ghi trung, Set khong cho trung
		// ham nay chi dung khi chi co 2 tab
		Set <String> listWindowID = driver.getWindowHandles();
		for (String ID: listWindowID) {
			if (!ID.equals(formTabID)) {
				driver.switchTo().window(ID);
			}
		}

		//next step
		System.out.println("Window ID " + driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");

		closeAllWindowWithoutParent(formTabID);
		sleepInSecond(3);
	}


	public void TC_02 () {
		//page A
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);

		//page B
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		//switch to page B
		switchToWindowByTitle("Google");

		//driver dang o page B
		driver.findElement(By.name("q")).sendKeys("Selenium");
		sleepInSecond(3);

		//back to page A
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		//driver dang o page A
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);

		//switch to page C
		switchToWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(3);
		//driver dang o page C
		driver.findElement(By.id("email")).sendKeys("nhukhanh94@yahoo.com");
		sleepInSecond(2);

		//switch to page A
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

	}


	public void TC_03 () {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		driver.findElement(By.xpath("//button[@title='Compare']")).click();

		//switch to compare window
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		sleepInSecond(3);

		//click close window button
		driver.findElement(By.xpath("//span[text()='Close Window']")).click();
		sleepInSecond(2);

		//switch to main page
		switchToWindowByTitle("Mobile");
		sleepInSecond(2);
		//driver is in main page
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Mobile']")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		driver.switchTo().alert().accept();

		sleepInSecond(2);



	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
