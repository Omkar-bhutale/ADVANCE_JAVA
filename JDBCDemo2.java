package BasicsOfJava;

import java.sql.*;

public class JDBCDemo2 {
    public static void main(String[] args) {
        Connection connection =null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            //step 1 ==> load and ragistr thr driver

            Class.forName("com.mysql.cj.jdbc.Driver");
            //step2 ==>create conneton
            String url = "jdbc:mysql://localhost:3306/omkar";
            String username = "root";
            String password = "RSML";
             connection = DriverManager.getConnection(url,username,password);
            //step3==>create a statement and execut ethe query
            statement = connection.createStatement();
            String query = "Select * from student";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println("Rool no "+ resultSet.getInt(1));
                System.out.println("Name is " + resultSet.getString(2));
                System.out.println("Address is " + resultSet.getString(3));
            }
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                try {
                    resultSet.close();
                    statement.close();
                    connection.close();

                } catch (SQLException e) {
                   e.printStackTrace();
                }
            }
        }
    }
}
