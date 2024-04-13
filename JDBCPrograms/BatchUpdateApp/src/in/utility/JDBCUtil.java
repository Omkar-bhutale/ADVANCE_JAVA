package in.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    private JDBCUtil(){};
    public static Connection getJDBCConnection() throws IOException , SQLException {
        FileInputStream fis = new FileInputStream("src//in//properties//Application.properties");
        Properties properties = new Properties();
        properties.load(fis);
        Connection connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));
        fis.close();
        return connection;
    }
    public static void cleanUp(Connection connection, Statement statement, ResultSet resultSet) throws  SQLException{
        if(resultSet!=null) resultSet.close();
        if(statement!=null) statement.close();
        if (connection != null) connection.close();
    }

    public static void main(String[] args) {

    }
}
