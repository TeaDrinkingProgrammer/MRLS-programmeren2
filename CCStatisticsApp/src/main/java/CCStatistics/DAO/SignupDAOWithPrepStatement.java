package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import CCStatistics.Domain.Certificate;
import CCStatistics.Domain.Course;
import CCStatistics.Domain.Signup;

//Vervang SignUp met het object dat je hebt (bijv. Student)
//Vervang signUps met de naam van het object --> zie genericReadQuery
//Vervang inputName met de input die je wilt voor de methode (bijv. emailadress)
//Vervang signup met de naam van de table in de database
//Na gebruik deze comments weghalen

public class SignupDAOWithPrepStatement {
    private SQLWithPrepStatement sql = null;
    private Connection connection = null;

    public SignupDAOWithPrepStatement() {
        // Pakt de SQL class, deze handelt de verbinding en het sturen en ontvangen van
        // de query's en hun replies
        sql = new SQLWithPrepStatement();
        // Pakt eerst een connection, deze is nodig om een prepared statement op te
        // maken
        connection = sql.getConnection();
    }

    public ArrayList<Signup> genericReadQuery(PreparedStatement preparedStatement) {
        //Geeft de prepared statement mee aan SQL readquery
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Signup",preparedStatement);
        //Slaat de resultaten op in deze Arraylist
        ArrayList<Signup> signUps = new ArrayList<>();
        if(tableList.size() > 0){
            for(ArrayList<String> row : tableList){
                //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                int signupID = Integer.valueOf(row.get(0));
                String signupDate = row.get(1);
                String courseName = row.get(2);
                //Studentemail wordt niet gelezen, die wordt gehandelt bij student
                String certificateID = row.get(4);
                CourseDAOWithPrepStatement courseDAO = new CourseDAOWithPrepStatement();
                Course course = courseDAO.read(courseName).get(0);
                Certificate certificate = null;
                if(!certificateID.equals(null) || !certificateID.equals("null")){
                    CertificateDAOWithPrepStatement certificateDAO = new CertificateDAOWithPrepStatement();
                    certificate = certificateDAO.read(courseName).get(0);
                }
                signUps.add(new Signup(signupID,signupDate,course,certificate));
            }
        }
        return signUps;
    }

    public ArrayList<Signup> getAll() {
        //De Query
        String rawquery = "SELECT * FROM Signup";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
            //Omdat dit getAll is wordt er geen waarde in geplaats dus de preparedstatement wordt rechtstreeks doorgestuurd naar genericReadQuery
            return genericReadQuery(preparedStatement);

             //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
            } catch (SQLException e){
                // print SQL exception information
                SQLWithPrepStatement.printSQLException(e);
        }
        return null;
    }

    public void create(String date, String courseName, String studentEmail) {
        //De query met ? ipv de waarde
        String rawquery = "INSERT INTO Signup (Signupdate, Course, StudentEmail) VALUES (?,?,?);";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, date);
        preparedStatement.setString(2, courseName);
        preparedStatement.setString(3, studentEmail);
        //Stuur de preparedstatement direct naar de goede methode in SQL
        sql.createQuery(preparedStatement);

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
        }

    } 

        //Updatet een signup (UPDATE) 
        public void update(String columnToChange, String columnToCheck, String changeInto, String valueIs) {
            //De query met ? ipv de waarde
            String rawquery = "UPDATE Signup SET ? = ? WHERE ? = ?;";
            //Probeert het eerste deel van de statement te sturen
            try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
            //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
            preparedStatement.setString(1, columnToChange);
            preparedStatement.setString(2, changeInto);
            preparedStatement.setString(2, columnToCheck);
            preparedStatement.setString(2, valueIs);
            //Stuur de preparedstatement direct naar de goede methode in SQL
            sql.createQuery(preparedStatement);
    
            //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
            } catch (SQLException e){
                // print SQL exception information
                SQLWithPrepStatement.printSQLException(e);
            }
        } 

        public void delete(String valueIs){
            //De query met ? ipv de waarde
            String rawquery = "DELETE FROM Signup WHERE SignupID = ?;";
            //Probeert het eerste deel van de statement te sturen
            try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
            //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
            preparedStatement.setString(1, valueIs);
            //Stuur de preparedstatement direct naar de goede methode in SQL
            sql.createQuery(preparedStatement);
    
            //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
            } catch (SQLException e){
                // print SQL exception information
                SQLWithPrepStatement.printSQLException(e);
            }
        } 
}