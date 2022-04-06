package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Element_Method {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Browser ()
	{
		//cac ham/method thao tac voi browser thong qua bien driver
		driver.get("https://www.facbook.com");
		
		//dong browser 1 tab
		driver.close();
		
		//dong tat ca cac tab va quit browser 
		driver.quit();
		
		//tim 1 element tren page, tra ve la 1 WebElement
		WebElement EmailTextbox =  driver.findElement(By.id("email"));
		
		
		//tim hon 1 element tren page, tra ve la 1 list WebElement
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		
		//lay ra url hien tai
		String currentURL = driver.getCurrentUrl();
		
		//Verify tuyet doi 
		Assert.assertEquals(currentURL,"https://www.facbook.com");
		
		//lay ra source code cua page hien tai, verify tuong doi gia tri nao do trong page
		String sourceCode = driver.getPageSource();
		Assert.assertTrue(sourceCode.contains("Welcome"));
		
		//lay ra title cua page hien tai
		//Tip: When inspect element in web, go to Console tab and input document.title will return title of the page
		String title = driver.getTitle();
		Assert.assertEquals(title, "Facebook");
		
		//WebDriver API - Windows/ Tab
		//tra ve 1 ID cua tab hien tai
		String signUpTabID = driver.getWindowHandle();
		
		//tra ve ID cua tat ca cac tab dang co (khong trung nhau}
		Set<String> allTabID = driver.getWindowHandles();
		
		//xu ly cookie
		Set<Cookie> cookie = driver.manage().getCookies();
		
		//tra ve log cua browser
		driver.manage().logs();
		
		//time cua viec findElement/ findElements
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		//time de page duoc load xong 
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		//time de 1 doan async script duoc thuc thi thanh cong (JavascriptExecutor)
		driver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
		
		// view full screen
		driver.manage().window().fullscreen();
		
		// maximinze screen 
		driver.manage().window().maximize();
		
		//lay ra vi tri so voi do phan giai cua man hinh
		Point point = driver.manage().window().getPosition();
		
		//set gia tri cho point
		Point a = new Point (0,250);
		driver.manage().window().setPosition(a);
		
		//lay ra chieu rong, chieu cao cua browser
		driver.manage().window().getSize();
		
		//set chieu rong, chieu cao cho browser
		driver.manage().window().setSize(new Dimension (1920,1080));
		
		//quay lai trang truoc do
		driver.navigate().back();
		
		//toi trang phia truoc
		driver.navigate().forward();
		
		//refresh lai trang
		driver.navigate().refresh();
		
		//navigate to
		driver.navigate().to("https://www.facebook.com");
		
		//WebDriver API - Alert / Authentication Alert 
		driver.switchTo().alert();
		
		//WebDriver API - Frame/Iframe
		driver.switchTo().frame(1);
		
		
		
		
		
		
		
	}
}