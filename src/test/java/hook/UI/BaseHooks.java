package hook.UI;

import base.UI.BaseUtil;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import util.UI.AdminUtil;

public class BaseHooks extends BaseUtil {
    @Before
    public void InitializeTest() {
        if(driver == null)
        {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("--ignore-ssl-errors=yes");
            chromeOptions.addArguments("--ignore-certificate-errors");
            System.setProperty("webdriver.chrome.driver", AdminUtil.CHROME_DRIVER_LOCATION);
            driver = new ChromeDriver(chromeOptions);
        }
    }

    @After
    public void quitDriver(Scenario scenario){
        if(scenario.isFailed()){
            saveScreenshotsForScenario(scenario);
        }

        //driver.quit();
    }

    private void saveScreenshotsForScenario(final Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        //scenario.embed(screenshot, "image/png");
    }
}