package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 9/12/2016.
 */
public class ScreenShot {

    public static String capture (WebDriver driver, String screenShotName){
        try {
            TakesScreenshot ts = (TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
            Date date = new Date();
            String dateStamp = dateFormat.format(date);
            String fileName =  screenShotName + "_"+dateStamp+".png";
            String dest = "./screensShots/"+fileName;
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot taken");
            return dest;

        } catch (Exception e) {
            System.out.println("Exception while taking screenshot"+e.getMessage());
            return e.getMessage();
        }
    }


}
