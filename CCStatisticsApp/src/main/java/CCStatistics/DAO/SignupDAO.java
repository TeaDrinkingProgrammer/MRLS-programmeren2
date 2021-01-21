package CCStatistics.DAO;

import java.util.ArrayList;
import java.util.Date;

import CCStatistics.Domain.Signup;

public class SignupDAO {
    SQL sql = new SQL();
    
    public ArrayList<Signup> genericReadQuery(String query){
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Signup", query);
        ArrayList<Signup> signUps = new ArrayList<>();
        if(tableList.size() > 0){
            for(ArrayList<String> row : tableList){
                String signupDate = row.get(0);
                String courseName = row.get(1);
                String studentEmail = row.get(2);
                String certificateID = row.get(3);
//Schrijf code om signup met certificateID en email toe te voegen                
//              signUps.add(new Signup(email, ));
            }
        } else{
            //Date nullDate = ;
            //students.add(new Student("No Students found!", "null", "null",date,"null","null","null","null","null","null"));
        }
        return signUps;
    }
//Maakt een aanmelding aan (CREATE)
    public void creatSignup(Date date, String courseName, String email) {
        String query = "INSERT INTO Signup (Signupdate, CourseName, StudentEmail) VALUES (" + date + "," + courseName + "," + email + ");";
        sql.cudQuery(query);
    }
//Leest alle aanmeldingen (READ)
    public ArrayList<Signup> getAllSignups() {
        String query = "SELECT * FROM Signup;";
        return this.genericReadQuery(query);
    }
//Updatet een aanmelding of meerdere aanmeldingen (UPDATE)   
    public void updateSignup(String columnToChange, String columnToCheck, String changeInto, String valueIs) {
        String query = "UPDATE Signup SET " + columnToChange + "= '" + changeInto + "' WHERE " + columnToCheck + " = '" + valueIs + "';";
        sql.cudQuery(query);
    }
//Deletet een aanmelding of meerdere aanmeldingen (DELETE)
    public void deleteSignup(String columnToCheck, String valueIs) {
        String query = "DELETE FROM Signup WHERE " + columnToCheck + "= '" + valueIs + "';";
        sql.cudQuery(query);
    }
}