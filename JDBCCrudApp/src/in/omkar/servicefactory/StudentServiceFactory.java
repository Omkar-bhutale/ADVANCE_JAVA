package in.omkar.servicefactory;

import in.omkar.service.IStudentService;
import in.omkar.service.StudentServiceImpl;
    //abstraction logic implementation
public class StudentServiceFactory {
    //make a constructor private to avoid object creation
    private StudentServiceFactory(){};
    private static IStudentService studentService =null;
    public static IStudentService getStudantService(){
        //singleton pattern code
        if (studentService == null) {
            studentService = new StudentServiceImpl();
        }
        return studentService;
    }
}
