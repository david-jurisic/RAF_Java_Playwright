package glue.UI;

import base.UI.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import map.UI.AeviAdminMap;
import util.UI.AdminUtil;

import static org.junit.Assert.assertTrue;

public class AdminSharedSteps extends BaseUtil {
    private String _url = AdminUtil.WEBPAGE_URL;

    @Given("I have opened the {string} page")
    public void iHaveOpenedThePage(String arg0) {
        this.driver.get(_url);
    }

    @Then("I should see the {string} page")
    public void iShouldSeeThePage(String arg0) {
        String page = "";
        switch(arg0)
        {
            case "Chrome Warning Screen":
                page = AeviAdminMap.ChromeWarningPage.chromePage;
                break;
            case "Login to Data Group":
                page = AeviAdminMap.LoginPage.page;
                break;
            case "Organization Units":
                page = AeviAdminMap.OrganizationUnits.page;
                break;
            case "Data Groups":
                page = AeviAdminMap.DataGroups.page;
                break;
            case "Data Groups Add":
                page = AeviAdminMap.DataGroupsAdd.page;
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;

        }

        driver.getCurrentUrl().equals(page);
    }


    @When("I click on the {string} button")
    public void iClickOnTheButton(String arg0) {
        switch (arg0)
        {
            case "Advanced":
                AeviAdminMap.ChromeWarningPage.btnAdvanced.click();
                break;
            case "Proceed to":
                AeviAdminMap.ChromeWarningPage.hlkProceedTo.click();
                break;
            case "Save Changes":
                AeviAdminMap.LoginPage.btnSaveChanges.click();
                break;
            case "User Settings":
                AeviAdminMap.OrganizationUnits.btnUserSettings.click();
                break;
            case "Log Out":
                AeviAdminMap.UserSettings.hlkLogOut.click();
            case "New Record":
                AeviAdminMap.DataGroups.btnNewRecord.click();
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
    }

    @And("I should see the {string} button")
    public void iShouldSeeTheButton(String arg0) {
        switch(arg0)
        {
            case "Advanced":
                AeviAdminMap.ChromeWarningPage.btnAdvanced.isDisplayed();
                break;
            case "Proceed to":
                AeviAdminMap.ChromeWarningPage.hlkProceedTo.isDisplayed();
                break;
            case "Save Changes":
                AeviAdminMap.LoginPage.btnSaveChanges.isDisplayed();
                break;
            case "User Settings":
                AeviAdminMap.OrganizationUnits.btnUserSettings.isDisplayed();
                break;
            case "Log Out":
                AeviAdminMap.UserSettings.hlkLogOut.isDisplayed();
                break;
            case "New Record":
                AeviAdminMap.DataGroups.btnNewRecord.isDisplayed();
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;

        }
    }

    @And("I should see the {string} hyperlink in the sidebar menu")
    public void iShouldSeeTheHyperlinkInTheSidebarMenu(String arg0) {
        switch(arg0)
        {
            case "Form Configs":
                AeviAdminMap.SideBarMenu.hlkFormConfigs.isDisplayed();
                break;
            case "Data Groups":
                AeviAdminMap.SideBarMenu.hlkDataGroups.isDisplayed();
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
    }

    @When("I click on the {string} hyperlink in the sidebar menu")
    public void iClickOnTheHyperlinkInTheSidebarMenu(String arg0) {
        switch(arg0)
        {
            case "Form Configs":
                AeviAdminMap.SideBarMenu.hlkFormConfigs.click();
                break;
            case "Data Groups":
                AeviAdminMap.SideBarMenu.hlkDataGroups.click();
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
    }
}
