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
            this.exists(true);
            ArrayList<WebElement> tableRows = (ArrayList<WebElement>) tableHeaderRow().findElements(By.tagName("th"));

            if (bExists && !tableRows.stream().anyMatch(m -> m.getText().equals(sColumnName)))
                throw new RuntimeException(MessageFormat.format("Table is not empty. Number of rows counted: {0}", tableRows.size()));

            if (!bExists && tableRows.stream().anyMatch(m -> m.getText().equals(sColumnName)))
                throw new RuntimeException("Table is empty.");

        }, this, "");
    }
}
