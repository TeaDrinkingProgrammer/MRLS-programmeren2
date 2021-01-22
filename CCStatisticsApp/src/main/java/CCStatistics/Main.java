
package CCStatistics;
import javafx.application.Application;
import CCStatistics.DAO.CertificateDAO;
import CCStatistics.GUI.CourseInteresting;


public class Main {    
    public static void main(String[] args) {
        Application.launch(CourseInteresting.class);
        // CertificateDAO newOne = new CertificateDAO();
        // newOne.createCertificate(7.5, "Jan");
    }
}
