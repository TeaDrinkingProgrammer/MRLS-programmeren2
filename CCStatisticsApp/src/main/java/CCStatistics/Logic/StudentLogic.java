package CCStatistics.Logic;

import java.util.ArrayList;

import CCStatistics.DAO.StudentDAOWithPrepStatement;
import CCStatistics.Domain.Student;

public class StudentLogic implements Logic<Student> {
    StudentDAOWithPrepStatement studentDAO = new StudentDAOWithPrepStatement();

//Haal alle studenten op in de DAO
    @Override
    public ArrayList<Student> getAll() {
        return studentDAO.getAll();
    }
//Verwijder student via DAO
    public void delete(String email) {
    studentDAO.delete(email);
}
//Creëer student via DAO
    public void create(Student student) {
        studentDAO.create(student);
    }
//Update student via DAO
    public void update(Student student, String string) {
        studentDAO.update(student, string);
    }
}
