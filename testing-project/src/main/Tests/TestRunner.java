import RAF3kGUItesting.WebTesting.BaseTypes.UITestCase;
import org.junit.jupiter.api.Test;

public class TestRunner extends UITestCase {
    @Test
    void First() {
        Map.Initialize();

        NewStep(1, "Open 'https://roxoftkale.azurewebsites.net/Account/Login' page.");
        Success(Map.Login.page.Navigate());
        Success(Map.Login.txtEmail.SetText("test",false,false,false));
    }
}
