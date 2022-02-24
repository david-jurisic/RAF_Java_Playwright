import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

public class TestRunnerWbLink extends UITestCase {
    public TestRunnerWbLink() {
        super("Test WBLink", "TC006", "Dražen Kozić");
    }

    @Test
    void First() {
        Map.initialize();

        newStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        success(Map.Login.page.navigate());
        success(Map.Login.page.verifyDisplayed());

        success(Map.Login.txtEmail.setText("admin@roxoft.hr",false,false,false));
        success(Map.Login.txtPassword.setText("RoxoftKale123",false,false,false));
        success(Map.Login.btnSignIn.click());

        success(Map.Home.link1.verifyText("2",true));
        success(Map.Home.link2.verifyTextContains("Projec",true));

        success(Map.Home.link1.verifyText("3",false));
        success(Map.Home.link2.verifyTextContains("Projecnnnn",false));

    }
}
