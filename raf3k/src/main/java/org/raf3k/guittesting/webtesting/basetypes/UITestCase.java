package org.raf3k.guittesting.webtesting.basetypes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.raf3k.guittesting.webtesting.SeleniumSetup;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.logging.TestCaseBase;
import org.raf3k.guittesting.UIReferences;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UITestCase extends TestCaseBase {
    public UITestCase(String testCaseName, String testCaseCode, String testCaseAuthor) {
        super(testCaseName, testCaseCode, testCaseAuthor);
        setupWebDriver(null);
    }

    public UITestCase() {
        super();
        setupWebDriver(null);
    }

    private void setupWebDriver(AbstractDriverOptions driverOptions)
    {
        try
        {
            if (UIReferences.getWebDriver() == null)
            {
                SeleniumSetup setup = new SeleniumSetup();
                if(driverOptions == null)
                    setup.setupWebDriver(null, null);
                else
                    setup.setupWebDriver(driverOptions,null);

                DebugLog.add("Driver setup complete", 2);
            }
            else
            {
                DebugLog.add("Driver already running", 2);
            }
        }
        catch (Exception ex)
        {
            DebugLog.add(ex);
            return;
        }
    }

    @AfterAll
    public void uiTeardown() {
        try {
            if(UIReferences.getWebDriver() != null)
                UIReferences.getWebDriver().quit();
            DebugLog.add("Driver Closed", 2);
        }catch(Exception ex) {
            DebugLog.add(ex);
            return;
        }
    }
}
