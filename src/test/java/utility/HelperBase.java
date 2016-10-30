package utility;

import appManager.AppManager;
import org.openqa.selenium.WebDriver;

/**
 * Created by Administrator on 9/26/2016.
 */
public class HelperBase {
    protected WebDriver driver;
    protected AppManager appManager;

    public HelperBase(AppManager appManager){
        this.appManager = appManager;
        driver = appManager.getWebDriverHelper().getWebDriver();

    }
}
