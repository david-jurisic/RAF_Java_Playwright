package shared.UI;

import base.UI.BaseUtil;
import map.UI.AeviAdminMap;
import org.openqa.selenium.WebElement;
import util.UI.AdminUtil;

public class AeviAdminShared extends BaseUtil {
    public static WebElement FindCheckBox(String sCheckBoxName)
    {
        WebElement element = null;
        String title = driver.getTitle();

        switch(title)
        {
            case "AEVI Pay Admin | Data Group":
                switch(sCheckBoxName)
                {
                    case "Status":
                        element = AeviAdminMap.DataGroupsAdd.chkStatus;
                        break;
                    case "Check Site ID/Terminal ID":
                        element = AeviAdminMap.DataGroupsAdd.chkSiteTerminalId;
                        break;
                    default:
                }
                break;
            default:
                break;

        }

        return element;
    }

    public static WebElement FindTextBox(String sTextBoxName)
    {
        WebElement element = null;
        String title = driver.getTitle();

        switch(title)
        {
            case "AEVI Pay Admin | Data Group":
                switch(sTextBoxName)
                {
                    case "Name":
                        element = AeviAdminMap.DataGroupsAdd.txtName;
                        break;
                    default:
                }
                break;
            default:
                break;

        }

        return element;
    }

}
