
package CCStatistics;

import javafx.application.Application;
import CCStatistics.GUI.HomeScreen;


public class Main {    
    public static void main(String[] args) {
        Application.launch(HomeScreen.class);
    //TestData voor de DAOs

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
        //testdao.updateGrade(7, 1);
        
    }
}
