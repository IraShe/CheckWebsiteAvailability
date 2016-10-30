package tests;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import utility.ExcelTestData;
import utility.Log;
import utility.ScreenShot;

import java.io.File;
import java.io.IOException;


/**
 * Created by Administrator on 9/13/2016.
 */
public class WebsiteAvailabilityTest extends ExcelTestData {
    Integer serverIp;
    String status;

    @Test(dataProvider = "excelData")
    public void checkPageTitle(String url, String expectedTitle){
        String actualTitle = appManager.getWebsiteAvailabilityHelper().goToPage(url)
                                                                      .getPageTitle();
        if (actualTitle.contains(expectedTitle)){
             status = "PASS";
        }
        else {
             status = "FAIL";
            ScreenShot.capture(appManager.getWebDriverHelper().getWebDriver(), "Error" + expectedTitle);//(driver, result.getName());
        }
        Log.info("|"+ "10.122.1."+ serverIp+"|"+url +"|" + status);
    }


    @Parameters("serverIp")
    @BeforeClass
    public void before(Integer serverIp){
        String hostsPath = "C:\\Windows\\System32\\drivers\\etc";
        this.serverIp = serverIp;
        File dest =  new File(hostsPath+"\\hosts");
        File source = new File(hostsPath+"\\hosts"+ Integer.toString(serverIp));
        try {
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterSuite
    public void after(){
        String hostsPath = "C:\\Windows\\System32\\drivers\\etc";
        File source =  new File(hostsPath+"\\hostsOrigin");
        File dest = new File(hostsPath+"\\hosts");
        try {
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
