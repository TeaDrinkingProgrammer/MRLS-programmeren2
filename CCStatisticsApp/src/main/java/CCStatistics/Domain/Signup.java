package CCStatistics.Domain;

public class Signup {
    private Course course;
    private String email;
    private String signupDate;
    private Certificate certificate;

    public Signup (Course course, String signupDate) {
        this.course = course;
        this.signupDate = signupDate;
    }

    public void addCertificate(int id, double grade, String employeeName) {
        Certificate certificate = new Certificate(id, grade, employeeName);
        this.certificate = certificate;
    }
}
