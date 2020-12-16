package CCStatistics.Domain;

import java.util.ArrayList;

public class Course {
    private String name;
    private String subject;
    private String introText;
    private LevelEnum level;
    private ArrayList<Module> modules;

    public Course(String name, String subject, String introText, LevelEnum level) {
        this.name = name;
        this.subject = subject;
        this.introText = introText;
        this.level = level;
        this.modules = null;
        this.relevantTo = null;
    }
    private ArrayList<Course> relevantTo;


    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getIntroText() {
        return this.introText;
    }

    public LevelEnum getLevel() {
        return this.level;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public ArrayList<Course> getRelevantTo() {
        return this.relevantTo;
    }
}