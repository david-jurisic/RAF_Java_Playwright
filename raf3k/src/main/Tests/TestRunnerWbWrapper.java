import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

import java.util.ArrayList;

public class TestRunnerWbWrapper extends UITestCase {
    public TestRunnerWbWrapper() {
        super("Test WbWrapper ", "TC011", "Dražen Kozić");
    }

    @Test
    public void First() {
        Map.initialize();

        newStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        success(Map.Login.page.navigate());
        success(Map.Login.page.verifyDisplayed());

        success(Map.Login.wrpMain.verifyAllCheckboxesChecked(false));
        success(Map.Login.chkRememberMe.check(true, false));
        success(Map.Login.wrpMain.verifyAllCheckboxesChecked(true));

        success(Map.Login.txtEmail.setText("admin@roxoft.hr", false, false, false));
        success(Map.Login.txtPassword.setText("RoxoftKale123", false, false, false));
        success(Map.Login.btnSignIn.click());

        success(Map.Home.btnProjects.click());
        success(Map.Projects.btnCreate.click());

        ArrayList<String> listWrp = Map.NewProject.wrpMain.getText();
        success(Map.NewProject.wrpMain.verifyText("name"));
        success(Map.NewProject.txtName.setText("test1", false, false, false));
        success(Map.NewProject.wrpMain.verifyInputText("test1"));
    }
}
