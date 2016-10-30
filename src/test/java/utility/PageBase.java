package utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;


//another abstraction layer
//Global reuse
//More readable
//Insulates us from Selenium API changes

public class PageBase {
    private WebDriver driver;
    private String expectedTitle;


    public String getExpectedTitle() {
        return expectedTitle;
    }

    public void setExpectedTitle(String expectedTitle) {
        this.expectedTitle = expectedTitle;
    }

    public PageBase(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void clickByJs(WebElement element, long timeoutSec){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        Assert.assertTrue(waitForDisplayed(element, timeoutSec));
        js.executeScript("arguments[0].click();", element);
    }

    public void typeByJs(WebElement element, String data, long timeoutSec){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        Assert.assertTrue(waitForDisplayed(element, timeoutSec));
        js.executeScript("arguments[0].setAttribute('value','" + data +"');", element);
    }

//    public void typeIntoBodyByJs(WebElement element, String data, long timeoutSec){
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        Boolean ind = waitForDisplayed(element, timeoutSec);
//        js.executeScript("document.body.innerHTML='" + data +"'");
//    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void visit(String url){
        driver.get(url);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void type(WebElement webElement, String inputText, long timeout){
        Assert.assertTrue(waitForDisplayed(webElement, timeout));
        webElement.sendKeys(inputText);
        Log.info("WebElement: "+ webElement.toString() + " sendKeys");
    }

    public void submit(WebElement webElement, long timeout){
        Assert.assertTrue(waitForDisplayed(webElement, timeout));
        webElement.submit();

        Log.info("WebElement: "+ webElement.toString() + " submit");
    }

    public void click (WebElement webElement, long timeout){
        Assert.assertTrue(waitForDisplayed(webElement, timeout));
        webElement.click();
        Log.info("WebElement: "+ webElement.toString() + " Clicked");
    }

    public void switchToFrame (WebElement iframe){
        driver.switchTo().frame(iframe);
    }
    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }
    public boolean waitForDisplayed (WebElement webElement, long timeoutSec){
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);

        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            System.out.println("waitForDisplayed function true"+ webElement.toString());
            return true;
        }
        catch (TimeoutException exception)
        {
            System.out.println("waitForDisplayed function false"+ webElement.toString());
            return false;
        }
    }

    public List<WebElement> findElements(By linkText){
        return driver.findElements(linkText);
    }


    public void clickIfExist (WebElement webElement, Integer... timeout){
        Boolean dispInd = waitForVisibilityOfOptional(webElement, timeout);
        if (dispInd){
            webElement.click();
            webElement.toString();
            System.out.println("clickIfExist WebElement: "+ webElement.toString() + " Clicked");
        }
        else {
            System.out.println("clickIfExist WebElement: "+ webElement.toString() + " wasn't Found");
        }
    }

    public Boolean waitForIsDisplayed(WebElement webElement, Integer timeout){
        Boolean flag;
        flag = false;
        System.out.println("waitForIsDisplayed started");
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        for (int i = 0; i< timeout;  i++){
            try {
                System.out.println("i: " +i);
                webElement.isDisplayed();
                flag = true;
                break;
            }
            catch (NoSuchElementException exception)
            {
                wait.withTimeout(1, TimeUnit.SECONDS);
                System.out.println("wait i: " +i);
                exception = null;
            }
        }
        if (flag){
            return true;
        }
        else{
            return false;
        }
    }

    public void waitForVisibilityOf(WebElement webElement, Integer... timeout){
        waitFor(ExpectedConditions.visibilityOf(webElement),
                (timeout.length > 0 ? timeout[0] : null));
    }



    public Boolean waitForVisibilityOfOptional(WebElement webElement, Integer... timeout){
        try{
            waitFor(ExpectedConditions.visibilityOf(webElement),
                    (timeout.length > 0 ? timeout[0] : null));
        }catch (TimeoutException exception) {
            return false;
        }
        return true;
    }


    public void waitFor(ExpectedCondition<WebElement> condition, Integer timeout){
        timeout = timeout != null ? timeout :5;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

}

