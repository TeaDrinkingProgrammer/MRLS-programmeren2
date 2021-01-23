package CCStatistics.Domain;

public class Certificate {
    private int certificateID;
    private double grade;
    private String employeeName;

    public Certificate( double grade, String employeeName) {
        this.grade = grade;
        this.employeeName = employeeName;
    }

    public Certificate(int certificateID, double grade, String employeeName) {
        this.certificateID = certificateID;
        this.grade = grade;
        this.employeeName = employeeName;
    }

    public int getCertificateID() {
        return this.certificateID;
    }

    public void setCertificateID(int certificateID) {
        this.certificateID = certificateID;
    }

    public double getGrade() {
        return this.grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
