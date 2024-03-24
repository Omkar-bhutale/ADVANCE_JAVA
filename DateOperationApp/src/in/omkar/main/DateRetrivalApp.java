package in.omkar.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.omkar.utility.JDBCUtil;

public class DateRetrivalApp {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		try {
			connection = JDBCUtil.getJDBConnection();
			String sqlSelectQuery = "select * from user where id = ?";
			if(connection!=null) {
				pstmt = connection.prepareStatement(sqlSelectQuery);
			}
			if(pstmt!=null) {
				scanner = new Scanner(System.in);
				int id = -1;
				if (scanner != null) {
					System.out.println("enter id to search");
					id = scanner.nextInt();
				}
				pstmt.setInt(1, id);
				resultSet = pstmt.executeQuery();
			}
			if(resultSet!=null) {
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					java.sql.Date dob = resultSet.getDate(3);
					java.sql.Date dom = resultSet.getDate(4);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String dobString = sdf.format(dob);
					String domString = sdf.format(dom);
					
					System.out.println("uid\tName\tdob\t\tdom");
					System.out.println(id+"\t"+name+"\t"+dobString+"\t"+domString);
					
				}else {
					System.out.println("record dose not exist");
				}
			}
		}catch (IOException | SQLException  e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			scanner.close();
			try {
				JDBCUtil.cleanup(connection, pstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
