package glue.UI;

import base.UI.BaseUtil;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import map.UI.AeviAdminMap;
import org.apache.velocity.runtime.directive.Foreach;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.pagefactory.ByChained;
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
            case "AEVI Pay Admin | Login":
                page = AeviAdminMap.LoginPage.page;
                break;
            case "AEVI Pay Admin | Organization Units":
                page = AeviAdminMap.OrganizationUnits.page;
                break;
            case "AEVI Pay Admin| Data Groups":
                page = AeviAdminMap.DataGroups.page;
                break;
            case "AEVI Pay Admin| Data Group":
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
            By button = AeviAdminShared.FindButtonByName(arg0);

            driver.findElement(button).click();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            String sPage = driver.getTitle();
            switch (sPage)
            {
                case "AEVI Pay Admin | Data Groups":
                    ///AeviAdminMap.DataGroups.btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a"));
                    ///AeviAdminMap.DataGroups.btnSearch = driver.findElement(By.name("_form1"));
                    ///AeviAdminMap.DataGroups.btnDelete = driver.findElement(By.id("deleteButton"));
                    break;
                default:
                    Assert.fail("Missing StaleElementReference for this page");
                    break;
            }

            By button = AeviAdminShared.FindButtonByName(arg0);

            driver.findElement(button).click();
        }
    }

    @And("I should see the {string} button in the sidebar menu")
    public void iShouldSeeTheButtonInTheSidebarMenu(String arg0) {
        By button = null;
        switch(arg0)
        {
            case "Form Configs":
                //AeviAdminMap.SideBarMenu.btnFormConfigs = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/a"));
                button = AeviAdminMap.SideBarMenu.btnFormConfigs;
                break;
            case "Data Groups":
                AeviAdminShared.ThreadWait(2000);
                //AeviAdminMap.SideBarMenu.btnDataGroups = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/div/ul/li[2]/a/span"));
                button = AeviAdminMap.SideBarMenu.btnDataGroups;
                break;
            case "Terminals":
                button = AeviAdminMap.SideBarMenu.btnTerminals;
                break;
            case "Contracts":
                //AeviAdminMap.SideBarMenu.btnContracts = driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/div/ul/li[5]/a/span"));
                button = AeviAdminMap.SideBarMenu.btnContracts;
                break;
            default:
                Assert.fail("Button in sidebar menu not found: " + arg0);
                break;
        }

        driver.findElement(button).isDisplayed();
    }

    @When("I click on the {string} button in the sidebar menu")
    public void iClickOnTheButtonlinkInTheSidebarMenu(String arg0) {
        By button = null;
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

        driver.findElement(button).click();
    }

    @And("I should see the {string} textbox which is enabled and empty")
    public void iShouldSeeTheTextboxWhichIsEnabledAndEmpty(String arg0) {
        try {
            By byTextbox = AeviAdminShared.FindTextboxByName(arg0);

            WebElement textbox = driver.findElement(byTextbox);
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
                    ///AeviAdminMap.TerminalsAdd.txtTerminalId = driver.findElement(By.id("terminalIdInput"));
                    ///AeviAdminMap.TerminalsAdd.ddlOrganizationUnit = driver.findElement(By.id("select2-organizationUnitInput-container"));
                    ///AeviAdminMap.TerminalsAdd.ddlTerminalProfile = driver.findElement(By.id("terminalProfileSelector"));
                    ///AeviAdminMap.TerminalsAdd.btnSiteIdReload = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[1]/div[1]/div[1]/div/button"));
                    ///AeviAdminMap.OrganizationUnitsSiteDropdownSite.txtParentUnitSearch = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
                    ///AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit = driver.findElement(By.id("select2-organizationUnitInput-results"));
                    break;
                case "AEVI Pay Admin | Data Groups":
                    ///AeviAdminMap.DataGroups.btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a"));
                    ///AeviAdminMap.DataGroups.btnSearch = driver.findElement(By.name("_form1"));
                    ///AeviAdminMap.DataGroups.txtName = driver.findElement(By.id("criteria.name"));
                    break;
                default:
                    Assert.fail("Missing StaleElementReference for this page");
                    break;
            }

            By byTextbox = AeviAdminShared.FindTextboxByName(arg0);

            WebElement textbox = driver.findElement(byTextbox);
            textbox.isDisplayed();
            textbox.isEnabled();
            Assert.assertTrue(textbox.getText().isEmpty());
        }
    }

    @And("I should see the {string} checkbox which is enabled and {string}")
    public void iShouldSeeTheCheckboxWhichIsEnabledAnd(String arg0, String arg1) {
        By byCheckbox = AeviAdminShared.FindCheckboxByName(arg0);

        WebElement checkbox = driver.findElement(byCheckbox);

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
            By textbox = AeviAdminShared.FindTextboxByName(arg1);

            driver.findElement(textbox).sendKeys(arg0);
        }
        catch(org.openqa.selenium.ElementNotInteractableException ex)
        {
            //WebElement textbox = AeviAdminShared.FindTextboxByName(arg1);
            By byTextbox = AeviAdminShared.FindTextboxByName(arg1);

            WebElement textbox = driver.findElement(byTextbox);

            textbox.click();
            textbox.sendKeys(arg0);
        }
    }

    @Then("the {string} textbox should contain {string} string")
    public void theTextboxShouldContainString(String arg0, String arg1)
    {
        try {
            By byTextbox = AeviAdminShared.FindTextboxByName(arg0);

            WebElement textbox = driver.findElement(byTextbox);

            Assert.assertEquals(arg1, textbox.getAttribute("value"));
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            String sPage = driver.getTitle();
            switch (sPage)
            {
                case "AEVI Pay Admin | Data Groups":
                    //AeviAdminMap.DataGroups.txtName = driver.findElement(By.id("criteria.name"));
                    break;
                default:
                    Assert.fail("Missing StaleElementReference for this page");
                    break;
            }

            By byTextbox = AeviAdminShared.FindTextboxByName(arg0);

            WebElement textbox = driver.findElement(byTextbox);

            Assert.assertEquals(arg1, textbox.getAttribute("value"));
        }
    }

    @When("I click on the {string} checkbox")
    public void iClickOnTheCheckbox(String arg0) {
        By byCheckbox = null;


        switch (arg0) {
            case "Status":
                byCheckbox = AeviAdminMap.DataGroupsAdd.chkStatus_Click;
                break;
            case "Check Site ID/Terminal ID":
                byCheckbox = AeviAdminMap.DataGroupsAdd.chkSiteTerminalId_Click;
                break;
            default:
                Assert.fail("Checkbox not found: " + arg0);
                break;
        }

        driver.findElement(byCheckbox).click();
    }

    @When("I click on the {string} tab")
    public void iClickOnTheTab(String arg0) {
        By byTab = AeviAdminShared.FindTabByName(arg0);

        driver.findElement(byTab).click();
    }

    @And("I should see the {string} dropdown menu which is enabled and has {string} value selected")
    public void iShouldSeeTheDropdownMenuWhichIsEnabledAndHasValueSelected(String arg0, String arg1)
    {
        By byDropdown = null;

        switch (arg0)
        {
            case "TID Generator Template":
                byDropdown = AeviAdminMap.DataGroupsAdd.ddlTIDGeneratorTemplate;
                break;
            case "Parent Unit":
                byDropdown = AeviAdminMap.OrganizationUnitsSite.ddlParentUnit;
                break;
            case "Organization Unit":
                byDropdown = AeviAdminMap.TerminalsAdd.ddlOrganizationUnit;
                break;
            case "Terminal Profile":
                byDropdown = AeviAdminMap.TerminalsAdd.ddlTerminalProfile;
                break;
            case "Applications Profile":
                AeviAdminShared.ThreadWait(2000);
                byDropdown = AeviAdminMap.ContractsAdd.ddlApplicationsProfile;
                break;
            default:
                Assert.fail("Dropdow menu element not found: " + arg0);
                break;
        }

        WebElement dropdown = driver.findElement(byDropdown);

        dropdown.isDisplayed();
        dropdown.isEnabled();

        Select items = new Select(dropdown);
        Assert.assertEquals(arg1, items.getFirstSelectedOption().getText());
    }

    @When("I select {string} string from the {string} dropdown menu")
    public void iSelectStringFromTheDropdownMenu(String arg0, String arg1) {
        By byDropdown = null;

        switch (arg1)
        {
            case "TID Generator Template":
                byDropdown = AeviAdminMap.DataGroupsAdd.ddlTIDGeneratorTemplate;
                break;
            case "Applications Profile":
                byDropdown = AeviAdminMap.ContractsAdd.ddlApplicationsProfile;
                break;
            case "Terminal Profile":
                byDropdown = AeviAdminMap.TerminalsAdd.ddlTerminalProfile;
                break;
            default:
                Assert.fail("Dropdow menu element not found: " + arg1);
                break;
        }

        WebElement dropdown = driver.findElement(byDropdown);

        Select items = new Select(dropdown);
        items.selectByVisibleText(arg0);
    }

    @Then("I should see the {string} success message")
    public void iShouldSeeTheSuccessMessage(String arg0) {
        try
        {
            By byMsg = null;
            switch (arg0)
            {
                case "Success! Data Group was created successfully.":
                    byMsg = AeviAdminMap.DataGroupsAddMessage.msgSuccess;
                    break;
                case "Success! Data Group was updated successfully.":
                    byMsg = AeviAdminMap.DataGroupsAddMessage.msgSuccess;
                    break;
                case "Success! Data Group test1 has been successfully removed.":
                    byMsg = AeviAdminMap.DataGroupsAddMessage.msgSuccess;
                    break;
                default:
                    Assert.fail("Message not found: " + arg0);
                    break;
            }

            WebElement msg = driver.findElement(byMsg);
            Assert.assertEquals(arg0, msg.getText());
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            //AeviAdminMap.DataGroupsAddMessage.msgSuccess = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div[1]/div[2]"));

            By byMsg = null;
            switch (arg0)
            {
                case "Success! Data Group was created successfully.":
                    byMsg = AeviAdminMap.DataGroupsAddMessage.msgSuccess;
                    break;
                case "Success! Data Group was updated successfully.":
                    byMsg = AeviAdminMap.DataGroupsAddMessage.msgSuccess;
                    break;
                case "Success! Data Group test1 has been successfully removed.":
                    byMsg = AeviAdminMap.DataGroupsAddMessage.msgSuccess;
                    break;
                default:
                    Assert.fail("Message not found: " + arg0);
                    break;
            }
            WebElement msg = driver.findElement(byMsg);
            Assert.assertEquals(arg0, msg.getText());
        }
    }

    @And("I should see the {string} button which is enabled")
    public void iShouldSeeTheButtonWhichIsEnabled(String arg0) {
        try
        {
            By byButton = AeviAdminShared.FindButtonByName(arg0);

            WebElement button = driver.findElement(byButton);
            button.isDisplayed();
            button.isEnabled();
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            String sPage = driver.getTitle();
            switch (sPage)
            {
                case "AEVI Pay Admin | Login":
                    ///AeviAdminMap.LoginPage.btnSaveChanges = driver.findElement(By.xpath("//*[@id=\"command\"]/div/div[3]/button[2]"));
                    break;
                case "AEVI Pay Admin | Data Group":
                    ///AeviAdminMap.DataGroupsAdd.btnOK = driver.findElement(By.name("_form2"));
                    ///AeviAdminMap.DataGroupsAdd.txtName = driver.findElement(By.id("dataGroup.name"));
                    ///AeviAdminMap.DataGroupsAdd.chkStatus = driver.findElement(By.id("status"));
                    ///AeviAdminMap.DataGroupsAdd.chkStatus_Click = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[2]/div[1]/div[1]"));
                    ///AeviAdminMap.DataGroupsAdd.chkSiteTerminalId = driver.findElement(By.id("checkSiteTerminalId"));
                    ///AeviAdminMap.DataGroupsAdd.chkSiteTerminalId_Click = driver.findElement(By.xpath("//*[@id=\"basicPropertyGroup\"]/div[3]/div[1]/div[1]/label/span"));
                    ///AeviAdminMap.DataGroupsAdd.tabParameters = driver.findElement(By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/ul/li[2]/a"));
                    ///AeviAdminMap.DataGroupsAdd.ddlTIDGeneratorTemplate = driver.findElement(By.id("dataGroup.tidGeneratorTemplate"));
                    break;
                case "AEVI Pay Admin | Data Groups":
                    ///AeviAdminMap.DataGroups.btnNewRecord = driver.findElement(By.xpath("//*[@id=\"command\"]/div[1]/div[2]/div/div/a"));
                    ///AeviAdminMap.DataGroups.btnSearch = driver.findElement(By.name("_form1"));
                    ///AeviAdminMap.DataGroups.txtName = driver.findElement(By.id("criteria.name"));
                    ///AeviAdminMap.DataGroups.btnDelete = driver.findElement(By.id("deleteButton"));
                    break;
                default:
                    Assert.fail("Missing StaleElementReference for this page");
                    break;
            }


            By byButton = AeviAdminShared.FindButtonByName(arg0);
            WebElement button = driver.findElement(byButton);

            button.isDisplayed();
            button.isEnabled();
        }
    }

    @When("I click on the {string} button in the button dropdown menu")
    public void iClickOnTheButtonInTheButtonDropdownMenu(String arg0) {

        By byButton = AeviAdminShared.FindButtonByName(arg0);

        driver.findElement(byButton).click();
    }

    @When("I click on the {string} dropdown menu")
    public void iClickOnTheDropdownMenu(String arg0) {
        By byDropdown = null;

        switch(arg0)
        {
            case "Parent Unit":
                byDropdown = AeviAdminMap.OrganizationUnitsSite.ddlParentUnit;
                break;
            case "Organization Unit":
                byDropdown = AeviAdminMap.TerminalsAdd.ddlOrganizationUnit;
                break;
            case "Terminal Profile":
                byDropdown = AeviAdminMap.TerminalsAdd.ddlTerminalProfile;
                break;
            default:
                Assert.fail("Dropdown not found: " + arg0);
                break;
        }

        driver.findElement(byDropdown).click();
    }

    @Then("the {string} dropdown list should contain {string} string")
    public void theDropdownListShouldContainString(String arg0, String arg1) {
        try
        {
            By byElement = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;

            WebElement element = driver.findElement(byElement);
            List<WebElement> list = element.findElements(By.tagName("li"));
            AeviAdminShared.ThreadWait(1000);
            Assert.assertEquals(arg1, list.get(0).getText());
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            //AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit = driver.findElement(By.id("select2-organizationUnitInput-results"));
            By byElement = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;

            WebElement element = driver.findElement(byElement);
            List<WebElement> list = element.findElements(By.tagName("li"));
            Assert.assertEquals(arg1, list.get(0).getText());
        }

    }

    @When("I click on the {string} dropdown menu item")
    public void iClickOnTheDropdownMenuItem(String arg0) {
        try
        {
            By byElement = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;
            WebElement element = driver.findElement(byElement);

            AeviAdminShared.Wait(2);
            List<WebElement> list = element.findElements(By.tagName("li"));
            list.get(0).click();
        }
        catch (org.openqa.selenium.StaleElementReferenceException ex)
        {
            By byElement = AeviAdminMap.OrganizationUnitsSiteDropdownSiteList.listParentUnit;
            WebElement element = driver.findElement(byElement);

            AeviAdminShared.Wait(2);
            List<WebElement> list = element.findElements(By.tagName("li"));
            list.get(0).click();
        }


    }

    @Then("I should see the {string} button dropdown menu")
    public void iShouldSeeTheButtonDropdownMenu(String arg0) {
        By byDropdown = null;
        String sPage = driver.getTitle();

        switch (sPage)
        {
            case "AEVI Pay Admin | Organization Units":
                byDropdown = AeviAdminMap.OrganizationUnits.ddlChooseAnOption;
                break;
            case "AEVI Pay Admin | Contracts":
                byDropdown = AeviAdminMap.Contracts.ddlChooseAnOption;
                break;
            default:
                Assert.fail("Button in dropdown menu not found: " + arg0);
                break;

        }

        driver.findElement(byDropdown).isDisplayed();
    }

    @And("I should see the {string} tab which is enabled")
    public void iShouldSeeTheTabWhichIsEnabled(String arg0) {
        By byTab = AeviAdminShared.FindTabByName(arg0);
        WebElement tab = driver.findElement(byTab);

        tab.isDisplayed();
        tab.isEnabled();
    }

    @Then("I should see the {string} dropdown text menu which is enabled and has {string} value selected")
    public void iShouldSeeTheDropdownTextMenuWhichIsEnabledAndHasValueSelected(String arg0, String arg1) {
        By byDropdown = null;

        switch (arg0)
        {
            case "Parent Unit":
                byDropdown = AeviAdminMap.OrganizationUnitsSite.ddlParentUnit;
                break;
            case "Organization Unit":
                byDropdown = AeviAdminMap.TerminalsAdd.ddlOrganizationUnit;
                break;
            default:
                Assert.fail("Dropdow text menu element not found: " + arg0);
                break;
        }

        WebElement dropdown = driver.findElement(byDropdown);

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
        By byTextbox = AeviAdminShared.FindTextboxByName(arg0);
        WebElement textbox = driver.findElement(byTextbox);

        AeviAdminShared.ThreadWait(1000);
        Assert.assertTrue (arg1, textbox.getAttribute("value").startsWith(arg1));
    }

    @And("I should see the {string} table")
    public void iShouldSeeTheTable(String arg0) {
        try {
            By byTable = null;
            switch (arg0)
            {
                case "Accepted Application Profiles":
                    byTable = AeviAdminMap.ContractsAdd.tblProfile;
                    break;
                case "Data Groups":
                    byTable = AeviAdminMap.DataGroups.tblDataGroups;
                    break;
            }

            driver.findElement(byTable).isDisplayed();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            //AeviAdminMap.DataGroups.tblDataGroups = driver.findElement(By.xpath("//*[@id=\"command\"]/div[2]/div[5]/div[1]/div/table"));

            By byTable = null;
            switch (arg0)
            {
                case "Accepted Application Profiles":
                    byTable = AeviAdminMap.ContractsAdd.tblProfile;
                    break;
                case "Data Groups":
                    byTable = AeviAdminMap.DataGroups.tblDataGroups;
                    break;
            }

            driver.findElement(byTable).isDisplayed();
        }
    }

    @When("I logout from {string} page")
    public void iLogoutFromPage(String arg0) {
        ///AeviAdminMap.UserCard.ddmUserCard = driver.findElement(By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[2]"));
        ///AeviAdminMap.UserCard.btnUserCard = driver.findElement(By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[1]/div"));
        ///AeviAdminMap.UserCard.btnLogOut = driver.findElement(By.xpath("//a[.='Log Out']"));

        By byUserCard = AeviAdminMap.UserCard.btnUserCard;
        driver.findElement(byUserCard).click();

        By byDdUserCard = AeviAdminMap.UserCard.ddmUserCard;
        driver.findElement(byDdUserCard).isDisplayed();

        By byLogOut = AeviAdminMap.UserCard.btnLogOut;
        WebElement logOut = driver.findElement(byLogOut);
        logOut.isDisplayed();
        logOut.click();
    }

    @And("I should see {string} string in row {string} of the {string} column in {string} table")
    public void iShouldSeeStringInRowOfTheColumnInTable(String arg0, String arg1, String arg2, String arg3) {
        List<WebElement> headers = null;
        List<WebElement> rowElements = null;

        switch (arg3) {
            case "Accepted Application Profiles":
                headers= driver.findElement(AeviAdminMap.ContractsAdd.tblProfileHeaders).findElements(By.tagName("th"));
                rowElements = driver.findElement(AeviAdminMap.ContractsAdd.tblProfileBodyFirstRow).findElements(
                        new ByChained(
                                By.xpath("./tr[" + (Integer.parseInt(arg1) + 1) + "]"),
                                By.tagName("td")));
                break;
            case "Data Groups":
                headers= driver.findElement(AeviAdminMap.DataGroups.tblDataGroups).findElements(By.tagName("th"));
                rowElements = driver.findElement(AeviAdminMap.DataGroups.tblDataGroups).findElements(
                        new ByChained(
                                By.xpath("./tbody/tr[" + arg1 + "]"),
                                By.tagName("td")));
                break;
        }

        String cellValue = AeviAdminShared.GetCellValueByColumnNameAndRowIndex(headers, rowElements, arg2);
        Assert.assertEquals(arg0, cellValue);
    }

    @Then("I should see the {string} edit page")
    public void iShouldSeeTheEditPage(String arg0) {
        String page = "";
        switch(arg0)
        {
            case "AEVI Pay Admin| Data Group":
                page = AeviAdminMap.DataGroupsAdd.pageEdit;
                break;
        }

        Assert.assertTrue (arg0, driver.getCurrentUrl().startsWith(page));
    }

    @When("I click on the row {string} in {string} table")
    public void iClickOnTheRowInTable(String arg0, String arg1) {
        WebElement element = null;

        switch (arg1) {
            case "Data Groups":
                element = driver.findElement(AeviAdminMap.DataGroups.tblDataGroups).findElement(By.xpath("./tbody/tr[" + arg0 + "]"));
                break;
        }

        element.click();
    }

    @When("I click on checkbox in row {string} in {string} table")
    public void iClickOnCheckboxInRowInTable(String arg0, String arg1) {
        WebElement element = null;

        switch (arg1) {
            case "Data Groups":
                element = driver.findElement(AeviAdminMap.DataGroups.tblDataGroups).findElement(By.xpath("./tbody/tr[" + arg0 + "]/td[2]/input[1]"));
                break;
        }

        element.click();
    }

    @Then("I should see the {string} modal")
    public void iShouldSeeTheModal(String arg0) {
        By byModal = null;
        switch (arg0)
        {
            case "Delete | Data Group":
                byModal = AeviAdminMap.DataGroupsDeleteModal.modal;
        }

        WebElement modal = driver.findElement(byModal);
        modal.isEnabled();
        modal.isDisplayed();
    }

    @And("I should see the {string} modal button which is enabled")
    public void iShouldSeeTheModalButtonWhichIsEnabled(String arg0) {
        By byButton = null;
        switch (arg0)
        {
            case "Delete":
                byButton = AeviAdminMap.DataGroupsDeleteModal.btnDelete;
        }

        AeviAdminShared.ThreadWait(2000);
        WebElement button = driver.findElement(byButton);

        button.isDisplayed();
        button.isEnabled();
    }

    @When("I click on the {string} modal button")
    public void iClickOnTheModalButton(String arg0) {
        By byButton = null;
        switch (arg0)
        {
            case "Delete":
                byButton = AeviAdminMap.DataGroupsDeleteModal.btnDelete;
        }

        driver.findElement(byButton).click();
    }
}
