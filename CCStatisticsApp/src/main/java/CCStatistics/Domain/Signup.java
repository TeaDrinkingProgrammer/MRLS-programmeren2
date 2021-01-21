package CCStatistics.Domain;

import java.util.Date;

public class Signup {
    private Course course;
    private String email;
    private Date signupDate;
    private Certificate certificate;

    public Signup (Course course, Date signupDate) {
        this.course = course;
        this.signupDate = signupDate;
    }

    public void addCertificate(int id, double grade, String employeeName) {
        Certificate certificate = new Certificate(id, grade, employeeName);
        this.certificate = certificate;
    }
}
