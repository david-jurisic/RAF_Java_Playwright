package glue.UI;

import base.UI.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import map.UI.AeviAdminMap;
import org.openqa.selenium.WebElement;
import shared.UI.AeviAdminShared;
import util.UI.AdminUtil;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
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
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "Data Groups":
                AeviAdminMap.SideBarMenu.hlkDataGroups.click();
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
    }

    @And("I should see the {string} textbox which is enabled and empty")
    public void iShouldSeeTheTextboxWhichIsEnabledAndEmpty(String arg0) {
        switch(arg0)
        {
            case "Name":
                AeviAdminMap.DataGroupsAdd.txtName.isDisplayed();
                AeviAdminMap.DataGroupsAdd.txtName.isEnabled();
                AeviAdminMap.DataGroupsAdd.txtName.getAttribute("value").isEmpty();
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
    }

    @And("I should see the {string} checkbox which is enabled and {string}")
    public void iShouldSeeTheCheckboxWhichIsEnabledAnd(String arg0, String arg1) {
        WebElement checkbox = AeviAdminShared.FindCheckBox(arg0);

        switch(arg1)
        {

            case "checked":
                checkbox.isDisplayed();
                checkbox.isEnabled();
                assertEquals(checkbox.isSelected(), true);
                break;
            case "not checked":
                checkbox.isDisplayed();
                checkbox.isEnabled();
                assertEquals(checkbox.isSelected(),false);
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
    }

    @When("I enter {string} string into {string} textbox")
    public void iEnterStringIntoTextbox(String arg0, String arg1) {
        AeviAdminShared.FindTextBox(arg1).sendKeys(arg0);
    }

    @Then("the {string} textbox should contain {string} string")
    public void theTextboxShouldContainString(String arg0, String arg1) {
        AeviAdminShared.FindTextBox(arg0).getAttribute("value").equals(arg1);
    }

    @When("I click on the {string} checkbox")
    public void iClickOnTheCheckbox(String arg0) {
        switch (arg0)
        {
            case "Status":
                AeviAdminMap.DataGroupsAdd.chkStatus_Click.click();;
                break;
            case "Check Site ID/Terminal ID":
                AeviAdminMap.DataGroupsAdd.chkSiteTerminalId_Click.click();
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
    }

    @When("I click on the {string} tab")
    public void iClickOnTheTab(String arg0) {
        switch(arg0)
        {
            case "Parameters":
                AeviAdminMap.DataGroupsAdd.pageParameters.click();
                break;
        }
    }

    @And("I should see the {string} tab")
    public void iShouldSeeTheTab(String arg0) {
        switch (arg0)
        {
            case "Parameters":
                AeviAdminMap.DataGroupsAdd.pageParameters.isDisplayed();
                break;
        }
    }

    @And("I should see the {string} dropdown")
    public void iShouldSeeTheDropdown(String arg0) {
        switch (arg0)
        {
            case "TID Generator Template";
            AeviAdminMap.
                break;
        }
    }
}
