package root;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
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
            //TimeSpan WaitTime = TimeSpan.FromSeconds(Convert.ToInt32(SharedVariables.Configuration.GetEntryValue("ControlWaitTime")));


            WebDriverWait wait = new WebDriverWait(UIReferences.getWebDriver(), Duration.ofSeconds(5));
            Control = wait.until(bMustBeVisible ? ExpectedConditions.visibilityOfElementLocated(searchBy) : ExpectedConditions.presenceOfElementLocated(searchBy));

            return Control;
        } catch (Exception e) {
            System.out.println("Error Finding control: " + sControlPath);
            return null;
        }

    }
}
