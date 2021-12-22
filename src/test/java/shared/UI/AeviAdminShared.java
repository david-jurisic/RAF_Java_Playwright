package shared.UI;

import base.UI.BaseUtil;
import map.UI.AeviAdminMap;
import org.openqa.selenium.WebElement;

public class AeviAdminShared extends BaseUtil {
    public static WebElement FindElement(String sElement, boolean... clone)
    {
        WebElement element = null;
        String title = driver.getTitle();

        switch (title)
        {
            case "Privacy error":
                switch (sElement)
                {
                    case "Advanced":
                        element = AeviAdminMap.ChromeWarningPage.btnAdvanced;
                        break;
                    case "Proceed to":
                        element = AeviAdminMap.ChromeWarningPage.hlkProceedTo;
                        break;
                    default:
                        break;
                }
                break;
            case "AEVI Pay Admin | Login":
                switch (sElement)
                {
                    case "Save Changes":
                        element = AeviAdminMap.LoginPage.btnSaveChanges;
                        break;
                    default:
                        break;
                }
                break;
            case "AEVI Pay Admin | Organization Units":
                switch (sElement)
                {
                    case "User Settings":
                        element = AeviAdminMap.OrganizationUnits.btnUserSettings;
                        break;
                    case "Log Out":
                        element = AeviAdminMap.UserSettings.hlkLogOut;
                        break;
                    default:
                        break;
                }
                break;
            case "AEVI Pay Admin | Data Groups":
                switch (sElement)
                {
                    case "New Record":
                        element = AeviAdminMap.DataGroups.btnNewRecord;
                        break;
                    case "Search":
                        element = AeviAdminMap.DataGroups.btnSearch;
                        break;
                    case "Name":
                        element = AeviAdminMap.DataGroups.txtName;
                        break;
                    case "Close Message":
                        element = AeviAdminMap.DataGroupsAddMessage.btnCloseMessage;
                        break;
                    default:
                        break;
                }
                break;
            case "AEVI Pay Admin | Data Group":
                switch (sElement)
                {
                    case "OK":
                        element = AeviAdminMap.DataGroupsAdd.btnOK;
                        break;
                    case "Name":
                        element = AeviAdminMap.DataGroupsAdd.txtName;
                        break;
                    case "Status":
                        element = AeviAdminMap.DataGroupsAdd.chkStatus;
                        break;
                    case "Check Site ID/Terminal ID":
                        element = AeviAdminMap.DataGroupsAdd.chkSiteTerminalId;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        return element;
    }
}
