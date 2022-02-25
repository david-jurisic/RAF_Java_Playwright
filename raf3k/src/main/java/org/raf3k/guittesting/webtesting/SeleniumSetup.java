package org.raf3k.guittesting.webtesting;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.SharedVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumSetup {
    public void setupWebDriver(AbstractDriverOptions driverOptions, WebDriver customWebDriver)
    {
        if (customWebDriver == null)
        {
            String Browser = SharedVariables.configuration.getProperty("browser");
            String sWebDriverPath = SharedVariables.configuration.getProperty("seleniumWebDriverPath");

            switch (Browser.toLowerCase())
            {

                case "firefox":
                    System.setProperty("webdriver.gecko.driver", sWebDriverPath + "\\firefox.exe");

                    if(driverOptions == null) {
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        UIReferences.setWebDriver(new FirefoxDriver(firefoxOptions));
                    }
                    else {
                        UIReferences.setWebDriver(new FirefoxDriver((FirefoxOptions)driverOptions));
                    }
                    break;
                case "internetexplorer":
                    break;
                case "chrome":
                default:
                    System.setProperty("webdriver.chrome.driver", sWebDriverPath + "\\chromedriver.exe");

                    if(driverOptions == null) {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        UIReferences.setWebDriver(new ChromeDriver(chromeOptions));
                    }
                    else {
                        UIReferences.setWebDriver(new ChromeDriver((ChromeOptions)driverOptions));
                    }
                    break;
            }
        }
        else
        {
            UIReferences.setWebDriver(customWebDriver);
        }
        UIReferences.getWebDriver().manage().window().maximize();
    }
}
