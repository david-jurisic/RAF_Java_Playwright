package org.raf3k.shared.databasetesting.types;

import org.raf3k.shared.ControlObject;
import org.raf3k.shared.SharedReferences;
import org.raf3k.shared.SharedVariables;
import org.raf3k.shared.databasetesting.librarytypes.LibDataTable;
import org.raf3k.shared.logging.Success;
import java.io.File;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Database extends ControlObject {

    private final String connectionString;
    private final String username;
    private final String password;
    public Connection connection = null;
    public RAFDataTable rafDataTable = null;
    public int iRowsAffected = 0;

    /**
     * A constructor for the Database class.
     *
     * @param sConnectionString connection string for the database, written in JDBC format
     *                          (e.g. 'jdbc:mysql://address:port/database').
     *                          For more info on connection string formats,
     *                          read the JDBC documentation.
     *                          (https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html)
     * @param sUsername         Database username.
     * @param sPassword         Database password.
     * @param sAlias            Database alias.
     */
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
    private String generateTableFromDict(String sNameHeader, String sValueHeader, Map<String, Object> dictionary) {
        if (dictionary == null) return "";
        var trs = dictionary.entrySet()
                .stream()
                .map(x -> MessageFormat.format("<tr><td>{0}</td><td>{1}</td></tr>", x.getKey(), x.getValue().toString()))
                .collect(Collectors.toList());

        var tableContents = String.join("", trs);

        return "<table><tr><th>" + sNameHeader + "</th><th>" + sValueHeader + "</th></tr>" + tableContents + "</table>";
    }

    /**
     * Method executes an SQL query from a string.
     *
     * @param sQuery        SQL query.
     * @param statementType SQL Statement type. Use 'retrieval' for actions like 'SELECT' and
     *                      'manipulation' for actions that alter tables or add something to them like 'ALTER, CREATE, INSERT, etc'.
     * @return Success object, RAFDataTable, iRowsAffected.
     */
    public Success executeQuery(String sQuery, statementType statementType) {
        Success suc = new Success(this);
        this.rafDataTable = null;
        this.iRowsAffected = -1;

        try {
            String sMessageAddon = "";
            if (sQuery != null && !sQuery.isEmpty()) {
                sMessageAddon += "<h3>SQL Query:</h3> <br><p>" + sQuery + "</p><br>";
            }
            suc.sMessageAddon = sMessageAddon;

            switch (statementType) {
                case retrieval:
                    try {
                        connection = DriverManager.getConnection(connectionString, username, password);
                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(sQuery);

                        this.rafDataTable = new RAFDataTable(new LibDataTable(rs), "rafDataTable");
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                case manipulation:
                    try {
                        connection = DriverManager.getConnection(connectionString, username, password);
                        Statement stmt = connection.createStatement();
                        Boolean rs = stmt.execute(sQuery);
                        iRowsAffected = stmt.getUpdateCount();
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
            }
            return suc.finish(null);
        } catch (Exception ex) {
            return suc.finish(ex);
        }
    }

    /**
     * Method executes an SQL query from a file.
     *
     * @param sSqlFileName  Name of an SQL script file.
     * @param statementType SQL Statement type. Use 'retrieval' for actions like 'SELECT' and
     *                      'manipulation' for actions that alter tables or add something to them like 'ALTER, CREATE, INSERT, etc'.
     * @param dicParameters SQL script parameters.
     * @return Success object, RAFDataTable.
     */
    public Success executeFromFile(String sSqlFileName, statementType statementType, java.util.Map<String, Object> dicParameters) {
        Success suc = new Success(this);
        this.rafDataTable = null;
        try {
            String sqlScriptFilesPath = SharedVariables.configuration.getProperty("sqlScriptFilesPath");
            String sFilePath = sqlScriptFilesPath + "\\" + sSqlFileName;
            String sQuery = new Scanner(new File(sFilePath)).useDelimiter("\\Z").next();

            String sMessageAddon = "";
            if (sSqlFileName != null && !sSqlFileName.isEmpty())
                sMessageAddon += "<h3>SQL File Name:</h3> <br><p>" + sSqlFileName + "</p><br>";

            sMessageAddon += "<h3>SQL File Path:</h3> <br><p>" + sFilePath + "</p><br>";
            sMessageAddon += "<h3>SQL Script:</h3> <br><p>" + sQuery + "</p><br>";

            if (dicParameters != null)
                sMessageAddon += "<h3> Stored Procedure Parameters:</h3> <br>" +
                        generateTableFromDict("Header", "Value", dicParameters);

            suc.sMessageAddon = sMessageAddon;

            switch (statementType) {
                case retrieval:
                    try {
                        connection = DriverManager.getConnection(connectionString, username, password);
                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(sQuery);

                        this.rafDataTable = new RAFDataTable(new LibDataTable(rs), "rafDataTable");
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                case manipulation:
                    try {
                        connection = DriverManager.getConnection(connectionString, username, password);
                        Statement stmt = connection.createStatement();
                        Boolean rs = stmt.execute(sQuery);
                        iRowsAffected = stmt.getUpdateCount();
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
            }
            return suc.finish(null);
        } catch (Exception ex) {
            return suc.finish(ex);
        }
    }

    public enum statementType {
        manipulation,
        retrieval
    }
}
