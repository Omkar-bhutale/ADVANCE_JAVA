package in.omkar.main;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import in.omkar.utility.JDBCUtil;
/*DELIMITER //
CREATE PROCEDURE get_car_stats_by_year(
    IN year_filter int,
    OUT cars_number int,
    OUT min_value decimal(10, 2),
    OUT avg_value decimal(10, 2),
    OUT max_value decimal(10, 2)
)
BEGIN
    SELECT COUNT(*), MIN(value), AVG(value), MAX(value)
    INTO cars_number, min_value, avg_value, max_value
    FROM cars
    WHERE year = year_filter ORDER BY make, value DESC;
END //
DELIMITER ;*/
public class CallebleApp3 {
	public static void main(String[] args) {
		Connection connection =null;
		CallableStatement cstmt = null;
		Scanner scanner = null;
		String storedProcedure = "{Call get_car_stats_by_year(?,?,?,?,?)}";
		try {
			connection = JDBCUtil.getJDBCConnection();
			if (connection != null) 
				cstmt = connection.prepareCall(storedProcedure);
				
			if (cstmt != null) {
				scanner = new Scanner(System.in);
				int year =0;
				if (scanner != null) {
					System.out.print("Enter the year :: ");
					year = scanner.nextInt();
				   cstmt.setInt(1, year);
				}
			}
			
			if (cstmt != null) {
				cstmt.registerOutParameter(2, Types.INTEGER);
				cstmt.registerOutParameter(3, Types.DECIMAL);
				cstmt.registerOutParameter(4, Types.DECIMAL);
				cstmt.registerOutParameter(5, Types.DECIMAL);
			}
			
			if (cstmt != null) {
				cstmt.execute();
			}
			
			if (cstmt != null) {
				System.out.println("Total number of cars :: " + cstmt.getInt(2));
				System.out.println("Minimum value car :: " +cstmt.getDouble(3));
				System.out.println("Average car value :: " +cstmt.getDouble(3));
				System.out.println("Maximun value car :: "+cstmt.getDouble(5));
			}
			
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			scanner.close();
			try {
				JDBCUtil.cleanUp(connection, cstmt, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
