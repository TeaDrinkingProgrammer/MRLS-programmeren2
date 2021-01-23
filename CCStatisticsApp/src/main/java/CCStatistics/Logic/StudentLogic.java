package CCStatistics.Logic;

import java.util.ArrayList;

import CCStatistics.DAO.SQLWithPrepStatement;
import CCStatistics.DAO.StudentDAOWithPrepStatement;
import CCStatistics.Domain.Student;

public class StudentLogic implements Logic<Student> {
    private StudentDAOWithPrepStatement studentDAO = new StudentDAOWithPrepStatement();
    private SQLWithPrepStatement sql = new SQLWithPrepStatement();
    private ArrayList<String> columns = new ArrayList<>();
//Haal alle studenten op in de DAO
    @Override
    public ArrayList<Student> getAll() {
        return studentDAO.getAll();
    }
//Verwijder student via DAO
    public String delete(String email) {
        int deleted = studentDAO.delete(email);
        return String.format("%d records deleted", deleted);
    }
//Creëer student via DAO
    public void create(String email, String firstName, String lastName, String dateOfBirth, String gender, String street, String houseNumber, String postalcode, String city, String country) {
        Student student = new Student(email, firstName, lastName, dateOfBirth, gender, street, houseNumber, postalcode, city, country);
        studentDAO.create(student);
    }
//Update student via DAO
    public ArrayList<Student> update(String columnToChange, String changeInto, String studentEmail) {
        studentDAO.update(columnToChange, changeInto, studentEmail);
        return this.getAll();
    }

    public ArrayList<String> getColumns(){
        if(columns.isEmpty()){
            columns = sql.getColumns("Student");
        }
        return columns;
    }
}
