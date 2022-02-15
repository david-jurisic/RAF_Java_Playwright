package RAF3kGUItesting.WebTesting.BaseTypes;

import RAF3kGUItesting.WebTesting.SeleniumSetup;
import RAF3kShared.DebugLog;
import RAF3kShared.Logging.TestCaseBase;
import RAF3kGUItesting.UIReferences;

//[Binding]
public class UITestCase extends TestCaseBase {
    public UITestCase(String TestCaseName, String TestCaseCode, String TestCaseAuthor) {
        super(TestCaseName, TestCaseCode, TestCaseAuthor);
        SetupWebDriver();
    }
    public UITestCase() {
        super();
        SetupWebDriver();
    }

    private void SetupWebDriver()
    {
        try
        {
            if (UIReferences.getWebDriver() == null)
            {
                SeleniumSetup setup = new SeleniumSetup();
                setup.SetupWebDriver(null);
                DebugLog.Add("Driver setup complete", 2);
            }
            else
            {
                DebugLog.Add("Driver already running", 2);
            }
        }
        catch (Exception ex)
        {
            DebugLog.Add(ex);
            return;
        }
    }
}
