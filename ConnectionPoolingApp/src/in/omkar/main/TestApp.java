package in.omkar.main;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestApp {
    public static void main(String[] args) throws SQLException {
        //create an object of class whic implements javax.sql.DataSource
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setURL("jdbc:mysql:///omkar");
        dataSource.setUser("root");
        dataSource.setPassword("password");

        //getting connection object from connection pool
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet =  statement.executeQuery("select * from student");
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));

            System.out.println(resultSet.getInt(3));
            System.out.println(resultSet.getString(4));
        }
        //sending connection object to comnnection pool
        connection.close();
    }
}
