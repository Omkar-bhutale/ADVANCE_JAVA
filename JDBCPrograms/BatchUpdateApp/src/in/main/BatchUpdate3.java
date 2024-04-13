package in.main;

import in.utility.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchUpdate3 {
    public static void main(String[] args) {

        try(Connection connection = JDBCUtil.getJDBCConnection()){
            Scanner sc = new Scanner(System.in);
            String sql = "insert into studant (name,marks) values (?,?)";
            connection.setAutoCommit(false);
            try (PreparedStatement pstmt = connection.prepareStatement(sql) ){

                System.out.println("Enter the number of records do you wanrt to insert");
                int n = sc.nextInt();
                for (int i=0;i<n;i++){
                    System.out.println("enter the name of studant ="+i);
                    pstmt.setString(1,sc.next());
                    System.out.println("enter the mark for studant ="+i);
                    pstmt.setInt(2,sc.nextInt());
                    pstmt.addBatch();
                }

                System.out.println("do you want to reflect these changes to databse type [Yse/no]");
                String input = sc.next();
                if(input.equalsIgnoreCase("yse")) {
                    pstmt.executeBatch();
                    
                    connection.commit();
                }
                sc.close();
            }

        }catch (IOException | SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
