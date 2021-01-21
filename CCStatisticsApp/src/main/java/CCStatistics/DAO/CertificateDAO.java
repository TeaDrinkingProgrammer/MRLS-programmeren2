
package CCStatistics.DAO;

import CCStatistics.Domain.Certificate;
import java.util.ArrayList;


public class CertificateDAO {

    public CertificateDAO() {
    }

    SQL sql = new SQL();
    
    public ArrayList<Certificate> genericReadQuery(String query){
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Certificate", query);
        ArrayList<Certificate> certificates = new ArrayList<>();
        if(tableList.size() > 0){
            for(ArrayList<String> row : tableList){
                int certificateID  = Integer.valueOf(row.get(0));
                double grade = Double.valueOf(row.get(1));
                String employeeName = row.get(2);
                
              certificates.add(new Certificate(certificateID, grade, employeeName));
            }
        } else{
            //Date nullDate = ;
            //students.add(new Student("No Students found!", "null", "null",date,"null","null","null","null","null","null"));
        }
        return certificates;
    }
//Maakt een certificate aan (CREATE)
    public void createCertificate(double grade, String employeeName) {
        String query = "INSERT INTO Certificate (EmployeeName, Grade) VALUES ('" + employeeName + "'," + grade + ");";
        sql.cudQuery(query);
    }
//Leest alle certificaten (READ)
    public ArrayList<Certificate> getAllCertificates() {
        String query = "SELECT * FROM Certificate;";
        return this.genericReadQuery(query);
    }
//Updatet een certificaat of meerdere certificaten (UPDATE)   
    public void updateCertificate(String columnToChange, String columnToCheck, String changeInto, String valueIs) {
        String query = "UPDATE Certificate SET " + columnToChange + "= '" + changeInto + "' WHERE " + columnToCheck + " = '" + valueIs + "';";
        sql.cudQuery(query);
    }

    public void updateCertificate(String columnToChange, String columnToCheck, String changeInto, Double valueIs) {
        String query = "UPDATE Certificate SET " + columnToChange + "= '" + changeInto + "' WHERE " + columnToCheck + " = '" + valueIs + "';";
        sql.cudQuery(query);
    }
//Deletet een certificaat of meerdere certificaten (DELETE)
    public void deleteCertificate(int valueIs) {
        String query = "DELETE FROM Certificate WHERE CertificateID = '" + valueIs + "';";
        sql.cudQuery(query);
    }
}
