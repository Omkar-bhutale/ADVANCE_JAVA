


import DataBaseUtil.JDBCUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBDCemo3 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getJDBCConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO student (RollNo, name, city) VALUES (2, 'Sanskruti', 'Bhuttan hipparga');";
            int n = statement.executeUpdate(query);
            System.out.println(n + "rows updated");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.cleanUp(connection, statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
