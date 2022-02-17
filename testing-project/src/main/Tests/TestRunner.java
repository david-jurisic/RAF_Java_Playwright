import RAF3kGUItesting.WebTesting.BaseTypes.UITestCase;
import RAF3kShared.RAFtestCaseAttribute;
import RAF3kShared.TestType;
import org.junit.jupiter.api.Test;

//@RAFtestCaseAttribute( testCaseName = "Incorrect login", testCaseCode = "TC001", testType = TestType.UI, author = "Dražen Kozić")
public class TestRunner extends UITestCase {
    public TestRunner() {
        super("Incorrect login","TC001","Dražen Kozić");
    }
    @Test
    void First() {

        Map.Initialize();

        NewStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        Success(Map.Login.page.Navigate());
        Success(Map.Login.txtEmail.SetText("test",false,false,false));
        Success(Map.Login.txtPassword.SetText("password",false,false,false));
    }
}
