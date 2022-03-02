import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

import java.util.List;

public class TestRunnerWbList extends UITestCase {
    public TestRunnerWbList() {
        super("List test", "TC0012", "Filip Borić");
    }

    @Test
    void First() {

        Map.initialize2();

        newStep(1, "Open 'https://www.w3schools.com/html/tryit.asp?filename=tryhtml_lists_unordered2' page.");
        success(Map.ListTest.page.navigate());
        UIReferences.getWebDriver().switchTo().frame("iframeResult");
        var blaa = Map.ListTest.list.getListItemByText("Coffee");
        var blaaaa = Map.ListTest.list.getListItemByIndex(1);
    }
}
