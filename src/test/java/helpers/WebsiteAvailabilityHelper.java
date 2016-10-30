package helpers;

import appManager.AppManager;
import utility.HelperBase;
import utility.PageBase;

/**
 * Created by Administrator on 9/13/2016.
 */

public class WebsiteAvailabilityHelper extends HelperBase {

    private PageBase webPage;

    public WebsiteAvailabilityHelper(AppManager appManager) {
        super(appManager);
    }

    public WebsiteAvailabilityHelper goToPage(String pageUrl){
        appManager.getWebDriverHelper().goToPage(pageUrl);
        webPage = new PageBase(driver);
        return this;
    }

    public String getPageTitle(){
        return webPage.getTitle();
    }

}
