package RAF3kGUItesting.WebTesting;

import RAF3kGUItesting.WebTesting.BaseTypes.WebControlBase;
import RAF3kGUItesting.UIReferences;
import RAF3kShared.DebugLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class WebEngine {
    public WebEngine() {
        AllAsyncLoaders = new ArrayList<By>();
    }

    private List<By> AllAsyncLoaders = new ArrayList<By>();

    public WebElement FindControl(String sControlType, String sControlPath, By searchBy, WebControlBase Parent, String sError, Boolean bDisplayed, Boolean bMustBeVisible, int iTimeoutOverride) {
        sError = "";

        try {
            WebElement Control = null;
            //TimeSpan WaitTime = TimeSpan.FromSeconds(Convert.ToInt32(SharedVariables.Configuration.GetEntryValue("controlWaitTime")));


            WebDriverWait wait = new WebDriverWait(UIReferences.getWebDriver(), Duration.ofSeconds(5));
            Control = wait.until(bMustBeVisible ? ExpectedConditions.visibilityOfElementLocated(searchBy) : ExpectedConditions.presenceOfElementLocated(searchBy));

            return Control;
        } catch (Exception e) {
            DebugLog.Add("Error Finding control: " + sControlPath, 3);
            return null;
        }

    }
}
