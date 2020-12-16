package CCStatistics;

import java.util.ArrayList;

import CCStatistics.DAO.CourseDAO;
import CCStatistics.Domain.Course;
public class Main {
    public static void main(String[] args) throws Exception {
        CourseDAO test = new CourseDAO();
        ArrayList<Course> courses = test.getAll();
        for (Course course : courses) {
            System.out.println(course.getName());
        }
    }
}
