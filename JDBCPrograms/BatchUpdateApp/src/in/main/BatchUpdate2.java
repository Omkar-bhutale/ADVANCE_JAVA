package in.main;

import com.mysql.cj.jdbc.JdbcConnection;
import in.utility.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdate2 {
    public static void main(String[] args) {
        try(Connection connection = JDBCUtil.getJDBCConnection()){
                connection.setAutoCommit(false);
             try(Statement statement = connection.createStatement()) {
                 statement.addBatch("insert into studant (name,marks) values ('pihu',120)");
                 statement.addBatch("insert into studant (name,marks) values ('sward',140)");
                 statement.addBatch("insert into studant (name,marks) values ('asha',160)");
                 statement.addBatch("insert into studant (name,marks) values ('rani',120)");

                 int[] updatedCount = statement.executeBatch();
                 for (int count : updatedCount) {
                     System.out.println(count);
                 }
                 System.out.println("do you want to reflect these changer");
                 System.in.read();
                 connection.commit();

             }
        }catch (IOException | SQLException ise){
           ise.getMessage();
        }
        catch (Exception e){
            e.getMessage();
        }

    }
}
