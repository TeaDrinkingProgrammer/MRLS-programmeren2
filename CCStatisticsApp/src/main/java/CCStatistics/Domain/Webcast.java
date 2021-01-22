package CCStatistics.Domain;

import java.util.HashMap;

public class Webcast extends ContentItem {
    private int duration;
    private String URL;
    private String speakerName;
    private String organization;

    public Webcast(int duration, String URL, String speakerName, String organization) {
        super.contentItemID = contentItemID;
        super.title = title;
        super.status = status;
        super.publicationDate = publicationDate;
        super.description = description;
        super.progress = new HashMap<>();
        this.duration = duration;
        this.URL = URL;
        this.speakerName = speakerName;
        this.organization = organization;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getSpeakerName() {
        return this.speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
