package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_17_UploadFile {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String file1 = projectPath + File.separator + "browserDrivers" + File.separator +"FileUpload" + File.separator + "Screenshot.png";
	String file2 = projectPath + File.separator + "browserDrivers" + File.separator +"FileUpload" + File.separator + "Image_from_IOS.MP4";
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		//driver = new FirefoxDriver ();

		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

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


	public void TC_01 () {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.cssSelector("input[name='files[]']"));
		//thuoc tinh multiple trong HTML de define rang co duoc upload multiple file hay khong, neu khong co thi chi upload dc 1 file
		//ap dung cho the input co type=file

		//truyen bang duong dan tuyet doi, nhung chi dung khi chay tren 1 may
		//uploadFile.sendKeys("/Users/hubblemacbookpro/IdeaProjects/selenium-webdriver-java-testng/browserDrivers/FileUpload/Screenshot.png");

		//truyen vao duong dan tuong doi
		// File.separator se tu update / or \ tuong ung window or mac
		uploadFile.sendKeys(projectPath + File.separator + "browserDrivers" + File.separator +"FileUpload" + File.separator + "Screenshot.png");
		sleepInSecond(3);

	}

	public void TC_02_OneFile_OnceTime () {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		//upload multiple file
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(file1);
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(file2);

		//verify file upload sucessful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Screenshot.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Image_from_IOS.MP4']")).isDisplayed());

		sleepInSecond(3);

		//click Start
		driver.findElement(By.xpath("//button[@class='btn btn-primary start']//span[text()='Start upload']")).click();

		//verify start upload successfully
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Screenshot.png']")).isDisplayed());


	}


	public void TC_03_MulFile_OnceTime () {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		//upload multiple file
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(file1 + "\n" + file2);


		//verify file upload sucessful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Screenshot.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Image_from_IOS.MP4']")).isDisplayed());

		sleepInSecond(3);

		//click Start
		driver.findElement(By.xpath("//button[@class='btn btn-primary start']//span[text()='Start upload']")).click();

		//verify start upload successfully
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Screenshot.png']")).isDisplayed());


	}

	@Test
	public void TC_04_Go_File () {
		//hien thi button, khong hien thi input[@type='file'] (vi bi an di)
		//nhung khi find element van find duoc
		driver.get("https://gofile.io/uploadFiles");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(file1);
		sleepInSecond(15);

		//verify upload successfully

		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());

		//click on download page
		String downloadURL = driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href");
		driver.get(downloadURL);


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
