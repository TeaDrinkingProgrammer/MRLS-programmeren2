
package CCStatistics;


import java.util.ArrayList;

import javafx.application.Application;
import CCStatistics.DAO.CourseDAO;
import CCStatistics.Domain.Course;
import CCStatistics.GUI.CourseInteresting;


public class Main {
    // public static void main(String[] args) {
    // CourseDAO test = new CourseDAO();
    // ArrayList<String> courses = test.getCoursesInterestingTo("TestCourse");
    //      for (String course : courses) {
    //          System.out.println(course);
    //      }
    //     }
    // }

    
    public static void main(String[] args) {
        Application.launch(CourseInteresting.class);
    }
}
