package utility;

import appManager.AppManager;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.*;


public class TestBase {
        protected static AppManager appManager;

        @BeforeSuite
        @Parameters({"browser"})
        public void setUp(String browser) {
            PropertyConfigurator.configure("Log4j.properties");
            appManager = AppManager.getInstance(); //.getInstance for parallel running
            appManager.driverType = browser;
        }

        @AfterSuite
        //public void tearDown(ITestResult result)
        public void tearDown(){
            //appManager.dismiss(result);\
            appManager.dismiss();
        }
    }
