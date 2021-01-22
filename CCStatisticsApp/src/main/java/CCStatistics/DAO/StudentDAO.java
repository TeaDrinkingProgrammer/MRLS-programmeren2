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
        sql.updateQuery(query);
    }
}
