import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


import utilJDBC.JDBCHelper;

public class UpdateInfo {
	public static void updateStudant(){
		Connection connection = null;
		PreparedStatement pstmtShow = null;
		
		PreparedStatement pstmtUpdate = null;
		int id = -1;
		String name = null;
		int age = 0;
		String address = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCHelper.getJDBConnection();
			System.out.println("Enter the roll number to Update");
			Scanner scanner = new Scanner(System.in);
			int rollNum = scanner.nextInt();
			String selectQuery = "select * from student1 where sid = ?";
			if(connection!=null) {
				pstmtShow = connection.prepareStatement(selectQuery);
				
				if(pstmtShow!=null) {
					pstmtShow.setInt(1, rollNum);
					resultSet = pstmtShow.executeQuery();
					if(resultSet!=null && resultSet.next()){
						System.out.println("The default data is ");
						id = resultSet.getInt(1);
						name = resultSet.getString(2);
						age = resultSet.getInt(3);
						address = resultSet.getString(4);
					System.out.println("Roll Number of student is :: "+id);
					System.out.println("Name of student is :: "+name);
					System.out.println("Age of student is:: "+age);
					System.out.println("Address of student is :: "+address);
					//giving the optin to update
					System.out.println(" type 1 to update(YES) ");
					System.out.println("type 0 or any Key to not update(NO)");
					System.out.println("Do uyou want to update name");
					int input = scanner.nextInt();
					if(input == 1) {
						System.out.println("Enter nem to update");
						name = scanner.next();
					}
					System.out.println("Do uyou want to update age");
					input = scanner.nextInt();
					if(input == 1) {
						System.out.println("Enter age to update");
						age = scanner.nextInt();
					}
					System.out.println("Do you want to update address");
					input = scanner.nextInt();
					if(input == 1) {
						System.out.println("Enter address to update");
						address = scanner.next();
					}
					 String updateQuery = "update student1 set sname = ? ,sage = ?, saddress =? where sid = ?";
					 pstmtUpdate = connection.prepareStatement(updateQuery);
							 if(pstmtUpdate!=null) {
								 pstmtUpdate.setInt(4,id);
								 pstmtUpdate.setInt(2, age);
								 pstmtUpdate.setString(1, name);
								 pstmtUpdate.setString(3, address);
								 int n = pstmtUpdate.executeUpdate();
								 if(n==1) System.out.println("update done sussefully");
								 else System.out.println("Update can not done");

							 }else {
								System.out.println("Update can not done");
							}
						}
						else {
						System.out.println("Record dose not found");
						}
					}else {
						System.out.println("Statement object can not be created");
					}
				}else {
					System.out.println("connection can not be established");
				}
		}catch (IOException | SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCHelper.cleanup(connection, pstmtUpdate, resultSet);
				JDBCHelper.cleanup(null, pstmtShow, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
