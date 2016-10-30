package utility;

import appManager.AppManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * Created by Administrator on 9/20/2016.
 */
public class AppManagerBase {
    private static AppManager appManager;
    public String driverType;
    private ExtentReports report;
    private ExtentTest logger;
    private WebDriverHelper webDriverHelper;


    public static AppManager getInstance() {
        return	appManager = new AppManager();
    }

    public WebDriverHelper getWebDriverHelper(){
        if (webDriverHelper == null){
            webDriverHelper = new WebDriverHelper(appManager);
        }
        return webDriverHelper;
    }

    public ExtentTest getExtentsTest(){
        if (logger == null){
            logger = getExtentsReport().startTest("T e s t");
        }
        return logger;
    }

    public ExtentReports getExtentsReport(){
        if (report == null){
            report = new ExtentReports("./reports/AutoTestResults.html");
        }
        return report;
    }
    //public void dismiss(ITestResult result){
    public void dismiss(){
        if (webDriverHelper!=null){
            //webDriverHelper.dismiss(result);
            webDriverHelper.dismiss();
        }
    }
}
