import org.junit.jupiter.api.Test;
import org.raf3k.apitesting.basetypes.APITestCase;
import org.raf3k.shared.databasetesting.types.Database;
import org.raf3k.shared.databasetesting.types.RAFDataTable;
import org.raf3k.shared.enumerations.Operations;

public class DatabaseTestRunner extends APITestCase {
    public DatabaseTestRunner() {
        super("Database test case", "TC002", "David Jurišić");
    }

    public static Database database = new Database("jdbc:mysql://localhost:3306/test", "root", "mysql", "test");

    @Test
    void executeQueryFromString() {

        String sQuery = "CREATE TABLE MyGuests ( id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, firstname VARCHAR(30) NOT NULL, lastname VARCHAR(30) NOT NULL, email VARCHAR(50) )";
        String sQuery2 = "SELECT * FROM MyGuests";
        String sQuery3 = "INSERT INTO MyGuests (firstname, lastname, email) VALUES ('David', 'Jurišić', 'david.jurisic@roxoft.hr')";
        String sQuery4 = "DELETE FROM MyGuests WHERE email='david.jurisic@roxoft.hr'";

        newStep(1, "Execute an INSERT SQL query from a string");
        success(database.executeQuery(sQuery3, Database.statementType.manipulation));
        int iRowsAffected = database.iRowsAffected;
        System.out.println(iRowsAffected);

        newStep(2, "Execute a SELECT SQL query from a string");
        success(database.executeQuery(sQuery2, Database.statementType.retrieval));
        RAFDataTable rafDataTable = database.rafDataTable;

        success(rafDataTable.verifyColumnExists("id", true));
        success(rafDataTable.getRowCount());
        int iRowCount = rafDataTable.iRowCount;
        System.out.println(iRowCount);
        success(rafDataTable.verifyRowCount(iRowCount, Operations.Equals));

        newStep(3, "Execute a DELETE SQL query from a string");
        success(database.executeQuery(sQuery4, Database.statementType.manipulation));
        iRowsAffected = database.iRowsAffected;
        System.out.println(iRowsAffected);

        newStep(4, "Execute a SELECT SQL query and verify that the table is empty");
        success(database.executeQuery(sQuery2, Database.statementType.retrieval));
        rafDataTable = database.rafDataTable;
        success(rafDataTable.verifyEmpty(true));

        newStep(5, "Execute an INSERT SQL query from a file");
        success(database.executeFromFile("insertScript.sql", Database.statementType.manipulation, null));
        iRowsAffected = database.iRowsAffected;
        System.out.println(iRowsAffected);

        newStep(6, "Execute a DELETE SQL query from a file");
        success(database.executeFromFile("deleteScript.sql", Database.statementType.manipulation, null));
        iRowsAffected = database.iRowsAffected;
        System.out.println(iRowsAffected);

        newStep(7, "Execute a SELECT SQL query from a file and verify that the table is empty");
        success(database.executeFromFile("selectScript.sql", Database.statementType.retrieval, null));
        rafDataTable = database.rafDataTable;
        success(rafDataTable.verifyEmpty(true));
    }
}