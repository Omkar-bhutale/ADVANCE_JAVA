
import java.sql.*;
class App{

	public static void main(String[] args){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		// step1 => load and registr the driver
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver class loded succes fully..");
			//step2==> Establish a connecton with the database
			String url = "jdbc:mysql://127.0.0.1:3306/college";
			String userName = "root";
			String passWord = "Sanskruti";
			connection = DriverManager.getConnection(url,userName,passWord);

			System.out.println("the implemented class name is ="+ connection.getClass().getName());	
		//the implemented class name is =com.mysql.cj.jdbc.ConnectionImpl
		System.out.println("connecton established sussfully");

		//step3==>create statement obj and send the query
		String selectQuery = "select * from student";
		statement = connection.createStatement();
		System.out.println("The implemented class name is =" + statement.getClass().getName());
		resultSet = statement.executeQuery(selectQuery);
		System.out.println("The implemented class name is =" + resultSet.getClass().getName());
			System.out.print("\n\n");
		while(resultSet.next()){
		
		  System.out.print("RollNo "+resultSet.getInt(1) );
		  System.out.println("   Name "+resultSet.getString(2) );
		}
		
		}
		catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
		catch(SQLException se){
		   	se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
			if(connection!=null)
			{
				connection.close();
			}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
	
}
