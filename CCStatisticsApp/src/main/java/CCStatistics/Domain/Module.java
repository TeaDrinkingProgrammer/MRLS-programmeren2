package CCStatistics.Domain;

import java.util.HashMap;

public class Module extends ContentItem {
    private double version;
    private int followupNr;
    private String contactName;
    private String contactEmail;

    public Module(int contentItemId, String title, EnumStatus status, String publicationDate, String description, double version, int followupNr, String contactName, String contactEmail) {
        super.contentItemID = contentItemID;
        super.title = title;
        super.status = status;
        super.publicationDate = publicationDate;
        super.description = description;
        super.progress = new HashMap<>();
        this.version = version;
        this.followupNr = followupNr;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public double getVersion() {
        return this.version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public int getFollowupNr() {
        return this.followupNr;
    }

    public void setFollowupNr(int followupNr) {
        this.followupNr = followupNr;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
