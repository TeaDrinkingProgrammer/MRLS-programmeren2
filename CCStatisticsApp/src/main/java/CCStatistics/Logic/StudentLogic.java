package CCStatistics.Logic;

import java.util.ArrayList;

import CCStatistics.DAO.StudentDAOWithPrepStatement;
import CCStatistics.Domain.DatabaseEntity;
import CCStatistics.Domain.Student;

public class StudentLogic implements Logic {
    StudentDAOWithPrepStatement studentDAO = new StudentDAOWithPrepStatement();

//Haal alle studenten op in de DAO
    @Override
    public ArrayList<Student> getAll() {
        return studentDAO.getAll();
    }

//Creëer student via DAO
    @Override
    public void create(DatabaseEntity databaseEntity) {
        studentDAO.CUDqueryWithVariable(inputName)
    }

//Update student via DAO
    @Override
    public void update(DatabaseEntity databaseEntity) {
        studentDAO.CUDqueryWithVariable(inputName)
    }
//Verwijder student via DAO
    @Override
    public void delete(DatabaseEntity databaseEntity) {
        studentDAO.CUDqueryWithVariable(inputName)
    }
}
