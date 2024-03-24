package in.omkar.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.omkar.utility.JDBCUtil;

public class DateInsertionApp {
	public static void main(String[] args) {
		//recourses used
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		//variables used
		java.sql.Date sqlDateofBirth = null;
		java.sql.Date sqlDateofMerrige = null;
		String nameString = null;
		String dobString = null;
		String domString = null;
		try {
			connection = JDBCUtil.getJDBConnection();
			if(connection!=null) {
				String insertQuery = "insert into user (name,dob,dom) values(?,?,?)";
				pstmt = connection.prepareStatement(insertQuery);
			}
			if (pstmt != null) {
				scanner = new Scanner(System.in);
				if (scanner != null) {
					System.out.println(" enter the name of user :: ");
					nameString = scanner.next();
					
					System.out.println("Enter the Date of Birth for user (yyyy=MM-dd) ::" );
					dobString = scanner.next();
					
					System.out.println("Enter the Date of merrige for user (dd-MM-yyyy) :: ");
					domString = scanner.next();
				}
				
				//converting date from string type to date type
				if(dobString!=null) {
					sqlDateofBirth = java.sql.Date.valueOf(dobString);
					System.out.println("sql Date odf Birth is"+sqlDateofBirth);
				}
				if(domString!=null) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					  java.util.Date udate  = sdf.parse(domString);
					  sqlDateofMerrige = new Date(udate.getTime());
					  System.out.println("sql date of merrige is :: "+sqlDateofMerrige);
				}
				
				pstmt.setString(1, nameString);
				pstmt.setDate(2, sqlDateofBirth);
				pstmt.setDate(3,sqlDateofMerrige);
				
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected==1) {
					System.out.println("insertion done");
				}else {
					System.out.println("insertion can not be done");
				}
				
				
				
			}
			
		}catch (IOException | SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			    scanner.close();
			    try {
					JDBCUtil.cleanup(connection, pstmt, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
