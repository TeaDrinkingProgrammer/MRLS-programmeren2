package CCStatistics.Logic;

import java.util.ArrayList;

import CCStatistics.DAO.StudentDAOWithPrepStatement;
import CCStatistics.Domain.Student;

public class StudentLogic {
    StudentDAOWithPrepStatement studentDAO = new StudentDAOWithPrepStatement();

//Haal alle studenten op in de DAO
    public ArrayList<Student> getAllStudents() {
        return studentDAO.getAll();
    }

//Creëer student via DAO
    public void createStudent(String courseName) {
        if (courseDAO.getCoursesInterestingTo(courseName).isEmpty()) {
            return null;
        }
        
        return courseDAO.getCoursesInterestingTo(courseName);
    }

//Update student via DAO

//Verwijder student via DAO

}
