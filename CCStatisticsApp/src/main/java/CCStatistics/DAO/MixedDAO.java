package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MixedDAO {
    //Deze DAO bevat alle queries die door joins niet in specefieke DAOs passen

    private MixedSQL sql = null;
    private Connection connection = null;
    public final String[] averageProgressForCourseColumns = {"Course","ModuleID","AverageProgress"};
    public final String[] moduleProgressColumns = {"Student Email", "Course", "ModuleID", "Progress"};
    public final String[] top3WebcastsColumns = {"WebcastID","Title","Viewed"};
    public final String[] studentsPassedInCourseColumns = {"Course","Passed"};
    public final String[] progressInModuleColumns = {"Email","ContentPerc","ModuleTitle"};
    public final String[] progressInWebcastColumns = {"Email","ContentPerc","WebcastTitle"};

    public MixedDAO(){
        //Pakt de SQL class, deze handelt de verbinding en het sturen en ontvangen van de query's en hun replies
        sql = new MixedSQL();
        //Pakt eerst een connection, deze is nodig om een prepared statement op te maken
        connection = sql.getConnection();
    }

    public ArrayList<String[]> getAverageProgressForCourse(String courseName) {
        //De query met ? ipv de waarde
        String rawquery = "SELECT Name AS Course, Module.ModuleID, AVG(ContentPerc) AS AverageProgress FROM Course JOIN Module ON Course.Name = Module.Course JOIN ContentItem ON Module.ModuleID = ContentItem.ModuleID JOIN Progress ON ContentItem.ContentItemID = Progress.ContentItemID WHERE Course.Name = ? GROUP BY Name, Module.ModuleID;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, courseName);
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery(averageProgressForCourseColumns,preparedStatement);
                //Slaat de resultaten op in deze Arraylist
                ArrayList<String[]> list = new ArrayList<>();
                if(tableList.size() > 0){
                    for(ArrayList<String> row : tableList){
                        //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                        String course = row.get(0);
                        String moduleID = row.get(1);
                        String averageProgress = row.get(2);
                        String[] stringArray = {course,moduleID,averageProgress};
                        list.add(stringArray);
                    }
                }
                return list;
        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
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

    public ArrayList<String[]> getModuleProgress(String courseName,String studentEmail) {
        //De query met ? ipv de waarde
        String rawquery = "SELECT Email AS Student Email, Name AS Course, Module.ModuleID, ContentPerc AS Progress FROM Progress JOIN ContentItem ON Progress.ContentItemID = ContentItem.ContentItemID JOIN Module ON ContentItem.ModuleID = Module.ModuleID JOIN Course ON Module.Course = Course.Name WHERE Progress.Email = ? AND Course.Name = ?;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
        preparedStatement.setString(1, studentEmail);
        preparedStatement.setString(2, courseName);
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery(moduleProgressColumns,preparedStatement);
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
            if(SQL.printSQLException(e)){
                return this.nothingFound();
            }
            // print SQL exception information
            SQL.printSQLException(e);
        }
    return null;
    } 
    



    public ArrayList<String[]> getTop3Webcasts() {
        //De query met ? ipv de waarde
        String rawquery = "SELECT TOP 3 Webcast.WebcastID, Webcast.Title, COUNT(Progress.ContentItemID) AS Viewed FROM Webcast JOIN ContentItem ON Webcast.WebcastID = ContentItem.WebcastID JOIN Progress ON ContentItem.ContentItemID = Progress.ContentItemID GROUP BY Webcast.WebcastID, Webcast.Title ORDER BY Viewed DESC;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery(top3WebcastsColumns,preparedStatement);
                //Slaat de resultaten op in deze Arraylist
                ArrayList<String[]> list = new ArrayList<>();
                if(tableList.size() > 0){
                    for(ArrayList<String> row : tableList){
                        //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                        String webcastID = row.get(0);
                        String title = row.get(1);
                        String viewed = row.get(2);
                        String[] stringArray = {webcastID,title,viewed};
                        list.add(stringArray);
                    }
                }
                return list;
        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
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

    public ArrayList<String[]> getStudentsPassedInCourse(String courseInput) {
        //De query met ? ipv de waarde
        String rawquery = "SELECT Course.Name AS Course, COUNT(Signup.CertificateID) AS Passed FROM Course  JOIN Signup ON Course.Name = Signup.Course WHERE Course.Name = ? GROUP BY Name;";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
                //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
                preparedStatement.setString(1, courseInput);
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery(studentsPassedInCourseColumns,preparedStatement);
                //Slaat de resultaten op in deze Arraylist
                ArrayList<String[]> list = new ArrayList<>();
                if(tableList.size() > 0){
                    for(ArrayList<String> row : tableList){
                        //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                        String course = row.get(0);
                        String passed = row.get(1);
                        String[] stringArray = {course,passed};
                        list.add(stringArray);
                    }
                }
                return list;
        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
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

    public ArrayList<String[]> getProgressInModule(String studentEmail,int moduleID) {
        //De query met ? ipv de waarde
        String rawquery = "SELECT Student.Email,Progress.ContentPerc, Module.Title AS ModuleTitle  FROM (((Student INNER JOIN Progress ON Student.Email = Progress.Email) INNER JOIN ContentItem ON Progress.ContentItemID = ContentItem.ContentItemID) INNER JOIN Module ON ContentItem.ModuleID = Module.ModuleID) WHERE Student.Email = ? AND Module.ModuleID = ?";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
                //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
                preparedStatement.setString(1, studentEmail);
                preparedStatement.setInt(2, moduleID);
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery(progressInModuleColumns,preparedStatement);
                //Slaat de resultaten op in deze Arraylist
                ArrayList<String[]> list = new ArrayList<>();
                if(tableList.size() > 0){
                    for(ArrayList<String> row : tableList){
                        //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                        String email = row.get(0);
                        String course = row.get(1);
                        String moduleTitle = row.get(2);
                        String[] stringArray = {email,course,moduleTitle};
                        list.add(stringArray);
                    }
                }
                return list;
        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
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


    public ArrayList<String[]> getProgressInWebcast(String studentEmail,int webcastID) {
        //De query met ? ipv de waarde
        String rawquery = "SELECT Student.Email,Progress.ContentPerc, Webcast.Title AS WebcastTitle FROM (((Student INNER JOIN Progress ON Student.Email = Progress.Email) INNER JOIN ContentItem ON Progress.ContentItemID = ContentItem.ContentItemID) INNER JOIN Webcast ON ContentItem.ModuleID = Webcast.WebcastID) WHERE Student.Email = ? AND Webcast.WebcastID = ?";
        //Probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
                //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten, begint op 1 met tellen
                preparedStatement.setString(1, studentEmail);
                preparedStatement.setInt(2, webcastID);
                //Geeft de prepared statement mee aan SQL readquery
                ArrayList<ArrayList<String>> tableList = sql.readQuery(progressInWebcastColumns,preparedStatement);
                //Slaat de resultaten op in deze Arraylist
                ArrayList<String[]> list = new ArrayList<>();
                if(tableList.size() > 0){
                    for(ArrayList<String> row : tableList){
                        //Pakt per rij alle waarden en slaat ze op in de de arraylist als het goede object
                        String email = row.get(0);
                        String course = row.get(1);
                        String webcastTitle = row.get(2);
                        String[] stringArray = {email,course,webcastTitle};
                        list.add(stringArray);
                    }
                }
                return list;
        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
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

    public ArrayList<String[]> nothingFound(){
        ArrayList<String[]> list = new ArrayList<>();
        String[] stringArray = {"nothingFound"};
        list.add(stringArray);
        return list;
    }


}
