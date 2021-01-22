package CCStatistics.Domain;

import java.util.Date;

public class Signup {
    private int signupID;
    private Course course;
    private String signupDate;
    private Certificate certificate;

    public Signup (int signupID, Course course, String signupDate) {
        this.signupID = signupID;
        this.course = course;
        this.signupDate = signupDate;
    }

    public void addCertificate(int id, double grade, String employeeName) {
        Certificate certificate = new Certificate(id, grade, employeeName);
        this.certificate = certificate;
    }
}
