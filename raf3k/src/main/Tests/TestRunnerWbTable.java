import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.enumerations.Operations;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
import org.raf3k.guittesting.webtesting.types.WbTable;

public class TestRunnerWbTable extends UITestCase {
    public TestRunnerWbTable() {
        super("Test WbTable ", "TC013", "Dražen Kozić");
    }

    @Test
    void First() {
        UIReferences.currentPageContext = "https://disenowebakus.net/en/tables-html";

        newStep(1, "Open 'https://disenowebakus.net/en/tables-html' page.");
        success(Map.TableTest.page.navigate());
        success(Map.TableTest.page.verifyDisplayed());


        Assertions.assertTrue(Map.TableTest.tblExample.findRowByIndex(1, true).bDisplayed);
        Assertions.assertTrue(Map.TableTest.tblExample.findRowByIndex(2, false).bDisplayed);

        success(Map.TableTest.tblExample.verifyColumnExists("Description", true));
        Assertions.assertTrue(Map.TableTest.tblExample.findCellByValue("Description", "Definition of table header").bDisplayed);
        Assertions.assertNull(Map.TableTest.tblExample.findCellByValue("Tags", "Test"));

        Assertions.assertEquals(Map.TableTest.tblExample.findRowByCellValue("Tags", "<tr>").sAlias, "Map.TableTestDynamicWebTable.Row[2]");
        Assertions.assertTrue(Map.TableTest.tblExample.findCellByIndex(2, 2).bDisplayed);

        success(Map.TableTest.tblExample.getRowCount());
        Assertions.assertEquals(Map.TableTest.tblExample.rowCount, 10);
        success(Map.TableTest.tblExample.verifyRowCount(10, Operations.Equals));
        success(Map.TableTest.tblExample.verifyRowCount(5, Operations.MoreThan));
        success(Map.TableTest.tblExample.verifyRowCount(21, Operations.LessThan));

        success(Map.TableTest.tblExample.verifyEmpty(false));
        //success(Map.TableTest.tblExample.clickOnHeader("Tags"));
        success(Map.TableTest.tblExample.verifyTermExists("group of one or more"));
        success(Map.TableTest.tblExample.verifyTermExists("hea"));
    }
}
