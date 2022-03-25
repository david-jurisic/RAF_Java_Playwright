package org.raf3k.guittesting.webtesting.engine;

import org.openqa.selenium.JavascriptExecutor;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.DebugLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.raf3k.shared.SharedVariables;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class WebEngine {
    public WebEngine() {
        allAsyncLoaders = new ArrayList<>();
    }

    private List<By> allAsyncLoaders;

    public WebElement findControl(String sControlPath, By searchBy, WebControlBase parent, Boolean bMustBeVisible, int iTimeoutOverride) {
        try {
            WebElement control;
            String timeSeconds = SharedVariables.configuration.getProperty("controlWaitTime");

            Duration waitTime = Duration.ofSeconds(Integer.parseInt(timeSeconds));
            if (iTimeoutOverride != -1)
                waitTime = Duration.ofSeconds(iTimeoutOverride);

            WebDriverWait wait = new WebDriverWait(UIReferences.getWebDriver(), waitTime);

            if (parent == null)
                control = wait.until(bMustBeVisible ? ExpectedConditions.visibilityOfElementLocated(searchBy) : ExpectedConditions.presenceOfElementLocated(searchBy));
            else
                control = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent.control(), searchBy));

            return control;
        } catch (Exception ex) {
            DebugLog.add("Error Finding control: " + sControlPath, 3);
            return null;
        }

    }

    /**
     * Checks if page loaded or not using JavaScript
     *
     * @return Boolean if page loaded or not
     */
    public Boolean waitForPageLoading() {
        try {
            int waitTime = Integer.parseInt(SharedVariables.configuration.getProperty("pageLoadWaitTime"));
            WebDriverWait wait = new WebDriverWait(UIReferences.getWebDriver(), Duration.ofSeconds(waitTime));
            wait.until((driver) -> {
                try {
                    var pageStatus = ((JavascriptExecutor) driver).executeScript("return document.readyState");
                    if (pageStatus == null || pageStatus.toString().equalsIgnoreCase("complete"))
                        return true;
                    return false;
                } catch (Exception ex) {
                    return false;
                }
            });
            return true;
        } catch (Exception ex) {
            return false;

        }
    }

    public String waitForLoaders() {
        try {
            for (By loader : allAsyncLoaders) {
                waitForAsyncLoader(loader);
            }
            return "";

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    private boolean waitForAsyncLoader(By searchBy) {
        try {
            int waitTime = Integer.parseInt(SharedVariables.configuration.getProperty("pageLoadWaitTime"));
            WebDriverWait wait = new WebDriverWait(UIReferences.getWebDriver(), Duration.ofSeconds(waitTime));

            wait.until((driver) -> {
                try {
                    if (driver.findElement(searchBy).isDisplayed())
                        return false;
                    return true;
                } catch (Exception ex) {
                    return true;
                }
            });
            return true;
        } catch (Exception ex) {
            return false;

        }
    }

    public String addCustomLoader(By searchParams) {
        try {
            allAsyncLoaders.add(searchParams);
            return "";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
