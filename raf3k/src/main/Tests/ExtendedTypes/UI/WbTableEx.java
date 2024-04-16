package ExtendedTypes.UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raf3k.guittesting.webtesting.basetypes.WebControlBase;
import org.raf3k.guittesting.webtesting.types.WbTable;
import org.raf3k.shared.DebugLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WbTableEx extends WbTable {
    By searchHeaderBy = By.tagName("thead");
    By searchTableBodyBy = By.tagName("tbody");
    By searchHeaderRowsBy = By.tagName("tr");
    By searchTableRowsBy = By.tagName("tr");

    public WbTableEx(By searchBy, String alias) {
        super(searchBy, alias);
    }

    public WbTableEx(WebElement webElement, String Alias) {
        super(webElement, Alias);
    }

    public WbTableEx(By searchBy, WebControlBase parent, String alias) {
        super(searchBy, parent, alias);
    }

    public WbTableEx(By searchBy, By searchHeaderBy, By searchTableBodyBy, By searchHeaderRowsBy, By searchTableRowsBy, String alias) {
        super(searchBy, alias);
        this.searchHeaderBy = searchHeaderBy;
        this.searchHeaderRowsBy = searchHeaderRowsBy;
        this.searchTableBodyBy = searchTableBodyBy;
        this.searchTableRowsBy = searchTableRowsBy;
    }

    public WbTableEx() {
    }

    public WebElement tableHeader() {
        try {
            return control().findElement(searchHeaderBy);
        } catch (Exception ex) {
            DebugLog.add("Table header not found.", 1);
            return null;
        }
    }

    public WebElement tableBody() {
        try {
            return control().findElement(searchTableBodyBy);
        } catch (Exception ex) {
            DebugLog.add("Table body not found.", 1);
            return null;
        }
    }

    public WebElement tableHeaderRow() {
        try {
            return tableHeader().findElement(searchHeaderRowsBy);
        } catch (Exception ex) {
            DebugLog.add("Table header row not found.", 1);
            return null;
        }
    }

    public ArrayList<WebElement> tableBodyRows() {
        try {
            return (ArrayList<WebElement>) tableBody().findElements(searchTableRowsBy);
        } catch (Exception ex) {
            DebugLog.add("Table body rows not found.", 1);
            return null;
        }
    }

    public WbTable findRowInTableByCellValue(String sCellValue) {
        try {
            exists();
            List<WebElement> allRows = control().findElements(By.cssSelector("table.r6table tbody.ng-scope tr"));
            for (WebElement row : allRows) {
                if (row.getText().contains(sCellValue)) {
                    return new WbTableEx(row, this.sPath);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public WbTable findButtonByCellValue(String sCellValue) {
        try {
            exists();
            List<WebElement> allRows = control().findElements(By.cssSelector("table.r6table tr"));
            for (WebElement row : allRows) {
                if (row.getText().contains(sCellValue)) {
                    return new WbTableEx(row.findElement(By.cssSelector("table.r6table button.r6btn")), this.sPath);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public WbTable findCellByIndexInProcessDetails(int iRowIndex, int iColumnIndex) {
        try {
            --iRowIndex;
            --iColumnIndex;
            List<WebElement> allRows = control().findElements(By.cssSelector("table.r6table tbody.ng-scope tr"));
            if (iRowIndex >= 0 && iRowIndex < allRows.size()) {
                WebElement row = allRows.get(iRowIndex);

                List<WebElement> cells = row.findElements(By.tagName("span"));

                if (iColumnIndex >= 0 && iColumnIndex < cells.size()) {
                    WebElement cell = cells.get(iColumnIndex);
                    return new WbTable(cell, this.sPath + "DynamicWebTable.Row[" + iRowIndex + "].Cell[" + iColumnIndex + "]");
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception var4) {
            return null;
        }
    }

    public WbTable findButtonByActionValue(String sCellValue) {
        try {
            exists();
            List<WebElement> allRows = control().findElements(By.xpath("//table[@class='r6table--fixed r6table r6table--provisioning--orders']/tbody"));
            for (WebElement row : allRows) {
                if (row.getText().contains(sCellValue)) {
                    return new WbTableEx(row.findElement(By.xpath("//div[@r6-e2e-locator='r6-provisioning-order-history-info-button']")), this.sPath);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public WbTable findCellByContentValue(String sColumnName, String sColumnValue) {
        try {
            int iCellIndex = -1;
            ArrayList<WebElement> headerColumns = (ArrayList)this.tableHeaderRow().findElements(By.tagName("th"));

            for(int i = 0; i < headerColumns.size(); ++i) {
                if (((WebElement)headerColumns.get(i)).getText().equals(sColumnName)) {
                    iCellIndex = i;
                    break;
                }
            }

            if (iCellIndex == -1) {
                return null;
            } else {
                Iterator var9 = this.tableBodyRows().iterator();

                WebElement row;
                ArrayList bodyColumns;
                do {
                    if (!var9.hasNext()) {
                        return null;
                    }

                    row = (WebElement)var9.next();
                    bodyColumns = (ArrayList)row.findElements(By.tagName("td"));
                } while(!((WebElement)bodyColumns.get(iCellIndex)).getText().contains(sColumnValue));

                WebElement var10002 = (WebElement)bodyColumns.get(iCellIndex);
                String var10003 = this.sPath;
                return new WbTable(var10002, var10003 + "DynamicWebTable.Row[" + this.tableBodyRows().indexOf(row) + "].Cell[" + iCellIndex + "]");
            }
        } catch (Exception var8) {
            return null;
        }
    }
}
