import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;

public class TestRunnerWbRadioButton extends UITestCase {
    public TestRunnerWbRadioButton() {
        super("Test RadioButton", "TC007", "Dražen Kozić");
    }

    @Test
    void First() {
        UIReferences.currentPageContext = "https://www.keynotesupport.com/";

        newStep(1, "Open 'https://www.keynotesupport.com/' page.");
        success(Map.RadioButton.page.navigate());

        success(Map.RadioButton.rbExcel.select(true));
        success(Map.RadioButton.rbExcel.verifySelected(true));

        success(Map.RadioButton.rbQuickBook.select(true));
        success(Map.RadioButton.rbQuickBook.verifySelected(true));

        success(Map.RadioButton.rbBeginner.select(true));
        success(Map.RadioButton.rbBeginner.verifySelected(true));

        success(Map.RadioButton.rbIntermediate.select(true));
        success(Map.RadioButton.rbIntermediate.verifySelected(true));
    }
}
