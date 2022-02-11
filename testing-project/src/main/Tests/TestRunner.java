import RAF3kGUItesting.WebTesting.BaseTypes.UITestCase;
import org.junit.jupiter.api.Test;

public class TestRunner extends UITestCase {
    @Test
    void First() throws Exception {
        Map.Initialize();
        Map.Login.page.Navigate();
        Map.Login.txtEmail.SetText("test",false,false,false);
    }
}
