import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
import org.junit.jupiter.api.Test;

//@RAFtestCaseAttribute( testCaseName = "Incorrect login", testCaseCode = "TC001", testType = TestType.UI, author = "Dražen Kozić")
public class TestRunnerWbEdit extends UITestCase {
    public TestRunnerWbEdit() {
        super("Incorrect login", "TC001", "Dražen Kozić");
    }

    @Test
    void First() {

        Map.initialize();

        newStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        System.out.println("Here 1");
        success(Map.Login.page.navigate());
        success(Map.Login.txtEmail.enabled(true, null));
        success(Map.Login.txtEmail.displayed(true));
        success(Map.Login.txtEmail.verifyEmpty(true));
        success(Map.Login.txtPassword.enabled(true, null));
        success(Map.Login.txtPassword.displayed(true));
        success(Map.Login.txtPassword.verifyEmpty(true));
        success(Map.Login.txtPassword.verifyAttributeValue("placeholder", "Password"));
        success(Map.Login.txtPassword.verifyAttributeExists("placeholder", true));
        success(Map.Login.txtEmail.setText("test@test.com", false, false, false));
        success(Map.Login.txtPassword.setText("test1234", false, false, false));
        success(Map.Login.txtEmail.verifyText("test@test.com"));
        success(Map.Login.txtEmail.getText());
        System.out.println("txtEmail: " + Map.Login.txtEmail.getTextValue);
        success(Map.Login.txtPassword.getText());
        System.out.println("txtPassword: " + Map.Login.txtPassword.getTextValue);
        success(Map.Login.txtEmail.verifyTextContains("st@"));
        success(Map.Login.txtPassword.verifyTextContains("st1"));
        success(Map.Login.txtEmail.clear(false));
        success(Map.Login.txtPassword.clear(true));
    }
}
