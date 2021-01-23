
package CCStatistics;
import javafx.application.Application;
import CCStatistics.Domain.Student;
import CCStatistics.GUI.CourseInteresting;
import CCStatistics.Logic.StudentLogic;

public class Main {    
    public static void main(String[] args) {
        //Application.launch(CourseInteresting.class);
        // CertificateDAO newOne = new CertificateDAO();
        // newOne.createCertificate(7.5, "Jan");
        StudentLogic logic = new StudentLogic();

        Student student = new Student("email@live.com", "firstName", "lastName", "01-01-2020", "M", "street", "Number", "city", "country", "4827GC");
        logic.create(student);

        
    }
}
