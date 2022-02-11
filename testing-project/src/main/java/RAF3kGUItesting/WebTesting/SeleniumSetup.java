package RAF3kGUItesting.WebTesting;

import RAF3kGUItesting.UIReferences;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumSetup {
    public void SetupWebDriver(WebDriver CustomWebDriver)
    {
        if (CustomWebDriver == null)
        {
            //String Browser = SharedVariables.Configuration.GetEntryValue("Browser");
            String Browser = "Chrome";
            //String sWebDriverPath = SharedVariables.Configuration.GetEntryValue("SeleniumWebDriverPath");
            String sWebDriverPath = "c:\\Automation\\chromedriver.exe";

            switch (Browser.toLowerCase())
            {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    System.setProperty("webdriver.chrome.driver", sWebDriverPath);
                    UIReferences.setWebDriver(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    //UIReferences.WebDriver = new FirefoxDriver(sWebDriverPath);
                    break;
                case "internetexplorer":
                    break;
                default:
                    ChromeOptions chromeOptionsDefault = new ChromeOptions();
                    System.setProperty("webdriver.chrome.driver", sWebDriverPath);
                    UIReferences.setWebDriver(new ChromeDriver(chromeOptionsDefault));
                    break;
            }
        }
        else
        {
            UIReferences.setWebDriver(CustomWebDriver);
        }
        UIReferences.getWebDriver().manage().window().maximize();
    }
}
