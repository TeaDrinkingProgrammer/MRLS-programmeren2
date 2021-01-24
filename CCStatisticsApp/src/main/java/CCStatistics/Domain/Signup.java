package CCStatistics.Domain;

public class Signup {
    private int signupID;
    private String signupDate;
    private Course course;
    private Certificate certificate;

    public Signup(String signupDate, Course course) {
        this.signupDate = signupDate;
        this.course = course;
    }

    public Signup(int signupID, String signupDate, Course course) {
        this.signupID = signupID;
        this.signupDate = signupDate;
        this.course = course;
    }

    public Signup(int signupID, String signupDate, Course course, Certificate certificate) {
        this.signupID = signupID;
        this.signupDate = signupDate;
        this.course = course;
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

    public String getSignupDate() {
        return this.signupDate;
    }

    public void setSignupDate(String signupDate) {
        this.signupDate = signupDate;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Certificate getCertificate() {
        return this.certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }
}
