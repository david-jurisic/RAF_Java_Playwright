package org.raf3k.shared.databasetesting.types;

import db.DataRow;
import db.DataTable;
import org.raf3k.shared.ControlObject;
import org.raf3k.shared.SharedReferences;
import org.raf3k.shared.logging.Success;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class RAFDataTable extends ControlObject {

    DataTable dtTable;

    public RAFDataTable(DataTable table, String sAlias) {
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
     * @param sValue Value to be verified.
     * @param bExists 'true' check that the value exists, 'false' checks that a value doesn't exist
     * @return Success object.
     */
    public Success VerifyTableValue(String sColumnName, String sValue, Boolean bExists)
    {
        return SharedReferences.eval().evaluate(() -> {
            int iRowCount = dtTable.Rows.size();
            List<String> rows = new ArrayList<String>(iRowCount);

            for (DataRow row : dtTable.Rows) {
                try {
                    String columnName = row.Get(sColumnName).toString();
                    rows.add(columnName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (bExists && !rows.contains(sValue))
                throw new RuntimeException(MessageFormat.format("Value '{0}' does not exist in column '{1}'.", sValue, sColumnName));

            if (!bExists && rows.contains(sValue))
                throw new RuntimeException(MessageFormat.format("Value '{0}' exists in column '{1}'.", sValue, sColumnName));

        }, this, "");
    }
}
