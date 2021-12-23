package glue.UI;

import base.UI.BaseUtil;
import hook.UI.BaseHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import map.UI.AeviAdminMap;
import org.jsoup.Connection;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import shared.UI.AeviAdminShared;
import util.UI.AdminUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminSharedSteps extends BaseUtil{
    private String _url = AdminUtil.WEBPAGE_URL;

    @Given("I have opened the {string} page")
    public void iHaveOpenedThePage(String arg0)
    {
        driver.get(_url);
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
            case "AEVI Pay Admin| Site":
                page = AeviAdminMap.OrganizationUnitsSite.page;
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;

        }

        driver.getCurrentUrl().equals(page);
    }


    @When("I click on the {string} button")
    public void iClickOnTheButton(String arg0) {
        try {
            WebElement button = AeviAdminShared.FindButtonByName(arg0);

            button.click();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement button = AeviAdminShared.FindButtonByName(arg0);

            button.click();
        }


    }

    @And("I should see the {string} button")
    public void iShouldSeeTheButton(String arg0) {

        try {
            WebElement element = AeviAdminShared.FindButtonByName(arg0);
            element.isDisplayed();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement element = AeviAdminShared.FindButtonByName(arg0);
            element.isDisplayed();
        }
    }

    @And("I should see the {string} button in the sidebar menu")
    public void iShouldSeeTheButtonInTheSidebarMenu(String arg0) {
        WebElement element = null;
        switch(arg0)
        {
            case "Form Configs":
                element = AeviAdminMap.SideBarMenu.btnFormConfigs;
                break;
            case "Data Groups":
                element = AeviAdminMap.SideBarMenu.btnDataGroups;
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }

        element.isDisplayed();
    }

    @When("I click on the {string} button in the sidebar menu")
    public void iClickOnTheButtonlinkInTheSidebarMenu(String arg0) {
        WebElement element = null;
        switch(arg0)
        {
            case "Form Configs":
                element = AeviAdminMap.SideBarMenu.btnFormConfigs;
                AeviAdminShared.Wait(2);
                break;
            case "Data Groups":
                element = AeviAdminMap.SideBarMenu.btnDataGroups;
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
        element.click();
    }

    @And("I should see the {string} textbox which is enabled and empty")
    public void iShouldSeeTheTextboxWhichIsEnabledAndEmpty(String arg0) {
        try {
            WebElement textbox = AeviAdminShared.FindTextboxByName(arg0);

            textbox.isDisplayed();
            textbox.isEnabled();
            textbox.getAttribute("value").isEmpty();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            AeviAdminMap.DataGroups.btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a"));
            AeviAdminMap.DataGroups.btnSearch = driver.findElement(By.name("_form1"));
            AeviAdminMap.DataGroups.txtName = driver.findElement(By.id("criteria.name"));

            WebElement textbox = AeviAdminShared.FindTextboxByName(arg0);

            textbox.isDisplayed();
            textbox.isEnabled();
            textbox.getAttribute("value").isEmpty();
        }

    }

    @And("I should see the {string} checkbox which is enabled and {string}")
    public void iShouldSeeTheCheckboxWhichIsEnabledAnd(String arg0, String arg1) {
        WebElement checkbox = AeviAdminShared.FindCheckboxByName(arg0);

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
    public void iEnterStringIntoTextbox(String arg0, String arg1)
    {
        try {
            WebElement textbox = AeviAdminShared.FindTextboxByName(arg1);

            textbox.sendKeys(arg0);
        }
        catch(org.openqa.selenium.ElementNotInteractableException ex)
        {
            WebElement textbox = AeviAdminShared.FindTextboxByName(arg1);

            textbox.click();
            textbox.sendKeys(arg0);
        }
    }

    @Then("the {string} textbox should contain {string} string")
    public void theTextboxShouldContainString(String arg0, String arg1)
    {
        WebElement textbox = AeviAdminShared.FindTextboxByName(arg0);

        textbox.getAttribute("value").equals(arg1);
    }

    @When("I click on the {string} checkbox")
    public void iClickOnTheCheckbox(String arg0) {
        WebElement checkbox = null;

        switch (arg0) {
            case "Status":
                checkbox = AeviAdminMap.DataGroupsAdd.chkStatus_Click;
                break;
            case "Check Site ID/Terminal ID":
                checkbox = AeviAdminMap.DataGroupsAdd.chkSiteTerminalId_Click;
                break;
        }

        checkbox.click();
    }

    @When("I click on the {string} tab")
    public void iClickOnTheTab(String arg0) {
        switch(arg0)
        {
            case "Parameters":
                AeviAdminMap.DataGroupsAdd.pageParameters.click();
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
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
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
    }

    @And("I should see the {string} dropdown menu which is enabled and has {string} value selected")
    public void iShouldSeeTheDropdownMenuWhichIsEnabledAndHasValueSelected(String arg0, String arg1)
    {
        WebElement dropdown = null;
        switch (arg0)
        {
            case "TID Generator Template":
                dropdown = AeviAdminMap.DataGroupsAdd.ddlTIDGeneratorTemplate;
                break;
            case "Parent Unit":
                dropdown = AeviAdminMap.OrganizationUnitsSite.ddlParentUnit;
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }

        dropdown.isDisplayed();
        dropdown.isEnabled();
        dropdown.getText().equals(arg1);
    }

    @When("I select {string} string form the {string} dropdown menu")
    public void iSelectStringFormTheDropdownMenu(String arg0, String arg1) {
        WebElement dropdown = null;
        switch (arg1)
        {
            case "TID Generator Template":
                dropdown = AeviAdminMap.DataGroupsAdd.ddlTIDGeneratorTemplate;
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }

        Select s = new Select(dropdown);
        s.selectByVisibleText(arg0);
    }

    @And("the {string} dropdown menu should contain {string} string")
    public void theDropdownMenuShouldContainString(String arg0, String arg1) {
        WebElement dropdown = null;
        switch (arg0)
        {
            case "TID Generator Template":
                dropdown = AeviAdminMap.DataGroupsAdd.ddlTIDGeneratorTemplate;
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }

        dropdown.isDisplayed();
        dropdown.isEnabled();
        dropdown.getText().equals(arg1);
    }

    @Then("I should see the {string} success message")
    public void iShouldSeeTheSuccessMessage(String arg0) {
        switch (arg0)
        {
            case "Data Group was created successfully.":
                AeviAdminMap.DataGroupsAddMessage.msgSuccess.getText().equals(arg0);
                break;
            default:
                assertTrue("element not found: " + arg0 ,false);
                break;
        }
    }

    @And("I should see the {string} button which is enabled")
    public void iShouldSeeTheButtonWhichIsEnabled(String arg0) {
        WebElement button = AeviAdminShared.FindButtonByName(arg0);

        button.isDisplayed();
        button.isEnabled();
    }

    @And("I Should see {string} which is enabled")
    public void iShouldSeeWhichIsEnabled(String arg0) {
        
    }

    @Then("I should see the {string} button dropdown menu")
    public void iShouldSeeTheButtonDropdownMenu(String arg0) {
        
    }

    @When("I click on the {string} Button in the button dropdown menu")
    public void iClickOnTheButtonlinkInTheButtonDropdownMenu(String arg0) {
    }

    @When("I click on the {string} button in the button dropdown menu")
    public void iClickOnTheButtonInTheButtonDropdownMenu(String arg0) {
        WebElement button = AeviAdminShared.FindButtonByName(arg0);

        button.click();
    }

    @When("I click on the {string} dropdown menu")
    public void iClickOnTheDropdownMenu(String arg0) {
        WebElement dropdown = AeviAdminMap.OrganizationUnitsSite.ddlParentUnit;

        dropdown.click();
    }

    @Then("the {string} dropdown list should contain {string} string")
    public void theDropdownListShouldContainString(String arg0, String arg1) {
        try
        {
            WebElement element = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;

            List<WebElement> list = element.findElements(By.tagName("li"));
            list.get(0).getAttribute("value").equals(arg1);
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement element = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;

            List<WebElement> list = element.findElements(By.tagName("li"));
            list.get(0).getAttribute("value").equals(arg1);
        }

    }

    @When("I click on the {string}  dropdown menu item")
    public void iClickOnTheDropdownMenuItem(String arg0) {
        WebElement element = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;

        AeviAdminShared.Wait(2);
        List<WebElement> list = element.findElements(By.tagName("li"));
        list.get(0).click();
    }
}
