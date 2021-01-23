package CCStatistics.Logic;

import java.util.ArrayList;
import CCStatistics.DAO.CourseDAOWithPrepStatement;
import CCStatistics.DAO.SignupDAOWithPrepStatement;
import CCStatistics.DAO.StudentDAOWithPrepStatement;
import CCStatistics.Domain.Course;
import CCStatistics.Domain.Signup;
import CCStatistics.Domain.Student;
import CCStatistics.DAO.SQLWithPrepStatement;

public class SignupLogic implements Logic<Signup> {
    private SignupDAOWithPrepStatement signupDAO = new SignupDAOWithPrepStatement();
    private SQLWithPrepStatement sql = new SQLWithPrepStatement();
    private ArrayList<String> columns = new ArrayList<>();
    //Haal alle Signup op in de DAO
    @Override
    public ArrayList<Signup> getAll() {
        return signupDAO.getAll();
    }
//Verwijder Signup via DAO
    public String delete(int ID) {
        int deleted = signupDAO.delete(ID);
        return String.format("%d records deleted", deleted);
    }
//Creëer Signup via DAO
    public String create(String signupDate, String courseTo, String studentEmail) {
        CourseDAOWithPrepStatement courseDAO = new CourseDAOWithPrepStatement();
        StudentDAOWithPrepStatement studentDAO = new StudentDAOWithPrepStatement();
        ArrayList<Course> courses = courseDAO.getAll();
        ArrayList<Student> students = studentDAO.getAll();
        Course courseFound = null;
        Boolean containsCourse = false;
        Boolean containsStudent = false;

        for (Student student : students) {
            if (student.getEmail().equals(studentEmail)) {
                containsStudent = true;
                break;
            }
        }

        for (Course course : courses) {
            if (course.getName().equals(courseTo)) {
                containsCourse = true;
                courseFound = course;
                break;
            }
        }
        
        if (containsStudent && containsCourse) {
            Signup signup = new Signup(signupDate, courseFound);
            signupDAO.create(signup, studentEmail);
            return "Sign up added!";
        }
        return "No such course or student!";
    }
//Update Signup via DAO
    public ArrayList<Signup> update(String columnToChange, String changeInto, int signupID) {
        signupDAO.update(columnToChange, changeInto, signupID);
        return this.getAll();
    }
    public ArrayList<String> getColumns(){
        if(columns.isEmpty()){
            columns = sql.getColumns("Signup");
        }
        return columns;
    }
}
