package in.omkar.daofactory;

import in.omkar.persistence.IStudentDao;
import in.omkar.persistence.StudentDaoImpl;

public class StudentDaoFactory {
    private StudentDaoFactory(){}
    private static IStudentDao studentDao = null;
    public static IStudentDao getStudentDao(){
        if (studentDao == null) {
            studentDao = new StudentDaoImpl();
        }
        return studentDao;
    }
}
