package Testbook.Cucumber.Glue.UI;

import ExtendedTypes.HelpersAction;
import ExtendedTypes.UI.*;
import Hooks.HTInfonovaHooks;
import Hooks.HTInfonovaOfferHooks;
import Maps.UI.HTInfonovaMap;
import Settings.GlobalParameters;
import Shared.FindPath;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.shared.Helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HTInfonovaSteps {
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    // region Hybrid
    @When("^I create subaccount with ([^\"]*), ([^\"]*), ([^\"]*), ([^\"]*), ([^\"]*), ([^\"]*), ([^\"]*), ([^\"]*) and \"(.*)\" offer$")
    public void iCreateSubaccountWithStreetNameNumberZipCityPlaceMobileEmailAndOffer(String StreetName, String Number, String Suffix, String Zip, String City, String Place, String Name, String Email, String Offer) {
        iClickOnButton("Add ...");
        iSelectItemInList("New Account", "Add ...");
        iShouldSeePage("Address Check");
        iEnterStringIntoTextbox(StreetName, "Street Name");
        iEnterStringIntoTextbox(Number, "Number");
        iEnterStringIntoTextbox(Suffix, "Suffix");
        iEnterStringIntoTextbox(Zip, "Zip");
        iEnterStringIntoTextbox(City, "City");
        iEnterStringIntoTextbox(Place, "Place");
        iShouldSeeButtonThatIs("Validate Address", "enabled");
        iClickOnButton("Validate Address");
        iShouldSeeListInPopupThatIs("Addresses", "Matching Addresses", "enabled");
        iShouldSeeLinkInPopupThatIs("Cancel", "Matching Addresses", "enabled");
        iSelectCorrespondingAddressOptionInAddressesListInMatchingAddressesPopup();
        iShouldSeeLabelThatContainsCorrespondingString("Service Address");
        iClickOnButton("Next");
        iShouldSeePage("Offer Selection");
        iClickOnAddToCartButtonInOffersTableForOffer(Offer);
        iShouldSeeButtonThatIs("Shopping Cart", "enabled");
        iShouldSeeLabelForOfferThatContainsCorrespondingString("Recurring Gross Charge", Offer);
        iShouldSeeLabelForOfferThatContainsCorrespondingString("Recurring Net Charge", Offer);
        iClickOnButton("Shopping Cart");
        iShouldSeeListThatContainsStringAndIs("Shopping Cart Content", Offer, "enabled");
        iShouldSeeLabelInSectionThatContainsString("Offers Counter", "Shopping Cart", "1");
        iClickOnButton("Next");
        iShouldSeePageForOffer("Offer Configuration", Offer);
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.contains("Fixed")) {
                iOpenUpSection("Fixed Voice Service [FIXED_VOICE_SERVICE]");
                iShouldSeeTextboxThatIsAnd("Short Number", "enabled", "empty");
                break;
            }
            if (tag.contains("Mobile")) {
                iOpenUpSection("Mobile Service [MOBILE_SERVICE]");
                break;
            }
        }
        iShouldSeeButtonThatIs("Get Number", "enabled");
        iClickOnButton("Get Number");
        iShouldSeeTableInPopupThatIsAnd("Numbers", "Select Number", "enabled", "not empty");
        iShouldSeeLinkInPopupThatIs("Cancel", "Select Number", "enabled");
        iSelectFirstOptionInPhoneNumberTableInSelectNumbePopup();
        iShouldSeeLabelThatContainsCorrespondingString("Phone Number");
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.contains("Fixed")) {
                iEnterStringIntoTextbox("200", "Short Number");
                break;
            }
        }
        iShouldSeeButtonThatIs("Next", "enabled");
        iClickOnButton("Next");
        iShouldSeePage("Customer Details");
        iEnterStringIntoTextbox(Name, "Displayed Name");
        iShouldSeeLabelThatContainsCorrespondingString("Customer Header");
        iShouldSeeButtonThatIs("Next", "enabled");
        iClickOnButton("Next");
        iShouldSeePage("Payment Details");
        iClickOnButton("Validate Address");
        iSelectItemInList("Service Address", "Validate Address");
        iShouldSeeLabelThatContainsCorrespondingString("Billing Address");
        iSelectInDropdownMenu("No", "EDI bill media eligible");
        iShouldSeeTextboxThatIsAnd("Region", "enabled", "not empty");
        iEnterStringIntoTextbox(Email, "E-Mail");
        iEnterStringIntoTextbox("222", "Frame Contract ID");
        iShouldSeeButtonThatIs("Next", "enabled");
        iClickOnButton("Next");
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.contains("Fixed")) {
                iShouldSeePage("Shipping Details");
                iClickOnButton("Validate Address");
                iSelectItemInList("Service Address", "Validate Address");
                iShouldSeeLabelThatContainsCorrespondingString("Shipping Address");
                iShouldSeeButtonThatIs("Next", "enabled");
                iClickOnButton("Next");
                break;
            }
        }
        iShouldSeePageForOffer("Summary", Offer);
        iSelectCheckbox("Terms And Conditions");
        iShouldSeeButtonThatIs("Create Customer", "enabled");
        iClickOnButton("Create Customer");
        iShouldSeePage("Subaccounts");
    }

    @When("I create subaccount with {string}, {string} and {string} offer")
    public void iCreateSubaccountWithAndOffer(String Name, String Email, String Offer) {
        iClickOnButton("Add ...");
        iSelectItemInList("New Account", "Add ...");
        iShouldSeePage("Address Check");
        iClickOnButton("Next");
        iShouldSeeButtonThatIs("Ok", "enabled");
        iClickOnButton("Ok");
        iShouldSeePage("Offer Selection");
        iClickOnAddToCartButtonInOffersTableForOffer(Offer);
        iShouldSeeButtonThatIs("Shopping Cart", "enabled");
        iShouldSeeLabelForOfferThatContainsCorrespondingString("Recurring Gross Charge", Offer);
        iShouldSeeLabelForOfferThatContainsCorrespondingString("Recurring Net Charge", Offer);
        iClickOnButton("Shopping Cart");
        iShouldSeeListThatContainsStringAndIs("Shopping Cart Content", Offer, "enabled");
        iShouldSeeLabelInSectionThatContainsString("Offers Counter", "Shopping Cart", "1");
        iClickOnButton("Next");
        iShouldSeePageForOffer("Offer Configuration", Offer);
        iOpenUpSection("Mobile Service [MOBILE_SERVICE]");
        iShouldSeeButtonThatIs("Get Number", "enabled");
        iClickOnButton("Get Number");
        iShouldSeeTableInPopupThatIsAnd("Numbers", "Select Number", "enabled", "not empty");
        iShouldSeeLinkInPopupThatIs("Cancel", "Select Number", "enabled");
        iSelectFirstOptionInPhoneNumberTableInSelectNumbePopup();
        iShouldSeeLabelThatContainsCorrespondingString("Phone Number");
        iShouldSeeButtonThatIs("Next", "enabled");
        iClickOnButton("Next");
        iShouldSeePage("Customer Details");
        iEnterStringIntoTextbox(Name, "Displayed Name");
        iShouldSeeLabelThatContainsCorrespondingString("Customer Header");
        iShouldSeeButtonThatIs("Next", "enabled");
        iClickOnButton("Next");
        iShouldSeePage("Payment Details");
        iClickOnButton("Validate Address");
        iSelectItemInList("Contact", "Validate Address");
        iShouldSeeLabelThatContainsCorrespondingString("Billing Address");
        iSelectInDropdownMenu("No", "EDI bill media eligible");
        iShouldSeeTextboxThatIsAnd("Region", "enabled", "not empty");
        iEnterStringIntoTextbox(Email, "E-Mail");
        iEnterStringIntoTextbox("222", "Frame Contract ID");
        iShouldSeeButtonThatIs("Next", "enabled");
        iClickOnButton("Next");
        iShouldSeePageForOffer("Summary", Offer);
        iSelectCheckbox("Terms And Conditions");
        iShouldSeeButtonThatIs("Create Customer", "enabled");
        iClickOnButton("Create Customer");
        iShouldSeePage("Subaccounts");
    }

    @When("I add offer on same account with {string} offer")
    public void iAddOfferOnSameAccountWithOffer(String Offer) {
        Helpers.waitForAction(5);
        iClickOnButton("Actions");
        iSelectItemInList("Add Offer", "Actions");
        iShouldSeePage("Add Offer Address Check");
        iClickOnButton("Validate Address");
        iSelectItemInList("Billing", "Validate Address");
        iShouldSeeLabelThatContainsCorrespondingString("Service Address");
        iClickOnButton("Next");
        iShouldSeePage("Offer Selection");
        iClickOnAddToCartButtonInOffersTableForOffer(Offer);
        iShouldSeeButtonThatIs("Shopping Cart", "enabled");
        iShouldSeeLabelForOfferThatContainsCorrespondingString("Recurring Gross Charge", Offer);
        iShouldSeeLabelForOfferThatContainsCorrespondingString("Recurring Net Charge", Offer);
        iClickOnButton("Shopping Cart");
        iShouldSeeListThatContainsStringAndIs("Shopping Cart Content", Offer, "enabled");
        iShouldSeeLabelInSectionThatContainsString("Offers Counter", "Shopping Cart", "1");
        iClickOnButton("Next");
        iShouldSeePageForOffer("Offer Configuration", Offer);
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.contains("Fixed")) {
                iOpenUpSection("Fixed Voice Service [FIXED_VOICE_SERVICE]");
                iShouldSeeTextboxThatIsAnd("Short Number", "enabled", "empty");
                break;
            }
            if (tag.contains("Mobile")) {
                iOpenUpSection("Mobile Service [MOBILE_SERVICE]");
                break;
            }
        }
        iShouldSeeButtonThatIs("Get Number", "enabled");
        iClickOnButton("Get Number");
        iShouldSeeTableInPopupThatIsAnd("Numbers", "Select Number", "enabled", "not empty");
        iShouldSeeLinkInPopupThatIs("Cancel", "Select Number", "enabled");
        iSelectFirstOptionInPhoneNumberTableInSelectNumbePopup();
        iShouldSeeLabelThatContainsCorrespondingString("Phone Number");
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.contains("Fixed")) {
                iEnterStringIntoTextbox("200", "Short Number");
                break;
            }
        }
        iShouldSeeButtonThatIs("Next", "enabled");
        iClickOnButton("Next");
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.contains("Fixed")) {
                iShouldSeePage("Shipping Details");
                iClickOnButton("Validate Address");
                iSelectItemInList("Service Address", "Validate Address");
                iShouldSeeLabelThatContainsCorrespondingString("Shipping Address");
                iShouldSeeButtonThatIs("Next", "enabled");
                iClickOnButton("Next");
                break;
            }
        }
        iShouldSeePageForOffer("Summary", Offer);
        iSelectCheckbox("Terms And Conditions");
        iShouldSeeButtonThatIs("Add Offer", "Enabled");
        iClickOnButton("Add Offer");
    }

    @When("I verify Provisioning Request Details for {string} offer")
    public void iVerifyProvisioningRequestDetailsForOffer(String Offer) {
        iShouldSeePage("Offers");
        iShouldSeeListOnLeftPartOfThePageThatIsAnd("Offers", "enabled", "not empty");
        iSelectUsersOptionInList("Orders", "Offers");
        iShouldSeePage("Orders and Service Requests");
        iSelectTypeOnTable(Offer, "Orders");
        iShouldSeeTableThatIsAnd("Process Details", "enabled", "not empty");
        iSelectCorrespondingInTable("Provisioning Order ID", "Process Details");
        iShouldSeeLabelThatContainsCorrespondingString("Provisioning Order");
        iShouldSeeListThatIsAnd("Provisioning", "enabled", "not empty");
        iSelectUsersOptionInList("History", "Provisioning");
        iShouldSeeTableThatIsAnd("Order History", "enabled", "not empty");
        iShouldSeeButtonForActionInTable("Show Request Details", "Create", "Order History");
        iClickOnButtonForActionInTable("Show Request Details", "Create", "Order History");
        iShouldSeeLabelThatContainsString("Provisioning Request Details", "httpStatusCode: 200");
        iShouldSeeLabelThatContainsString("Provisioning Request Details", "responseState: SUCCESS");
        iShouldSeeButtonThatIs("Close", "enabled");
        iClickOnButton("Close");
        iSelectUsersOptionInList("Offers", "Offers");
    }
    // endregion

    // region Textbox
    @Then("I should see {string} textbox that is {string} and {string}")
    public void iShouldSeeTextboxThatIsAnd(String sTextboxName, String sAvailability, String sState) {
        WbEditEx txtTextbox = FindPath.FindTextbox(sTextboxName);
        boolean bAvailability = validateAvailability(sAvailability);
        boolean bState = validateState(sState);

        GlobalParameters.testCaseBase.success(txtTextbox.displayed(10, true));
        GlobalParameters.testCaseBase.success(txtTextbox.enabled(bAvailability));
        GlobalParameters.testCaseBase.success(txtTextbox.verifyEmpty(bState));
    }

    @When("I enter corresponding string into {string} textbox")
    public void iEnterCorrespondingStringIntoTextbox(String sTextboxName) {
        WbEditEx txtTextbox = FindPath.FindTextbox(sTextboxName);

        switch (sTextboxName.toLowerCase()) {
            case "username":
                GlobalParameters.testCaseBase.success(txtTextbox.setText(GlobalParameters.Username));
                GlobalParameters.testCaseBase.success(txtTextbox.verifyText(GlobalParameters.Username));
                break;

            case "password":
                GlobalParameters.testCaseBase.success(txtTextbox.setText(GlobalParameters.Password));
                GlobalParameters.testCaseBase.success(txtTextbox.verifyText(GlobalParameters.Password));
                break;

            case "search customers":
                for (String tag : scenario.getSourceTagNames()) {
                    if (tag.contains("Ordering")) {
                        GlobalParameters.testCaseBase.success(txtTextbox.setText(HTInfonovaHooks.BillingAccountID));
                        GlobalParameters.testCaseBase.success(txtTextbox.verifyText(HTInfonovaHooks.BillingAccountID));
                        break;
                    }
                    if (tag.equals("@CreateUser")) {
                        GlobalParameters.testCaseBase.success(txtTextbox.setText(HTInfonovaHooks.BillingAccountIDTC2));
                        GlobalParameters.testCaseBase.success(txtTextbox.verifyText(HTInfonovaHooks.BillingAccountIDTC2));
                        break;
                    }
                }

                GlobalParameters.testCaseBase.success(HTInfonovaMap.CustomerPage.Search.displayed(10, true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.CustomerPage.Search.enabled(true));
                break;
        }
    }

    @When("I enter {string} string into {string} textbox")
    public void iEnterStringIntoTextbox(String sText, String sTextboxName) {
        WbEditEx txtTextbox = FindPath.FindTextbox(sTextboxName);
        if (sTextboxName.equalsIgnoreCase("displayed name")) {
            String ID = HelpersAction.randomDigits(5);
            GlobalParameters.testCaseBase.success(txtTextbox.setText(sText + ID));
            GlobalParameters.testCaseBase.success(txtTextbox.verifyText(sText + ID));
            HTInfonovaHooks.DisplayedName = sText + ID;

        } else if (!sText.equalsIgnoreCase("N/A")) {
            GlobalParameters.testCaseBase.success(txtTextbox.setText(sText));
            GlobalParameters.testCaseBase.success(txtTextbox.verifyText(sText));
        }

        switch (sTextboxName.toLowerCase()) {
            case "street name":
                HTInfonovaHooks.StreetName = sText.toUpperCase();
                break;
            case "number":
                HTInfonovaHooks.Number = sText;
                break;
            case "suffix":
                HTInfonovaHooks.Suffix = sText;
                break;
            case "zip":
                HTInfonovaHooks.Zip = sText;
                break;
            case "city":
                HTInfonovaHooks.City = sText.toUpperCase();
                break;
            case "place":
                HTInfonovaHooks.Place = sText.toUpperCase();
                break;
            case "short number":
                GlobalParameters.testCaseBase.success(txtTextbox.sendEnter());
                Helpers.waitForAction(3);
                break;
            case "e-mail":
                HTInfonovaHooks.Email = sText;
                break;
        }
    }

    @When("I enter {string} string into {string} textbox in {string} section")
    public void iEnterStringIntoTextboxInSection(String sText, String sTextboxName, String sSection) {
        WbEditEx textbox = FindPath.FindTextbox(sTextboxName);

        if (sTextboxName.equalsIgnoreCase("legal name (short)")) {
            String ID = HelpersAction.randomDigits(5);
            GlobalParameters.testCaseBase.success(textbox.setText(sText + ID));
            GlobalParameters.testCaseBase.success(textbox.verifyText(sText + ID));
            HTInfonovaHooks.LegalName = sText + ID;

        } else if (sText.equalsIgnoreCase("valid oib")) {
            String sOib = HelpersAction.generateOib();
            GlobalParameters.testCaseBase.success(textbox.setText(sOib));
            GlobalParameters.testCaseBase.success(textbox.verifyText(sOib));
            Helpers.waitForAction(5);

        } else if (sText.equalsIgnoreCase("current date")) {
            String Date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
            GlobalParameters.testCaseBase.success(textbox.setText(Date));
            GlobalParameters.testCaseBase.success(textbox.verifyText(Date));

        } else if (sText.equalsIgnoreCase("next year")) {
            String nextYear = HelpersAction.nextYear();
            GlobalParameters.testCaseBase.success(textbox.setText(nextYear));
            GlobalParameters.testCaseBase.success(textbox.verifyText(nextYear));

        } else if (!sText.equalsIgnoreCase("N/A")) {
            GlobalParameters.testCaseBase.success(textbox.setText(sText));
            GlobalParameters.testCaseBase.success(textbox.verifyText(sText));
        }

        switch (sTextboxName.toLowerCase()) {
            case "last name":
                HTInfonovaHooks.LastName = sText;
                break;
            case "first name":
                HTInfonovaHooks.FirstName = sText;
                break;
            case "street name":
                HTInfonovaHooks.StreetName = sText.toUpperCase();
                break;
            case "number":
                HTInfonovaHooks.Number = sText;
                break;
            case "suffix":
                HTInfonovaHooks.Suffix = sText;
                break;
            case "zip":
                HTInfonovaHooks.Zip = sText;
                break;
            case "city":
                HTInfonovaHooks.City = sText.toUpperCase();
                break;
            case "place":
                HTInfonovaHooks.Place = sText.toUpperCase();
                break;
            case "mobile":
                HTInfonovaHooks.Mobile = sText;
                break;
            case "email":
                HTInfonovaHooks.Email = sText;
                break;
        }
    }
    // endregion

    // region Button
    @Then("I should see {string} button that is {string}")
    public void iShouldSeeButtonThatIs(String sButtonName, String sAvailability) {
        WbButtonEx button = FindPath.FindButton(sButtonName);
        boolean bAvailability = validateAvailability(sAvailability);
        GlobalParameters.testCaseBase.success(button.displayed(10, true));
        GlobalParameters.testCaseBase.success(button.enabled(bAvailability));
    }

    @Then("I should see {string} button in {string} section that is {string}")
    public void iShouldSeeButtonInSectionThatIs(String sButtonName, String sSectionName, String sAvailability) {
        WbButtonEx button = FindPath.FindButton(sButtonName);
        boolean bAvailability = validateAvailability(sAvailability);

        GlobalParameters.testCaseBase.success(button.displayed(10, true));
        GlobalParameters.testCaseBase.success(button.enabled(bAvailability));

        if (sButtonName.equalsIgnoreCase("edit address")) {
            GlobalParameters.testCaseBase.success(button.verifyAttributeValue("title", "Edit Address"));
        }
    }

    @Then("I should see {string} button in {string} popup that is {string}")
    public void iShouldSeeButtonInPopupThatIs(String sButtonName, String sPopupName, String sAvailability) {
        WbButtonEx button = FindPath.FindButton(sButtonName);
        boolean bAvailability = validateAvailability(sAvailability);
        GlobalParameters.testCaseBase.success(button.displayed(10, true));
        GlobalParameters.testCaseBase.success(button.enabled(bAvailability));
    }

    @When("I click on {string} button")
    public void iClickOnButton(String sButtonName) {
        WbButtonEx button = FindPath.FindButton(sButtonName);

        switch (sButtonName.toLowerCase()) {
            case "get number":
                Helpers.waitForAction(5);
                break;

            case "validate address":
                for (String tag : scenario.getSourceTagNames()) {
                    if (tag.contains("Fixed")) {
                        if (HTInfonovaHooks.Suffix.equals("N/A"))
                            HTInfonovaHooks.Location = HTInfonovaHooks.StreetName + " " + HTInfonovaHooks.Number + ", " + HTInfonovaHooks.City + " " + HTInfonovaHooks.Zip + " " + HTInfonovaHooks.Country;
                        else
                            HTInfonovaHooks.Location = HTInfonovaHooks.StreetName + " " + HTInfonovaHooks.Number + ", Suffix: " + HTInfonovaHooks.Suffix + ", " + HTInfonovaHooks.City + " " + HTInfonovaHooks.Zip + " " + HTInfonovaHooks.Country;
                        break;
                    } else {
                        HTInfonovaHooks.Location = HTInfonovaHooks.CustomerLocation;
                    }
                }
        }

        GlobalParameters.testCaseBase.success(button.click());

        switch (sButtonName.toLowerCase()) {
            case "validate address":
                HTInfonovaHooks.Logout = true;
                break;

            case "save changes":
                HTInfonovaHooks.User = true;
                break;

            case "create customer":
                HTInfonovaHooks.SubAccount = true;
                HTInfonovaHooks.Logout = false;
                HTInfonovaHooks.DisplayedNames.add(HTInfonovaHooks.DisplayedName);
                Helpers.waitForAction(2);
                break;

            case "add offer":
                HTInfonovaHooks.Offer = true;
                Helpers.waitForAction(2);
                break;
        }
    }

    @When("I click on {string} button in {string} section")
    public void iClickOnButtonInSection(String sButtonName, String sSectionName) {
        WbButtonEx button = FindPath.FindButton(sButtonName);
        GlobalParameters.testCaseBase.success(button.click());
    }

    @When("I click on {string} button in {string} popup")
    public void iClickOnButtonInPopup(String sButtonName, String sPopupName) {
        WbButtonEx button = FindPath.FindButton(sButtonName);
        GlobalParameters.testCaseBase.success(button.click());
    }

    @When("I click on {string} button on {string} page")
    public void iClickOnButtonOnPage(String sButtonName, String sPageName) {
        WbButtonEx button = FindPath.FindButton(sButtonName);
        GlobalParameters.testCaseBase.success(button.click());
    }

    @Then("I should see {string} button for {string} action in {string} table")
    public void iShouldSeeButtonForActionInTable(String sButtonName, String sActionName, String sTableName) {
        WbTableEx table = FindPath.FindTable(sTableName);
        GlobalParameters.testCaseBase.success(table.findButtonByActionValue(sActionName).displayed(true));
        GlobalParameters.testCaseBase.success(table.findButtonByActionValue(sActionName).enabled(true));
    }

    @When("I click on {string} button for {string} action in {string} table")
    public void iClickOnButtonForActionInTable(String sButtonName, String sActionName, String sTableName) {
        WbTableEx table = FindPath.FindTable(sTableName);
        GlobalParameters.testCaseBase.success(table.findButtonByActionValue(sActionName).click());
    }
    // endregion

    // region Dropdown
    @When("I select {string} in {string} dropdown menu")
    public void iSelectInDropdownMenu(String sItem, String sDropdownMenu) {
        WbDropDownEx ddDropdown = FindPath.FindDropdown(sDropdownMenu);
        GlobalParameters.testCaseBase.success(ddDropdown.verifyItemExists(sItem, true));
        GlobalParameters.testCaseBase.success(ddDropdown.setItem(sItem, 0));
        GlobalParameters.testCaseBase.success(ddDropdown.verifySelected(sItem, true));
    }

    @When("I select {string} in {string} dropdown menu in {string} section")
    public void iSelectInDropdownMenuInSection(String sItem, String sDropdownMenu, String sSection) {
        WbDropDownEx ddDropdown = FindPath.FindDropdown(sDropdownMenu);
        GlobalParameters.testCaseBase.success(ddDropdown.verifyItemExists(sItem, true));
        GlobalParameters.testCaseBase.success(ddDropdown.setItem(sItem, 0));
        GlobalParameters.testCaseBase.success(ddDropdown.verifySelected(sItem, true));
    }
    // endregion

    // region Table
    @Then("I should see {string} table that is {string} and {string}")
    public void iShouldSeeTableThatIsAnd(String sTableName, String sAvailability, String sState) {
        WbTableEx table = FindPath.FindTable(sTableName);
        boolean bAvailability = validateAvailability(sAvailability);
        boolean bState = validateState(sState);

        GlobalParameters.testCaseBase.success(table.displayed(10, true));
        GlobalParameters.testCaseBase.success(table.enabled(bAvailability));
        GlobalParameters.testCaseBase.success(table.verifyEmpty(bState));
    }

    @Then("I should see {string} option in {string} table")
    public void iShouldSeeOptionInTable(String sOption, String sTableName) {
        WbTableEx table = FindPath.FindTable(sTableName);
        Helpers.waitForAction(5);
        GlobalParameters.testCaseBase.success(table.verifyTermExists(sOption));
    }

    @Then("I should see {string} table in {string} popup that is {string} and {string}")
    public void iShouldSeeTableInPopupThatIsAnd(String sTableName, String sPopupName, String sAvailability, String sState) {
        WbTableEx table = FindPath.FindTable(sTableName);
        boolean bAvailability = validateAvailability(sAvailability);
        boolean bState = validateState(sState);

        Helpers.waitForAction(15);
        GlobalParameters.testCaseBase.success(table.displayed(true));
        GlobalParameters.testCaseBase.success(table.enabled(bAvailability));
        GlobalParameters.testCaseBase.success(table.verifyEmpty(bState));
    }

    @When("I select corresponding {string} in {string} table")
    public void iSelectCorrespondingInTable(String sData, String sTableName) {
        WbTableEx table = FindPath.FindTable(sTableName);

        switch (sData.toLowerCase()) {
            case "customer":
                for (String tag : scenario.getSourceTagNames()) {
                    if (tag.contains("Ordering")) {
                        GlobalParameters.testCaseBase.success(table.verifyTermExists(HTInfonovaHooks.BillingAccountID));
                        GlobalParameters.testCaseBase.success(table.findRowInTableByCellValue(HTInfonovaHooks.BillingAccountID).click());
                        break;
                    }
                    if (tag.equals("@CreateUser")) {
                        GlobalParameters.testCaseBase.success(table.verifyTermExists(HTInfonovaHooks.BillingAccountIDTC2));
                        GlobalParameters.testCaseBase.success(table.findRowInTableByCellValue(HTInfonovaHooks.BillingAccountIDTC2).click());
                        break;
                    }
                }
                break;

            case "subaccount":
                GlobalParameters.testCaseBase.success(table.findRowInTableByCellValue(HTInfonovaHooks.DisplayedName).click());
                break;

            case "provisioning order id":
                Helpers.waitForAction(20);
                HTInfonovaHooks.ProvisID = table.findCellByIndexInProcessDetails(4, 2).getControlText();
                GlobalParameters.testCaseBase.success(table.findCellByIndexInProcessDetails(4, 2).click());
                break;
        }
    }

    @When("I select {string} type in {string} table")
    public void iSelectTypeOnTable(String sType, String sTableName) {
        WbTableEx table = FindPath.FindTable(sTableName);
        GlobalParameters.testCaseBase.success(table.findCellByContentValue("Type", sType).click());
    }

    @When("I remove {string} option in {string} table")
    public void iRemoveOptionInTable(String sItem, String sTableName) {
        WbTableEx table = FindPath.FindTable(sTableName);
        GlobalParameters.testCaseBase.success(table.findButtonByCellValue(sItem).click());
    }
    // endregion

    // region Label
    @Then("I should see {string} label that contains {string} string")
    public void iShouldSeeLabelThatContainsString(String sLabelName, String sText) {
        WbLabelEx lblLabel = FindPath.FindLabel(sLabelName);
        GlobalParameters.testCaseBase.success(lblLabel.displayed(10, true));
        GlobalParameters.testCaseBase.success(lblLabel.verifyTextContains(sText, true));
    }

    @Then("I should see {string} label in {string} section that contains {string} string")
    public void iShouldSeeLabelInSectionThatContainsString(String sLabelName, String sSection, String sText) {
        WbLabelEx lblLabel = FindPath.FindLabel(sLabelName);
        GlobalParameters.testCaseBase.success(lblLabel.displayed(10, true));
        GlobalParameters.testCaseBase.success(lblLabel.verifyTextContains(sText, true));
    }

    @Then("I should see {string} label that contains corresponding string")
    public void iShouldSeeLabelThatContainsCorrespondingString(String sLabelName) {
        String sText = null;
        WbLabelEx lblLabel = FindPath.FindLabel(sLabelName);
        GlobalParameters.testCaseBase.success(lblLabel.displayed(10, true));
        switch (sLabelName.toLowerCase()) {
            case "service address", "billing address", "shipping address":
                for (String tag : scenario.getSourceTagNames()) {
                    if (tag.contains("Fixed")) {
                        sText = HTInfonovaHooks.Address;
                        if (HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("address check") ||
                                HTInfonovaHooks.LastArea.get(HTInfonovaHooks.LastArea.size() - 1).equalsIgnoreCase("add offer address check"))
                            Helpers.waitForAction(17);
                        break;
                    }
                    sText = HTInfonovaHooks.CustomerLocationFormatted;
                    Helpers.waitForAction(5);
                }
                break;
            case "phone number":
                sText = HTInfonovaHooks.MobileNumber;
                Helpers.waitForAction(5);
                break;
            case "customer header":
                sText = HTInfonovaHooks.DisplayedName;
                break;
            case "provisioning order":
                sText = "Provisioning Order " + HTInfonovaHooks.ProvisID;
                break;
        }
        GlobalParameters.testCaseBase.success(lblLabel.verifyText(sText, true));
    }

    @Then("I should see {string} label in {string} section that contains corresponding string")
    public void iShouldSeeLabelInSectionThatContainsCorrespondingString(String sLabelName, String sSection) {
        String sText = null;
        WbLabelEx lblLabel = FindPath.FindLabel(sLabelName);
        GlobalParameters.testCaseBase.success(lblLabel.displayed(10, true));
        switch (sLabelName.toLowerCase()) {
            case "legal address", "contact address":
                sText = HTInfonovaHooks.Address;
                break;
        }
        GlobalParameters.testCaseBase.success(lblLabel.verifyText(sText, true));
    }

    @Then("I should see {string} label that contains {string} string in {string} popup")
    public void iShouldSeeLabelThatContainsStringInPopup(String sLabelName, String sText, String sPopup) {
        WbLabelEx lblLabel = FindPath.FindLabel(sLabelName);
        GlobalParameters.testCaseBase.success(lblLabel.displayed(10, true));
        GlobalParameters.testCaseBase.success(lblLabel.verifyText(sText, true));
        HTInfonovaHooks.LastArea.add(sPopup);
    }

    @Then("I should see {string} label for {string} offer that contains corresponding string")
    public void iShouldSeeLabelForOfferThatContainsCorrespondingString(String sLabelName, String sItem) {
        WbLabelEx lblLabel = FindPath.FindLabel(sLabelName);
        String sTotalGrossCharge;
        String sTotalNetCharge;

        GlobalParameters.testCaseBase.success(lblLabel.displayed(10, true));

        switch (sItem.toLowerCase()) {
            case "digital xs":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalXS.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalXS.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalXS.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalXS.NetCharge);
                }
                break;
            case "digital s":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalS.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalS.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalS.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalS.NetCharge);
                }
                break;
            case "digital m":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalM.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalM.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalM.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalM.NetCharge);
                }
                break;
            case "digital l":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalL.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalL.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalL.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalL.NetCharge);
                }
                break;
            case "digital office xs":
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.OnceOnlyChargeTotal.verifyTextContains(HTInfonovaOfferHooks.DigitalOffice.GrossCharge + " once-only", true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.OnceOnlyChargeAlt.verifyTextContains(HTInfonovaOfferHooks.DigitalOffice.NetCharge + " excl. 25% tax", true));
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalOfficeXS.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeXS.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalOfficeXS.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeXS.NetCharge);
                }
                break;
            case "digital office s":
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.OnceOnlyChargeTotal.verifyTextContains(HTInfonovaOfferHooks.DigitalOffice.GrossCharge + " once-only", true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.OnceOnlyChargeAlt.verifyTextContains(HTInfonovaOfferHooks.DigitalOffice.NetCharge + " excl. 25% tax", true));
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalOfficeS.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeS.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalOfficeS.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeS.NetCharge);
                }
                break;
            case "digital office m":
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.OnceOnlyChargeTotal.verifyTextContains(HTInfonovaOfferHooks.DigitalOffice.GrossCharge + " once-only", true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.OnceOnlyChargeAlt.verifyTextContains(HTInfonovaOfferHooks.DigitalOffice.NetCharge + " excl. 25% tax", true));
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalOfficeM.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeM.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalOfficeM.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeM.NetCharge);
                }
                break;
            case "digital office l":
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.OnceOnlyChargeTotal.verifyTextContains(HTInfonovaOfferHooks.DigitalOffice.GrossCharge + " once-only", true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCartContent.OnceOnlyChargeAlt.verifyTextContains(HTInfonovaOfferHooks.DigitalOffice.NetCharge + " excl. 25% tax", true));
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalOfficeL.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeL.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalOfficeL.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeL.NetCharge);
                }
                break;
            case "go digital s":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.GoDigitalS.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.GoDigitalS.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.GoDigitalS.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.GoDigitalS.NetCharge);
                }
                break;
            case "go digital m":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.GoDigitalM.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.GoDigitalM.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.GoDigitalM.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.GoDigitalM.NetCharge);
                }
                break;
            case "go digital l":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.GoDigitalL.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.GoDigitalL.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.GoDigitalL.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.GoDigitalL.NetCharge);
                }
                break;
            case "go digital 1 tb":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.GoDigital1TB.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.GoDigital1TB.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.GoDigital1TB.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.GoDigital1TB.NetCharge);
                }
                break;
            default:
                break;
        }
    }

    @Then("I should see {string} label for {string} option that contains corresponding string")
    public void iShouldSeeLabelForOptionThatContainsCorrespondingString(String sLabelName, String sItem) {
        WbLabelEx lblLabel = FindPath.FindLabel(sLabelName);
        String sTotalGrossCharge;
        String sTotalNetCharge;

        GlobalParameters.testCaseBase.success(lblLabel.displayed(10, true));
        Helpers.waitForAction(3);

        switch (sItem.toLowerCase()) {
            case "dxs additional national data 10 gb monthly charge":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalXS.GrossCharge + HTInfonovaOfferHooks.DigitalXS.DXSAdditionalNationalData10GBMonthlyCharge.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalXS.GrossCharge + HTInfonovaOfferHooks.DigitalXS.DXSAdditionalNationalData10GBMonthlyCharge.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalXS.NetCharge + HTInfonovaOfferHooks.DigitalXS.DXSAdditionalNationalData10GBMonthlyCharge.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalXS.NetCharge + HTInfonovaOfferHooks.DigitalXS.DXSAdditionalNationalData10GBMonthlyCharge.NetCharge);
                }
                break;
            case "dxs pp3 sales discount 24 months":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalXS.GrossCharge + HTInfonovaOfferHooks.DigitalXS.DXSPP3SalesDiscount24Months.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalXS.GrossCharge + HTInfonovaOfferHooks.DigitalXS.DXSPP3SalesDiscount24Months.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalXS.NetCharge + HTInfonovaOfferHooks.DigitalXS.DXSPP3SalesDiscount24Months.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalXS.NetCharge + HTInfonovaOfferHooks.DigitalXS.DXSPP3SalesDiscount24Months.NetCharge);
                }
                break;
            case "digital roaming world data 500 mb monthly charge":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalS.GrossCharge + HTInfonovaOfferHooks.DigitalS.DigitalRoamingWorldData500MBMonthlyCharge.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalS.GrossCharge + HTInfonovaOfferHooks.DigitalS.DigitalRoamingWorldData500MBMonthlyCharge.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalS.NetCharge + HTInfonovaOfferHooks.DigitalS.DigitalRoamingWorldData500MBMonthlyCharge.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalS.NetCharge + HTInfonovaOfferHooks.DigitalS.DigitalRoamingWorldData500MBMonthlyCharge.NetCharge);
                }
                break;
            case "digital allowance roaming world 30 voice":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalM.GrossCharge + HTInfonovaOfferHooks.DigitalM.DigitalAllowanceRoamingWorld30.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalM.GrossCharge + HTInfonovaOfferHooks.DigitalM.DigitalAllowanceRoamingWorld30.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalM.NetCharge + HTInfonovaOfferHooks.DigitalM.DigitalAllowanceRoamingWorld30.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalM.NetCharge + HTInfonovaOfferHooks.DigitalM.DigitalAllowanceRoamingWorld30.NetCharge);
                }
                break;
            case "digital roaming world data 2 gb monthly charge":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalM.GrossCharge + HTInfonovaOfferHooks.DigitalM.DigitalRoamingWorldData2GBMonthlyCharge.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalM.GrossCharge + HTInfonovaOfferHooks.DigitalM.DigitalRoamingWorldData2GBMonthlyCharge.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalM.NetCharge + HTInfonovaOfferHooks.DigitalM.DigitalRoamingWorldData2GBMonthlyCharge.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalM.NetCharge + HTInfonovaOfferHooks.DigitalM.DigitalRoamingWorldData2GBMonthlyCharge.NetCharge);
                }
                break;
            case "digital allowance international world 120 voice":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalL.GrossCharge + HTInfonovaOfferHooks.DigitalL.DigitalAllowanceInternationalWorld120Voice.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalL.GrossCharge + HTInfonovaOfferHooks.DigitalL.DigitalAllowanceInternationalWorld120Voice.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalL.NetCharge + HTInfonovaOfferHooks.DigitalL.DigitalAllowanceInternationalWorld120Voice.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalL.NetCharge + HTInfonovaOfferHooks.DigitalL.DigitalAllowanceInternationalWorld120Voice.NetCharge);
                }
                break;
            case "ds simo discount duration 6 months":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalS.GrossCharge + HTInfonovaOfferHooks.DigitalS.DSSIMODis6Mth.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalS.GrossCharge + HTInfonovaOfferHooks.DigitalS.DSSIMODis6Mth.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalS.NetCharge + HTInfonovaOfferHooks.DigitalS.DSSIMODis6Mth.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalS.NetCharge + HTInfonovaOfferHooks.DigitalS.DSSIMODis6Mth.NetCharge);
                }
                break;
            case "dl pp1 sales discount 12 months":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalL.GrossCharge + HTInfonovaOfferHooks.DigitalL.DLPP1SalesDiscount12Months.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalL.GrossCharge + HTInfonovaOfferHooks.DigitalL.DLPP1SalesDiscount12Months.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalL.NetCharge + HTInfonovaOfferHooks.DigitalL.DLPP1SalesDiscount12Months.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalL.NetCharge + HTInfonovaOfferHooks.DigitalL.DLPP1SalesDiscount12Months.NetCharge);
                }
                break;
            case "dl pp3 sales discount 12 months":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalL.GrossCharge + HTInfonovaOfferHooks.DigitalL.DLPP3SalesDiscount12Months.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalL.GrossCharge + HTInfonovaOfferHooks.DigitalL.DLPP3SalesDiscount12Months.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalL.NetCharge + HTInfonovaOfferHooks.DigitalL.DLPP3SalesDiscount12Months.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalL.NetCharge + HTInfonovaOfferHooks.DigitalL.DLPP3SalesDiscount12Months.NetCharge);
                }
                break;
            case "do allowance hr 10000 minutes":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalOfficeXS.GrossCharge + HTInfonovaOfferHooks.DigitalOfficeXS.DOAllowanceHR10000Minutes.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeXS.GrossCharge + HTInfonovaOfferHooks.DigitalOfficeXS.DOAllowanceHR10000Minutes.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalOfficeXS.NetCharge + HTInfonovaOfferHooks.DigitalOfficeXS.DOAllowanceHR10000Minutes.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeXS.NetCharge + HTInfonovaOfferHooks.DigitalOfficeXS.DOAllowanceHR10000Minutes.NetCharge);
                }
                break;
            case "dol sales discount 2 24 months":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalOfficeL.GrossCharge + HTInfonovaOfferHooks.DigitalOfficeL.DOLSalesDiscount224Months.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeL.GrossCharge + HTInfonovaOfferHooks.DigitalOfficeL.DOLSalesDiscount224Months.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalOfficeL.NetCharge + HTInfonovaOfferHooks.DigitalOfficeL.DOLSalesDiscount224Months.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeL.NetCharge + HTInfonovaOfferHooks.DigitalOfficeL.DOLSalesDiscount224Months.NetCharge);
                }
                break;
            case "do allowance worldwide 120 minutes":
                if (sLabelName.equalsIgnoreCase("recurring gross charge")) {
                    sTotalGrossCharge = String.format("€ %.2f / mo", HTInfonovaOfferHooks.DigitalOfficeM.GrossCharge + HTInfonovaOfferHooks.DigitalOfficeM.DOAllowanceWorldwide120Minutes.GrossCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalGrossCharge, true));
                    HTInfonovaOfferHooks.GrossCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeM.GrossCharge + HTInfonovaOfferHooks.DigitalOfficeM.DOAllowanceWorldwide120Minutes.GrossCharge);
                } else if (sLabelName.equalsIgnoreCase("recurring net charge")) {
                    sTotalNetCharge = String.format("€ %.2f excl. 25%% tax", HTInfonovaOfferHooks.DigitalOfficeM.NetCharge + HTInfonovaOfferHooks.DigitalOfficeM.DOAllowanceWorldwide120Minutes.NetCharge);
                    GlobalParameters.testCaseBase.success(lblLabel.verifyText(sTotalNetCharge, true));
                    HTInfonovaOfferHooks.NetCharge = String.format("%.2f", HTInfonovaOfferHooks.DigitalOfficeM.NetCharge + HTInfonovaOfferHooks.DigitalOfficeM.DOAllowanceWorldwide120Minutes.NetCharge);
                }
                break;
            default:
                break;
        }
    }
    // endregion

    // region List
    @Then("I should see {string} list that is {string} and {string}")
    public void iShouldSeeListThatIsAnd(String sListName, String sAvailability, String sState) {
        WbListEx list = FindPath.FindList(sListName);
        boolean bAvailability = validateAvailability(sAvailability);
        boolean bState = validateState(sState);

        GlobalParameters.testCaseBase.success(list.displayed(true));
        GlobalParameters.testCaseBase.success(list.enabled(bAvailability));
        GlobalParameters.testCaseBase.success(list.verifyEmpty(bState));
    }

    @Then("I should see {string} list that contains {string} string and is {string}")
    public void iShouldSeeListThatContainsStringAndIs(String sListName, String sOffer, String sAvailability) {
        WbListEx list = FindPath.FindList(sListName);
        boolean bAvailability = validateAvailability(sAvailability);

        GlobalParameters.testCaseBase.success(list.displayed(true));
        GlobalParameters.testCaseBase.success(list.enabled(bAvailability));
        GlobalParameters.testCaseBase.success(list.verifyShoppingCartItemExist(sOffer, true));
    }

    @Then("I should see {string} list in {string} section that is {string}")
    public void iShouldSeeListInSectionThatIs(String sListName, String sSectionName, String sState) {
        WbListEx list = FindPath.FindList(sListName);
        boolean bState = validateState(sState);

        GlobalParameters.testCaseBase.success(list.displayed(5, true));
        GlobalParameters.testCaseBase.success(list.verifyEmpty(bState));
    }

    @Then("I should see {string} list in {string} popup that is {string}")
    public void iShouldSeeListInPopupThatIs(String sListName, String sPopupName, String sAvailability) {
        WbListEx list = FindPath.FindList(sListName);
        boolean bAvailability = validateAvailability(sAvailability);
        GlobalParameters.testCaseBase.success(list.displayed(15, true));
        GlobalParameters.testCaseBase.success(list.enabled(bAvailability));
    }

    @Then("I should see {string} list on left part of the page that is {string}")
    public void iShouldSeeListOnLeftPartOfThePageThatIs(String sListName, String sAvailability) {
        WbListEx list = FindPath.FindList(sListName);
        boolean bAvailability = validateAvailability(sAvailability);

        GlobalParameters.testCaseBase.success(list.displayed(true));
        GlobalParameters.testCaseBase.success(list.enabled(bAvailability));
    }

    @Then("I should see {string} list on left part of the page that is {string} and {string}")
    public void iShouldSeeListOnLeftPartOfThePageThatIsAnd(String sListName, String sAvailability, String sState) {
        WbListEx list = FindPath.FindList(sListName);
        boolean bAvailability = validateAvailability(sAvailability);
        boolean bState = validateState(sState);

        GlobalParameters.testCaseBase.success(list.displayed(true));
        GlobalParameters.testCaseBase.success(list.enabled(bAvailability));
        GlobalParameters.testCaseBase.success(list.verifyEmpty(bState));
    }

    @When("I select {string} option in {string} list")
    public void iSelectUsersOptionInList(String sItem, String sListName) {
        WbListEx list = FindPath.FindList(sListName);
        GlobalParameters.testCaseBase.success(list.verifyListItemExists(sItem, true));
        GlobalParameters.testCaseBase.success(list.getListItemByText(sItem).click());
    }

    @When("I select {string} item in {string} list")
    public void iSelectItemInList(String sItem, String sListName) {
        WbListEx list = FindPath.FindList(sListName);

        GlobalParameters.testCaseBase.success(list.displayed(10, true));
        GlobalParameters.testCaseBase.success(list.enabled(true));
        GlobalParameters.testCaseBase.success(list.verifyEmpty(false));
        Helpers.waitForAction(3);

        switch (sItem.toLowerCase()) {
            case "service address", "billing address", "billing":
                GlobalParameters.testCaseBase.success(list.verifyListItemExists(sItem + ": " + HTInfonovaHooks.Location, true));
                GlobalParameters.testCaseBase.success(list.getListItemByText(sItem + ": " + HTInfonovaHooks.Location).click());
                Helpers.waitForAction(5);
                break;

            case "contact":
                GlobalParameters.testCaseBase.success(list.verifyListItemExists(sItem + ": " + HTInfonovaHooks.CustomerLocation, true));
                GlobalParameters.testCaseBase.success(list.getListItemByText(sItem + ": " + HTInfonovaHooks.CustomerLocation).click());
                Helpers.waitForAction(5);
                break;

            default:
                GlobalParameters.testCaseBase.success(list.verifyListItemExists(sItem, true));
                GlobalParameters.testCaseBase.success(list.getListItemByText(sItem).click());
                break;
        }
    }

    @When("I select {string} option in {string} list on left part of the page")
    public void iSelectOptionInListOnLeftPartOfThePage(String sItem, String sListName) {
        WbListEx list = FindPath.FindList(sListName);
        switch (sListName.toLowerCase()) {
            case "feature filter":
                GlobalParameters.testCaseBase.success(list.findFeatureFilterByText(sItem).click());
                break;
            case "feature group":
                GlobalParameters.testCaseBase.success(list.findFeatureGroupByText(sItem).click());
                break;
            case "customer menu":
                GlobalParameters.testCaseBase.success(list.selectItemByText(sItem));
                break;
        }
    }
    // endregion

    // region Checkbox
    @When("I select {string} checkbox")
    public void iSelectCheckbox(String sCheckboxName) {
        WbCheckBoxEx checkbox = FindPath.FindCheckbox(sCheckboxName);
        GlobalParameters.testCaseBase.success(checkbox.check(true, true));
        GlobalParameters.testCaseBase.success(checkbox.verifyChecked(true));
    }

    @When("I select {string} checkbox in {string} section")
    public void iSelectCheckboxInSection(String sCheckboxName, String sSectionName) {
        WbCheckBoxEx checkbox = FindPath.FindCheckbox(sCheckboxName);
        GlobalParameters.testCaseBase.success(checkbox.check(true, true));
        GlobalParameters.testCaseBase.success(checkbox.verifyChecked(true));
    }
    // endregion

    // region Page

    @Given("I navigate to {string} page")
    public void iNavigateToPage(String sPageName) {
        HTInfonovaMap.initialize();
        WbPageEx page = FindPath.FindPage(sPageName);
        GlobalParameters.testCaseBase.success(page.navigate());
    }

    @Then("I should see {string} page")
    public void iShouldSeePage(String sPageName) {
        Helpers.waitForAction(5);
        WbPageEx page = FindPath.FindPage(sPageName);

        switch (sPageName.toLowerCase()) {
            case "login":
                GlobalParameters.testCaseBase.success(page.verifyDisplayed(10));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.LoginPage.Username.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.LoginPage.Username.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.LoginPage.Username.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.LoginPage.Password.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.LoginPage.Password.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.LoginPage.Password.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.LoginPage.Login.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.LoginPage.Login.enabled(true));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "main":
                GlobalParameters.testCaseBase.success(page.verifyDisplayed(30));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.Menu.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.Menu.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.Dashboard.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.Dashboard.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.Dashboard.verifyListItemExists("Dashboard", true));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "customers":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("customermanagement/find"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.NavigationBar.displayed(5, true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.NavigationBar.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.NavigationBar.verifyListItemExists("Customers", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.CustomerPage.SearchCustomers.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.CustomerPage.SearchCustomers.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.CustomerPage.SearchCustomers.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.CustomerPage.CreateCustomer.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.CustomerPage.CreateCustomer.enabled(true));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "profile":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("account/profile"));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "overview":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("overview"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.BillingAccount.displayed(10, true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.BillingAccount.enabled(true));
                HTInfonovaHooks.BillingAccount = HTInfonovaMap.OverviewPage.BillingAccount.getControlText();
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.BillingAccount.verifyText(HTInfonovaHooks.BillingAccount, true));

                GlobalParameters.testCaseBase.success(page.verifyURLChunk(HTInfonovaHooks.BillingAccount));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.CustomerMenu.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.CustomerMenu.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.CustomerMenu.verifyEmpty(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.ContactDetails.displayed(true));
                for (String tag : scenario.getSourceTagNames()) {
                    if (tag.equals("@CreateCustomer")) {
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.NavigationBar.verifyListItemExists(HTInfonovaHooks.LegalName, true));
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.ContactDetails.verifyListItemExists(HTInfonovaHooks.Address, true));
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.ContactDetails.verifyListItemExists(HTInfonovaHooks.Mobile, true));
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OverviewPage.ContactDetails.verifyListItemExists(HTInfonovaHooks.Email, true));
                        break;
                    }
                }
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "users":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("/account/users"));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.AddUser.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.AddUser.enabled(true));

                if (HTInfonovaHooks.User) {
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.Message.displayed(true));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.Message.verifyTextContains(HTInfonovaHooks.TelekomID, true));

                    GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.UsersTable.displayed(true));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.UsersTable.verifyEmpty(false));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.UsersTable.verifyTermExists(HTInfonovaHooks.TelekomID));
                }
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "subaccounts":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("account/subaccounts"));

                if (HTInfonovaHooks.SubAccount) {
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Message.displayed(true));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Message.verifyTextContains("Subaccount has been successfully created", true));

                    GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.Subaccounts.displayed(true));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.Subaccounts.enabled(true));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.Subaccounts.verifyEmpty(false));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.Subaccounts.verifyTermExists(HTInfonovaHooks.DisplayedName));
                    HTInfonovaHooks.SubAccount = false;
                }

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.AddSubaccounts.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.AddSubaccounts.enabled(true));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "address check":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("addressCheckOrderCapture"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.SaveProspect.displayed(5, true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.SaveProspect.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.CreateCustomer.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.CreateCustomer.enabled(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCart.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CreateSubAccount.ShoppingCart.verifyAttributeValueContains("class", "is-disabled"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Next.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Next.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Previous.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Previous.enabled(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Cancel.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Cancel.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.StreetName.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.StreetName.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.StreetName.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Number.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Number.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Number.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Suffix.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Suffix.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Suffix.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Zip.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Zip.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Zip.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.City.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.City.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.City.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Place.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Place.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Place.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Country.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Country.enabled(true));
                HTInfonovaHooks.Country = HTInfonovaMap.OffersAndCustomers.AddressCheck.Country.getControlTextEx();
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Country.verifySelected(HTInfonovaHooks.Country, true));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "offer selection":
                Helpers.waitForAction(5);
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("offerselection"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferSelection.Offers.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferSelection.Offers.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferSelection.Offers.verifyEmpty(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Previous.enabled(true));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "customer details":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("customerdetails"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CustomerDetails.DisplayName.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CustomerDetails.DisplayName.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CustomerDetails.DisplayName.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CustomerDetails.BusinessNumberOIB.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CustomerDetails.BusinessNumberOIB.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.CustomerDetails.BusinessNumberOIB.verifyEmpty(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Next.enabled(false));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "payment details":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("paymentdetails"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.CustomerName.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.CustomerName.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.CustomerName.verifyText(HTInfonovaHooks.DisplayedName));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.ValidateAddress.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.ValidateAddress.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.BillMedia.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.BillMedia.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.BillMedia.verifySelected("E-bill IDC+e-mail", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.DunningTreatment.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.DunningTreatment.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.DunningTreatment.verifySelected("Standard", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.Email.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.Email.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.Email.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.TestAccNo.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.TestAccNo.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.TestAccNo.verifySelected(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.TestAccYes.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.TestAccYes.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.TestAccYes.verifySelected(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.BillType.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.BillType.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.BillType.verifySelected("Detailed specification", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.CreditClass.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.CreditClass.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.CreditClass.verifySelected("BUSSdef", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.EDIBillMediaEligible.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.EDIBillMediaEligible.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.EDIBillMediaEligible.verifySelected("Please select...", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.Region.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.Region.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.Region.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.FrameContractID.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.FrameContractID.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.SubaccountsPage.PaymentDetails.FrameContractID.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Next.enabled(false));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "shipping details":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("shippingdetails"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.ShippingDetails.FullName.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.ShippingDetails.FullName.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.ShippingDetails.FullName.verifyText(HTInfonovaHooks.DisplayedName));


                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.ShippingDetails.ValidateAddress.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.ShippingDetails.ValidateAddress.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Next.enabled(false));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "offers":
                Helpers.waitForAction(3);
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("offer/offers"));

                if (HTInfonovaHooks.Offer) {
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Message.displayed(true));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Message.verifyTextContains("Order", true));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Message.verifyTextContains("has been placed successfully", true));

                    Helpers.waitForAction(3);
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.Page.refreshPage());

                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.Offers.displayed(10, true));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.Offers.enabled(true));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.Offers.verifyEmpty(false));
                    GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.Offers.verifyTermExists(HTInfonovaHooks.Offers));
                }

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.Actions.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.Actions.enabled(true));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "orders and service requests":
                Helpers.waitForAction(3);
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("offer/orders/v2"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.OrdersAndServiceRequests.Orders.displayed(5, true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.OrdersAndServiceRequests.Orders.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.OrdersAndServiceRequests.Orders.verifyEmpty(false));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "add offer address check":
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("addressCheckAddOffer"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.SaveDraftOrder.displayed(5, true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.SaveDraftOrder.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddOffer.displayed(5, true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddOffer.enabled(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.ValidateServiceAddress.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.ValidateServiceAddress.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.AddOffer.ShoppingCart.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersPage.AddOffer.ShoppingCart.verifyAttributeValueContains("class", "is-disabled"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Next.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Next.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Previous.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Previous.enabled(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Cancel.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Cancel.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.StreetName.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.StreetName.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.StreetName.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Number.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Number.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Number.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Zip.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Zip.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Zip.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.City.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.City.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.City.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Place.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Place.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Place.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Country.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Country.enabled(true));
                HTInfonovaHooks.Country = HTInfonovaMap.OffersAndCustomers.AddressCheck.Country.getControlTextEx();
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.AddressCheck.Country.verifySelected(HTInfonovaHooks.Country, true));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "new customer":
                GlobalParameters.testCaseBase.success(page.verifyDisplayed(15));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.NavigationBar.verifyListItemExists("New", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.Cancel.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.Cancel.enabled(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.CreateCustomer.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.CreateCustomer.enabled(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.BusinessOIB.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.BusinessOIB.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.BusinessOIB.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.BusinessType.displayed(5, true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.BusinessType.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.BusinessType.verifySelected("Please select...", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.LegalNameShort.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.LegalNameShort.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.LegalNameShort.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.IndustryType.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.IndustryType.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.IndustryType.verifySelected("Please select...", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.CustomerSegment.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.CustomerSegment.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.CustomerSegment.verifySelected("VSE", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.CompanyStatus.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.CompanyStatus.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.CompanyStatus.verifySelected("Please select...", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.PublicTender.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.PublicTender.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.PublicTender.verifyChecked(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.Residency.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.Residency.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.BusinessDetails.Residency.verifySelected("Please select...", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.LastName.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.LastName.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.LastName.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.FirstName.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.FirstName.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.FirstName.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.Department.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.Department.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.Department.verifySelected("CEO/ Managing Director", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.AuthorisationPerson.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.AuthorisationPerson.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.AuthorisationPerson.verifySelected("Please select...", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.ResponsibleIssue.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.ResponsibleIssue.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.ResponsibleIssue.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.ResponsibleExpiration.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.ResponsibleExpiration.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PrimaryResponsiblePerson.ResponsibleExpiration.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalOIB.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalOIB.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalOIB.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalDocumentType.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalDocumentType.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalDocumentType.verifySelected("Personal ID-Card", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalDocumentID.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalDocumentID.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.PersonalDocumentID.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentIssue.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentIssue.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentIssue.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentExpiration.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentExpiration.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentExpiration.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentIssueIns.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentIssueIns.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.PersonalIdentificationDocument.DocumentIssueIns.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.StreetName.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.StreetName.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.StreetName.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Number.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Number.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Number.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Suffix.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Suffix.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Suffix.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Zip.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Zip.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Zip.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.City.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.City.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.City.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Place.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Place.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Place.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Country.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Country.enabled(true));
                HTInfonovaHooks.Country = HTInfonovaMap.NewCustomer.LegalAddress.Country.getControlTextEx();
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.LegalAddress.Country.verifySelected(HTInfonovaHooks.Country, true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.StreetName.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.StreetName.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.StreetName.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Number.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Number.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Number.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Suffix.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Suffix.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Suffix.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Zip.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Zip.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Zip.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.City.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.City.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.City.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Place.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Place.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Place.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Country.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Country.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactAddress.Country.verifySelected("Hrvatska", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.ContactMethod.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.ContactMethod.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.ContactMethod.verifySelected("Email", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.Mobile.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.Mobile.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.Mobile.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.Email.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.Email.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.Email.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.ContactLanguage.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.ContactLanguage.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ContactDetails.ContactLanguage.verifySelected("Croatian", true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.CreditScoring.CheckCredit.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.CreditScoring.CheckCredit.enabled(false));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;
        }
    }

    @Then("^I should see \"(.*)\" page for ([^\"]*) offer$")
    public void iShouldSeePageForOffer(String sPageName, String sItem) {
        WbPageEx page = FindPath.FindPage(sPageName);

        switch (sPageName.toLowerCase()) {
            case "offer configuration":
                Helpers.waitForAction(5);
                GlobalParameters.testCaseBase.success(page.verifyURLChunk("offerconfiguration"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.TargetDate.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.TargetDate.enabled(true));

                String Date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.TargetDate.verifyText(Date));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.ChargeDate.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.ChargeDate.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.ChargeDate.verifyEmpty(true));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.OfferHead.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.OfferHead.enabled(true));

                for (String tag : scenario.getSourceTagNames()) {
                    if (tag.contains("Fixed")) {
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Fixed_Service.displayed(true));
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Fixed_Service.enabled(true));
                        break;
                    }
                    if (tag.contains("Mobile")) {
//                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.RecurringAllowances.displayed(true));
//                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.RecurringAllowances.enabled(true));

                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Mobile_Service.displayed(true));
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Mobile_Service.enabled(true));
                        break;
                    }
                }

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Next.enabled(false));
                HTInfonovaHooks.LastArea.add(sPageName);
                break;

            case "summary":
                String Address = "";

                for (String tag : scenario.getSourceTagNames()) {
                    if (tag.contains("Fixed")) {
                        if (HTInfonovaHooks.Suffix.equals("N/A"))
                            Address = HTInfonovaHooks.StreetName + " " + HTInfonovaHooks.Number + ", " + HTInfonovaHooks.City + " " + HTInfonovaHooks.Zip + " " + HTInfonovaHooks.Country;
                        else
                            Address = HTInfonovaHooks.StreetName + " " + HTInfonovaHooks.Number + HTInfonovaHooks.Suffix + ", " + HTInfonovaHooks.City + " " + HTInfonovaHooks.Zip + " " + HTInfonovaHooks.Country;
                        break;
                    } else {
                        Address = HTInfonovaHooks.CustomerLocation;
                    }
                }

                Helpers.waitForAction(5);

                GlobalParameters.testCaseBase.success(page.verifyURLChunk("summary"));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Next.enabled(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.BillingDetails.verifyTermExists(HTInfonovaHooks.DisplayedName + " " + HTInfonovaHooks.DisplayedName));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.BillingDetails.verifyTermExists(Address));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.TermsAndConditions.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.TermsAndConditions.enabled(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.TermsAndConditions.verifyChecked(false));

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.OfferNameAndMobileNumber.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.OfferNameAndMobileNumber.verifyText(sItem + " [" + HTInfonovaHooks.MobileNumber + "]", true));

                for (String tag : scenario.getSourceTagNames()) {
                    if (tag.contains("Mobile")) {
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.ServiceDescription.displayed(true));
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.ServiceDescription.verifyEmpty(false));
                        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.ServiceDescription.verifyTermExists(HTInfonovaHooks.MobileNumber));
                        break;
                    }
                }

                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.ChargeSummaryForThisOrder.displayed(true));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.ChargeSummaryForThisOrder.verifyEmpty(false));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.ChargeSummaryForThisOrder.verifyTermExists("€ " + HTInfonovaOfferHooks.GrossCharge));
                GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.Summary.ChargeSummaryForThisOrder.verifyTermExists("€ " + HTInfonovaOfferHooks.NetCharge));

                HTInfonovaHooks.Logout = false;
                HTInfonovaHooks.LastArea.add(sPageName);
                break;
        }
    }
    // endregion

    // region Link
    @Then("I should see {string} link in {string} popup that is {string}")
    public void iShouldSeeLinkInPopupThatIs(String sLinkName, String Popup, String sAvailability) {
        WbButtonEx button = FindPath.FindButton(sLinkName);
        boolean bAvailability = validateAvailability(sAvailability);
        GlobalParameters.testCaseBase.success(button.displayed(10, true));
        GlobalParameters.testCaseBase.success(button.enabled(bAvailability));
    }

    @When("I click on {string} link in {string} popup")
    public void iClickOnLinkInPopup(String sLinkName, String sPopupName) {
        WbButtonEx link = FindPath.FindButton(sLinkName);
        GlobalParameters.testCaseBase.success(link.click());
    }
    // endregion

    // region Section and Popup
    @When("I open up {string} section")
    public void iOpenUpSection(String sButtonName) {
        WbButtonEx button = FindPath.FindButton(sButtonName);
        GlobalParameters.testCaseBase.success(button.click());
    }

    @Then("I should see New User popup")
    public void iShouldSeeNewUserPopup() {
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.Page.verifyURLChunk("/account/users"));

        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.TelekomID.displayed(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.TelekomID.enabled(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.TelekomID.verifyEmpty(false));
        HTInfonovaHooks.TelekomID = HTInfonovaMap.UsersPage.TelekomID.getControlTextEx();

        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.LastName.displayed(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.LastName.enabled(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.LastName.verifyEmpty(true));

        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.FirstName.displayed(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.FirstName.enabled(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.FirstName.verifyEmpty(true));

        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.Email.displayed(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.Email.enabled(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.Email.verifyEmpty(true));

        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.UserRole.displayed(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.UserRole.enabled(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.UserRole.verifySelected("Please select...", true));

        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.Cancel.displayed(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.Cancel.enabled(true));

        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.SaveChanges.displayed(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.UsersPage.SaveChanges.enabled(false));
    }
    // endregion

    // region Unique
    @When("I select corresponding address option in Addresses list in Matching Addresses popup")
    public void iSelectCorrespondingAddressOptionInAddressesListInMatchingAddressesPopup() {
        if (HTInfonovaHooks.Suffix.equals("N/A"))
            HTInfonovaHooks.Address = HTInfonovaHooks.StreetName + " " + HTInfonovaHooks.Number + "\nPlace: " + HTInfonovaHooks.Place + "\n" + HTInfonovaHooks.Zip + " " + HTInfonovaHooks.City + " " + HTInfonovaHooks.Country;
        else
            HTInfonovaHooks.Address = HTInfonovaHooks.StreetName + " " + HTInfonovaHooks.Number + "\nSuffix: " + HTInfonovaHooks.Suffix + "\nPlace: " + HTInfonovaHooks.Place + "\n" + HTInfonovaHooks.Zip + " " + HTInfonovaHooks.City + " " + HTInfonovaHooks.Country;

        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.MatchingAddresses.Addresses.selectAddress(HTInfonovaHooks.Address));
    }

    @When("I select corresponding address option in Existing Addresses list in Contact Addresses section")
    public void iSelectCorrespondingAddressOptionInExistingAddressesListInContactAddressesSection() {
        String Address;
        if (HTInfonovaHooks.Suffix.equals("N/A"))
            Address = HTInfonovaHooks.StreetName + " " + HTInfonovaHooks.Number + ", " + HTInfonovaHooks.City + " " + HTInfonovaHooks.Zip + " " + HTInfonovaHooks.Country;
        else
            Address = HTInfonovaHooks.StreetName + " " + HTInfonovaHooks.Number + ", " + "Suffix: " + HTInfonovaHooks.Suffix + ", " + HTInfonovaHooks.City + " " + HTInfonovaHooks.Zip + " " + HTInfonovaHooks.Country;
        GlobalParameters.testCaseBase.success(HTInfonovaMap.NewCustomer.ExistingAddressesList.getListItemByText("Legal Address: " + Address).click());
    }

    @When("^I click on Add To Cart button in Offers table for ([^\"]*) offer$")
    public void iClickOnAddToCartButtonInOffersTableForOffer(String sItem) {
        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferSelection.Offers.findButtonByCellValue(sItem).click());
        HTInfonovaHooks.Offers = sItem;
    }

    @When("I select first option in Phone Number table in Select Number popup")
    public void iSelectFirstOptionInPhoneNumberTableInSelectNumbePopup() {
        HTInfonovaHooks.MobileNumber = HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Service.SelectNumber.NumbersMenu.findRowByIndex(0, true).getControlText();
        GlobalParameters.testCaseBase.success(HTInfonovaMap.OffersAndCustomers.OfferConfiguration.Service.SelectNumber.NumbersMenu.findRowByIndex(0, true).click());
        Helpers.waitForAction(3);
    }
    // endregion

    // region Logout and validation
    @When("I select {string} option in Profile list on right part of the page")
    public static void iSelectOptionInProfileListOnRightPartOfThePage(String sItem) {
        GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.Logout.click());

        GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.LogoutList.displayed(5, true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.LogoutList.enabled(true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.LogoutList.verifyEmpty(false));

        GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.LogoutList.verifyListItemExists(sItem, true));
        GlobalParameters.testCaseBase.success(HTInfonovaMap.MainPage.LogoutList.getListItemByText(sItem).click());
    }

    @Then("I should see the Pop-up window")
    public static void iShouldSeeThePopUpWindow() {
        UIReferences.getWebDriver().switchTo().alert();
    }

    @When("I click on {string} button in Pop-up window")
    public static void iClickOnButtonInPopUpWindow(String sBtnName) {
        HTInfonovaHooks.Logout = false;

        if (sBtnName.equalsIgnoreCase("leave"))
            UIReferences.getWebDriver().switchTo().alert().accept();
        else
            UIReferences.getWebDriver().switchTo().alert().dismiss();
    }

    private boolean validateAvailability(String sAvailability) {
        if (!sAvailability.equalsIgnoreCase("enabled") && !sAvailability.equalsIgnoreCase("disabled"))
            throw new IllegalArgumentException("Invalid value for availability. Expected 'enabled' or 'disabled'.");
        else if (sAvailability.equalsIgnoreCase("enabled"))
            return true;
        else
            return false;
    }

    private boolean validateState(String sState) {
        if (!sState.equalsIgnoreCase("empty") && !sState.equalsIgnoreCase("not empty"))
            throw new IllegalArgumentException("Invalid value for state. Expected 'empty' or 'not empty'.");
        else if (sState.equalsIgnoreCase("empty"))
            return true;
        else
            return false;
    }
    // endregion
}