package irfan.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatementTest {
    @Test
    void testPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String username = "admin'; #";
        String password = "admin";

        String sql = "SELECT * FROM admin WHERE username = ? AND PASSWORD = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            // success login
            System.out.println("Sukses login : " + resultSet.getString("username"));
        } else {
            // failed login
            System.out.println("Gagal login");
        }

        preparedStatement.close();
        connection.close();
    }
}
