import org.junit.jupiter.api.Test;
import org.raf3k.apitesting.basetypes.APITestCase;
import org.raf3k.guittesting.webtesting.basetypes.UITestCase;
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

        newStep(1, "Execute a database query from string");
        success(database.executeQuery(sQuery2, Database.statementType.retrieval));
        RAFDataTable rafDataTable = database.rafDataTable;

        success(rafDataTable.verifyColumnExists("id", true));
        success(rafDataTable.getRowCount());
        int iRowCount = rafDataTable.iRowCount;

        success(rafDataTable.verifyRowCount(iRowCount, Operations.Equals));
        success(rafDataTable.verifyEmpty(true));
    }
}