package DataBaseUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCHelper {
	private JDBCHelper() {};
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	public static Connection getJDBCConnection() throws IOException , SQLException{
		FileInputStream fis = new FileInputStream("src\\DataBaseUtil\\Application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		Connection connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));
		System.out.println("Connecttion is established");
		return connection;
	}
	public static void cleanUp(Connection connection,Statement statement,ResultSet resultSet) throws SQLException {
		if(connection!=null) {
			connection.close();
		}
		if(statement != null) {
			statement.close();
		}
		if(resultSet!=null) {
			resultSet.close();
			
		}
		
		
	}
	
}
