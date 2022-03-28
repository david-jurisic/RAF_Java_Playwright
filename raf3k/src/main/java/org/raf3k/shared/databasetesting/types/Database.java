package org.raf3k.shared.databasetesting.types;

import db.DataTable;
import org.raf3k.apitesting.APIReferences;
import org.raf3k.shared.ControlObject;
import org.raf3k.shared.SharedReferences;
import org.raf3k.shared.logging.Success;

import java.sql.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Database extends ControlObject {

    private final String connectionString;
    private final String username;
    private final String password;
    public Connection connection = null;
    public RAFDataTable rafDataTable = null;

    public Database(String sConnectionString, String sUsername, String sPassword, String sAlias) {
        String sControl = this.getClass().toString();
        this.sControlType = sControl.substring(sControl.lastIndexOf((".")) + 1);
        this.sAlias = MessageFormat.format("({0})", sAlias);

        if (this.sControlType.toLowerCase().substring(this.sControlType.length() - 2).contains("ex"))
            this.sPath = SharedReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());
        else
            this.sPath = SharedReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());

        this.connectionString = sConnectionString;
        this.username = sUsername;
        this.password = sPassword;
    }

    /**
     * Method generates a table from a dictionary hash map.
     *
     * @param sNameHeader  Name of a table header.
     * @param sValueHeader Value of a table header.
     * @param dictionary   Map object
     * @return Table in HTML format.
     */
    public String generateTableFromDict(String sNameHeader, String sValueHeader, Map<String, Object> dictionary) {
        if (dictionary == null) return "";
        var trs = dictionary.entrySet()
                .stream()
                .map(x -> MessageFormat.format("<tr><td>{0}</td><td>{1}</td></tr>", x.getKey(), x.getValue().toString()))
                .collect(Collectors.toList());

        var tableContents = String.join("", trs);

        return "<table><tr><th>" + sNameHeader + "</th><th>" + sValueHeader + "</th></tr>" + tableContents + "</table>";
    }

    /**
     * Method executes a SQL query from a string.
     *
     * @param sQuery        SQL query.
     * @param statementType SQL Statement type. Use 'retrieval' for actions like 'SELECT' and
     *                      'manipulation' for actions that alter tables or add something to them like 'ALTER, CREATE, INSERT, etc'.
     * @return Success object, RAFDataTable.
     */
    public Success executeQuery(String sQuery, statementType statementType) {
        return SharedReferences.eval().evaluate(() -> {
            this.rafDataTable = null;

            String sMessageAddon = "";
            if (sQuery != null && !sQuery.isEmpty()) {
                sMessageAddon += "<h3>SQL Query:</h3> <br><p>" + sQuery + "</p><br>";
            }

            switch (statementType) {
                case retrieval:
                    try {
                        connection = DriverManager.getConnection(connectionString, username, password);
                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(sQuery);

                        this.rafDataTable = new RAFDataTable(new DataTable(rs), "rafDataTable");
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                case manipulation:
                    try {
                        connection = DriverManager.getConnection(connectionString, username, password);
                        Statement stmt = connection.createStatement();
                        Boolean rs = stmt.execute(sQuery);
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
            }
        }, this, "");
    }

    public enum statementType {
        manipulation,
        retrieval
    }
}
