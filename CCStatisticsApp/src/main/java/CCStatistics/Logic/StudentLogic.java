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
//Creëer student via DAO
    @Override
    public void create(Student object) {
        studentDAO.create(object);
    }
//Update student via DAO
    @Override
    public void update(Student object, String string) {
        studentDAO.update(object, string);
    }
//Verwijder student via DAO
    @Override
    public void delete(Student object) {
        studentDAO.create(object);
    }
}
