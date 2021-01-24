package CCStatistics.Logic;

import java.util.ArrayList;
import CCStatistics.DAO.SQL;
import CCStatistics.DAO.StudentDAO;
import CCStatistics.Domain.Student;

public class StudentLogic implements Logic<Student> {
    private StudentDAO studentDAO = new StudentDAO();
    private SQL sql = new SQL();
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
    public String create(String email, String firstName, String lastName, String dateOfBirth, String gender, String street, String houseNumber, String postalCode, String city, String country) {
        String[] dateParts = dateOfBirth.split("-");
        int day = Integer.valueOf(dateParts[0]);
        int month = Integer.valueOf(dateParts[1]);
        int year = Integer.valueOf(dateParts[2]);
        String formattedPostalCode = null;

        try {
            formattedPostalCode = ValidationFormatLogic.formatPostalCode(postalCode);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "No valid postal code.";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "No postal code input. Input is Null.";
        }

        if (ValidationFormatLogic.validateMailAddress(email) && ValidationFormatLogic.validateDate(day, month, year)) {
            String formattedDateOfBirth = String.format("%d-%d-%d", month, day, year);            
            Student student = new Student(email, firstName, lastName, formattedDateOfBirth, gender, street, houseNumber, formattedPostalCode, city, country);
            studentDAO.create(student);
            return "Student added.";
        }

        return "Student NOT added. Check input.";
    }

//Update student via DAO
    public ArrayList<Student> update(String columnToChange, String changeInto, String studentEmail) {
        studentDAO.update(columnToChange, changeInto, studentEmail);
        return this.getAll();
    }

//Gets columnnames
    public ArrayList<String> getColumns(){
        if(columns.isEmpty()){
            columns = sql.getColumns("Student");
        }
        return columns;
    }
}
