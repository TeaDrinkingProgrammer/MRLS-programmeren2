package CCStatistics.Logic;

import java.util.ArrayList;
import CCStatistics.DAO.CourseDAO;
import CCStatistics.DAO.SignupDAO;
import CCStatistics.DAO.StudentDAO;
import CCStatistics.Domain.Course;
import CCStatistics.Domain.Signup;
import CCStatistics.Domain.Student;
import CCStatistics.DAO.SQL;

public class SignupLogic implements Logic<Signup> {
    private SignupDAO signupDAO = new SignupDAO();
    private SQL sql = new SQL();
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
        CourseDAO courseDAO = new CourseDAO();
        StudentDAO studentDAO = new StudentDAO();
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

        String[] dateParts = signupDate.split("-");
        int day = Integer.valueOf(dateParts[0]);
        int month = Integer.valueOf(dateParts[1]);
        int year = Integer.valueOf(dateParts[2]);

        if (!ValidationFormatLogic.validateDate(day, month, year) && !ValidationFormatLogic.validateMailAddress(studentEmail)) {
            return "No valid signup date and student email.";
        } else if (!ValidationFormatLogic.validateDate(day, month, year)) {
            return "No valid signup date.";
        } else if (!ValidationFormatLogic.validateMailAddress(studentEmail)) {
            return "No valid email.";
        }

        if (containsStudent && containsCourse) {
            String formattedSignupDate = ValidationFormatLogic.formatDate(day, month, year);
            Signup signup = new Signup(formattedSignupDate, courseFound);
            signupDAO.create(signup, studentEmail);
            return "Signup added.";
        }

        return "No such course or student!";
    }

//Update Signup via DAO
    public ArrayList<Signup> update(String columnToChange, String changeInto, int signupID) {
        signupDAO.update(columnToChange, changeInto, signupID);
        return this.getAll();
    }

//Gets columnnames
    public ArrayList<String> getColumns(){
        if(columns.isEmpty()){
            columns = sql.getColumns("Signup");
        }
        return columns;
    }
}
