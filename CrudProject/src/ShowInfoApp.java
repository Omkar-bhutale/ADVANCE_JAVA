import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import utilJDBC.JDBCHelper;
public class ShowInfoApp {
	public static void getStudentInfo() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
			try{
			connection = JDBCHelper.getJDBConnection();
			Scanner scan = new Scanner(System.in);

			String selectQuery = "select * from student1 where sid = ?";
			if(connection!=null)
			pstmt = connection.prepareStatement(selectQuery);
			
			System.out.println("Enter the sid of studant");
			pstmt.setInt(1, scan.nextInt());
			if(pstmt!=null)
			resultSet = pstmt.executeQuery();
			
			if(resultSet!=null && resultSet.next()){
			System.out.println("Roll Number of student is :: "+resultSet.getInt(1));
			System.out.println("Name of student is :: "+resultSet.getString(2));
			System.out.println("Age of student is:: "+resultSet.getInt(3));
			System.out.println("Address of student is :: "+resultSet.getString(4));
			}else {
				System.out.println("no recoed found");
				}
			scan.close();
		}catch(IOException | SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
				try {
					JDBCHelper.cleanup(connection, pstmt, resultSet);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	public static void main(String[] args) {
		getStudentInfo();
	}
}
