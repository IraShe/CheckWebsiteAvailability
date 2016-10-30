package utility;

import appManager.AppManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class WebDriverHelper {
    private WebDriver driver;
    private AppManager appManager;
    static String driverPath = "./webdrivers/";

    public WebDriverHelper (AppManager appManager) {
        this.appManager = appManager;

        if (appManager.driverType.contentEquals("Firefox")){
            System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if (appManager.driverType.contentEquals("Chrome")){
            System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (appManager.driverType.contentEquals("InternetExplorer")){
            System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        else{
            System.setProperty("webdriver.gecko.driver",  driverPath + "geckodriver.exe");
            System.out.println("browser : " + appManager.driverType + " is invalid, Launching Firefox as browser of choice..");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
    }

    public WebDriver getWebDriver(){
        return driver;
    }

    public PageBase goToPage(String pageUrl){
        driver.navigate().to(pageUrl);
        System.out.println("goToPage url: " + pageUrl);
        return new PageBase(driver);
    }

    //public void dismiss(ITestResult result){
    public void dismiss(){
        ExtentTest logger = appManager.getExtentsTest();
        //String screenShotPath = ScreenShot.capture(driver, "Test");//(driver, result.getName());
       // String image = logger.addScreenCapture(screenShotPath);
       /* if (result.getStatus()!=ITestResult.SUCCESS){
            logger.log(LogStatus.FAIL, result.getName(),image);
        }
        else {
            logger.log(LogStatus.PASS, result.getName(),image);
        }*/
        ExtentReports report = appManager.getExtentsReport();
        report.endTest(logger);
        report.flush();
        if (driver!=null){
            driver.quit();
        }
    }
}
