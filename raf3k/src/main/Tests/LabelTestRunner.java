import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
import org.junit.jupiter.api.Test;


public class LabelTestRunner extends UITestCase {
    public LabelTestRunner() {
        super("label test case", "TC002", "Denis Milič");
    }

    @Test
    void First() {

        Map.initialize();

        newStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        success(Map.Login.page.navigate());
        success(Map.Login.txtEmail.setText("test@test.com", false, false, false));
        success(Map.Login.txtPassword.setText("test1234", false, false, false));
        success(Map.Login.lblMemberLogin.verifyText("Member Login", true));
    }
}
