package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import CCStatistics.Domain.Certificate;

public class CertificateDAOWithPrepStatement{
    private SQLWithPrepStatement sql = null;
    private Connection connection = null;

    public CertificateDAOWithPrepStatement(){
        //Pakt de SQL class, deze handelt de verbinding en het sturen en ontvangen van de query's en hun replies
        sql = new SQLWithPrepStatement();
        //Pakt eerst een connection, deze is nodig om een prepared statement op te maken
        connection = sql.getConnection();
    }

    public ArrayList<Certificate> genericReadQuery(PreparedStatement preparedStatement){
        //Geeft de prepared statement mee aan SQL readquery
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Certificate",preparedStatement);
        //Slaat de resultaten op in deze Arraylist
        ArrayList<Certificate> certificates = new ArrayList<>();
        if(tableList.size() > 0){
            for(ArrayList<String> row : tableList){
                //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                int certificateID  = Integer.valueOf(row.get(0));
                String employeeName = row.get(1);
                double grade = Double.valueOf(row.get(2));
                certificates.add(new Certificate(certificateID, grade, employeeName));
            }
        } else{
            //Als er geen enkel resultaat wordt dat aangegeven in het object. Dit ziet de gebruiker omdat deze uitgelezen wordt door de GUI
            //HAAL REGEL WEG NA GELEZEN TE HEBBEN -- Plaats de No certificates found! op goede plek zodat deze uitgelezen kan worden door de GUI
            certificates.add(new Certificate(0,0.0, "No Certificates Found!")); 
        }
        return certificates;
    }

    //Leest alle certificaten (READ)
    public ArrayList<Certificate> getAll() {
        //De Query
        String rawquery = "SELECT * FROM Certificate";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
            //Omdat dit getAll is wordt er geen waarde in geplaats dus de preparedstatement wordt rechtstreeks doorgestuurd naar genericReadQuery
            return genericReadQuery(preparedStatement);

             //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
            } catch (SQLException e){
                //Als er geen resultaat komt, komt er een bepaalde error (zie printSQLException), dan vangt dit het af en stuurt een not found resultaat"
                if(SQLWithPrepStatement.printSQLException(e)){
                    return this.nothingFound();
                }
                // print SQL exception information
                SQLWithPrepStatement.printSQLException(e);
        }
        return null;
    }

        //Leest een certificate uit (READ)
        public ArrayList<Certificate> read(int certificateID) {
        //De query met ? ipv de waarde
        String rawquery = "SELECT * FROM Certificate WHERE CertificateID = ?";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setInt(1, certificateID);
        //Geeft deze statement mee aan genericreadquery
        return genericReadQuery(preparedStatement);
        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e){
            //Als er geen resultaat komt, komt er een bepaalde error (zie printSQLException), dan vangt dit het af en stuurt een not found resultaat"
            if(SQLWithPrepStatement.printSQLException(e)){
                return this.nothingFound();
            }
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
        }
    return null;
    }

    //Maakt een certificate aan (CREATE)
    public void create(Certificate certificate) {
        //De query met ? ipv de waarde
        String rawquery = "INSERT INTO Certificate (EmployeeName, Grade) VALUES (?,?);";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, certificate.getEmployeeName());
        preparedStatement.setDouble(2, certificate.getGrade());
        //Stuur de preparedstatement direct naar de goede methode in SQL
        sql.createQuery(preparedStatement);

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
        }

    } 

    //Updatet een certificaat of meerdere certificaten (UPDATE)
    //Updatet de EmployeeName in Certificate-tabel
    public void updateEmployeeName(int ID, String employeeName) {
        //De query met ? ipv de waarde
        String rawquery = "UPDATE Certificate SET EmployeeName = ? WHERE CertificateID = ?;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, employeeName);
        preparedStatement.setLong(2, ID);
        //Stuur de preparedstatement direct naar de goede methode in SQL
        sql.createQuery(preparedStatement);

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
        }
    } 

    //Updatet de Grade in Certificate-tabel
    public void updateGrade(int ID, double changeInto){
        //De query met ? ipv de waarde
        String rawquery = "UPDATE Certificate SET Grade = ? WHERE CertificateID = ?;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setDouble(1, changeInto);
        preparedStatement.setLong(2, ID);
        //Stuur de preparedstatement direct naar de goede methode in SQL
        sql.updateQuery(preparedStatement);

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
        }
    } 
    
    //Deletet een certificaat of meerdere certificaten (DELETE)
    public void delete(int ID){
        //De query met ? ipv de waarde
        String rawquery = "DELETE FROM Certificate WHERE CertificateID = ?;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setInt(1, ID);
        //Stuur de preparedstatement direct naar de goede methode in SQL
        sql.deleteQuery(preparedStatement);

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
        }
    } 

        public ArrayList<Certificate> nothingFound(){
            ArrayList<Certificate> certificates = new ArrayList<>();
            certificates.add(new Certificate(0,0.0, "nothingFound")); 
            return certificates;
        }
}