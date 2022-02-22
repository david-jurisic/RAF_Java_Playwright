import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
import org.junit.jupiter.api.Test;

//@RAFtestCaseAttribute( testCaseName = "Incorrect login", testCaseCode = "TC001", testType = TestType.UI, author = "Dražen Kozić")
public class TestRunner extends UITestCase {
    public TestRunner() {
        super("Incorrect login","TC001","Dražen Kozić");
    }
    @Test
    void First() {

        Map.initialize();

        newStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        success(Map.Login.page.Navigate());
        success(Map.Login.txtEmail.setText("test@test.com",false,false,false));
        success(Map.Login.txtPassword.setText("test1234",false,false,false));
    }
}
