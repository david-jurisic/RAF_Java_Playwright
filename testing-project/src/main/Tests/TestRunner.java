import RAF3kGUItesting.WebTesting.BaseTypes.UITestCase;
import org.junit.jupiter.api.Test;

public class TestRunner extends UITestCase {
    @Test
    void First() throws Exception {
        Map.Initialize();
        Success(Map.Login.page.Navigate());
        Success(Map.Login.txtEmail.SetText("test",false,false,false));
    }
}
