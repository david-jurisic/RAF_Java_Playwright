import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
import org.junit.jupiter.api.Test;
import org.raf3k.shared.logging.Success;

//@RAFtestCaseAttribute( testCaseName = "Incorrect login", testCaseCode = "TC001", testType = TestType.UI, author = "Dražen Kozić")
public class TestRunnerWbEdit extends UITestCase {
    public TestRunnerWbEdit() {
        super("Incorrect login", "TC001", "Dražen Kozić");
    }

    @Test
    void First() {

        Map.initialize();

        newStep(1, "When I Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        success(Map.Login.page.navigate());
        newStep(2, "Then I should see email textbox which enabled and empty");
        success(Map.Login.txtEmail.enabled(true, null));
        success(Map.Login.txtEmail.displayed(true));
        success(Map.Login.txtEmail.verifyEmpty(true));
        newStep(3, "And I should see password textbox which enabled and empty");
        success(Map.Login.txtPassword.enabled(true, null));
        success(Map.Login.txtPassword.displayed(true));
        success(Map.Login.txtPassword.verifyEmpty(true));
        success(Map.Login.txtPassword.verifyAttributeValue("placeholder", "Password"));
        success(Map.Login.txtPassword.verifyAttributeExists("placeholder", true));
        var attributeValue = Map.Login.txtPassword.getAttributeValue("placeholder");
        System.out.println(attributeValue);
        newStep(4, "And I should see sign in button which is enabled");
        success(Map.Login.btnSignIn.displayed(true));
        success(Map.Login.btnSignIn.enabled(true, null));
        newStep(5, "When I enter test@test.com in email textbox");
        success(Map.Login.txtEmail.setText("test@test.com", false, false, false));
        newStep(6, "And I enter test1234 in email textbox");
        success(Map.Login.txtPassword.setText("test1234", false, false, false));
        newStep(7, "Then email textbox should contain test@test.com");
        success(Map.Login.txtEmail.verifyText("test@test.com"));
        success(Map.Login.txtEmail.getText());
        System.out.println("txtEmail: " + Map.Login.txtEmail.getTextValue);
        success(Map.Login.txtPassword.getText());
        System.out.println("txtPassword: " + Map.Login.txtPassword.getTextValue);
        success(Map.Login.txtEmail.verifyTextContains("st@"));
        newStep(8, "Then password textbox should contain test@test.com");
        success(Map.Login.txtPassword.verifyText("test1234"));
        success(Map.Login.txtPassword.verifyTextContains("st1"));
        newStep(9, "When I click on sign in button");
        success(Map.Login.btnSignIn.click());
        success((Map.Login.btnSignIn.moveToElementAndClick()));
        success(Map.Login.txtEmail.clear(false));
        success(Map.Login.txtPassword.clear(true));
    }
}
