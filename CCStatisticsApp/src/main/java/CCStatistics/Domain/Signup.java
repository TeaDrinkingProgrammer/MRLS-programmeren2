package CCStatistics.Domain;

public class Signup {
    private int signupID;
    private Course course;
    private String signupDate;
    private Certificate certificate;

    public Signup(int signupID, Course course, String signupDate) {
        this.signupID = signupID;
        this.course = course;
        this.signupDate = signupDate;
    }

    public Signup(int signupID, Course course, String signupDate, Certificate certificate) {
        this.signupID = signupID;
        this.course = course;
        this.signupDate = signupDate;
        this.certificate = certificate;
    }

    public void addCertificate(int id, double grade, String employeeName) {
        Certificate certificate = new Certificate(id, grade, employeeName);
        this.certificate = certificate;
    }

    public int getSignupID() {
        return this.signupID;
    }

    public void setSignupID(int signupID) {
        this.signupID = signupID;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSignupDate() {
        return this.signupDate;
    }

    public void setSignupDate(String signupDate) {
        this.signupDate = signupDate;
    }

    public Certificate getCertificate() {
        return this.certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }
}
