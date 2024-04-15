package in.omkar.main.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestApp {
    public static void main(String[] args) throws Exception {
        String configFile = "src/in/omkar/main/db/db.properties";
        HikariConfig  config = new HikariConfig(configFile);
        try (HikariDataSource dataSource = new HikariDataSource(config)){
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
}
