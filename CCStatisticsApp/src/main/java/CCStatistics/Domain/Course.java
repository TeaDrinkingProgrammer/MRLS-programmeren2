package CCStatistics.Domain;

import java.util.ArrayList;

public class Course {
    private String name;
    private String subject;
    private String introText;
    private EnumLevel level;
    private ArrayList<Module> modules;
    private ArrayList<Course> interestingCourses;

    public Course(String name, String subject, String introText, EnumLevel level) {
        this.name = name;
        this.subject = subject;
        this.introText = introText;
        this.level = level;
        this.modules = null;
        this.interestingCourses = null;
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }

    public void addInterestingCourse(Course course) {
        this.interestingCourses.add(course);
    }

    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getIntroText() {
        return this.introText;
    }

    public EnumLevel getLevel() {
        return this.level;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public ArrayList<Course> getInterestingCourses() {
        return this.interestingCourses;
    }
}