package CCStatistics.Logic;

import java.util.ArrayList;

import CCStatistics.DAO.CourseDAO;
import CCStatistics.Domain.Course;

public class CourseInterestingToLogic {

    CourseDAO courseDAO = new CourseDAO();

    public ArrayList<Course> getAllCourses(String loginName) {
        return courseDAO.getAll(loginName);
    }

    public ArrayList<Course> getAllInterestingCourses(String courseName, String loginName) {
        if (courseDAO.getCoursesInterestingTo(courseName, loginName).isEmpty()) {
            return null;
        }
        
        return courseDAO.getCoursesInterestingTo(courseName, loginName);
    }
}
