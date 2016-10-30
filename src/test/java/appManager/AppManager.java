package appManager;


import helpers.WebsiteAvailabilityHelper;
import utility.AppManagerBase;

/**
 * Created by Administrator on 9/12/2016.
 */
public class AppManager extends AppManagerBase {

    private WebsiteAvailabilityHelper websiteAvailabilityHelper;

    public WebsiteAvailabilityHelper getWebsiteAvailabilityHelper(){
        if (websiteAvailabilityHelper == null){
            websiteAvailabilityHelper = new WebsiteAvailabilityHelper(this);
        }
        return websiteAvailabilityHelper;
    }

    //public void dismiss(ITestResult result){

}
