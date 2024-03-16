package src;

import DataBaseUtil.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//Dynamic Query insert
public class JDBCDemo4 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);
        try {
             connection = JDBCUtil.getJDBCConnection();
            System.out.println("Enter the data to insert the record");
            System.out.println("Enter teh roll no");
             int RollNo = scanner.nextInt();
            System.out.println("Enter the Name");
             String name = scanner.next();
            System.out.println("Enter the address");
             String city = scanner.next();
             String query =  String.format("insert into student (RollNo,name,city) values(%d,'%s','%s')",RollNo,name,city);
            statement = connection.createStatement();
            statement.executeUpdate(query);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCUtil.cleanUp(connection,statement,resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
