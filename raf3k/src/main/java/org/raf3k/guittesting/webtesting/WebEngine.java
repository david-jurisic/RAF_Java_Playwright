package org.raf3k.guittesting.webtesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.DebugLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class WebEngine {
    public WebEngine() {
        allAsyncLoaders = new ArrayList<By>();
    }

    private List<By> allAsyncLoaders = new ArrayList<By>();

    public WebElement findControl(String sControlType, String sControlPath, By searchBy, WebControlBase Parent, String sError, Boolean bDisplayed, Boolean bMustBeVisible, int iTimeoutOverride) {
        try {
            WebElement control = null;
            //TimeSpan WaitTime = TimeSpan.FromSeconds(Convert.ToInt32(SharedVariables.Configuration.GetEntryValue("controlWaitTime")));


            WebDriverWait wait = new WebDriverWait(UIReferences.getWebDriver(), Duration.ofSeconds(5));

            if (Parent == null) {
                control = wait.until(bMustBeVisible ? ExpectedConditions.visibilityOfElementLocated(searchBy) : ExpectedConditions.presenceOfElementLocated(searchBy));
            } else {
                control = (WebElement) wait.until(
                        ExpectedConditions.presenceOfNestedElementLocatedBy(Parent.control(), searchBy)
                );
            }

            return control;
        } catch (Exception e) {
            DebugLog.add("Error Finding control: " + sControlPath, 3);
            return null;
        }

    }

}
