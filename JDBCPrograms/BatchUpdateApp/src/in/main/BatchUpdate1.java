package in.main;

import in.utility.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdate1 {

    public static void main(String[] args) {

        try(Connection connection = JDBCUtil.getJDBCConnection()){
            try(Statement statement = connection.createStatement()){
                statement.addBatch("insert into studant (name,marks) values ('omkar',120)");
                statement.addBatch("insert into studant (name,marks) values ('chetan',140)");
                statement.addBatch("insert into studant (name,marks) values ('raj',160)");
                statement.addBatch("insert into studant (name,marks) values ('shubham',120)");

                int[] updateCount = statement.executeBatch();

                for (int count : updateCount    ) {
                    System.out.println(count);
                }


            }
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }

    }

}
