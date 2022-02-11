package RAF3kGUItesting.WebTesting.Types;

import RAF3kGUItesting.UIReferences;
import RAF3kGUItesting.WebTesting.BaseTypes.WebControlBase;
import RAF3kShared.Logging.Success;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class WbEdit extends WebControlBase {
    public WbEdit(By SearchBy, String Alias) {
      super(SearchBy,Alias);
    }

    public WbEdit(By SearchBy, WebControlBase Parent, String Alias) {
        super(SearchBy,Parent,Alias);
    }

    public Success SetText(String sText, Boolean bSetValue, Boolean bSetWithAction, Boolean bClickControl) throws Exception {
        if (bSetWithAction && bSetValue)
            throw new Exception("Only one parameter can be set to true,either 'bSetValue' or 'bSetWithAction'.");

        if (bClickControl)
            Control().click();

        if (!getsAlias().toLowerCase().contains("password"))
        {
            Control().clear();
        }
        else
        {
            Control().sendKeys(Keys.CONTROL + "a");
            Control().sendKeys(Keys.DELETE);
        }

        Control().sendKeys(sText);
        return null;
    }
}
