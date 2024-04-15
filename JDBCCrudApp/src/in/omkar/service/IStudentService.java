package in.omkar.service;

import in.omkar.dto.Student;

public interface IStudentService {

    public String addStudent(String sname,Integer sage,String saddress);
    public Student searchStudent(Integer sid);
    public String updateStudent(Integer sid,String sname,Integer sage,String saddress);
    public String deleteStudent(Integer sid);

}
