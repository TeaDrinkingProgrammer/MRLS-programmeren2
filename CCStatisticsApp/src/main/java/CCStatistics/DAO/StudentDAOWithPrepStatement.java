package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import CCStatistics.Domain.Student;

public class StudentDAOWithPrepStatement{
    SQLWithPrepStatement sql = null;
    Connection connection = null;
    public StudentDAOWithPrepStatement(){
        //Pakt de SQL class, deze handelt de verbinding en het sturen en ontvangen van de query's en hun replies
        sql = new SQLWithPrepStatement();
        //Pakt eerst een connection, deze is nodig om een prepared statement op te maken
        connection = sql.getConnection();
    }
    
    public ArrayList<Student> genericReadQuery(PreparedStatement preparedStatement){
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Student",preparedStatement);
        ArrayList<Student> students = new ArrayList<>();
        if(tableList.size() > 0){
            for(ArrayList<String> row : tableList){
                String email = row.get(0);
                String firstName = row.get(1);
                String lastName = row.get(2);
                String dateOfBirth = row.get(3);
                String gender = row.get(4);
                String street = row.get(5);
                String houseNumber = row.get(6);
                String city = row.get(7);
                String country = row.get(8);
                String postalcode = row.get(9);
                students.add(new Student(email, firstName, lastName, dateOfBirth, gender, street, houseNumber, city, country, postalcode));
            }
        } else{
            String nullDate = "1-1-1000";
            students.add(new Student("No Students found!", "null", "null",nullDate,"null","null","null","null","null","null"));
        }
        return students;
    }
    public ArrayList<Student> getAll() {
        String rawquery = "SELECT * FROM Students;";
                
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
            return genericReadQuery(preparedStatement);
            } catch (SQLException e){
                // print SQL exception information
                SQLWithPrepStatement.printSQLException(e);
        }
        return null;
    }

    public ArrayList<Student> getModulesPercentage(String studentEmail) {
        //de query met ? ipv de waarde
        String rawquery = "SELECT * FROM Course WHERE Name IN(SELECT InterestingCourseName FROM InterestingToCourse WHERE CourseName = ?)";
        //probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten
        preparedStatement.setString(0, studentEmail);
        //Geeft deze statement mee aan genericreadquery

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        return genericReadQuery(preparedStatement);
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
    }
    return null;
    }
}
