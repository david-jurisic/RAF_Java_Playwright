package org.raf3k.shared.databasetesting.librarytypes;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibDataRow {
    private ArrayList<String> columnsNames;
    public LibDataTable DataTable;
    public final ArrayList<Object> Items = new ArrayList();

    public int ColumnCount() {
        return this.getColumnNames().size();
    }

    private ArrayList<String> getColumnNames() {
        return this.columnsNames == null ? this.DataTable.ColumnNames : this.columnsNames;
    }

    public LibDataRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
        int colcount = rsmd.getColumnCount();
        this.columnsNames = new ArrayList();

        for (int i = 1; i <= colcount; ++i) {
            this.Items.add(rs.getObject(i));
            this.columnsNames.add(rsmd.getColumnName(i));
        }
    }

    public LibDataRow(LibDataTable dt, ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
        this.DataTable = dt;
        int colcount = rsmd.getColumnCount();

        for (int i = 1; i <= colcount; ++i) {
            this.Items.add(rs.getObject(i));
        }
    }

    public String ColumnName(int index) {
        return (String) this.getColumnNames().get(index);
    }

    public Object Get(int index) {
        return this.Items.get(index);
    }

    public Object Get(String ColumnName) throws SQLException {
        int idx = this.getColumnNames().indexOf(ColumnName);
        if (idx > -1) {
            return this.Items.get(idx);
        } else {
            throw new SQLException("DataRow has no columns with that name");
        }
    }
}
