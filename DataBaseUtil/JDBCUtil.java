package DataBaseUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    private JDBCUtil(){}
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
    }
    public static Connection getJDBCConnection() throws IOException, SQLException {
        FileInputStream fis = new FileInputStream("C:\\Users\\LENOVO\\IdeaProjects\\ADVANCE JAVA\\src\\DataBaseUtil\\Application.properties");
        Properties properties = new Properties();
        properties.load(fis);
        String url = properties.getProperty("url");
        String password = properties.getProperty("password");
        String user = properties.getProperty("user");
        fis.close();
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println("connection is created");
        return connection;
    }
    public static void cleanUp(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if(connection!=null)
            connection.close();
        if(statement!=null)
            statement.close();
        if(resultSet!=null)
            resultSet.close();

    }

}
