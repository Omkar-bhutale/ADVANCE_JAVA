package in.onkar.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.ResultSet;


import in.omkar.utility.JDBCUtil;

public class TransectionDemo1 {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			connection = JDBCUtil.getJDBCConnection();
			if(connection!=null) {
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			}
			
			
			if (statement != null) {
				statement.execute("update bankholders set balance = balance-500 where id = 1");
			}
			if (statement != null) {
				statement.execute("update bankholders set balance = balance+500 where id = 2");
			}
			System.out.println("do you want to do transection");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
			if(input.equalsIgnoreCase("yes")) {
				connection.commit();
			}else {
				connection.rollback();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.cleanUp(connection, statement, resultset);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
}
