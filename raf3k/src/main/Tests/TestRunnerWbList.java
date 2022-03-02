import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

import java.util.List;

public class TestRunnerWbList extends UITestCase {
    public TestRunnerWbList() {
        super("List test", "TC0012", "Filip Borić");
    }

    @Test
    void First() {

        Map.ListTest.initialize();

        newStep(1, "Open 'https://www.w3schools.com/html/tryit.asp?filename=tryhtml_lists_unordered2' page.");
        success(Map.ListTest.page.navigate());
        success(Map.ListTest.list.getAllListItems());
    }
}
