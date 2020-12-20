package CCStatistics.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import CCStatistics.Domain.Course;
import CCStatistics.Domain.LevelEnum;

public class CourseDAO{
    //Pakt de connectionURL van login zodat deze aan te passen is. Geeft ook de mogelijkheid voor bijv. meerdere connectionURLS
    Login login = new Login();


    public ArrayList<Course> getAll(String loginName) {
        
        
        // Dit zijn de instellingen voor de verbinding. Vervang de databaseName indien deze voor jou anders is.
        String connectionUrl = login.getLogin(loginName);

        // Connection beheert informatie over de connectie met de database.
        Connection con = null;

        // Statement zorgt dat we een SQL query kunnen uitvoeren.
        Statement stmt = null;

        // ResultSet is de tabel die we van de database terugkrijgen.
        // We kunnen door de rows heen stappen en iedere kolom lezen.
        ResultSet rs = null;

        ArrayList<Course> courses = new ArrayList<>();

        try {
            // 'Importeer' de driver die je gedownload hebt.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Maak de verbinding met de database.
            con = DriverManager.getConnection(connectionUrl);

            // Stel een SQL query samen.
            String SQL = "SELECT * FROM Course";
            stmt = con.createStatement();
            // Voer de query uit op de database.
            rs = stmt.executeQuery(SQL);
            //System.out.print(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));

            // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
            while (rs.next()) {
                // Vraag per row de kolommen in die row op.
                String name = rs.getString("Name");
                String subject = rs.getString("Subject");
                String introText = rs.getString("IntroText");
                LevelEnum level = LevelEnum.valueOf(rs.getString("Level"));
                courses.add(new Course(name,subject,introText,level));
                // Print de kolomwaarden.
                // System.out.println(ISBN + " " + title + " " + author);

                // Met 'format' kun je de string die je print het juiste formaat geven, als je dat wilt.
                // %d = decimal, %s = string, %-32s = string, links uitgelijnd, 32 characters breed.
                //System.out.format("| %-32s | %-32s | %-24s | %-24s | \n", name, subject, introText,level);
            }
            //System.out.println(String.format("| %7s | %-32s | %-24s |\n", " ", " ", " ").replace(" ", "-"));

        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }

        return courses;
    }

    public ArrayList<Course> getCoursesInterestingTo(String courseName, String loginName){
                
        // Dit zijn de instellingen voor de verbinding. Vervang de databaseName indien deze voor jou anders is.
        String connectionUrl = "";
        
        connectionUrl = login.getLogin(loginName);
        
        if (connectionUrl.equals("wrongUser")) {
            return new ArrayList<>();
        }
        

        // Connection beheert informatie over de connectie met de database.
        Connection con = null;

        // Statement zorgt dat we een SQL query kunnen uitvoeren.
        Statement stmt = null;

        // ResultSet is de tabel die we van de database terugkrijgen.
        // We kunnen door de rows heen stappen en iedere kolom lezen.
        ResultSet rs = null;

        ArrayList<Course> courses = new ArrayList<>();

        try {
            // 'Importeer' de driver die je gedownload hebt.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Maak de verbinding met de database.
            con = DriverManager.getConnection(connectionUrl);

            // Neemt de courses die interesant zijn voor de geinputte course uit InterestingToCourse en geeft alle info uit de Course tabel terug.
            String SQL = "SELECT * FROM Course WHERE Name IN(SELECT InterestingCourseName FROM InterestingToCourse WHERE CourseName = '" + courseName + "')";
            stmt = con.createStatement();
            // Voer de query uit op de database.
            rs = stmt.executeQuery(SQL);

            // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
            while (rs.next()) {
                // Vraag per row de kolommen in die row op.
                String name = rs.getString("Name");
                String subject = rs.getString("Subject");
                String introText = rs.getString("IntroText");
                LevelEnum level = LevelEnum.valueOf(rs.getString("Level"));
                //Cast de individuele items uit het queryresultaat als course in de arraylist
                courses.add(new Course(name,subject,introText,level));
            }
        }

        // Handles mogelijke errors
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        System.out.println(courses.toString());
        return courses;
    }
}
