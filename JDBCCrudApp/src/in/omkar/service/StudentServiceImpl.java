package in.omkar.service;

import in.omkar.daofactory.StudentDaoFactory;
import in.omkar.dto.Student;
import in.omkar.persistence.IStudentDao;
import in.omkar.servicefactory.StudentServiceFactory;

public class StudentServiceImpl implements  IStudentService{
    IStudentDao studentDao = null;
    @Override

    public String addStudent(String sname, Integer sage, String saddress) {
        if (studentDao == null) {
            studentDao = StudentDaoFactory.getStudentDao();
        }
        if (studentDao != null) {
            return studentDao.addStudent(sname,sage,saddress);
        }
        return "failure";
    }

    @Override
    public Student searchStudent(Integer sid) {
        if (studentDao == null) {
            studentDao = StudentDaoFactory.getStudentDao();
        }
        if (studentDao != null) {
            return studentDao.searchStudent(sid);
        }
        return null;
    }

    @Override
    public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
        if (studentDao == null) {
            studentDao = StudentDaoFactory.getStudentDao();
        }
        if (studentDao != null) {
            return studentDao.updateStudent(sid,sname,sage,saddress);

        }
        return null;
    }

    @Override
    public String deleteStudent(Integer sid) {
        if (studentDao == null) {
            studentDao = StudentDaoFactory.getStudentDao();
        }
        if (studentDao != null) {
            return studentDao.deleteStudent(sid);

        }
        return "failuer";
    }
}
