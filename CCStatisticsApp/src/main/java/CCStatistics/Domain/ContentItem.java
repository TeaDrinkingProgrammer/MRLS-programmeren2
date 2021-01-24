package CCStatistics.Domain;

import java.util.HashMap;

public abstract class ContentItem {
    protected int contentItemID;
    protected String title;
    protected EnumStatus status;
    protected String publicationDate;
    protected String description;
    protected HashMap<Student, Double> progress;

    public void addParticipant(Student student) {
        progress.put(student, 0.0);
    }

    public void advanceParticipant(Student student, double advanceByPerc) {
        progress.put(student, advanceByPerc);
    }
}
