package glue.ui;

import base.ui.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import map.ui.AeviAdminMap;
import org.openqa.selenium.*;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.Select;
import shared.ui.AeviAdminShared;
import org.testng.Assert;
import shared.ui.WebDriverWaitShared;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminSharedSteps extends BaseUtil {
    private final String _url = WEBPAGE_URL;
    String storedValue = "";

    @Given("I have opened the {string} page")
    public void iHaveOpenedThePage(String page) {
        driver.get(_url);
    }

    @Then("I should see the {string} page")
    public void iShouldSeeThePage(String pageTitle) {
        String page = "";
        switch (pageTitle) {
            case "AEVI Pay Admin | Login" -> {
                page = AeviAdminMap.LoginPage.page;
                WebDriverWaitShared.waitUrlToBe(page);
            }
            case "AEVI Pay Admin | Organization Units" -> page = AeviAdminMap.OrganizationUnits.page;
            case "AEVI Pay Admin | Data Groups" -> page = AeviAdminMap.DataGroups.page;
            case "AEVI Pay Admin | Data Group" -> page = AeviAdminMap.DataGroupsAdd.page;
            case "AEVI Pay Admin | Site" -> page = AeviAdminMap.OrganizationUnitsAddSite.page;
            case "AEVI Pay Admin | Terminals" -> page = AeviAdminMap.Terminals.page;
            case "AEVI Pay Admin | Terminal" -> page = AeviAdminMap.TerminalsAdd.page;
            case "AEVI Pay Admin | Contracts" -> page = AeviAdminMap.Contracts.page;
            case "AEVI Pay Admin | Contract" -> page = AeviAdminMap.ContractsAdd.page;
            case "AEVI Pay Admin | Acquirers/Service Provider" -> page = AeviAdminMap.AcquirersServiceProvidersAdd.page;
            case "AEVI Pay Admin | Acquirers/Service Providers" -> page = AeviAdminMap.AcquirersServiceProviders.page;
            case "AEVI Pay Admin | Contract Profiles" -> page = AeviAdminMap.ContractProfiles.page;
            case "AEVI Pay Admin | Contract Profile" -> page = AeviAdminMap.ContractProfilesAdd.page;
            default -> Assert.fail("Page not found: " + pageTitle);
        }

        Assert.assertEquals(page, driver.getCurrentUrl());
    }

    @When("I click on the {string} button")
    public void iClickOnTheButton(String button) {
        By byButton = AeviAdminShared.findButtonByName(button, driver.getTitle());

        driver.findElement(byButton).click();
    }

    @And("I should see the {string} button in the sidebar menu")
    public void iShouldSeeTheButtonInTheSidebarMenu(String button) {
        By byButton = null;
        switch (button) {
            case "Data Groups" -> {
                byButton = AeviAdminMap.SideBarMenu.btnDataGroups;
                WebDriverWaitShared.waitTextToBe(byButton, "Data Groups");
            }
            case "Advanced Configs" -> {
                byButton = AeviAdminMap.SideBarMenu.btnAdvancedConfigs;
                WebDriverWaitShared.waitTextToBe(byButton, "Advanced Configs");
            }
            case "Contract Profiles" -> {
                byButton = AeviAdminMap.SideBarMenu.btnContractProfiles;
                WebDriverWaitShared.waitTextToBe(byButton, "Contract Profiles");
            }
            case "Terminals" -> {
                byButton = AeviAdminMap.SideBarMenu.btnTerminals;
                WebDriverWaitShared.waitTextToBe(byButton, "Terminals");
            }
            case "Form Configs" -> byButton = AeviAdminMap.SideBarMenu.btnFormConfigs;
            case "Contracts" -> byButton = AeviAdminMap.SideBarMenu.btnContracts;
            case "Acquirers/Service Providers" -> byButton = AeviAdminMap.SideBarMenu.btnAcquirerServiceProvides;
            case "Profiles" -> byButton = AeviAdminMap.SideBarMenu.btnProfiles;
            default -> Assert.fail("Button in sidebar menu not found: " + button);
        }

        driver.findElement(byButton).isDisplayed();
    }

    @When("I click on the {string} button in the sidebar menu")
    public void iClickOnTheButtonlinkInTheSidebarMenu(String button) {
        By byButton = null;
        switch (button) {
            case "Form Configs" -> byButton = AeviAdminMap.SideBarMenu.btnFormConfigs;
            case "Data Groups" -> byButton = AeviAdminMap.SideBarMenu.btnDataGroups;
            case "Terminals" -> byButton = AeviAdminMap.SideBarMenu.btnTerminals;
            case "Contracts" -> byButton = AeviAdminMap.SideBarMenu.btnContracts;
            case "Advanced Configs" -> byButton = AeviAdminMap.SideBarMenu.btnAdvancedConfigs;
            case "Acquirers/Service Providers" -> byButton = AeviAdminMap.SideBarMenu.btnAcquirerServiceProvides;
            case "Profiles" -> byButton = AeviAdminMap.SideBarMenu.btnProfiles;
            case "Contract Profiles" -> byButton = AeviAdminMap.SideBarMenu.btnContractProfiles;
            case "Organizations & Terminals" -> byButton = AeviAdminMap.SideBarMenu.btnOrganizationsAndTerminals;
            default -> Assert.fail("Button in sidebar menu not found: " + button);
        }

        driver.findElement(byButton).click();
    }

    @And("I should see the {string} textbox which is enabled and empty")
    public void iShouldSeeTheTextboxWhichIsEnabledAndEmpty(String textbox) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());
        WebElement textboxElement = driver.findElement(byTextbox);

        textboxElement.isDisplayed();
        textboxElement.isEnabled();
        Assert.assertTrue(textboxElement.getText().isEmpty());
    }

    @And("I should see the {string} checkbox which is enabled and {string}")
    public void iShouldSeeTheCheckboxWhichIsEnabledAnd(String checkbox, String status) {
        By byCheckbox = AeviAdminShared.findCheckboxByName(checkbox, driver.getTitle());
        WebElement checkboxElement = driver.findElement(byCheckbox);

        checkboxElement.isDisplayed();
        checkboxElement.isEnabled();

        switch (status) {
            case "checked" -> Assert.assertTrue(checkboxElement.isSelected());
            case "not checked" -> Assert.assertFalse(checkboxElement.isSelected());
            default -> Assert.fail("Checkbox not found: " + checkbox);
        }
    }

    @When("I enter {string} string into {string} textbox")
    public void iEnterStringIntoTextbox(String text, String textbox) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());

        driver.findElement(byTextbox).sendKeys(text);
    }

    @Then("the {string} textbox should contain {string} string")
    public void theTextboxShouldContainString(String textbox, String text) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());
        WebElement textboxElement = driver.findElement(byTextbox);

        Assert.assertEquals(textboxElement.getAttribute("value"), text);
    }

    @When("I click on the {string} checkbox")
    public void iClickOnTheCheckbox(String checkbox) {
        By byCheckbox = null;

        switch (checkbox) {
            case "Status" -> byCheckbox = AeviAdminMap.DataGroupsAdd.chkStatus_Click;
            case "Check Site ID/Terminal ID" -> byCheckbox = AeviAdminMap.DataGroupsAdd.chkSiteTerminalId_Click;
            default -> Assert.fail("Checkbox not found: " + checkbox);
        }

        driver.findElement(byCheckbox).click();
    }

    @When("I click on the {string} tab")
    public void iClickOnTheTab(String tab) {
        By byTab = AeviAdminShared.findTabByName(tab, driver.getTitle());

        driver.findElement(byTab).click();
    }

    @And("I should see the {string} dropdown menu which is enabled and has {string} value selected")
    public void iShouldSeeTheDropdownMenuWhichIsEnabledAndHasValueSelected(String dropdown, String text) {
        By byDropdown = null;

        switch (dropdown) {
            case "TID Generator Template" -> byDropdown = AeviAdminMap.DataGroupsAdd.ddlTIDGeneratorTemplate;
            case "Parent Unit" -> byDropdown = AeviAdminMap.OrganizationUnitsAddSite.ddlParentUnit;
            case "Organization Unit" -> byDropdown = AeviAdminMap.TerminalsAdd.ddlOrganizationUnit;
            case "Terminal Profile" -> byDropdown = AeviAdminMap.TerminalsAdd.ddlTerminalProfile;
            case "Applications Profile" -> byDropdown = AeviAdminMap.ContractsAdd.ddlApplicationsProfile;
            case "Host" -> byDropdown = AeviAdminMap.AcquirersServiceProvidersAdd.ddlHost;
            case "Batch Close Type" -> byDropdown = AeviAdminMap.AcquirersServiceProvidersAdd.ddlBatchCloseType;
            case "Acquirer/Service Provider" -> byDropdown = AeviAdminMap.ContractsAdd.ddlAquireServiceProvider;
            case "Contract Applications Profile" -> byDropdown = AeviAdminMap.ContractProfilesAdd.ddlApplicationsProfile;
            case "Host TID Type" -> byDropdown = AeviAdminMap.AcquirersServiceProvidersAdd.ddlHostTIDType;
            case "Host Device ID Type" -> byDropdown = AeviAdminMap.AcquirersServiceProvidersAdd.ddlHostDeviceIdType;
            default -> Assert.fail("Dropdow menu element not found: " + dropdown);
        }

        WebElement dropdownElement = driver.findElement(byDropdown);

        dropdownElement.isDisplayed();
        dropdownElement.isEnabled();

        Select items = new Select(dropdownElement);
        String ddlText = "";
        if (items.getOptions().size() > 0) {
            ddlText = items.getFirstSelectedOption().getText();
        }

        Assert.assertEquals(text, ddlText);
    }

    @When("I select {string} string from the {string} dropdown menu")
    public void iSelectStringFromTheDropdownMenu(String text, String dropdown) {
        By byDropdown = null;

        switch (dropdown) {
            case "TID Generator Template" -> byDropdown = AeviAdminMap.DataGroupsAdd.ddlTIDGeneratorTemplate;
            case "Applications Profile" -> byDropdown = AeviAdminMap.ContractsAdd.ddlApplicationsProfile;
            case "Terminal Profile" -> byDropdown = AeviAdminMap.TerminalsAdd.ddlTerminalProfile;
            case "Acquirer/Service Provider" -> byDropdown = AeviAdminMap.ContractsAdd.ddlAquireServiceProvider;
            case "Contract Applications Profile" -> byDropdown = AeviAdminMap.ContractProfilesAdd.ddlApplicationsProfile;
            case "Host TID Type" -> byDropdown = AeviAdminMap.AcquirersServiceProvidersAdd.ddlHostTIDType;
            case "Host Device ID Type" -> byDropdown = AeviAdminMap.AcquirersServiceProvidersAdd.ddlHostDeviceIdType;
            default -> Assert.fail("Dropdow menu element not found: " + dropdown);
        }

        WebElement dropdownElement = driver.findElement(byDropdown);

        Select items = new Select(dropdownElement);
        items.selectByVisibleText(text);
    }

    @Then("I should see the {string} success message")
    public void iShouldSeeTheSuccessMessage(String message) {
        WebElement messageElement = driver.findElement(AeviAdminMap.DataGroups.msgSuccess);

        Assert.assertEquals(message, messageElement.getText());
    }

    @And("I should see the {string} button which is enabled")
    public void iShouldSeeTheButtonWhichIsEnabled(String button) {
        By byButton = AeviAdminShared.findButtonByName(button, driver.getTitle());

        WebElement buttonElement = driver.findElement(byButton);
        buttonElement.isDisplayed();
        buttonElement.isEnabled();
    }

    @When("I click on the {string} button in the button dropdown menu")
    public void iClickOnTheButtonInTheButtonDropdownMenu(String button) {
        By byButton = AeviAdminShared.findButtonByName(button, driver.getTitle());

        driver.findElement(byButton).click();
    }

    @When("I click on the {string} dropdown menu")
    public void iClickOnTheDropdownMenu(String dropdown) {
        By byDropdown = null;

        switch (dropdown) {
            case "Parent Unit" -> byDropdown = AeviAdminMap.OrganizationUnitsAddSite.ddlParentUnit;
            case "Organization Unit" -> byDropdown = AeviAdminMap.TerminalsAdd.ddlOrganizationUnit;
            case "Terminal Profile" -> byDropdown = AeviAdminMap.TerminalsAdd.ddlTerminalProfile;
            case "Site" -> byDropdown = AeviAdminMap.ContractsAdd.ddlSite;
            default -> Assert.fail("Dropdown not found: " + dropdown);
        }

        driver.findElement(byDropdown).click();
    }

    @Then("the {string} dropdown list should contain {string} string")
    public void theDropdownListShouldContainString(String dropdown, String text) {
        By byDropdown = null;

        switch (dropdown) {
            case "Parent Unit" -> byDropdown = AeviAdminMap.OrganizationUnitsAddSite.listParentUnit;
            case "Organization Unit" -> byDropdown = AeviAdminMap.TerminalsAdd.listOrganizationUnit;
            case "Site" -> byDropdown = AeviAdminMap.ContractsAdd.listSite;
            default -> Assert.fail("Dropdown list not found: " + dropdown);
        }

        WebElement element = driver.findElement(byDropdown);
        WebDriverWaitShared.waitTextToBe(byDropdown, text);

        List<WebElement> list = element.findElements(By.tagName("li"));
        Assert.assertEquals(text, list.get(0).getText());
    }

    @When("I click on the {string} dropdown menu item")
    public void iClickOnTheDropdownMenuItem(String dropdown) {
        By byElement = AeviAdminMap.OrganizationUnitsAddSite.listParentUnit;
        WebElement element = driver.findElement(byElement);

        List<WebElement> list = element.findElements(By.tagName("li"));
        list.get(0).click();
    }

    @Then("I should see the {string} button dropdown menu")
    public void iShouldSeeTheButtonDropdownMenu(String button) {
        By byDropdown = null;
        String sPage = driver.getTitle();

        switch (sPage) {
            case "AEVI Pay Admin | Organization Units" -> byDropdown = AeviAdminMap.OrganizationUnits.ddlChooseAnOption;
            case "AEVI Pay Admin | Contracts" -> byDropdown = AeviAdminMap.Contracts.ddlChooseAnOption;
            case "AEVI Pay Admin | Acquirers/Service Providers" -> byDropdown = AeviAdminMap.AcquirersServiceProviders.ddlChooseAnOption;
            default -> Assert.fail("Button in dropdown menu not found: " + button);
        }

        driver.findElement(byDropdown).isDisplayed();
    }

    @And("I should see the {string} tab which is enabled")
    public void iShouldSeeTheTabWhichIsEnabled(String tab) {
        By byTab = AeviAdminShared.findTabByName(tab, driver.getTitle());
        WebElement tabElement = driver.findElement(byTab);

        tabElement.isDisplayed();
        tabElement.isEnabled();
    }

    @Then("I should see the {string} dropdown text menu which is enabled and has {string} value selected")
    public void iShouldSeeTheDropdownTextMenuWhichIsEnabledAndHasValueSelected(String textbox, String text) {
        By byDropdown = null;

        switch (textbox) {
            case "Parent Unit" -> byDropdown = AeviAdminMap.OrganizationUnitsAddSite.ddlParentUnit;
            case "Organization Unit" -> byDropdown = AeviAdminMap.TerminalsAdd.ddlOrganizationUnit;
            case "Site" -> byDropdown = AeviAdminMap.ContractsAdd.ddlSite;
            case "Copy Modal Organization Unit" -> byDropdown = AeviAdminMap.Terminals.ddlCopyModalOrganizationUnit;
            default -> Assert.fail("Dropdow text menu element not found: " + textbox);
        }

        WebElement dropdownElement = driver.findElement(byDropdown);

        String value;
        switch (text) {
            case "" -> {
                text = null;
                value = dropdownElement.getAttribute("value");
            }
            default -> value = dropdownElement.getText();
        }
        dropdownElement.isDisplayed();
        dropdownElement.isEnabled();

        Assert.assertEquals(text, value);
    }

    @Then("the {string} textbox should starts with {string} string")
    public void theTextboxShouldStartsWithString(String textbox, String text) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());
        WebElement textboxElement = driver.findElement(byTextbox);

        WebDriverWaitShared.waitAttributeToBeNotEmpty(textboxElement, "value");

        Assert.assertTrue(textboxElement.getAttribute("value").startsWith(text));
    }

    @And("I should see the {string} table")
    public void iShouldSeeTheTable(String table) {
        By byTable = null;

        switch (table) {
            case "Accepted Application Profiles" -> byTable = AeviAdminMap.ContractsAdd.tblProfile;
            case "Contracts" -> byTable = AeviAdminMap.Contracts.tblContracts;
            case "Data Groups" -> byTable = AeviAdminMap.DataGroups.tblDataGroups;
            case "Contract Accepted Application Profiles" -> byTable = AeviAdminMap.ContractProfilesAdd.tblProfile;
            case "Acquirers/Service Providers" -> byTable = AeviAdminMap.AcquirersServiceProviders.tblAcquirerServiceProviders;
            case "Terminals" -> byTable = AeviAdminMap.Terminals.tblTerminals;
        }

        WebDriverWaitShared.waitVisibilityOfElementLocated(byTable);

        driver.findElement(byTable).isDisplayed();
    }

    @When("I logout from {string} page")
    public void iLogoutFromPage(String page) {
        By byUserCard = AeviAdminMap.LoginPage.btnUserCard;
        driver.findElement(byUserCard).click();

        By byDdUserCard = AeviAdminMap.LoginPage.ddmUserCard;
        driver.findElement(byDdUserCard).isDisplayed();

        By byLogOut = AeviAdminMap.LoginPage.btnLogOut;
        WebElement logOutElement = driver.findElement(byLogOut);
        logOutElement.isDisplayed();
        logOutElement.click();
    }

    @And("I should see {string} string in row {string} of the {string} column in {string} table")
    public void iShouldSeeStringInRowOfTheColumnInTable(String text, String row, String columnName, String table) {
        List<WebElement> headers = null;
        List<WebElement> rowElements = null;

        switch (table) {
            case "Accepted Application Profiles" -> {
                headers = driver.findElement(AeviAdminMap.ContractsAdd.tblProfileHeaders).findElements(By.tagName("th"));
                rowElements = driver.findElement(AeviAdminMap.ContractsAdd.tblProfileBodyFirstRow).findElements(
                    new ByChained(
                        By.xpath("./tr[" + (Integer.parseInt(row) + 1) + "]"),
                        By.tagName("td")));
            }
            case "Data Groups" -> {
                headers = driver.findElement(AeviAdminMap.DataGroups.tblDataGroups).findElements(By.tagName("th"));
                rowElements = driver.findElement(AeviAdminMap.DataGroups.tblDataGroups).findElements(
                    new ByChained(
                        By.xpath("./tbody/tr[" + row + "]"),
                        By.tagName("td")));
            }
            case "Contracts" -> {
                headers = driver.findElement(AeviAdminMap.Contracts.tblContracts).findElements(By.tagName("th"));
                rowElements = driver.findElement(AeviAdminMap.Contracts.tblContracts).findElements(
                    new ByChained(
                        By.xpath("./tbody/tr[" + row + "]"),
                        By.tagName("td")));
            }
            case "Contract Accepted Application Profiles" -> {
                headers = driver.findElement(AeviAdminMap.ContractProfilesAdd.tblProfileHeaders).findElements(By.tagName("th"));
                rowElements = driver.findElement(AeviAdminMap.ContractProfilesAdd.tblProfileBodyFirstRow).findElements(
                    new ByChained(
                        By.xpath("./tr"),
                        By.tagName("td")));
            }
            case "Acquirers/Service Providers" -> {
                headers = driver.findElement(AeviAdminMap.AcquirersServiceProviders.tblAcquirerServiceProviders).findElements(By.tagName("th"));
                rowElements = driver.findElement(AeviAdminMap.AcquirersServiceProviders.tblAcquirerServiceProviders).findElements(
                    new ByChained(
                        By.xpath("./tbody/tr[" + row + "]"),
                        By.tagName("td")));
            }
            case "Terminals" -> {
                headers = driver.findElement(AeviAdminMap.Terminals.tblTerminals).findElements(By.tagName("th"));
                rowElements = driver.findElement(AeviAdminMap.Terminals.tblTerminals).findElements(
                    new ByChained(
                        By.xpath("./tbody/tr[" + row + "]"),
                        By.tagName("td")));
            }
        }

        String cellValue = AeviAdminShared.getCellValueByColumnNameAndRowIndex(headers, rowElements, columnName);
        Assert.assertEquals(text, cellValue);
    }

    @Then("I should see the {string} edit page")
    public void iShouldSeeTheEditPage(String pageTitle) {
        String page = "";
        switch (pageTitle) {
            case "AEVI Pay Admin | Data Group" -> page = AeviAdminMap.DataGroupsAdd.pageEdit;
            case "AEVI Pay Admin | Contract" -> page = AeviAdminMap.ContractsAdd.pageEdit;
            case "AEVI Pay Admin | Acquirer/Service Provider" -> page = AeviAdminMap.AcquirersServiceProvidersAdd.pageEdit;
            case "AEVI Pay Admin | Terminal" -> page = AeviAdminMap.Terminals.pageEdit;
        }

        Assert.assertTrue(driver.getCurrentUrl().startsWith(page));
    }

    @When("I click on the row {string} in {string} table")
    public void iClickOnTheRowInTable(String row, String table) {
        WebElement element = null;

        switch (table) {
            case "Data Groups" -> element = driver.findElement(AeviAdminMap.DataGroups.tblDataGroups)
                .findElement(By.xpath("./tbody/tr[" + row + "]"));
            case "Contracts" -> element = driver.findElement(AeviAdminMap.Contracts.tblContracts)
                .findElement(By.xpath("./tbody/tr[" + row + "]"));
            case "Acquirers/Service Providers" -> element = driver.findElement(AeviAdminMap.AcquirersServiceProviders.tblAcquirerServiceProviders)
                .findElement(By.xpath("./tbody/tr[" + row + "]"));
            case "Terminals" -> element = driver.findElement(AeviAdminMap.Terminals.tblTerminals)
                .findElement(By.xpath("./tbody/tr[" + row + "]"));
        }

        element.click();
    }

    @When("I click on checkbox in row {string} in {string} table")
    public void iClickOnCheckboxInRowInTable(String row, String table) {
        WebElement element = null;

        switch (table) {
            case "Data Groups" -> element = driver.findElement(AeviAdminMap.DataGroups.tblDataGroups)
                .findElement(By.xpath("./tbody/tr[" + row + "]/td[2]/input[1]"));
            case "Contracts" -> element = driver.findElement(AeviAdminMap.Contracts.tblContracts)
                .findElement(By.xpath("./tbody/tr[" + row + "]/td[2]/input[1]"));
            case "Terminals" -> element = driver.findElement(AeviAdminMap.Terminals.btnTableFirstCheckBox);
        }

        element.click();
    }

    @Then("I should see the {string} modal")
    public void iShouldSeeTheModal(String modal) {
        By byModal = null;
        switch (modal) {
            case "Delete | Data Group" -> byModal = AeviAdminMap.DataGroups.modal;
            case "Delete | Contract" -> byModal = AeviAdminMap.Contracts.modal;
            case "Copy | Terminal" -> byModal = AeviAdminMap.Terminals.modalCopy;
            case "Delete | Terminal" -> byModal = AeviAdminMap.Terminals.modalDelete;
            case "Change | Host Terminal Id" -> byModal = AeviAdminMap.AcquirersServiceProvidersAdd.modal;
        }

        WebElement modalElement = driver.findElement(byModal);
        modalElement.isEnabled();
        modalElement.isDisplayed();
    }

    @And("I should see the {string} modal button which is enabled")
    public void iShouldSeeTheModalButtonWhichIsEnabled(String modal) {
        By byButton = null;
        switch (modal) {
            case "Delete" -> byButton = AeviAdminMap.DataGroups.btnModalDelete;
        }

        WebElement buttonElement = driver.findElement(byButton);

        buttonElement.isDisplayed();
        buttonElement.isEnabled();
    }

    @When("I click on the {string} modal button")
    public void iClickOnTheModalButton(String button) {
        By byButton = null;
        switch (button) {
            case "Delete" -> {
                byButton = AeviAdminMap.DataGroups.btnModalDelete;
                WebDriverWaitShared.waitVisibilityOfElementLocated(byButton);
            }
        }

        driver.findElement(byButton).click();
    }

    @When("I delete the string from {string} textbox")
    public void iDeleteTheStringFromTextbox(String textbox) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());
        WebElement textboxElement = driver.findElement(byTextbox);

        textboxElement.clear();
    }

    @Then("the {string} textbox should be empty")
    public void theTextboxShouldBeEmpty(String textbox) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());
        WebElement textboxElement = driver.findElement(byTextbox);

        Assert.assertTrue(textboxElement.getText().isEmpty());
    }

    @Then("I should see the {string} textbox {string} validation error")
    public void iShouldSeeTheTextboxValidationError(String textbox, String validationMessage) {
        By byValidationMsg = null;
        switch (textbox) {
            case "Contract Code" -> {
                byValidationMsg = AeviAdminMap.ContractsAdd.msgContractCodeValidationMessage;
                WebDriverWaitShared.waitVisibilityOfElementLocated(byValidationMsg);
            }
        }

        WebElement msgElement = driver.findElement(byValidationMsg);
        Assert.assertEquals(msgElement.getText(), validationMessage);
    }

    @And("I should see the {string} textbox which is enabled and has today's date value selected")
    public void iShouldSeeTheTextboxWhichIsEnabledAndHasTodaySDateValueSelected(String textbox) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());
        WebElement textboxElement = driver.findElement(byTextbox);
        String dateToday = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        Assert.assertEquals(textboxElement.getAttribute("value"), dateToday);
    }

    @Then("the {string} textbox should contain {string} string after reload")
    public void theTextboxShouldContainStringAfterReload(String textbox, String text) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());
        WebElement textboxElement = driver.findElement(byTextbox);

        WebDriverWaitShared.waitAttributeToBeNotEmpty(textboxElement, "value");

        Assert.assertEquals(textboxElement.getAttribute("value"), text);
    }

    @Then("the {string} textbox should have valid GUID string")
    public void theTextboxShouldHaveValidGUIDString(String textbox) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());
        WebElement textboxElement = driver.findElement(byTextbox);

        storedValue = textboxElement.getAttribute("value");

        String regex
            = "^[{]?[0-9a-fA-F]{8}"
            + "-([0-9a-fA-F]{4}-)"
            + "{3}[0-9a-fA-F]{12}[}]?$";

        Pattern ptrn = Pattern.compile(regex);
        Matcher mtc = ptrn.matcher(storedValue);

        Assert.assertTrue(mtc.matches());
    }

    @Then("the {string} textbox should contain unique GUID")
    public void theTextboxShouldContainUniqueGUID(String textbox) {
        By byTextbox = AeviAdminShared.findTextboxByName(textbox, driver.getTitle());
        WebElement textboxElement = driver.findElement(byTextbox);

        Assert.assertNotEquals(storedValue, textboxElement.getAttribute("value"));
    }
}
