package irfan.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverTest {

    @Test
    void testRegister() {
        try {
            Driver mysqlDrive = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDrive);
            // Require Java 11
        } catch (SQLException exception){
            Assertions.fail(exception);
        }
    }
}
