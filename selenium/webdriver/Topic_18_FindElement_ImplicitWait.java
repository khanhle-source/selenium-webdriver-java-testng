package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_18_FindElement_ImplicitWait {
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

		driver.get("https://facebook.com/");

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

	public void TC_01_Find_Element () {
		// - Co duy nhat 1 element
		// Neu element xuat hien ngay > tra ve element do, khong can cho het timeout
		// Neu element chua xuat hien > moi 0.5s se tim lai cho den khi het timeout
		// 		System.out.println ("Start time: " + getCurrentTime());
		//  	driver.findElement (By.xpath("//input[@name='firstname']"));
		//  	System.out.println ("End time: " + getCurrentTime());

		// - Khong co element nao het
		// No se tim di tim lai cho den khi het timeout
		// Sau khi het timeout se danh fail test case nay, loi throw exception: No such element
		// Khong chay cac step con lai
		// 		System.out.println ("Start time: " + getCurrentTime());
		//  	driver.findElement (By.xpath("//input[@name='abcdef']"));
		//  	System.out.println ("End time: " + getCurrentTime());


		// - Co nhieu hon 1 element
		// No se lay ra element dau tien de thao tac
		// 		driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).click();

	}

	public void TC_02_Find_Elements () {
		int elementNumber = 0;
		// - Co duy nhat 1 element
		// - Co nhieu hon 1 element
		// Neu element xuat hien ngay > tra ve element do ko can cho het timeout
		// Neu element chua xuat hien > sau 0.5s se tim lai cho den het timeout
		elementNumber = driver.findElements(By.xpath("//input[@id='email']")).size();
		System.out.println("1 element: " + elementNumber);

		elementNumber = driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).size();
		System.out.println("n element: " + elementNumber);

		// - Khong co element nao het
		// Tim di tim lai cho den khi het timeout
		// Sau khi het timeout, KO danh fail, van chay cac step tiep theo
		System.out.println("Start time" /*+ getcurrentTime()*/);
		elementNumber = driver.findElements(By.xpath("//input[@name='abcdef']")).size();
		System.out.println("0 element: " + elementNumber);
		System.out.println("Start time" /*+ getcurrentTime()*/);

	}

	@Test
	public void TC_01 () {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// implicitWait chi anh huong truc tiep toi findelement va findelements
			// nen dat no truoc hay sau driver.get deu khong anh huong
			driver.get("https://automationfc.github.io/dynamic-loading/");
			driver.findElement(By.cssSelector("div#start>button")).click();

			Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}


	public void TC_02 () {
		}

	public void TC_03 () {
	}


	public void TC_04 () {
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
