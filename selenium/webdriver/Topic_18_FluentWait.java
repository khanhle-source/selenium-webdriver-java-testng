package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Topic_18_FluentWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

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

	public void TC_01() {
		fluentDriver = new FluentWait<WebDriver>(driver);
		// tong thoi gian cho cho dieu kien
		fluentDriver.withTimeout(Duration.ofSeconds(15))
		//polling time: lặp lại để tìm điều kiện chưa thoả mãn
				.pollingEvery(Duration.ofSeconds(1))
		//nếu gặp exception là find không thấy element thì sẽ bỏ qua
				.ignoring(NoSuchElementException.class)
		// Điều kiện
				.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@name='btnI-fail']"));
		}
		});

		WebElement loginButton = driver.findElement(By.xpath(""));
		fluentElement = new FluentWait<WebElement>(loginButton);
		//setting time
		fluentElement.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(ElementNotVisibleException.class);
		//apply điều kiện và trả về String
		String loginButtonText = fluentElement.until(new Function<WebElement, String>() {
			public String apply(WebElement element) {
				return element.getText();
			}
		});
		Assert.assertEquals(loginButtonText, "");

		// apply dieu kien tra ve boolean
		boolean loginButtonStatus = fluentElement.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				return element.isDisplayed();
			}
		});
		Assert.assertTrue(loginButtonStatus);

	}

	public void TC_09_Execise() {
		//đối với fluentWait quan trọng là tìm điều kiện để nó trả về
		//nếu chỉ tìm element và check nó display hay không, thì dùng <T> là <WebDriver>
		//nếu đã khai báo element trước và  trạng thái thôi thì dùng <T> là <WebElement>
		driver.get("https://automationfc.github.io/dynamic-loading/");
		fluentDriver = new FluentWait<WebDriver>(driver);
		driver.findElement(By.cssSelector("div#start>button")).click();
		//sau khi bấm loading icon xuất hiện và mất đi trong 5s
		//icon loading biến mất = Hello World text xuất hiện
		fluentDriver.withTimeout(Duration.ofSeconds(6))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class)
				.until(new Function<WebDriver, Boolean>() {
					public Boolean apply(WebDriver driver) {
						boolean textStatus = driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
						System.out.println(textStatus);
						return textStatus;
					}
				});
	}

	// có thể dùng cac hàm để custom lai method
	// https://docs.google.com/document/d/12fm_slphnj78ZtgYPdPlx3lQzWCsih6s0EGOYhG0ftw/edit#
	// 1h16s: https://www.youtube.com/watch?v=gxYDkvs1aMg&list=PLo1QA-RK2zyryB4iZO_cXYF9OAW5SPUlt&index=39&ab_channel=AutomationFC



	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
