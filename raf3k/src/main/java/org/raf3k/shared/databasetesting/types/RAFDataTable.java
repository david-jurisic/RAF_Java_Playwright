package org.raf3k.shared.databasetesting.types;

import org.raf3k.shared.ControlObject;
import org.raf3k.shared.SharedReferences;
import org.raf3k.shared.databasetesting.librarytypes.LibDataRow;
import org.raf3k.shared.databasetesting.librarytypes.LibDataTable;
import org.raf3k.shared.enumerations.Operations;
import org.raf3k.shared.logging.Success;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class RAFDataTable extends ControlObject {

    LibDataTable dtTable;
    public int iRowCount;

    public RAFDataTable(LibDataTable table, String sAlias) {
        String sControl = this.getClass().toString();
        this.sControlType = sControl.substring(sControl.lastIndexOf((".")) + 1);
        this.sAlias = MessageFormat.format("({0})", sAlias);

        if (this.sControlType.toLowerCase().substring(this.sControlType.length() - 2).contains("ex"))
            this.sPath = SharedReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());
        else
            this.sPath = SharedReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());

        this.dtTable = table;
    }

    /**
     * Method verifies if table contains value.
     *
     * @param sColumnName Column name where value is.
     * @param sValue      Value to be verified.
     * @param bExists     'true' check that the value exists, 'false' checks that a value doesn't exist
     * @return Success object.
     */
    public Success verifyTableValue(String sColumnName, String sValue, Boolean bExists) {
        return SharedReferences.eval().evaluate(() -> {
            int iRowCount = dtTable.Rows.size();
            List<String> rows = new ArrayList<String>(iRowCount);

            for (LibDataRow row : dtTable.Rows) {
                try {
                    String columnName = row.Get(sColumnName).toString();
                    rows.add(columnName);
                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

            if (bExists && !rows.contains(sValue))
                throw new RuntimeException(MessageFormat.format("Value '{0}' does not exist in column '{1}'.", sValue, sColumnName));

            if (!bExists && rows.contains(sValue))
                throw new RuntimeException(MessageFormat.format("Value '{0}' exists in column '{1}'.", sValue, sColumnName));

        }, this, "");
    }

    /**
     * Method verifies if a table column exists.
     *
     * @param sColumnName Name of the column to be verified.
     * @param bExists     Set this to 'false' if you want to check that column does not exist.
     * @return Success object.
     */
    public Success verifyColumnExists(String sColumnName, Boolean bExists) {
        return SharedReferences.eval().evaluate(() -> {
            if (bExists && !dtTable.ColumnNames.contains(sColumnName))
                throw new RuntimeException(MessageFormat.format("Column '{0}' does not exist.", sColumnName));

            if (!bExists && dtTable.ColumnNames.contains(sColumnName))
                throw new RuntimeException(MessageFormat.format("Column '{0}' exists.", sColumnName));

        }, this, "");
    }

    /**
     * Method returns the number of table rows.
     *
     * @return Success object, iRowCount.
     */
    public Success getRowCount() {
        return SharedReferences.eval().evaluate(() -> {
            this.iRowCount = -1;

            try {
                iRowCount = dtTable.Rows.size();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

        }, this, "");
    }

    /**
     * Method returns the number of table rows.
     *
     * @param iExpectedRowCount Expected number of rows.
     * @param operation         Verifies that table has equal/less/more rows than specified
     * @return Success object.
     */
    public Success verifyRowCount(int iExpectedRowCount, Operations operation) {
        return SharedReferences.eval().evaluate(() -> {
            int iRowCount = dtTable.Rows.size();

            switch (operation) {
                case Equals:
                    if (iRowCount != iExpectedRowCount)
                        throw new RuntimeException(MessageFormat
                                .format("Row count not as expected. <br>Expected: {0} <br> Current: {1}",
                                        iExpectedRowCount, iRowCount));
                    break;
                case LessThan:
                    if (iRowCount >= iExpectedRowCount)
                        throw new RuntimeException(MessageFormat
                                .format("Row count not as expected. <br>Expected: {0} <br> Current: {1}",
                                        iExpectedRowCount, iRowCount));
                    break;
                case MoreThan:
                    if (iRowCount <= iExpectedRowCount)
                        throw new RuntimeException(MessageFormat
                                .format("Row count not as expected. <br>Expected: {0} <br> Current: {1}",
                                        iExpectedRowCount, iRowCount));
                    break;
            }
        }, this, "");
    }

    /**
     * Method verifies if a specified table is empty.
     *
     * @param bEmpty Set to 'false' if you want to check that the table is empty.
     * @return Success object.
     */
    public Success verifyEmpty(Boolean bEmpty) {
        return SharedReferences.eval().evaluate(() -> {
            int iRowCount = dtTable.Rows.size();
            List<String> rows = new ArrayList<String>(iRowCount);

            for (LibDataRow row : dtTable.Rows) {
                rows.add(row.toString());
            }

            if (bEmpty) {
                if (rows.size() > 0)
                    throw new RuntimeException(MessageFormat
                            .format("Table is not empty. Number of rows counted: {0}", rows.size()));
            }
            if (!bEmpty) {
                if (rows.size() == 0)
                    throw new RuntimeException("Table is empty.");
            }
        }, this, "");
    }

}
