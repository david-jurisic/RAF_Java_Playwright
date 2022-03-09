package org.raf3k.apptesting;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.raf3k.apptesting.basetypes.AppControlBase;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.SharedVariables;

import java.time.Duration;

public class AppEngine {

    public WebElement findControl(String sControlType, String sControlPath, By searchBy, AppControlBase parent, String sError, boolean bDisplayed, boolean bMustBeVisible, int iTimeoutOverride){
        try {
            WebElement control = null;
            var timeSeconds = SharedVariables.configuration.getProperty("controlWaitTime");

            Duration waitTime = Duration.ofSeconds(Integer.parseInt(timeSeconds));
            if (iTimeoutOverride != -1)
                waitTime = Duration.ofSeconds(iTimeoutOverride);

            WebDriverWait wait = new WebDriverWait(AppReferences.getAppDriver(), waitTime);

            if (parent == null)
                control = wait.until(bMustBeVisible ? ExpectedConditions.visibilityOfElementLocated(searchBy) : ExpectedConditions.presenceOfElementLocated(searchBy));
            else
                control = (WebElement)wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent.control(), searchBy));
            return control;
        }
        catch(Exception e){
            DebugLog.add("Error finding control: " + sControlPath, 3);
            return null;
        }
    }
}
