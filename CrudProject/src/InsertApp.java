import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import utilJDBC.JDBCHelper;

public class InsertApp {
  public static void insert() {
	  Connection connection = null;
	  PreparedStatement pstmt = null;
	  PreparedStatement pstmt1 = null;
	  ResultSet resultSet = null;
	  try {
		connection = JDBCHelper.getJDBConnection();
		String insertQuery = "insert into student1 (sname,sage,saddress)values(?,?,?)";
		pstmt = connection.prepareStatement(insertQuery);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Your name ::");
		String sname = scan.next();
		System.out.println("Enter Your Age :: ");
		int sage = scan.nextInt();
		System.out.println("Enter Your Address ::");
		String saddress = scan.next();
		
		pstmt.setString(1, sname);
		pstmt.setInt(2, sage);
		pstmt.setString(3, saddress);
		int rowAffected = pstmt.executeUpdate();
		if(rowAffected==1) {
		System.out.println("Record inserted succes fully Your Info is");
		
		//getting and showing info for inserted data
		String selectQuery = "select * from student1 order by sid desc limit 1";
		pstmt1 = connection.prepareStatement(selectQuery);
		resultSet = pstmt1.executeQuery();
		resultSet.next();
	
		System.out.println("Roll Number of student is :: "+resultSet.getInt(1));
		System.out.println("Name of student is :: "+resultSet.getString(2));
		System.out.println("Age of student is:: "+resultSet.getInt(3));
		System.out.println("Address of student is :: "+resultSet.getString(4));
		}else {
			System.out.println("Record insertion faild");
			
			}
		scan.close();
		
	} catch (IOException | SQLException  e) {
		
		e.printStackTrace();
	}
	  catch (Exception e) {
		System.out.println("Input mistmath Exception");
	}finally {
		try {
			JDBCHelper.cleanup(connection, pstmt, resultSet);
			JDBCHelper.cleanup(null, pstmt1, null);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  }
 
}
