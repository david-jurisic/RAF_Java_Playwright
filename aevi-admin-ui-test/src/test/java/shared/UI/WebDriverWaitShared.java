package shared.ui;

import base.ui.BaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverWaitShared extends BaseUtil {
    private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));

    public static void waitVisibilityOfElementLocated(By locator) {
        wait.withTimeout(Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitUrlToBe(String page) {
        wait.withTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(page));
    }

    public static void waitTextToBe(By locator, String text) {
        wait.withTimeout(Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    public static void waitAttributeToBeNotEmpty(WebElement element, String attribute) {
        wait.withTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }
}
