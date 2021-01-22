package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import CCStatistics.Domain.Course;
import CCStatistics.Domain.LevelEnum;

//Vervang ObjectToChange met het object dat je hebt (bijv. Student)
//Vervang listName met de naam van het object --> zie genericReadQuery
//Vervang inputName met de input die je wilt voor de methode (bijv. emailadress)
//Vervang TableToChange met de naam van de table in de database
//Na gebruik deze comments weghalen


public class DAOWithPrepStatementTemplate{
    SQLWithPrepStatement sql = null;
    Connection connection = null;

    public DAOWithPrepStatementTemplate(){
        //Pakt de SQL class, deze handelt de verbinding en het sturen en ontvangen van de query's en hun replies
        sql = new SQLWithPrepStatement();
        //Pakt eerst een connection, deze is nodig om een prepared statement op te maken
        connection = sql.getConnection();
    }

    public ArrayList<ObjectToChange> genericReadQuery(PreparedStatement preparedStatement){
        //Geeft de prepared statement mee aan SQL readquery
        ArrayList<ArrayList<String>> tableList = sql.readQuery("TableToChange",preparedStatement);
        //Slaat de resultaten op in deze Arraylist
        ArrayList<ObjectToChange> listName = new ArrayList<>();
        if(tableList.size() > 0){
            for(ArrayList<String> row : tableList){
                //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                String item1 = row.get(0);
                String item2 = row.get(1);
                String item3 = row.get(2);
                listName.add(new ObjectToChange(name,subject,introText,level));
            }
        } else{
            //Als er geen enkel resultaat wordt dat aangegeven in het object. Dit ziet de gebruiker omdat deze uitgelezen wordt door de GUI
            //HAAL REGEL WEG NA GELEZEN TE HEBBEN -- Plaats de No listname found! op goede plek zodat deze uitgelezen kan worden door de GUI
            listName.add(new ObjectToChange("No listName found!", "item2", "item3", "item4")); 
        }
        return listName;
    }

    public ArrayList<ObjectToChange> getAll() {
        //De Query
        String rawquery = "SELECT * FROM TableToChange";
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

    public ArrayList<ObjectToChange> SelectQueryWithVariable(String inputName) {
        //De query met ? ipv de waarde
        String rawquery = "Select query with ? here";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten
        preparedStatement.setString(0, inputName);
        //Geeft deze statement mee aan genericreadquery

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        return genericReadQuery(preparedStatement);
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
        }
    return null;
    }  
    public ArrayList<ObjectToChange> CUDqueryWithVariable(String inputName) {
        //De query met ? ipv de waarde
        String rawquery = "Update query with ? here";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten
        preparedStatement.setString(0, inputName);
        //Stuur de preparedstatement direct naar de goede methode in SQL
        sql.SomeCUDqueryHere(preparedStatement);

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
        }

    }   
}