package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import CCStatistics.Domain.Course;
import CCStatistics.Domain.LevelEnum;

public class CourseDAOWithPrepStatement{
    SQLWithPrepStatement sql = null;
    Connection connection = null;
    public CourseDAOWithPrepStatement(){
        //Pakt de connectionURL van login zodat deze aan te passen is. Geeft ook de mogelijkheid voor bijv. meerdere connectionURLS
        sql = new SQLWithPrepStatement();
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
                LevelEnum level = LevelEnum.valueOf(row.get(3));
                courses.add(new Course(name,subject,introText,level));
            }
        } else{
            courses.add(new Course("No courses found!", "Subject", "IntroText", LevelEnum.valueOf("Beginner")));
        }
        return courses;
    }
    public ArrayList<Course> getAll() {
        String rawquery = "SELECT * FROM Course";
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
            return genericReadQuery(preparedStatement);
            } catch (SQLException e){
                // print SQL exception information
                SQLWithPrepStatement.printSQLException(e);
        }
        return null;
    }
    public ArrayList<Course> getCoursesInterestingTo(String courseName) {
        //de query met ? ipv de waarde
        String rawquery = "SELECT * FROM Course WHERE Name IN(SELECT InterestingCourseName FROM InterestingToCourse WHERE CourseName = ?)";
        //probeert het eerste deel van de statement te sturen
        try(PreparedStatement preparedStatement = connection.prepareStatement(rawquery)){
        //Stuurt de eerste waarde mee om in de plaats van het vraagteken te zetten
        preparedStatement.setString(0, courseName);
        //Geeft deze statement mee aan genericreadquery

        //Omdat de verbinding ook fout kan gaan is hier ook een catch voor SQLexception
        return genericReadQuery(preparedStatement);
        } catch (SQLException e){
            // print SQL exception information
            SQLWithPrepStatement.printSQLException(e);
    }
    return null;
}
    
    // public void updateCourse(String courseName,String column, String changeInto) {
    //     String query = "UPDATE Course SET " + column + "= '" + changeInto +"' WHERE Name = '" + courseName + " ' ";
    //     sql.cudQuery(query);
    // }




    
    // public ArrayList<Course> getCoursesInterestingTo(String courseName, String loginName){
                
    //     // Dit zijn de instellingen voor de verbinding. Vervang de databaseName indien deze voor jou anders is.
    //     String connectionUrl = "";
        
    //     connectionUrl = login.getLogin(loginName);
        
    //     if (connectionUrl.equals("wrongUser")) {
    //         return new ArrayList<>();
    //     }
        

    //     // Connection beheert informatie over de connectie met de database.
    //     Connection con = null;

    //     // Statement zorgt dat we een SQL query kunnen uitvoeren.
    //     Statement stmt = null;

    //     // ResultSet is de tabel die we van de database terugkrijgen.
    //     // We kunnen door de rows heen stappen en iedere kolom lezen.
    //     ResultSet rs = null;

    //     ArrayList<Course> courses = new ArrayList<>();

    //     try {
    //         // 'Importeer' de driver die je gedownload hebt.
    //         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    //         // Maak de verbinding met de database.
    //         con = DriverManager.getConnection(connectionUrl);

    //         // Neemt de courses die interesant zijn voor de geinputte course uit InterestingToCourse en geeft alle info uit de Course tabel terug.
    //         String SQL = "SELECT * FROM Course WHERE Name IN(SELECT InterestingCourseName FROM InterestingToCourse WHERE CourseName = '" + courseName + "')";
    //         stmt = con.createStatement();
    //         // Voer de query uit op de database.
    //         rs = stmt.executeQuery(SQL);

    //         // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
    //         while (rs.next()) {
    //             // Vraag per row de kolommen in die row op.
    //             String name = rs.getString("Name");
    //             String subject = rs.getString("Subject");
    //             String introText = rs.getString("IntroText");
    //             LevelEnum level = LevelEnum.valueOf(rs.getString("Level"));
    //             //Cast de individuele items uit het queryresultaat als course in de arraylist
    //             courses.add(new Course(name,subject,introText,level));
    //         }

    //         if (courses.isEmpty()) {
                
    //         }
    //     }

    //     // Handles mogelijke errors
    //     catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     finally {
    //         if (rs != null) try { rs.close(); } catch(Exception e) {}
    //         if (stmt != null) try { stmt.close(); } catch(Exception e) {}
    //         if (con != null) try { con.close(); } catch(Exception e) {}
    //     }
    //     System.out.println(courses.toString());
    //     return courses;
    // }

}
