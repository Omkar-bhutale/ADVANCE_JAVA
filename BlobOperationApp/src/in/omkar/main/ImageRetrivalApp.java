package in.omkar.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import in.omkar.utility.JDBCUtil;

public class ImageRetrivalApp {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		int id = 0;
		String name = null;
		try {
			connection =JDBCUtil.getJDBCConnection();
			String query = "select * from blobdemo where id = ?";
			if (connection != null) {
				pstmt = connection.prepareStatement(query);
				
				if (pstmt != null) {
				scanner = new Scanner(System.in);
				if (scanner != null) {
					System.out.println("enter the is to get info");
					id= scanner.nextInt();
					pstmt.setInt(1,id);
					resultSet = pstmt.executeQuery();
					if (resultSet != null) {
						if(resultSet.next()) {
							name = resultSet.getString(2);
							InputStream in = resultSet.getBinaryStream(3);
							
							//File f = new File("C:\\Users\\Omkar Bhutale\\Downloads\\adhar.png");
//							OutputStream os = new FileOutputStream(f);
//							int i = in.read();
//							while(i!=-1) {
//								os.write(i);
//								i = in.read();
//							}
//							in.close();
//							os.close();
							saveImageToFile("C:\\Users\\Omkar Bhutale\\eclipse-workspace\\BlobOperationApp\\adhar.jpg", in);
							
						}else {
							System.out.println("dose not exist");
						}
					}
					
				}
				}
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			scanner.close();
			try {
				JDBCUtil.cleanUp(connection, pstmt, resultSet);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
		}
	}
	private static void saveImageToFile(String name, InputStream in) throws IOException {
        File file = new File(name);
        try (OutputStream os = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
