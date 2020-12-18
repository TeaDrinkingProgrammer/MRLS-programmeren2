package CCStatistics.Logic;

import java.util.ArrayList;

import CCStatistics.DAO.CourseDAO;
import CCStatistics.Domain.Course;

public class CourseInterestingToLogic {
    CourseDAO courseDAO = new CourseDAO();
    public ArrayList<Course> getAllCourses(){
        return courseDAO.getAll();
    }

    public ArrayList<Course> getAllInterestingCourses(Course course){
       return courseDAO.getCoursesInterestingTo(course.getName());
    }
}
