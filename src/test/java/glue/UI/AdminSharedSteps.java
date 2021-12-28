package glue.UI;

import base.UI.BaseUtil;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import map.UI.AeviAdminMap;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import shared.UI.AeviAdminShared;
import util.UI.AdminUtil;
import java.util.List;

import static org.junit.Assert.*;

public class AdminSharedSteps extends BaseUtil{
    private final String _url = AdminUtil.WEBPAGE_URL;

    @Given("I have opened the {string} page")
    public void iHaveOpenedThePage(String arg0)
    {
        try
        {
            driver.get(_url);
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            driver.get(_url);
        }
    }

    @Then("I should see the {string} page")
    public void iShouldSeeThePage(String arg0) {
        String page = "";
        switch(arg0)
        {
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
            case "AEVI Pay Admin| Terminals":
                page = AeviAdminMap.Terminals.page;
                break;
            case "AEVI Pay Admin| Terminal":
                page = AeviAdminMap.TerminalsAdd.page;
                break;
            case "AEVI Pay Admin| Contracts":
                page = AeviAdminMap.Contracts.page;
                break;
            case "AEVI Pay Admin| Contract":
                page = AeviAdminMap.ContractsAdd.page;
                break;
            default:
                Assert.fail("Page not found: " + arg0);
                break;
        }

        Assert.assertEquals(page, driver.getCurrentUrl());
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

    @And("I should see the {string} button in the sidebar menu")
    public void iShouldSeeTheButtonInTheSidebarMenu(String arg0) {
        WebElement button = null;
        switch(arg0)
        {
            case "Form Configs":
                AeviAdminMap.SideBarMenu.btnFormConfigs = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/a"));
                button = AeviAdminMap.SideBarMenu.btnFormConfigs;
                break;
            case "Data Groups":
                AeviAdminShared.ThreadWait(2000);
                AeviAdminMap.SideBarMenu.btnDataGroups = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/div/ul/li[2]/a/span"));
                button = AeviAdminMap.SideBarMenu.btnDataGroups;
                break;
            case "Terminals":
                button = AeviAdminMap.SideBarMenu.btnTerminals;
                break;
            case "Contracts":
                AeviAdminMap.SideBarMenu.btnContracts = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/div/ul/li[5]/a/span"));
                button = AeviAdminMap.SideBarMenu.btnContracts;
                break;
            default:
                Assert.fail("Button in sidebar menu not found: " + arg0);
                break;
        }

        button.isDisplayed();
    }

    @When("I click on the {string} button in the sidebar menu")
    public void iClickOnTheButtonlinkInTheSidebarMenu(String arg0) {
        WebElement button = null;
        switch(arg0)
        {
            case "Form Configs":
                button = AeviAdminMap.SideBarMenu.btnFormConfigs;
                //AeviAdminShared.Wait(2);
                break;
            case "Data Groups":
                button = AeviAdminMap.SideBarMenu.btnDataGroups;
                break;
            case "Terminals":
                button = AeviAdminMap.SideBarMenu.btnTerminals;
                break;
            case "Contracts":
                button = AeviAdminMap.SideBarMenu.btnContracts;
                break;
            default:
                Assert.fail("Button in sidebar menu not found: " + arg0);
                break;
        }

        button.click();
    }

    @And("I should see the {string} textbox which is enabled and empty")
    public void iShouldSeeTheTextboxWhichIsEnabledAndEmpty(String arg0) {
        try {
            WebElement textbox = AeviAdminShared.FindTextboxByName(arg0);

            textbox.isDisplayed();
            textbox.isEnabled();
            Assert.assertTrue(textbox.getText().isEmpty());
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            String sPage = driver.getTitle();
            switch (sPage)
            {
                case "AEVI Pay Admin | Terminal":
                    AeviAdminMap.TerminalsAdd.txtTerminalId = driver.findElement(By.id("terminalIdInput"));
                    AeviAdminMap.TerminalsAdd.ddlOrganizationUnit = driver.findElement(By.id("select2-organizationUnitInput-container"));
                    AeviAdminMap.TerminalsAdd.ddlTerminalProfile = driver.findElement(By.id("terminalProfileSelector"));
                    AeviAdminMap.TerminalsAdd.btnSiteIdReload = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[1]/div[1]/div[1]/div/button"));
                    AeviAdminMap.OrganizationUnitsSiteDropdownSite.txtParentUnitSearch = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
                    AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit = driver.findElement(By.id("select2-organizationUnitInput-results"));
                    break;
                case "AEVI Pay Admin | Data Groups":
                    AeviAdminMap.DataGroups.btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a"));
                    AeviAdminMap.DataGroups.btnSearch = driver.findElement(By.name("_form1"));
                    AeviAdminMap.DataGroups.txtName = driver.findElement(By.id("criteria.name"));
                    break;
                default:
                    Assert.fail("Missing StaleElementReference for this page");
                    break;
            }

            WebElement textbox = AeviAdminShared.FindTextboxByName(arg0);

            textbox.isDisplayed();
            textbox.isEnabled();
            Assert.assertTrue(textbox.getText().isEmpty());
        }
    }

    @And("I should see the {string} checkbox which is enabled and {string}")
    public void iShouldSeeTheCheckboxWhichIsEnabledAnd(String arg0, String arg1) {
        WebElement checkbox = AeviAdminShared.FindCheckboxByName(arg0);

        checkbox.isDisplayed();
        checkbox.isEnabled();
        switch(arg1)
        {
            case "checked":
                assertEquals(true,checkbox.isSelected() );
                break;
            case "not checked":
                assertEquals(false,checkbox.isSelected());
                break;
            default:
                Assert.fail("Checkbox not found: " + arg0);
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

        Assert.assertEquals(arg1, textbox.getAttribute("value"));
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
            default:
                Assert.fail("Checkbox not found: " + arg0);
                break;
        }

        checkbox.click();
    }

    @When("I click on the {string} tab")
    public void iClickOnTheTab(String arg0) {
        WebElement tab = AeviAdminShared.FindTabByName(arg0);

        tab.click();
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
            case "Organization Unit":
                dropdown = AeviAdminMap.TerminalsAdd.ddlOrganizationUnit;
                break;
            case "Terminal Profile":
                dropdown = AeviAdminMap.TerminalsAdd.ddlTerminalProfile;
                break;
            case "Applications Profile":
                dropdown = AeviAdminMap.ContractsAdd.ddlApplicationsProfile;
                break;
            default:
                Assert.fail("Dropdow menu element not found: " + arg0);
                break;
        }

        dropdown.isDisplayed();
        dropdown.isEnabled();

        Select items = new Select(dropdown);
        Assert.assertEquals(arg1, items.getFirstSelectedOption().getText());
    }

    @When("I select {string} string from the {string} dropdown menu")
    public void iSelectStringFromTheDropdownMenu(String arg0, String arg1) {
        WebElement dropdown = null;

        switch (arg1)
        {
            case "TID Generator Template":
                dropdown = AeviAdminMap.DataGroupsAdd.ddlTIDGeneratorTemplate;
                break;
            case "Applications Profile":
                dropdown = AeviAdminMap.ContractsAdd.ddlApplicationsProfile;
                break;
            case "Terminal Profile":
                dropdown = AeviAdminMap.TerminalsAdd.ddlTerminalProfile;
                break;
            default:
                Assert.fail("Dropdow menu element not found: " + arg1);
                break;
        }

        Select items = new Select(dropdown);
        items.selectByVisibleText(arg0);
    }

    @Then("I should see the {string} success message")
    public void iShouldSeeTheSuccessMessage(String arg0) {
        WebElement msg = null;
        switch (arg0)
        {
            case "Success! Data Group was created successfully.":
                msg = AeviAdminMap.DataGroupsAddMessage.msgSuccess;
                break;
            default:
                Assert.fail("Message not found: " + arg0);
                break;
        }

        Assert.assertEquals(arg0, msg.getText());
    }

    @And("I should see the {string} button which is enabled")
    public void iShouldSeeTheButtonWhichIsEnabled(String arg0) {
        try
        {
            WebElement button = AeviAdminShared.FindButtonByName(arg0);

            button.isDisplayed();
            button.isEnabled();
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            AeviAdminMap.LoginPage.btnSaveChanges = driver.findElement(By.xpath("//*[@id=\"command\"]/div/div[3]/button[2]"));
            WebElement button = AeviAdminShared.FindButtonByName(arg0);

            button.isDisplayed();
            button.isEnabled();
        }
    }

    @When("I click on the {string} button in the button dropdown menu")
    public void iClickOnTheButtonInTheButtonDropdownMenu(String arg0) {
        WebElement button = AeviAdminShared.FindButtonByName(arg0);

        button.click();
    }

    @When("I click on the {string} dropdown menu")
    public void iClickOnTheDropdownMenu(String arg0) {
        WebElement dropdown = null;

        switch(arg0)
        {
            case "Parent Unit":
                dropdown = AeviAdminMap.OrganizationUnitsSite.ddlParentUnit;
                break;
            case "Organization Unit":
                dropdown = AeviAdminMap.TerminalsAdd.ddlOrganizationUnit;
                break;
            case "Terminal Profile":
                dropdown = AeviAdminMap.TerminalsAdd.ddlTerminalProfile;
                break;
            default:
                Assert.fail("Dropdown not found: " + arg0);
                break;
        }

        dropdown.click();
    }

    @Then("the {string} dropdown list should contain {string} string")
    public void theDropdownListShouldContainString(String arg0, String arg1) {
        try
        {
            WebElement element = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;

            List<WebElement> list = element.findElements(By.tagName("li"));
            AeviAdminShared.ThreadWait(1000);
            Assert.assertEquals(arg1, list.get(0).getText());
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit = driver.findElement(By.id("select2-organizationUnitInput-results"));
            WebElement element = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;

            List<WebElement> list = element.findElements(By.tagName("li"));
            Assert.assertEquals(arg1, list.get(0).getText());
        }

    }

    @When("I click on the {string} dropdown menu item")
    public void iClickOnTheDropdownMenuItem(String arg0) {
        try
        {
            WebElement element = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;

            AeviAdminShared.Wait(2);
            List<WebElement> list = element.findElements(By.tagName("li"));
            list.get(0).click();
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement element = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;

            AeviAdminShared.Wait(2);
            List<WebElement> list = element.findElements(By.tagName("li"));
            list.get(0).click();
        }


    }

    @Then("I should see the {string} button dropdown menu")
    public void iShouldSeeTheButtonDropdownMenu(String arg0) {
        WebElement dropdown = null;
        String sPage = driver.getTitle();

        switch (sPage)
        {
            case "AEVI Pay Admin | Organization Units":
                dropdown = AeviAdminMap.OrganizationUnits.ddlChooseAnOption;
                break;
            case "AEVI Pay Admin | Contracts":
                dropdown = AeviAdminMap.Contracts.ddlChooseAnOption;
                break;
            default:
                Assert.fail("Button in dropdown menu not found: " + arg0);
                break;

        }

        dropdown.isDisplayed();
    }

    @And("I should see the {string} tab which is enabled")
    public void iShouldSeeTheTabWhichIsEnabled(String arg0) {
        WebElement tab = AeviAdminShared.FindTabByName(arg0);

        tab.isDisplayed();
        tab.isEnabled();
    }

    @When("I click on the {string} button user card menu")
    public void iClickOnTheButtonUserCardMenu(String arg0) {
        AeviAdminMap.UserCard.ddmUserCard = driver.findElement(By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[2]"));
        AeviAdminMap.UserCard.btnUserCard = driver.findElement(By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[1]/div"));
        AeviAdminMap.UserCard.btnLogOut = driver.findElement(By.xpath("//a[.='Log Out']"));

        WebElement button = AeviAdminMap.UserCard.btnUserCard;

        button.click();
    }

    @Then("I should see the {string} button dropdown user card menu")
    public void iShouldSeeTheButtonDropdownUserCardMenu(String arg0) {
        WebElement ddmUserCard = AeviAdminMap.UserCard.ddmUserCard;

        ddmUserCard.isDisplayed();
    }

    @And("I should see the {string} button in usercard menu which is enabled")
    public void iShouldSeeTheButtonInUsercardMenuWhichIsEnabled(String arg0) {
        WebElement button = AeviAdminMap.UserCard.btnLogOut;

        button.isDisplayed();
    }

    @When("I click on the {string} button in usercard menu")
    public void iClickOnTheButtonInUsercardMenu(String arg0) {
        WebElement button = AeviAdminMap.UserCard.btnLogOut;

        button.click();
    }

    @Then("I should see the {string} dropdown text menu which is enabled and has {string} value selected")
    public void iShouldSeeTheDropdownTextMenuWhichIsEnabledAndHasValueSelected(String arg0, String arg1) {
        WebElement dropdown = null;

        switch (arg0)
        {
            case "Parent Unit":
                dropdown = AeviAdminMap.OrganizationUnitsSite.ddlParentUnit;
                break;
            case "Organization Unit":
                dropdown = AeviAdminMap.TerminalsAdd.ddlOrganizationUnit;
                break;
            default:
                Assert.fail("Dropdow text menu element not found: " + arg0);
                break;
        }

        String value = null;
        switch (arg1)
        {
            case "":
                arg1 = null;
                value = dropdown.getAttribute("value");
                break;
            default:
                value = dropdown.getText();
                break;
        }
        dropdown.isDisplayed();
        dropdown.isEnabled();

        Assert.assertEquals(arg1, value);
    }

    @Then("the {string} textbox should starts with {string} string")
    public void theTextboxShouldStartsWithString(String arg0, String arg1) {
        WebElement textbox = AeviAdminShared.FindTextboxByName(arg0);

        AeviAdminShared.ThreadWait(1000);
        Assert.assertTrue (arg1, textbox.getAttribute("value").startsWith(arg1));
    }
}
