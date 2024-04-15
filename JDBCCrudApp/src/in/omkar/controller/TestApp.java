package in.omkar.controller;

import in.omkar.dto.Student;
import in.omkar.service.IStudentService;
import in.omkar.servicefactory.StudentServiceFactory;

import java.util.Scanner;

//controller logic
public class TestApp {
    public static void main(String[] args) {



    }
    private static void deleteOperation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the student ID to delete");
        Integer sid = sc.nextInt();
        IStudentService studentService = StudentServiceFactory.getStudantService();
        String str = studentService.deleteStudent(sid);
        if(str.equalsIgnoreCase("success")){
            System.out.println("deletion success full");
        }else{
            System.out.println("deletion failed");
        }
    }
    private static void updateOperation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the student ID to update");
        Integer sid = sc.nextInt();
        IStudentService studentService = StudentServiceFactory.getStudantService();
        Student student = studentService.searchStudent(sid);
        if (student != null) {
            updateString(student,"sname",sc);
            updateString(student,"sage",sc);
            updateString(student,"saddress",sc);
            studentService.updateStudent(student.getSid(),student.getSname(),student.getSage(),student.getSaddress());

        }
    }
    private static void updateString(Student student,String field ,Scanner sc){
        if(field == null) return ;
        System.out.println("do you want to uodate "+ field +" type [YES/NO]" );
        String input = null;
        if(sc!=null) {
            input = sc.next();
        }
        if( input != null &&input.equalsIgnoreCase("yes")){
            System.out.println("enter the updated value of ::"+field);
           if(student!=null){
                switch (field.toLowerCase()){
                    case "sname":
                        student.setSname(sc.next());
                        break;
                    case "sage":
                        student.setSage(sc.nextInt());
                        break;
                    case "saddress":
                        student.setSaddress(sc.next());
                        break;
                    default:
                        System.out.println("invalid fiels of error occurd");
                        System.out.println("updation failed");

                }

           }
            System.out.println("updation of "+field +"is dane");
           return;
        }
        System.out.println(field +"remains unchanged" );
    }
    private static void searchOperation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the student ID   to search");
        Integer sid = sc.nextInt();

        IStudentService studentService = StudentServiceFactory.getStudantService();
        Student student = studentService.searchStudent(sid);

        if (student != null) {
            System.out.println("Name of student ::" + student.getSname());
            System.out.println("Age of Student ::"+ student.getSage());
            System.out.println("Address of student" + student.getSaddress());
        }else {
            System.out.println("Record not Found with id  :: "+ sid);
        }
    }

    private static void insertOperation(){

        IStudentService studentService = StudentServiceFactory.getStudantService();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the name od student");
        String sname = sc.next();
        System.out.println("enter the age of student");
        Integer sage = sc.nextInt();
        System.out.println("enter the address of student");
        String saddress = sc.next();

        String msg = studentService.addStudent(sname,sage,saddress);

        if(msg.equalsIgnoreCase("success")){
            System.out.println("record inserted success fully");
        }else {
            System.out.println("record insertion failed");
        }
    }
}
