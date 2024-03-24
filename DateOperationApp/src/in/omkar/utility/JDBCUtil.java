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
	//private JDBCUtil() {}
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getJDBConnection() throws IOException,SQLException {
		FileInputStream fis =new FileInputStream("C:\\Users\\Omkar Bhutale\\eclipse-workspace\\DateOperationApp\\src\\in\\omkar\\properties\\Application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		String url=properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Connection connection = DriverManager.getConnection(url,user,password);
		System.out.println("con");
		return connection;
	}
	public static void cleanup(Connection connection,Statement statement,ResultSet resultSet) throws SQLException {
		if(connection!=null) {
			connection.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			statement.close();
			
		}
	
		
	}
}
