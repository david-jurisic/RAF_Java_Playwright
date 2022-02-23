import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

public class TestRunnerWbPage extends UITestCase {
    public TestRunnerWbPage() {
        super("Test WbPage", "TC005", "Dražen Kozić");
    }

    @Test
    void First() {
        Map.initialize();

        newStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        //success(Map.Login.page.navigate());
        //success(Map.Login.page.verifyDisplayed());

        //success(Map.Login.txtEmail.setText("admin@roxoft.hr",false,false,false));
        //success(Map.Login.txtPassword.setText("RoxoftKale123",false,false,false));
        //success(Map.Login.btnSignIn.click());

        //success(Map.Home.page.verifyDisplayed());

        //success(Map.Home.btnProjects.click());
        //success(Map.Projects.page.verifyDisplayed());

        //success(Map.Projects.btnCreate.click());

        success(Map.NewProject.page.navigate());
        success(Map.NewProject.page.verifyDisplayedByLink());

        //Map.NewProject.page.Test();

    }
}
