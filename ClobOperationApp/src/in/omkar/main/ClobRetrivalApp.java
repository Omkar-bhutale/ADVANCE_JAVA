package in.omkar.main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.omkar.utility.JDBCUtil;

public class ClobRetrivalApp {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		 
		int id = -1;
		
		try {
			connection = JDBCUtil.getJDBCConnection();
			String sqlSelectQuery = "Select * from cities where id = ?";
			pstmt = connection.prepareStatement(sqlSelectQuery);
			if (pstmt != null) {
				scanner = new Scanner(System.in);
				if (scanner != null) {
					System.out.println("enter the id of studant");
					id = scanner.nextInt();
					pstmt.setInt(1, id);
					resultSet = pstmt.executeQuery();
				}
				
			if(resultSet.next()) {
			    System.out.println("name of the city is :: "+resultSet.getString(2));
			    
			    File file = new File("temp.pdf");
			    Reader reder = resultSet.getCharacterStream(3);
			    FileWriter writer = new FileWriter(file);
			    int c = reder.read();
			    while(c>-1) {
			    	writer.write(c);
			    	c = reder.read();
			    }
			    writer.close();
			    System.out.println(file.getAbsolutePath());
			}
			
			}
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			scanner.close();
			try {
				JDBCUtil.cleanUp(connection, pstmt, resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
