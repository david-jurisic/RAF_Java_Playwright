package org.raf3k.guittesting.webtesting.basetypes;

import org.junit.jupiter.api.AfterAll;
import org.raf3k.guittesting.webtesting.SeleniumSetup;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.logging.TestCaseBase;
import org.raf3k.guittesting.UIReferences;

//[Binding]
public class UITestCase extends TestCaseBase {
    public UITestCase(String testCaseName, String testCaseCode, String testCaseAuthor) {
        super(testCaseName, testCaseCode, testCaseAuthor);
        setupWebDriver();
    }
    public UITestCase() {
        super();
        setupWebDriver();
    }

    private void setupWebDriver()
    {
        try
        {
            if (UIReferences.getWebDriver() == null)
            {
                SeleniumSetup setup = new SeleniumSetup();
                setup.setupWebDriver(null);
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
