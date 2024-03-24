package in.omkar.main;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.omkar.utility.JDBCUtil;
/*
 * DELIMITER //
CREATE PROCEDURE get_cars_by_year(
    IN year_filter int
)
BEGIN
    SELECT * FROM cars WHERE year = year_filter ORDER BY make, value DESC;
END //
DELIMITER ;
 */
public class CallebleApp2 {
	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		String storedProcedure = "{call get_cars_by_year(?)}";
		
		try {
			connection = JDBCUtil.getJDBCConnection();
			if (connection != null) {
				cstmt =  connection.prepareCall(storedProcedure);
			}
			if(cstmt!=null) {
				System.out.println("enter the year to disolay caes");
				scanner = new Scanner(System.in);
				int year =0;
				if (scanner != null) 
				 year = scanner.nextInt();
				
				cstmt.setInt(1, year);
				
				if(cstmt.execute()) {
					resultSet = cstmt.getResultSet();
				}else {
					System.out.println("Rows Affected ::"+cstmt.executeUpdate());
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
			scanner.close();
			try {
				JDBCUtil.cleanUp(connection, cstmt, resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
