package RAF3kGUItesting.WebTesting.BaseTypes;

import RAF3kGUItesting.WebTesting.SeleniumSetup;
import RAF3kShared.DebugLog;
import RAF3kShared.Logging.TestCaseBase;
import RAF3kGUItesting.UIReferences;

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
}
