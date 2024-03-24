import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import utilJDBC.JDBCHelper;

public class Delete {
	public static void delRow() {
		Scanner scanner = new Scanner(System.in);
		int id = -1;
		System.out.println("enter the id to delet");
		id = scanner.nextInt();
		Connection connection = null;
		PreparedStatement pstmt = null;
		int rowAffected = 0;
		
		try {
			connection = JDBCHelper.getJDBConnection();
			String delQuery = "delete from student1 where sid = ?";
			if (connection != null) {
				pstmt = connection.prepareStatement(delQuery);
				if (pstmt != null) {
				   rowAffected = 	pstmt.executeUpdate(); 
				}
			}
			if(rowAffected ==1) System.out.println("Deletion susses full");
			else {
				System.out.println("deletion failed or record dose not exist");
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCHelper.cleanup(connection, pstmt, null);
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
