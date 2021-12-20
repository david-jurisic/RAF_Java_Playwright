package shared.UI;

import base.UI.BaseUtil;
import map.UI.AeviAdminMap;
import org.openqa.selenium.By;
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

    public static WebElement FindElement(String sElement)
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
                    default:
                        break;
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
                        break;
                }
                break;
            case "AEVI Pay Admin | Data Groups":
                switch(sTextBoxName)
                {
                    case "Name":
                        element = AeviAdminMap.DataGroups.txtName;
                        break;
                    default:
                        break;
                }
                break;
        }

        return element;
    }

}
