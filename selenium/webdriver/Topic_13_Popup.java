package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_13_Popup {
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
// fixpopup khac voi random popup o cho phai co if else vi random popup se mat trong DOM khi click x

	public void TC_02_FixedPopup () {
		driver.get("https://jtexpress.vn/");
		sleepInSecond(15);
		By FixedPopup = By.xpath("//ul/a[@href='https://webinar.jtexpress.vn/']");
		By closeButton = By.cssSelector("div.w-auto.mx-auto.relative button");
		if (driver.findElement(FixedPopup).isDisplayed())
		{
			driver.findElement(closeButton).click();
			sleepInSecond(3);
		}

		Assert.assertFalse(driver.findElement(FixedPopup).isDisplayed());
	}


	public void TC_03_RandomPopup () {
		driver.get("https://www.kmplayer.com/home");
		// se co th khong vao if va khong vao else: khi driver.findelement(By.id()) khong tim thay (khong nam trong DOM)

		if(driver.findElement(By.id("layer2")).isDisplayed())
		{
			jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("area#btn-r")) );
		}
		else {
			//neu popup khong hien thi thi qua step tiep theo
		}
	}

	@Test
	public void TC_04_Random_Not_In_DOM_Popup ()
	{
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);
		List <WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));
		//Step 2 -
		// khong the dung findElement isdisplay nhu tc tren vi findelement khong co trong DOM se bi fail (khong the tra ve true or false)
		if (popupContent.size() > 0 ) {
			System.out.println("Case 1 - Popup hien thi - thao tac - close popup");

			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Khanh");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("khanh@yahoo.com");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("01234456789");

			//close popup
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(3);
			//verify popup is display = false
			Assert.assertEquals(driver.findElements(By.cssSelector("div.popup-content")).size(), 0);
		}
		else {
			System.out.println ("Case 2 - Popup khong hien thi va qua step tiep theo");

		}
		//Step 3, step 4: verify tiep cac buoc sau


	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
