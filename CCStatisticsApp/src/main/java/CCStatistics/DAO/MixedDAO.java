package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MixedDAO {
    //Deze DAO bevat alle queries die door joins niet in specefieke DAOs passen

    private SQLWithPrepStatement sql = null;
    private Connection connection = null;

    public MixedDAO(){
        //Pakt de SQL class, deze handelt de verbinding en het sturen en ontvangen van de query's en hun replies
        sql = new SQLWithPrepStatement();
        //Pakt eerst een connection, deze is nodig om een prepared statement op te maken
        connection = sql.getConnection();
    }
       public ArrayList<ObjectToChange> MixedQueryExample(String inputName) {
        //De query met ? ipv de waarde
        String rawquery = "Select query with ? here";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, inputName);
        //Geeft deze statement mee aan genericreadquery

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
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
                    return null;
                }
                return listName;
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
        }
    return null;
    }   

    public ArrayList<String[]> getAverageProgressForCourse(String courseName) {
        //De query met ? ipv de waarde
        String rawquery = " SELECT Name AS Course, Module.ModuleID, AVG(ContentPerc) AS AverageProgress FROM Course JOIN Module ON Course.Name = Module.Course JOIN ContentItem ON Module.ModuleID = ContentItem.ModuleID JOIN Progress ON ContentItem.ContentItemID = Progress.ContentItemI WHERE Course.Name = ? GROUP BY Name, Module.ModuleID;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, courseName);
        //Geeft deze statement mee aan genericreadquery

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery("TableToChange",preparedStatement);
                //Slaat de resultaten op in deze Arraylist
                ArrayList<String[]> list = new ArrayList<>();
                if(tableList.size() > 0){
                    for(ArrayList<String> row : tableList){
                        //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                        String course = row.get(1);
                        String moduleID = row.get(2);
                        String averageProgress = row.get(3);
                        String[] stringArray = {course,moduleID,averageProgress};
                        list.add(stringArray);
                    }
                }
                return list;
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

    public ArrayList<String[]> nothingFound(){
        ArrayList<String[]> list = new ArrayList<>();
        String[] stringArray = {"nothingFound"};
        list.add(stringArray);
        return list;
    }


}
