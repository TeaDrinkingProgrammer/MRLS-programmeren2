package CCStatistics.Logic;

import java.util.ArrayList;

// import CCStatistics.DAO.CourseDAO;
import CCStatistics.DAO.CourseDAOWithPrepStatement;
import CCStatistics.Domain.Course;

public class CourseInterestingToLogic {

    CourseDAOWithPrepStatement courseDAO = new CourseDAOWithPrepStatement();

    public ArrayList<Course> getAllCourses(String loginName) {
        return courseDAO.getAll();
    }

    public ArrayList<Course> getAllInterestingCourses(String courseName) {
        if (courseDAO.getCoursesInterestingTo(courseName).isEmpty()) {
            return null;
        }
        
        return courseDAO.getCoursesInterestingTo(courseName);
    }
}
