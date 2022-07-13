package webdriver;

import org.openqa.selenium.By;
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

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_18_Mix_Implicit_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		//driver = new FirefoxDriver ();

		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

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

	public String getTimeNow () {
		Date date = new Date();
		return date.toString();
	}


	public void TC_01_Element_Found() {
		driver.get("https://facebook.com/");
		By emailIDBy = By.id("email");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Start implicit: " + getTimeNow());
		driver.findElement(emailIDBy).isDisplayed();
		System.out.println("End implicit: " + getTimeNow());

		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit: " + getTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		System.out.println("End explicit: " + getTimeNow());

	}


	public void TC_02_Element_Not_Found_Only_Implicit () {
		driver.get("https://facebook.com/");
		By emailIDBy = By.id("vietnam");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Start implicit: " + getTimeNow());
		// neu khong co try catch thi test se fail (chua dc chay toi doan print End implicit)
		try {
			driver.findElement(emailIDBy).isDisplayed();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End implicit: " + getTimeNow());

		}

	public void TC_03_Element_Not_Found_Only_Explicit () {
		driver.get("https://facebook.com/");
		By emailIDBy = By.id("vietnam");

		explicitWait = new WebDriverWait(driver, 15); //implicit = 0
		System.out.println("Start explicit: " + getTimeNow());

		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End explicit: " + getTimeNow());

	}

	public void TC_04_Element_Not_Found_Mix_1 () {
		driver.get("https://facebook.com/");
		By emailIDBy = By.id("vietnam");

		// 1. Implicit < Explicit
		// Implicit khong bi anh huong boi bat ky loai wait nao khac
		// Explicit dang bi implicit anh huong len no (async), vi trong ham visibilityOfElementLocated co goi findElements: nen implicit se kich hoat chay song song voi explicit va co the chenh nhau 0.5s
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Start implicit: " + getTimeNow());
		try {
			driver.findElement(emailIDBy).isDisplayed();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End implicit: " + getTimeNow());

		explicitWait = new WebDriverWait(driver, 10);
		System.out.println("Start explicit: " + getTimeNow());

		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End explicit: " + getTimeNow());
		// 2. Implicit = Explicit

		//3. Implicit > Explicit
	}

	@Test
	public void TC_04_Element_Not_Found_Mix_2 () {
		driver.get("https://facebook.com/");
		By emailIDBy = By.id("vietnam");

		// 1. Implicit = Explicit
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Start implicit: " + getTimeNow());
		try {
			driver.findElement(emailIDBy).isDisplayed();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End implicit: " + getTimeNow());

		explicitWait = new WebDriverWait(driver, 5);
		System.out.println("Start explicit: " + getTimeNow());

		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End explicit: " + getTimeNow());

	}

	public void TC_04_Element_Not_Found_Mix_3 () {
		driver.get("https://facebook.com/");
		By emailIDBy = By.id("vietnam");

		// 1. Implicit < Explicit
		// khi nao dat du timeout cua ca 2 thi dung lai
		// trong luc explicit chay, 2 loai wait se chay song song (async) vi trong explicit co FindElement ma FindElement bi anh huong boi implicit
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		System.out.println("Start explicit: " + getTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End explicit: " + getTimeNow());

	}

	@Test
	public void TC_05_Element_Not_Found_Only_Explicit () {
		driver.get("https://facebook.com/");
		WebElement emailIDTextBox = driver.findElement(By.id("vietnam"));

		explicitWait = new WebDriverWait(driver, 15); //implicit = 0
		System.out.println("Start explicit: " + getTimeNow());
		// neu khong co try catch se fail test case tai vi visibility of(EmailIDTextBox) khong ton tai
		// nen trong truong hop nay no se vao catch de in ra loi
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(emailIDTextBox));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End explicit: " + getTimeNow());

		// test case nay se fail o dong 197, 0s, tra loi No such element found: Unable to found #vietnam

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
