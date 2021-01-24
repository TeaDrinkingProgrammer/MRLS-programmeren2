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
        // Pakt de connectionURL van login zodat deze aan te passen is. Geeft ook de
        // mogelijkheid voor bijv. meerdere connectionURLS
        sql = new SQL();
        // Pakt eerst een connection, deze is nodig om een prepared statement op te
        // maken
        connection = sql.getConnection();
    }

    // De Generic Read Query handelt alle readqueries uiteindelijk af.
    public ArrayList<Course> genericReadQuery(PreparedStatement preparedStatement) {
        // Geeft de prepared statement mee aan sql readquery
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Course", preparedStatement);
        // Slaat de resultaten op in deze Arraylist
        ArrayList<Course> courses = new ArrayList<>();
        if (tableList.size() > 0) {
            for (ArrayList<String> row : tableList) {
                // Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede
                // object
                String name = row.get(0);
                String subject = row.get(1);
                String introText = row.get(2);
                EnumLevel level = EnumLevel.valueOf(row.get(3));
                courses.add(new Course(name, subject, introText, level));
            }
        } else {
            return this.nothingFound();
        }
        return courses;
    }

    // Leest alle courses (READ)
    public ArrayList<Course> getAll() {
        // De Query
        String rawquery = "SELECT * FROM Course";
        // Probeert het eerste deel van de statement te sturen
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
            // Omdat dit getAll is wordt er geen waarde in geplaats dus de preparedstatement
            // wordt rechtstreeks doorgestuurd naar genericReadQuery
            return genericReadQuery(preparedStatement);
            // Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e) {
            // Als er geen resultaat komt, komt er een bepaalde error (zie
            // printSQLException), dan vangt dit het af en stuurt een not found resultaat"
            if (SQL.printSQLException(e)) {
                return this.nothingFound();
            }
            // print SQL exception information
            SQL.printSQLException(e);
        }
        return null;
    }

    // Leest een certificate uit (READ)
    public ArrayList<Course> getCoursesInterestingTo(String courseName) {
        // de query met ? ipv de waarde
        String rawquery = "SELECT * FROM Course WHERE Name IN(SELECT InterestingCourse FROM InterestingToCourse WHERE Course = ?)";
        // probeert het eerste deel van de statement te sturen
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
            // Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten,
            // begint op 1 met tellen
            preparedStatement.setString(1, courseName);
            // Geeft deze statement mee aan genericreadquery

            // Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
            return genericReadQuery(preparedStatement);
        } catch (SQLException e) {
            // Als er geen resultaat komt, komt er een bepaalde error (zie
            // printSQLException), dan vangt dit het af en stuurt een not found resultaat"
            if (SQL.printSQLException(e)) {
                return this.nothingFound();
            }
            // print SQL exception information
            SQL.printSQLException(e);
        }
        return null;
    }

    // Pakt alle courses met een bepaalde Naam (READ)
    public ArrayList<Course> read(String courseName) {
        // de query met ? ipv de waarde
        String rawquery = "SELECT * FROM Course WHERE Name = ?";
        // probeert het eerste deel van de statement te sturen
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
            // Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten,
            // begint op 1 met tellen
            preparedStatement.setString(1, courseName);
            // Geeft deze statement mee aan genericreadquery

            // Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
            return genericReadQuery(preparedStatement);
        } catch (SQLException e) {
            // Als er geen resultaat komt, komt er een bepaalde error (zie
            // printSQLException), dan vangt dit het af en stuurt een not found resultaat"
            if (SQL.printSQLException(e)) {
                return this.nothingFound();
            }
            // print SQL exception information
            SQL.printSQLException(e);
        }
        return null;
    }

    // Updatet een course (UPDATE) (werkt niet)
    public void update(String columnToChange, String changeInto, String courseName) {
        // De query met ? ipv de waarde
        String rawquery = "UPDATE Course SET ? = ? WHERE Name = ?;";
        // Probeert het eerste deel van de statement te sturen
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
            // Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten,
            // begint op 1 met tellen
            preparedStatement.setString(1, columnToChange);
            preparedStatement.setString(2, changeInto);
            preparedStatement.setString(2, courseName);
            // Stuur de preparedstatement direct naar de goede methode in SQL
            sql.createQuery(preparedStatement);

            // Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e) {
            // print SQL exception information
            SQL.printSQLException(e);
        }
    }

    // Deletet een course (DELETE)
    public int delete(String courseName) {
        // De query met ? ipv de waarde
        String rawquery = "DELETE FROM Course WHERE Name = ?;";
        // Probeert het eerste deel van de statement te sturen
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
            // Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten,
            // begint op 1 met tellen
            preparedStatement.setString(1, courseName);
            // Stuur de preparedstatement direct naar de goede methode in SQL
            return sql.deleteQuery(preparedStatement);
            // Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e) {
            // print SQL exception information
            SQL.printSQLException(e);
        }
        return 0;
    }

    // Geeft deze waarde terug als er niets gevonden is ipv null om errors te
    // voorkomen
    public ArrayList<Course> nothingFound() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("nothingFound", "null", "null", EnumLevel.valueOf("Beginner")));
        return courses;
    }
}
