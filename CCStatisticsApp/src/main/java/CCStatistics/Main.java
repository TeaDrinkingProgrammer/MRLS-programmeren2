
package CCStatistics;

import javafx.application.Application;
import CCStatistics.DAO.CertificateDAOWithPrepStatement;
import CCStatistics.DAO.CourseDAOWithPrepStatement;
import CCStatistics.Domain.Certificate;
import CCStatistics.Domain.Course;
import CCStatistics.Domain.Student;
import CCStatistics.GUI.CourseInteresting;
import CCStatistics.Logic.StudentLogic;

public class Main {    
    public static void main(String[] args) {
        //Application.launch(CourseInteresting.class);
        // CertificateDAO newOne = new CertificateDAO();
        // newOne.createCertificate(7.5, "Jan");
        
        CourseDAOWithPrepStatement testdao = new CourseDAOWithPrepStatement();
        for(Course certificate : testdao.getAll()){
            System.out.println(certificate.getName());
            System.out.println(certificate.getSubject());
            System.out.println(certificate.getIntroText());
            System.out.println(certificate.getLevel());
            System.out.println("--------------------------");
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        for(Course certificate : testdao.getCoursesInterestingTo("TestCourse1")){
            System.out.println(certificate.getName());
            System.out.println(certificate.getSubject());
            System.out.println(certificate.getIntroText());
            System.out.println(certificate.getLevel());
            System.out.println("--------------------------");
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        for(Course certificate : testdao.read("TestCourse1")){
            System.out.println(certificate.getName());
            System.out.println(certificate.getSubject());
            System.out.println(certificate.getIntroText());
            System.out.println(certificate.getLevel());
            System.out.println("--------------------------");
        }
        // testdao.delete(6);

        // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        // testdao.create("Wassim",1.0);

        //testdao.updateGrade(7, 1);
        
    }
}
