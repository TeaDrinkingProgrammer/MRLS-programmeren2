package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MixedDAO {
    //Deze DAO bevat alle queries die door joins niet in specefieke DAOs passen

    private MixedSQL sql = null;
    private Connection connection = null;

    public MixedDAO(){
        //Pakt de SQL class, deze handelt de verbinding en het sturen en ontvangen van de query's en hun replies
        sql = new MixedSQL();
        //Pakt eerst een connection, deze is nodig om een prepared statement op te maken
        connection = sql.getConnection();
    }

    public ArrayList<String[]> getAverageProgressForCourse(String courseName) {
        //De query met ? ipv de waarde
        String rawquery = " SELECT Name AS Course, Module.ModuleID, AVG(ContentPerc) AS AverageProgress FROM Course JOIN Module ON Course.Name = Module.Course JOIN ContentItem ON Module.ModuleID = ContentItem.ModuleID JOIN Progress ON ContentItem.ContentItemID = Progress.ContentItemI WHERE Course.Name = ? GROUP BY Name, Module.ModuleID;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, courseName);
                ArrayList<String> columns = new ArrayList<>();
                columns.add("Accounts");
                columns.add("Course");
                columns.add("ModuleID");
                columns.add("Progress");
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery(columns,preparedStatement);
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

    public ArrayList<String[]> getModuleProgress(String courseName,String studentEmail) {
        //De query met ? ipv de waarde
        String rawquery = "SELECT Email AS Account, Name AS Course, Module.ModuleID, ContentPerc AS Progress FROM Progress JOIN ContentItem ON Progress.ContentItemID = ContentItem.ContentItemID JOIN Module ON ContentItem.ModuleID = Module.ModuleID JOIN Course ON Module.Course = Course.Name WHERE Progress.Email = ? AND Course.Name = ?;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, studentEmail);
        preparedStatement.setString(2, courseName);
            ArrayList<String> columns = new ArrayList<>();
            columns.add("Accounts");
            columns.add("Course");
            columns.add("ModuleID");
            columns.add("Progress");
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery(columns,preparedStatement);
                //Slaat de resultaten op in deze Arraylist
                ArrayList<String[]> list = new ArrayList<>();
                if(tableList.size() > 0){
                    for(ArrayList<String> row : tableList){
                        //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                        String account = row.get(1);
                        String course = row.get(2);
                        String moduleID = row.get(3);
                        String[] stringArray = {account,course,course,moduleID};
                        list.add(stringArray);
                    }
                }
                return list;
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
    
    public ArrayList<String[]> getTop3Webcasts() {
        //De query met ? ipv de waarde
        String rawquery = "SELECT TOP 3 Webcast.WebcastID, Webcast.Title, COUNT(Progress.ContentItemID) AS Viewed FROM WebcastJOIN ContentItem ON Webcast.WebcastID = ContentItem.WebcastID JOIN Progress ON ContentItem.ContentItemID = Progress.ContentItemID GROUP BY Webcast.WebcastID, Webcast.Title ORDER BY Viewed DESC;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
                ArrayList<String> columns = new ArrayList<>();
                columns.add("WebcastID");
                columns.add("Title");
                columns.add("Viewed");
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery(columns,preparedStatement);
                //Slaat de resultaten op in deze Arraylist
                ArrayList<String[]> list = new ArrayList<>();
                if(tableList.size() > 0){
                    for(ArrayList<String> row : tableList){
                        //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                        String webcastID = row.get(1);
                        String title = row.get(2);
                        String viewed = row.get(3);
                        String[] stringArray = {webcastID,title,viewed};
                        list.add(stringArray);
                    }
                }
                return list;
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


    public ArrayList<String[]> nothingFound(){
        ArrayList<String[]> list = new ArrayList<>();
        String[] stringArray = {"nothingFound"};
        list.add(stringArray);
        return list;
    }


}
