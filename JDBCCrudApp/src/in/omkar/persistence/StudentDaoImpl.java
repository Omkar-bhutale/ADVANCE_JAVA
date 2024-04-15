package in.omkar.persistence;

import in.omkar.dto.Student;
import in.omkar.utility.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//persistance logic using JDBCAPI
public class StudentDaoImpl implements IStudentDao {
    PreparedStatement pstmt = null;
    Connection connection = null;
    @Override
    public String addStudent(String sname, Integer sage, String saddress) {
        String sqlInsertQuery = "insert into student (sname,sage,saddress) values (?,?,?)";
        try {
            if (connection == null) {
                connection = JDBCUtil.getJDBCConnection();
            }
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlInsertQuery);
            }
            if (pstmt != null) {
                pstmt.setString(1,sname);
                pstmt.setInt(2,sage);
                pstmt.setString(3,saddress);

                int rowAffected = pstmt.executeUpdate();
                if (rowAffected>0){
                    return "success";
                }
                return "failuer";

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student searchStudent(Integer sid) {
        Student student = null;
        String sqlSerchtQuery = "select * from student where sid = ?";
        try {
            if (connection == null) {
                connection = JDBCUtil.getJDBCConnection();
            }
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlSerchtQuery);
            }
            if (pstmt != null) {
                pstmt.setInt(1,sid);
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet != null) {
                    if (resultSet.next()){
                        student = new Student();
                        student.setSid(resultSet.getInt("sid"));
                        student.setSname(resultSet.getString("sname"));
                        student.setSaddress(resultSet.getString("saddress"));
                        student.setSage(resultSet.getInt("sage"));
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {

        String sqlUpdateQuery = "update student set sname = ?,sage = ?,saddress = ? where sid = ? ";
        try {
            if (connection == null) {
                connection = JDBCUtil.getJDBCConnection();
            }
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlUpdateQuery);
            }
            if (pstmt != null) {
                pstmt.setString(1,sname);
                pstmt.setInt(2,sage);
                pstmt.setString(3,saddress);
                pstmt.setInt(4,sid);

                int rowAffected =  pstmt.executeUpdate();
                 return "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "failed";
    }

    @Override
    public String deleteStudent(Integer sid) {
        String sqlDeleteQuery = "delete from student where sid = ? ";
        try {
            if (connection == null) {
                connection = JDBCUtil.getJDBCConnection();
            }
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlDeleteQuery);
            }
            if (pstmt != null) {

                pstmt.setInt(1,sid);

                int rowAffected =  pstmt.executeUpdate();
                if(rowAffected>0)
                 return "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
