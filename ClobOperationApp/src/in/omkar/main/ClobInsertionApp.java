package in.omkar.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Callable;

import in.omkar.utility.JDBCUtil;

public class ClobInsertionApp {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		String sqlInsertQuery = null;
		String name = null;
		String pdfFileLoc = null;
		try {
			
			connection = JDBCUtil.getJDBCConnection();
			sqlInsertQuery = "insert into cities (name,history) values(?,?)";
			if (connection != null) 
				pstmt = connection.prepareStatement(sqlInsertQuery);
			if(pstmt!=null) {
				scanner = new Scanner(System.in); 
				if (scanner != null) {
					System.out.print("Enter the name of city ::");
					name = scanner.next();
					System.out.print("enter the pdf File loc ::");
					pdfFileLoc = scanner.next();
					
					pstmt.setString(1, name);
					pstmt.setCharacterStream(2, new FileReader(new File(pdfFileLoc)));
					
					pstmt.executeUpdate();
				}
			}
		}catch (IOException | SQLException e) {
			 e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			scanner.close();
			try {
				JDBCUtil.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
