package cucumber.glue;

import cucumber.maps.Map;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.types.*;
import org.raf3k.shared.SharedVariables;
import org.testng.Assert;

public class TestSharedSteps {
    private static Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("I navigate to the {string} page")
    public void iNavigateToThePage(String sPageName) {
        WbPage page = findPageByName(sPageName);
        UIReferences.uiTestCase.success(page.navigate());
    }

    @Then("I should see the {string} page")
    public void iShouldSeeThePage(String sPageName) {
        WbPage page = findPageByName(sPageName);
        UIReferences.uiTestCase.success(page.verifyDisplayed());
    }

    @And("I should see the {string} textbox which is enabled and empty")
    public void iShouldSeeTheTextboxWhichIsEnabledAndEmpty(String sTextboxName) {
        WbEdit textbox = findTextboxByName(sTextboxName);

        UIReferences.uiTestCase.success(textbox.displayed(true));
        UIReferences.uiTestCase.success(textbox.enabled(true, 0));
        UIReferences.uiTestCase.success(textbox.verifyEmpty(true));
    }

    @And("I should see the {string} checkbox which is enabled and {string}")
    public void iShouldSeeTheCheckboxWhichIsEnabledAnd(String sCheckboxName, String sChecked) {
        WbCheckBox checkbox = findCheckboxByName(sCheckboxName);


        UIReferences.uiTestCase.success(checkbox.displayed(true));
        UIReferences.uiTestCase.success(checkbox.enabled(true, 0));

        switch (sChecked) {
            case "checked":
                UIReferences.uiTestCase.success(checkbox.verifyChecked(true));
                break;
            case "not checked":
                UIReferences.uiTestCase.success(checkbox.verifyChecked(false));
                break;
            default:
                Assert.fail("Checkbox not found");
        }
    }

    @And("I should see the {string} button which is enabled")
    public void iShouldSeeTheButtonWhichIsEnabled(String sButtonName) {
        WbButton button = findButtonByName(sButtonName);

        UIReferences.uiTestCase.success(button.displayed(true));
        UIReferences.uiTestCase.success(button.enabled(true, 0));
    }

    @When("I enter {string} string into {string} textbox")
    public void iEnterStringIntoTextbox(String sText, String sTextboxName) {
        WbEdit textbox = findTextboxByName(sTextboxName);

        UIReferences.uiTestCase.success(
                switch (sText) {
                    case "<adminEmail>" -> textbox.setText(SharedVariables.configuration.getProperty("adminEmail"));
                    case "<adminPassword>" -> textbox.setText(SharedVariables.configuration.getProperty("adminPassword"));
                    default -> textbox.setText(sText, false, false, false);
                });
    }

    @Then("the {string} textbox should contain {string} string")
    public void theTextboxShouldContainString(String sTextboxName, String sText) {
        WbEdit textbox = findTextboxByName(sTextboxName);

        UIReferences.uiTestCase.success(
                switch (sText) {
                    case "<adminEmail>" -> textbox.verifyText(SharedVariables.configuration.getProperty("adminEmail"));
                    case "<adminPassword>" -> textbox.verifyText(SharedVariables.configuration.getProperty("adminPassword"));
                    default -> textbox.verifyText(sText);
                });
    }

    @When("I click on the {string} checkbox")
    public void iClickOnTheCheckbox(String sCheckboxName) {
        WbCheckBox checkbox = findCheckboxByName(sCheckboxName);

        UIReferences.uiTestCase.success(checkbox.click());
    }

    @Then("the {string} checkbox should be checked")
    public void theCheckboxShouldBeChecked(String sCheckboxName) {
        WbCheckBox checkbox = findCheckboxByName(sCheckboxName);

        UIReferences.uiTestCase.success(checkbox.verifyChecked(true));
    }

    @When("I click on the {string} button")
    public void iClickOnTheButton(String sButtonName) {
        WbButton button = findButtonByName(sButtonName);
        UIReferences.uiTestCase.success(button.click());
    }

    @Then("I should see the {string} label message saying {string}")
    public void iShouldSeeTheLabelMessageSaying(String sLabelName, String sLabelMessage) {
        WbLabel label = findLabelByName(sLabelName);

        UIReferences.uiTestCase.success(label.verifyText(sLabelMessage, true));
    }

    @And("the {string} textbox should be empty")
    public void theTextboxShouldBeEmpty(String sTextboxName) {

        WbEdit textbox = findTextboxByName(sTextboxName);

        UIReferences.uiTestCase.success(textbox.verifyEmpty(true));
    }

    @When("I delete string in {string} textbox")
    public void iDeleteStringInTextbox(String sTextboxName) {
        WbEdit textbox = findTextboxByName(sTextboxName);

        UIReferences.uiTestCase.success(textbox.clear(true));
    }

    private static String getFeatureFileNameFromScenarioId(Scenario scenario) {
        String[] tab = scenario.getUri().toString().split("/");
        int rawFeatureNameLength = tab.length;
        String featureName = tab[rawFeatureNameLength - 1].split("\\.")[0];

        return featureName;
    }

    private WbPage findPageByName(String sPageName) {
        WbPage page = null;

        switch (sPageName.toLowerCase()) {
            case "login":
                page = Map.Login.page;
                break;
            case "home":
                page = Map.Home.page;
                break;
            default:
                Assert.fail("Case not found");
        }

        return page;
    }

    private WbEdit findTextboxByName(String sTextboxName) {
        String sFeature = getFeatureFileNameFromScenarioId(scenario);
        WbEdit textbox = null;

        if (sFeature.toLowerCase().contains("cucumber"))
            switch (sTextboxName) {
                case "Email":
                    textbox = Map.Login.txtEmail;
                    break;
                case "Password":
                    textbox = Map.Login.txtPassword;
                    break;
                default:
                    Assert.fail("Textbox not found");
            }
        return textbox;
    }

    private static WbCheckBox findCheckboxByName(String sCheckboxName) {
        String sFeature = getFeatureFileNameFromScenarioId(scenario);
        WbCheckBox checkbox = null;

        if (sFeature.toLowerCase().contains("cucumber"))
            switch (sCheckboxName) {
                case "Remember me":
                    checkbox = Map.Login.chkRememberMe;
                    break;
                default:
                    Assert.fail("Checkbox not found");
            }
        return checkbox;
    }

    public static WbButton findButtonByName(String sButtonName) {
        String sFeature = getFeatureFileNameFromScenarioId(scenario);
        WbButton button = null;

        if (sFeature.toLowerCase().contains("cucumber"))
            switch (sButtonName) {
                case "Sign in":
                    button = Map.Login.btnSignIn;
                    break;
                case "Sign out":
                    button = Map.Home.btnLogOut;
                    break;
                default:
                    Assert.fail("Button not found");
            }
        return button;
    }

    private WbLabel findLabelByName(String sLabelName) {
        String sFeature = getFeatureFileNameFromScenarioId(scenario);
        WbLabel message = null;

        if (sFeature.toLowerCase().contains("cucumber"))
            switch (sLabelName.toLowerCase()) {
                case "email":
                    message = Map.Login.lblEmailErrorMessage;
                    break;
                case "password":
                    message = Map.Login.lblPasswordErrorMessage;
                    break;
                case "invalid login":
                    message = Map.Login.lblInvalidLoginAttempt;
                    break;
                default:
                    Assert.fail("Label not found");
            }
        return message;
    }
}
