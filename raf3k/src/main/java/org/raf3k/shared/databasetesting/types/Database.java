package org.raf3k.shared.databasetesting.types;

import org.raf3k.apitesting.APIReferences;
import org.raf3k.shared.ControlObject;
import org.raf3k.shared.SharedReferences;
import org.raf3k.shared.logging.Success;

import java.sql.Connection;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Database extends ControlObject {

    private final String connectionString;
    public Connection connection = null;

    public Database(String sConnectionString, String sAlias) {
        String sControl = this.getClass().toString();
        this.sControlType = sControl.substring(sControl.lastIndexOf((".")) + 1);
        this.sAlias = MessageFormat.format("({0})", sAlias);

        if (this.sControlType.toLowerCase().substring(this.sControlType.length() - 2).contains("ex"))
            this.sPath = SharedReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());
        else
            this.sPath = SharedReferences.hlpr().cleanupPath(Thread.currentThread().getStackTrace()[2].getClassName());

        this.connectionString = sConnectionString;
    }

    /**
     * Method generates a table from a dictionary hash map.
     *
     * @param sNameHeader Name of a table header.
     * @param sValueHeader Value of a table header.
     * @param dictionary Map object
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

    public Success executeQuery(String sQuery) {
        Success suc = new Success(this);

        try {

        } catch (Exception e) {
            return suc.finish(e);
        }
        return suc;
    }
}
