package in.omkar.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.omkar.utility.JDBCUtil;

public class BlobInsertionApp {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		 
		String name = null;
		String location = null;
		try {
			connection = JDBCUtil.getJDBCConnection();
			String query = "insert into blobdemo (name,image) values (?,?)";
			if (connection != null) {
				pstmt = connection.prepareStatement(query);
				if (pstmt != null) {
					scanner = new Scanner(System.in);
					if (scanner != null) {
							System.out.println("Enter the name of user");
							name = scanner.next();
							System.out.println("Enter the path of image");
							location = scanner.next();
							
							FileInputStream fis =new FileInputStream(new File(location));
							
							
							pstmt.setString(1, name);
							pstmt.setBinaryStream(2, fis);
							
							int rowAffected = pstmt.executeUpdate();
							if(rowAffected>0) System.out.println("sucssisful");
							else System.out.println("unsussfull");
					}
					
				}
			}
		}catch (IOException | SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			JDBCUtil.cleanUp(connection, pstmt, null);
			scanner.close();
			}catch (SQLException e) {
				 e.printStackTrace();
			}
		}
	}
}
