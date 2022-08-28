package testng.tech;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.listener.Report_TestNG;

import java.util.concurrent.TimeUnit;
@Listeners(Report_TestNG.class)
public class Topic_08_Listener {
    public static WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Actions action;

    @BeforeClass
    public void beforeClass () {
        System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();

       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
       driver.manage().window().maximize();
   }
   @Test
    public void TC_01_Login() {
       driver.get("http://live.techpanda.org/index.php/customer/account/login/");
       driver.findElement(By.id("email")).sendKeys("khanh@gmail.com");
       driver.findElement(By.id("pass")).sendKeys("12345678");
       driver.findElement(By.id("send2")).click();
   }

@AfterClass
    public void afterClass () {
       driver.quit();
}
}

