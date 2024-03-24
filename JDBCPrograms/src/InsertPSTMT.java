import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DataBaseUtil.JDBCHelper;


public class InsertPSTMT {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = new Scanner(System.in);
		try {
			connection = JDBCHelper.getJDBCConnection();
			String query = "insert into student (rollno,name)values(?,?)";
			pstmt = connection.prepareStatement(query);
			System.out.println("enter the roll number of studant ");
			int rollno = scanner.nextInt();
			pstmt.setInt(2, rollno);
			
			System.out.println("Enter the name ");
			String name = scanner.next();
			pstmt.setString(1, name);
			
			int rowAffected = pstmt.executeUpdate();
			System.out.println("Total roads affected"+rowAffected);
			
		} catch (SQLException | IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			scanner.close();
			try {
			JDBCHelper.cleanUp(connection, pstmt, resultSet);
			}catch (SQLException e) {
				e.printStackTrace();
				
			}
			System.out.println("end of program");
		}
	}
}
