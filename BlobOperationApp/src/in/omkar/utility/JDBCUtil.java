package in.omkar.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class JDBCUtil {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	    public static Connection getJDBCConnection() throws IOException, SQLException {
		FileInputStream fis = new FileInputStream("src//in//omkar//properties//Application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),properties.getProperty("password"));
		return connection;
	}
	    public static void cleanUp(Connection connection,Statement statement,ResultSet resultSet) throws SQLException {
	    	if (connection != null) {
				connection.close();
			}
	    	if (statement != null) {
				statement.close();
			}
	    	if (resultSet != null) {
				resultSet.close();
			}
	    }
}
