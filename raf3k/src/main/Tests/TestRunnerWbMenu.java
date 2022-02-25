import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

public class TestRunnerWbMenu extends UITestCase {
    public TestRunnerWbMenu() {
        super("Test WbMenu", "TC009", "Dražen Kozić");
    }

    @Test
    void First() {
        UIReferences.currentPageContext = "https://www.w3schools.com/";

        newStep(1, "Open 'https://www.w3schools.com/' page.");
        success(Map.Menu.page.navigate());
        success(Map.Menu.page.verifyDisplayed());

        success(Map.Menu.btnAccept.click());
        success(Map.Menu.menu.verifyMenuItemPresent("Icon Bar"));
        success(Map.Menu.menu.verifyMenuItemPresent("Menu Icon"));
        success(Map.Menu.menu.setMenuItem("Menu Icon"));
    }
}
