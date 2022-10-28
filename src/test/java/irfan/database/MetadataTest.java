package irfan.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetadataTest {
    @Test
    void testDatabaseMetaData() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());

        ResultSet resultSet = databaseMetaData.getTables("belajar_java_database", null, null, null);
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println(tableName);
        }

        connection.close();
    }

    @Test
    void testParameterMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        System.out.println(parameterMetaData.getParameterCount());
//        System.out.println(parameterMetaData.getParameterType(1)); // not support for mysql
//        System.out.println(parameterMetaData.getParameterType(2));

        preparedStatement.close();
        connection.close();

    }

    @Test
    void testResultSetMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "SELECT * FROM sample_time";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData metaData = resultSet.getMetaData();
        System.out.println(metaData.getColumnCount());

        for (int i = 1; i <= metaData.getColumnCount(); i++) {

            System.out.println("Name : " + metaData.getColumnName(i));
            System.out.println("Type : " + metaData.getColumnType(i));
            System.out.println("Type Name : " + metaData.getColumnTypeName(i));

        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
