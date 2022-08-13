package testng.tech;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Topic_06_Multi_Server {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Actions action;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Brower name is not valid");
                break;
        }

       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
       driver.manage().window().maximize();
   }
   @Parameters("server")
   @Test
   // neu quen de parameter cho server o file xml thi no se dung gia tri cua optional
    public void TC_01_Login(@Optional("LIVE") String serverName) {
        String serverURL = getServerURL(serverName) ;
       driver.get("http://" + serverURL + "/index.php/customer/account/login/");
       driver.findElement(By.id("email")).sendKeys("khanh@gmail.com");
       driver.findElement(By.id("pass")).sendKeys("12345678");
       driver.findElement(By.id("send2")).click();
   }

   private String getServerURL (String serverName) {
        switch (serverName) {
            case "DEV":
                serverName = "dev.techpanda.org";
                break;
            case "TESTING":
                serverName = "testing.techpanda.org";
                break;
            case "LIVE":
                serverName = "live.techpanda.org";
                break;
            default:
                System.out.println("Server name is not valid");
                break;

        }
    return serverName;
   }

@AfterClass
    public void afterClass () {
       driver.quit();
}
}

