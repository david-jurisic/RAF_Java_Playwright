import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

public class TestRunnerWbDropDownList extends UITestCase {
    public TestRunnerWbDropDownList() {
        super("Test WbDropDownList", "TC010", "Dražen Kozić");
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

        success(Map.Home.btnTags.click());

        success(Map.Tags.ddlPageSize.verifySelectedItem("10"));
        success(Map.Tags.ddlPageSize.click());
        success(Map.Tags.ddlPageSize.doubleClickOnOption(3));

    }
}
