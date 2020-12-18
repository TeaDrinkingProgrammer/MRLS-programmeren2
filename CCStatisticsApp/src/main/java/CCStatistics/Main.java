package CCStatistics;

import java.util.ArrayList;

import CCStatistics.DAO.CourseDAO;
import CCStatistics.Domain.Course;




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

