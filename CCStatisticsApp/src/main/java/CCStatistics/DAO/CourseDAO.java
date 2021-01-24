package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import CCStatistics.Domain.Course;
import CCStatistics.Domain.EnumLevel;

public class CourseDAO {
    private SQL sql = null;
    private Connection connection = null;
    
    public CourseDAO() {
        //Pakt de connectionURL van login zodat deze aan te passen is. Geeft ook de mogelijkheid voor bijv. meerdere connectionURLS
        sql = new SQL();
        //Pakt eerst een connection, deze is nodig om een prepared statement op te maken
        connection = sql.getConnection();
    }

    public ArrayList<Course> genericReadQuery(PreparedStatement preparedStatement){
        //Geeft de prepared statement mee aan sql readquery
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Course",preparedStatement);
        ArrayList<Course> courses = new ArrayList<>();
        if(tableList.size() > 0){
            for(ArrayList<String> row : tableList){
                String name = row.get(0);
                String subject = row.get(1);
                String introText = row.get(2);
                EnumLevel level = EnumLevel.valueOf(row.get(3));
                courses.add(new Course(name,subject,introText,level));
            }
        } else{
            courses.add(new Course("No courses found!", "Subject", "IntroText", EnumLevel.valueOf("Beginner")));
        }
        return courses;
    }
    public ArrayList<Course> getAll() {
        String rawquery = "SELECT * FROM Course";
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
            return genericReadQuery(preparedStatement);
            } catch (SQLException e){
                //Als er geen resultaat komt, komt er een bepaalde error (zie printSQLException), dan vangt dit het af en stuurt een not found resultaat"
                if(SQL.printSQLException(e)){
                    return this.nothingFound();
                }
                // print SQL exception information
                SQL.printSQLException(e);
        }
        return null;
    }
    public ArrayList<Course> getCoursesInterestingTo(String courseName) {
        //de query met ? ipv de waarde
        String rawquery = "SELECT * FROM Course WHERE Name IN(SELECT InterestingCourse FROM InterestingToCourse WHERE Name = ?)";
        //probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, courseName);
        //Geeft deze statement mee aan genericreadquery

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        return genericReadQuery(preparedStatement);
        } catch (SQLException e){
            //Als er geen resultaat komt, komt er een bepaalde error (zie printSQLException), dan vangt dit het af en stuurt een not found resultaat"
            if(SQL.printSQLException(e)){
                return this.nothingFound();
            }
            // print SQL exception information
            SQL.printSQLException(e);
    }
    return null;
}

    public ArrayList<Course> read(String courseName) {
        //de query met ? ipv de waarde
        String rawquery = "SELECT * FROM Course WHERE Name = ?";
        //probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, courseName);
        //Geeft deze statement mee aan genericreadquery

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        return genericReadQuery(preparedStatement);
        } catch (SQLException e){
            //Als er geen resultaat komt, komt er een bepaalde error (zie printSQLException), dan vangt dit het af en stuurt een not found resultaat"
            if(SQL.printSQLException(e)){
                return this.nothingFound();
            }
            // print SQL exception information
            SQL.printSQLException(e);
    }
    return null;
    }

    //Updatet een employee
    public void update(String columnToChange, String changeInto, String courseName) {
        //De query met ? ipv de waarde
        String rawquery = "UPDATE Course SET ? = ? WHERE Name = ?;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, columnToChange);
        preparedStatement.setString(2, changeInto);
        preparedStatement.setString(2, courseName);
        //Stuur de preparedstatement direct naar de goede methode in SQL
        sql.createQuery(preparedStatement);

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e){
            // print SQL exception information
            SQL.printSQLException(e);
        }
    } 

    //Deletet een certificaat of meerdere certificaten (DELETE)
    public int delete(String courseName){
        //De query met ? ipv de waarde
        String rawquery = "DELETE FROM Course WHERE Name = ?;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, courseName);
        //Stuur de preparedstatement direct naar de goede methode in SQL
        return sql.deleteQuery(preparedStatement);

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e){
            // print SQL exception information
            SQL.printSQLException(e);
        }
        return 0;
    } 

    public ArrayList<Course> nothingFound(){
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("nothingFound", "null", "null", EnumLevel.valueOf("Beginner")));
        return courses;
    }
}
