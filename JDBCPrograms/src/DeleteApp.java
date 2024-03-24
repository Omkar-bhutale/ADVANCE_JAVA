
import java.io.IOException;
import java.sql.*;
import DataBaseUtil.JDBCHelper;
public class DeleteApp {
   public static void main(String[] args) {
	   Connection connection = null;
	   Statement statement = null;
	   try {
		  connection = JDBCHelper.getJDBCConnection();
		  statement = connection.createStatement();
		  String nonSelectQuery = "delete from student where rollno =2";
		  int rowAffected = statement.executeUpdate(nonSelectQuery);
		  System.out.println("Rows Affected are ::"+rowAffected);
		  
	} catch (SQLException | IOException e) {
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
