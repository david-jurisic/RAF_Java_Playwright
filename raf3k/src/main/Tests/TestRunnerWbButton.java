import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

public class TestRunnerWbButton extends UITestCase {
    public TestRunnerWbButton() {
        super("Button test", "TC004", "Dražen Kozić");
    }

    @Test
    void First() {

        Map.initialize();

        newStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        success(Map.Login.page.navigate());
        success(Map.Login.btnSignIn.verifyText("Sing in", false));
    }
}
