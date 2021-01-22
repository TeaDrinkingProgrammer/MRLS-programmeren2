package CCStatistics.DAO;


import java.util.ArrayList;


import CCStatistics.Domain.Student;

public class StudentDAO{
    //Pakt de connectionURL van login zodat deze aan te passen is. Geeft ook de mogelijkheid voor bijv. meerdere connectionURLS
    SQL sql = new SQL();
    
    public ArrayList<Student> genericReadQuery(String query){
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Student",query);
        ArrayList<Student> students = new ArrayList<>();
        if(tableList.size() > 0){
            for(ArrayList<String> row : tableList){
                String email = row.get(0);
                String firstName = row.get(1);
                String lastName = row.get(2);
                String dateOfBirth = row.get(3);
                String gender = row.get(4);
                String street = row.get(5);
                String houseNumber = row.get(6);
                String city = row.get(7);
                String country = row.get(8);
                String postalcode = row.get(9);
                students.add(new Student(email, firstName, lastName, dateOfBirth, gender, street, houseNumber, city, country, postalcode));
            }
        } else{
            String nullDate = "1-1-1000";
            students.add(new Student("No Students found!", "null", "null",nullDate,"null","null","null","null","null","null"));
        }
        return students;
    }
    public ArrayList<Student> getAll() {
        String query = "SELECT * FROM Course;";
        return this.genericReadQuery(query);
    }

    public ArrayList<Student> getModulesPercentage(String contentItem) {
        String query = "";
        return this.genericReadQuery(query);
    }
    
    public void updateCourse(String courseName,String column, String changeInto) {
        String query = "UPDATE Course SET " + column + "= '" + changeInto +"' WHERE Name = '" + courseName + " ';";
        sql.cudQuery(query);
    }




    
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
