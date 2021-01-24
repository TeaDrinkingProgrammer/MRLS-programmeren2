
package CCStatistics;

import javafx.application.Application;
import CCStatistics.DAO.CertificateDAO;
import CCStatistics.DAO.CourseDAO;
import CCStatistics.DAO.MixedDAO;
import CCStatistics.Domain.Certificate;
import CCStatistics.Domain.Course;
import CCStatistics.Domain.Student;
import CCStatistics.GUI.CourseInteresting;
import CCStatistics.GUI.GUITemplate;
import CCStatistics.GUI.HomeScreen;
import CCStatistics.GUI.UDScreen;
import CCStatistics.Logic.MixedLogic;
import CCStatistics.Logic.StudentLogic;

public class Main {    
    public static void main(String[] args) {
        //Application.launch(HomeScreen.class);
        Application.launch(GUITemplate.class);
        // CertificateDAO newOne = new CertificateDAO();
        // newOne.createCertificate(7.5, "Jan");
        
    //    MixedDAO testdao = new MixedDAO();
    //     for(String[] stringArray: testdao.getTop3Webcasts()){
    //         for(String string : stringArray){
    //             System.out.println(string);
    //         }
    //     }
    //     System.out.println("%%%%%%%%%%");
    //     MixedLogic newOne = new MixedLogic();
    //     for (String[] stringArray : newOne.getAverageProgressForCourse("TestCourse1")) {
    //         for (String string : stringArray) {
    //             System.out.println(string);
    //         }
    //     }
    //     System.out.println("%%%%%%%%%%");
    //     for (String[] stringArray : newOne.getProgressInWebcast("student@live.com", 1)) {
    //         for (String string : stringArray) {
    //             System.out.println(string);
    //         }
    //     }
        

        // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        // for(Course certificate : testdao.getCoursesInterestingTo("TestCourse1")){
        //     System.out.println(certificate.getName());
        //     System.out.println(certificate.getSubject());
        //     System.out.println(certificate.getIntroText());
        //     System.out.println(certificate.getLevel());
        //     System.out.println("--------------------------");
        // }
        // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        // for(Course certificate : testdao.read("TestCourse1")){
        //     System.out.println(certificate.getName());
        //     System.out.println(certificate.getSubject());
        //     System.out.println(certificate.getIntroText());
        //     System.out.println(certificate.getLevel());
        //     System.out.println("--------------------------");
        // }
        // testdao.delete(6);

        // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        // testdao.create("Wassim",1.0);

        //testdao.updateGrade(7, 1);
        
    }
}
