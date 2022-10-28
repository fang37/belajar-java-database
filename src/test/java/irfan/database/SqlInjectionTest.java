package irfan.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {

    @Test
    void testSqlInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin'; #";
        String password = "salah";

        String sql = "SELECT * FROM admin WHERE username = '"+ username +
                "' AND PASSWORD = '"+ password +"'";
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            // success login
            System.out.println("Sukses login : " + resultSet.getString("username"));
        } else {
            // failed login
            System.out.println("Gagal login");
        }

        statement.close();
        connection.close();
    }
}
