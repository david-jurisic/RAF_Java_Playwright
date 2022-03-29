package org.raf3k.shared.databasetesting.librarytypes;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibDataTable {
    public ArrayList<String> ColumnNames;
    public ArrayList<LibDataRow> Rows = new ArrayList();

    public LibDataTable(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int colcount = rsmd.getColumnCount();
        this.ColumnNames = new ArrayList();

        for (int i = 1; i <= colcount; ++i) {
            this.ColumnNames.add(rsmd.getColumnName(i));
        }

        while (rs.next()) {
            this.Rows.add(new LibDataRow(this, rs, rsmd));
        }
    }

    public void Append(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();

        while (rs.next()) {
            this.Rows.add(new LibDataRow(this, rs, rsmd));
        }
    }

    public void Clear() {
        this.Rows.clear();
    }
}
