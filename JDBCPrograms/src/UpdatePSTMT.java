import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import DataBaseUtil.JDBCHelper;

public class UpdatePSTMT {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scan = new Scanner(System.in);
		try {
			connection = JDBCHelper.getJDBCConnection();
			String query = "update student set name = ? where rollno =?";
			pstmt = connection.prepareStatement(query);
			System.out.println("Enter the roll number to update");
			pstmt.setInt(2, scan.nextInt());
			System.out.println("enter the Name ");
			pstmt.setString(1,scan.next());
			int rowAffected =  pstmt.executeUpdate();
			System.out.println("rows affected are ::"+rowAffected);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			scan.close();
			try {
				JDBCHelper.cleanUp(connection, pstmt, null);
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
