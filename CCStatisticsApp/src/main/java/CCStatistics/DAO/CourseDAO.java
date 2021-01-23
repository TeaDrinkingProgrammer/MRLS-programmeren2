package CCStatistics.DAO;

import java.util.ArrayList;

import CCStatistics.Domain.Course;
import CCStatistics.Domain.EnumLevel;

public class CourseDAO{
    //Pakt de connectionURL van login zodat deze aan te passen is. Geeft ook de mogelijkheid voor bijv. meerdere connectionURLS
    private SQL sql = new SQL();
    
    public ArrayList<Course> genericReadQuery(String query){
        ArrayList<ArrayList<String>> tableList = sql.readQuery("Course",query);
        ArrayList<Course> courses = new ArrayList<>();
        if(tableList.size() > 0){
            for(ArrayList<String> row : tableList){
                String name = row.get(0);
                String subject = row.get(1);
                String introText = row.get(2);
                EnumLevel level = EnumLevel.valueOf(row.get(3));
                courses.add(new Course(name,subject,introText,level));
            }
        } else{
            courses.add(new Course("No courses found!", "Subject", "IntroText", EnumLevel.valueOf("Beginner")));
        }
        return courses;
    }
    public ArrayList<Course> getAll() {
        String query = "SELECT * FROM Course";
        return genericReadQuery(query);
    }

    public ArrayList<Course> getCoursesInterestingTo(String courseName) {
        String query = "SELECT * FROM Course WHERE Name IN(SELECT InterestingCourseName FROM InterestingToCourse WHERE CourseName = '" + courseName + "')";
        return genericReadQuery(query);
    }
    
    public void updateCourse(String courseName,String column, String changeInto) {
        String query = "UPDATE Course SET " + column + "= '" + changeInto +"' WHERE Name = '" + courseName + " ' ";
        sql.updateQuery(query);
    }
}
