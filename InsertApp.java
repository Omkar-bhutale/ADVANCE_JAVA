import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DataBaseUtil.JDBCHelper;

public class InsertApp {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = JDBCHelper.getJDBCConnection();
			statement = connection.createStatement();
			String nonselectQuery = "insert into student (rollno,name)values(13,'omkar')";
			int rowAffected = statement.executeUpdate(nonselectQuery);
			  System.out.println("Rows Affected are ::"+rowAffected);
		} catch (SQLException | IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("unknown Exception");
			e.printStackTrace();
		}
		finally {
			try {
				JDBCHelper.cleanUp(connection, statement, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
