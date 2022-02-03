package hook.ui;

import base.ui.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseHooks extends BaseUtil {
    private static final String projectDir = System.getProperty("user.dir");

    @Before
    public void initializeTest() {
        if (driver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("--ignore-ssl-errors=yes");
            chromeOptions.addArguments("--ignore-certificate-errors");
            System.setProperty("webdriver.chrome.driver", projectDir + CHROME_DRIVER_LOCATION);
            driver = new ChromeDriver(chromeOptions);
        }
    }

    @After
    public void quitDriver() {
        //driver.quit();
    }
}
