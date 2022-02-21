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

        NewStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        Success(Map.Login.page.Navigate());
        Success(Map.Login.txtEmail.verifyEmpty(true));
        Success(Map.Login.txtPassword.verifyEmpty(true));
        Success(Map.Login.txtEmail.setText("test@test.com", false, false, false));
        Success(Map.Login.txtPassword.setText("test1234", false, false, false));
        Success(Map.Login.txtEmail.verifyText("test@test.com"));
        Success(Map.Login.txtEmail.getText());
        System.out.println("txtEmail: " + Map.Login.txtEmail.getTextValue);
        Success(Map.Login.txtPassword.getText());
        System.out.println("txtPassword: " + Map.Login.txtPassword.getTextValue);
        Success(Map.Login.txtEmail.verifyTextContains("st@"));
        Success(Map.Login.txtPassword.verifyTextContains("st1"));
        Success(Map.Login.txtEmail.clear(false));
        Success(Map.Login.txtPassword.clear(true));
    }
}
