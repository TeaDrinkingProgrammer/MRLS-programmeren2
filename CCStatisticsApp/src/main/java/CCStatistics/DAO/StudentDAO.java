package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import CCStatistics.Domain.Student;

public class StudentDAO {
    private SQL sql = null;
    private Connection connection = null;

    public StudentDAO() {
        // Pakt de SQL class, deze handelt de verbinding en het sturen en ontvangen van
        // de query's en hun replies
        sql = new SQL();
        // Pakt eerst een connection, deze is nodig om een prepared statement op te
        // maken
        connection = sql.getConnection();
    }

    // De Generic Read Query handelt alle readqueries uiteindelijk af.
    public ArrayList<Student> genericReadQuery(PreparedStatement preparedStatement) {
        // Geeft de prepared statement mee aan SQL readquery
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Student", preparedStatement);
        // Slaat de resultaten op in deze Arraylist
        ArrayList<Student> students = new ArrayList<>();
        if (tableList.size() > 0) {
            for (ArrayList<String> row : tableList) {
                // Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede
                // object
                String email = row.get(0);
                String firstName = row.get(1);
                String lastName = row.get(2);
                String dateOfBirth = row.get(3);
                String gender = row.get(4);
                String street = row.get(5);
                String houseNumber = row.get(6);
                String postalcode = row.get(7);
                String city = row.get(8);
                String country = row.get(9);

                students.add(new Student(email, firstName, lastName, dateOfBirth, gender, street, houseNumber,
                        postalcode, city, country));
            }
        } else {
            return this.nothingFound();
        }
        return students;
    }

    // Leest alle Studenten (READ)
    public ArrayList<Student> getAll() {
        String rawquery = "SELECT * FROM Student;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
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

    // Maakt een Student aan (CREATE)
    public void create(Student student) {
        // De query met ? ipv de waarde
        String rawquery = "INSERT INTO Student (Email,FirstName,LastName,DateOfBirth,Gender,Street,HouseNr,PostCode,City,Country) VALUES (?,?,?,?,?,?,?,?,?,?);";
        // Probeert het eerste deel van de statement te sturen
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
            // Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten,
            // begint op 1 met tellen
            preparedStatement.setString(1, student.getEmail());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getDateOfBirth());
            preparedStatement.setString(5, student.getGender());
            preparedStatement.setString(6, student.getStreet());
            preparedStatement.setString(7, student.getHouseNumber());
            preparedStatement.setString(8, student.getPostalCode());
            preparedStatement.setString(9, student.getCity());
            preparedStatement.setString(10, student.getCountry());
            // Stuur de preparedstatement direct naar de goede methode in SQL
            sql.createQuery(preparedStatement);

            // Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e) {
            // print SQL exception information
            SQL.printSQLException(e);
        }
    }

    // Leest een student uit (READ)
    public ArrayList<Student> read(String studentEmail) {
        // De query met ? ipv de waarde
        String rawquery = "SELECT * FROM Student WHERE Email = ?";
        // Probeert het eerste deel van de statement te sturen
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
            // Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten,
            // begint op 1 met tellen
            preparedStatement.setString(1, studentEmail);
            // Geeft deze statement mee aan genericreadquery
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

    // Updatet een student (UPDATE), (werkt niet)
    public void update(String columnToChange, String changeInto, String studentEmail) {
        // De query met ? ipv de waarde
        String rawquery = "UPDATE Student SET ? = ? WHERE Email = ?;";
        // Probeert het eerste deel van de statement te sturen
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
            // Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten,
            // begint op 1 met tellen
            preparedStatement.setString(1, columnToChange);
            preparedStatement.setString(2, changeInto);
            preparedStatement.setString(3, studentEmail);
            // Stuur de preparedstatement direct naar de goede methode in SQL
            sql.createQuery(preparedStatement);

            // Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e) {
            // print SQL exception information
            SQL.printSQLException(e);
        }
    }

    // Delete een student (DELETE)
    public int delete(String studentEmail) {
        // De query met ? ipv de waarde
        String rawquery = "DELETE FROM Student WHERE Email = ?;";
        // Probeert het eerste deel van de statement te sturen
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawquery)) {
            // Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten,
            // begint op 1 met tellen
            preparedStatement.setString(1, studentEmail);
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
    public ArrayList<Student> nothingFound() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("NothingFound", "null", "null", "1-1-1000", "null", "null", "null", "null", "null",
                "null"));
        return students;
    }
}
