package run;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import root.Success;
import root.UIReferences;
import root.WbPageEx;

public class RunTest {
    public static void main(String[] args){
        SetupWebDriver();
        Map.Initialize();
        Map.Login.page.Navigate();
    }

    private static void SetupWebDriver()
    {
        try
        {
            if (UIReferences.getWebDriver() == null) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--ignore-ssl-errors=yes");
                chromeOptions.addArguments("--ignore-certificate-errors");
                System.setProperty("webdriver.chrome.driver", "c:\\Automation\\chromedriver.exe");
                UIReferences.setWebDriver(new ChromeDriver(chromeOptions));
                System.out.println("Driver setup complete");
            }
            else {
                System.out.println("Driver already running");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
