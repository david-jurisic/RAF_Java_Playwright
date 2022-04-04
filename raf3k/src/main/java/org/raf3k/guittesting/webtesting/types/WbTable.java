package org.raf3k.guittesting.webtesting.types;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.UIReferences;
import org.raf3k.guittesting.enumerations.Operations;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.shared.DebugLog;
import org.raf3k.shared.logging.Success;

import java.text.MessageFormat;
import java.util.ArrayList;

public class WbTable extends WebControlBase {
    public WbTable(){}

    public WbTable(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbTable(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbTable(WebElement webElement, String alias) {
        super(webElement, alias);
    }

    public WebElement tableHeader() {
        try {
            return control().findElement(By.tagName("thead"));
        } catch (Exception ex) {
            DebugLog.add("Table header not found.", 1);
            return null;
        }
    }

    public int rowCount;

    public WebElement tableBody() {
        try {
            return control().findElement(By.tagName("tbody"));
        } catch (Exception ex) {
            DebugLog.add("Table body not found.", 1);
            return null;
        }
    }

    public WebElement tableHeaderRow() {
        try {
            return tableHeader().findElement(By.tagName("tr"));
        } catch (Exception ex) {
            DebugLog.add("Table header row not found.", 1);
            return null;
        }
    }

    public ArrayList<WebElement> tableBodyRows() {
        try {
            return (ArrayList<WebElement>) tableBody().findElements(By.tagName("tr"));
        } catch (Exception ex) {
            DebugLog.add("Table body rows not found.", 1);
            return null;
        }
    }

    /**
     * Method finds table row by index.
     *
     * @param iRowIndex Table row to find. Starts at 1.
     * @param bIsHeader Search header rows is set to true. It is false by default.
     * @return WebTable object
     */
    public WbTable findRowByIndex(int iRowIndex, boolean bIsHeader) {
        try {
            ArrayList<WebElement> rows = new ArrayList<>();

            if (bIsHeader)
                rows.add(tableHeaderRow());
            else
                rows = tableBodyRows();

            if (iRowIndex > rows.size() || iRowIndex < 0)
                return null;

            WebElement row = tableBodyRows().get(iRowIndex);
            return new WbTable(row, this.sPath + "DynamicWebTable.Row[" + iRowIndex + "]");
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Method finds table row by column name and column value.
     *
     * @param sColumnName  Column name.
     * @param sColumnValue Column value.
     * @return WebTable object
     */
    public WbTable findRowByCellValue(String sColumnName, String sColumnValue) {
        try {
            int iCellIndex = -1;
            ArrayList<WebElement> headerColumns = (ArrayList<WebElement>) tableHeaderRow().findElements(By.tagName("th"));

            for (int i = 0; i < headerColumns.size(); i++) {
                if (headerColumns.get(i).getText().equals(sColumnName)) {
                    iCellIndex = i;
                    break;
                }
            }

            if (iCellIndex == -1)
                return null;

            for (WebElement row : tableBodyRows()) {
                ArrayList<WebElement> bodyColumns = (ArrayList<WebElement>) row.findElements(By.tagName("td"));

                if (bodyColumns.get(iCellIndex).getText().equals(sColumnValue)) {
                    return new WbTable(row, this.sPath + "DynamicWebTable.Row[" + tableBodyRows().indexOf(row) + "]");
                }
            }

            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Method finds table cell by row and cell index.
     *
     * @param iRowIndex  Table row to find.
     * @param iCellIndex Table cell to find.
     * @return WebTable object
     */
    public WbTable findCellByIndex(int iRowIndex, int iCellIndex) {
        try {
            iRowIndex = iRowIndex - 1;
            iCellIndex = iCellIndex - 1;
            if (iRowIndex < 1 || iRowIndex > tableBodyRows().size())
                return null;

            if (iCellIndex < 1 || iCellIndex > tableBodyRows().get(iRowIndex).findElements(By.tagName("td")).size())
                return null;

            WebElement cell = tableBodyRows().get(iRowIndex).findElements(By.tagName("td")).get(iCellIndex);

            return new WbTable(cell, this.sPath + "DynamicWebTable.Row[" + iRowIndex + "].Cell[" + iCellIndex + "]");
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Method finds table cell by column name and column value.
     *
     * @param sColumnName  Column name.
     * @param sColumnValue Column value.
     * @return WebTable object
     */
    public WbTable findCellByValue(String sColumnName, String sColumnValue) {
        try {
            int iCellIndex = -1;
            ArrayList<WebElement> headerColumns = (ArrayList<WebElement>) tableHeaderRow().findElements(By.tagName("th"));

            for (int i = 0; i < headerColumns.size(); i++) {
                if (headerColumns.get(i).getText().equals(sColumnName)) {
                    iCellIndex = i;
                    break;
                }
            }

            if (iCellIndex == -1)
                return null;

            for (WebElement row : tableBodyRows()) {
                ArrayList<WebElement> bodyColumns = (ArrayList<WebElement>) row.findElements(By.tagName("td"));

                if (bodyColumns.get(iCellIndex).getText().equals(sColumnValue)) {
                    return new WbTable(bodyColumns.get(iCellIndex), this.sPath + "DynamicWebTable.Row[" + tableBodyRows().indexOf(row) +
                            "].Cell[" + iCellIndex + "]");
                }
            }

            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Method verifies cell values in header row.
     *
     * @param sColumnName Column name you want to verify.
     * @param bExists     Set to false if you want to check if column does not exist.
     * @return Success object
     */
    public Success verifyColumnExists(String sColumnName, boolean bExists) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            ArrayList<WebElement> tableRows = (ArrayList<WebElement>) tableHeaderRow().findElements(By.tagName("th"));

            if (bExists && !tableRows.stream().anyMatch(m -> m.getText().equals(sColumnName)))
                throw new RuntimeException(MessageFormat.format("Table is not empty. Number of rows counted: {0}", tableRows.size()));

            if (!bExists && tableRows.stream().anyMatch(m -> m.getText().equals(sColumnName)))
                throw new RuntimeException("Table is empty.");

        }, this, "");
    }

    /**
     * Method retrieves current table row count.
     *
     * @return rowCount, Success object
     */
    public Success getRowCount() {
        rowCount = 0;
        Success suc = new Success(this);
        try {
            this.exists();

            rowCount = tableBodyRows().size();

            return suc.finish(null);
        } catch (Exception ex) {
            return suc.finish(ex);
        }
    }

    /**
     * Method verifies table row count.
     *
     * @param iExpectedRowCount Expected number of rows in a table.
     * @param operation         Verifies that table has equal/less/more then rows specified.
     * @return Success object
     */
    public Success verifyRowCount(int iExpectedRowCount, Operations operation) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            int iRowCount = tableBodyRows().size();

            switch (operation) {
                case Equals:
                    if (iRowCount != iExpectedRowCount)
                        throw new RuntimeException(MessageFormat.format("Row count not as expected. <br>Expected: {0} <br> Current: {1}",
                                iExpectedRowCount, iRowCount));
                    break;
                case LessThan:
                    if (iRowCount >= iExpectedRowCount)
                        throw new RuntimeException(MessageFormat.format("Row count not as expected.<br>Expected to be less than: {0} <br> Current: {1}",
                                iExpectedRowCount, iRowCount));
                    break;
                case MoreThan:
                    if (iRowCount <= iExpectedRowCount)
                        throw new RuntimeException(MessageFormat.format("Row count not as expected.<br>Expected to be more than: {0} <br> Current: {1}",
                                iExpectedRowCount, iRowCount));
                    break;
            }
        }, this, "");
    }

    /**
     * Verifies if the table is empty or not.
     *
     * @param bEmpty Set to 'false' if you want to check if table is empty.
     * @return Success object
     */
    public Success verifyEmpty(boolean bEmpty) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            ArrayList<WebElement> tableRows = tableBodyRows();

            if (bEmpty) {
                if (tableRows.size() > 0)
                    throw new RuntimeException(MessageFormat.format("Table is not empty. Number of rows counted: {0}", tableRows.size()));
            }
            if (!bEmpty) {
                if (tableRows.size() == 0)
                    throw new RuntimeException("Table is empty.");
            }
        }, this, "");
    }

    /**
     * Clicks on header
     *
     * @param sHeader Name of header in a table.
     * @return Success object
     */
    public Success clickOnHeader(String sHeader) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            ArrayList<WebElement> allRows = (ArrayList<WebElement>) control().findElements(By.cssSelector("tr"));
            int iCellNumber = 0;
            boolean bfound = false;
            ArrayList<WebElement> allHeaders = (ArrayList<WebElement>) allRows.get(0).findElements(By.cssSelector("th"));
            for (int i = 0; i < allHeaders.size(); i++) {
                if (allHeaders.get(i).getText().toLowerCase().contains(sHeader.toLowerCase())) {
                    iCellNumber = i;
                    bfound = true;
                    break;
                }
            }
            if (bfound)
                allHeaders.get(iCellNumber).click();

            if (!bfound)
                throw new RuntimeException("Cannot find table header: " + sHeader);
        }, this, "");
    }

    /**
     * Verifies term in table exists.
     *
     * @param sText Text expected to be found.
     * @return Success object
     */
    public Success verifyTermExists(String sText) {
        return UIReferences.eval().evaluate(() ->
        {
            this.exists();

            ArrayList<WebElement> allCells = (ArrayList<WebElement>) control().findElements(By.tagName("tr"));
            boolean bFound = false;
            int i = 0;

            for (WebElement cell : allCells) {
                if (cell.getText().toLowerCase().contains(sText.toLowerCase())) {
                    bFound = true;
                    break;
                }
                i++;
            }
            if (!bFound)
                throw new RuntimeException("Table does not contain specified text. Searched for: " + sText);
        }, this, "");
    }
}
