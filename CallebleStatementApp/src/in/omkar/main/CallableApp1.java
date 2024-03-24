package in.omkar.main;

import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.omkar.utility.JDBCUtil;
/*
 *DELIMITER //
CREATE PROCEDURE get_all_cars()
BEGIN
    SELECT * FROM cars ORDER BY make, value DESC;
END //
DELIMITER ; 
 */
public class CallableApp1 {
	
	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet resultSet = null;
		String sqlStoredProcedure = "{CALL get_all_cars()}";
		try {
			connection = JDBCUtil.getJDBCConnection();
			if (connection != null) {
				cstmt = connection.prepareCall(sqlStoredProcedure);
			}
			if(cstmt!=null) {
				if (cstmt.execute()) {
					resultSet = cstmt.getResultSet();
				}else {
					int rowsAffected = cstmt.executeUpdate();
					System.out.println("no of rows affected" + rowsAffected);
				}
			}
			boolean flage = false;
			if (resultSet != null) {
				System.out.println("make\t\tmodal\t\tyear\t\tvalue");
				while (resultSet.next()) {
					System.out.println(resultSet.getString(1)+"\t\t"+resultSet.getString(2)+"\t\t"+resultSet.getInt(3)+"\t\t"+resultSet.getString(4));
					flage = true;
				}
				
			}
			if(!flage) System.out.println("no tecord found");
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.cleanUp(connection, cstmt, resultSet);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	}
}
