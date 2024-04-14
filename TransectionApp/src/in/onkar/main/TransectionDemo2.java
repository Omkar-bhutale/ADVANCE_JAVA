package in.onkar.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import in.omkar.utility.JDBCUtil;

public class TransectionDemo2 {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getJDBCConnection();
			if (connection != null) {
				connection.setAutoCommit(false);
				statement = connection.createStatement();
			}
			Savepoint sp = null;
			if (statement != null) {
				statement.executeUpdate("update studant set gender = 'M' where sid =1 ");
				statement.executeUpdate("update studant set gender = 'M' where sid =2 ");
				statement.executeUpdate("update studant set gender = 'M' where sid =3 ");
			
				statement.executeUpdate("update studant set gender = 'M' where sid =4 ");
				 sp = connection.setSavepoint();
				statement.executeUpdate("update studant set gender = 'M' where sid =5 ");
			}
			System.out.println("opps mistake here");
			if (sp != null) {
				connection.rollback(sp);
			}
			if (connection != null) {
				connection.commit();
			}
			System.out.println("transection Done");
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JDBCUtil.cleanUp(connection, statement, resultSet);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
