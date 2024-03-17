import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import DataBaseUtil.JDBCHelper;

public class InsertApp {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		Scanner scanner = new Scanner(System.in);
		try {
			connection = JDBCHelper.getJDBCConnection();
			statement = connection.createStatement();
			//taking an input from user 
			System.out.println("Enter theroll no student");
		    int rollno =scanner.nextInt();
		    System.out.println("Enter the name of studant");
		    String name = scanner.next();
		    name = "'"+name+"'";
			String nonselectQuery = "insert into student (rollno,name)values("+rollno+","+name+")";
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
