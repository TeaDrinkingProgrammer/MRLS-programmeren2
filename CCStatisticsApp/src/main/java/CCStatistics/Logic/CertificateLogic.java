package CCStatistics.Logic;

import java.util.ArrayList;
import CCStatistics.DAO.CertificateDAO;
import CCStatistics.DAO.SQL;
import CCStatistics.Domain.Certificate;

public class CertificateLogic implements Logic<Certificate> {
    private CertificateDAO certificateDAO = new CertificateDAO();
    private SQL sql = new SQL();
    private ArrayList<String> columns = new ArrayList<>();

    // Haal alle studenten op in de DAO
    @Override
    public ArrayList<Certificate> getAll() {
        return certificateDAO.getAll();
    }

    // Verwijder student via DAO
    public String delete(int ID) {
        int deleted = certificateDAO.delete(ID);
        return String.format("%d records deleted", deleted);
    }

    // Creëer student via DAO
    public String create(String EmployeeName, double grade) {
        if (!ValidationFormatLogic.isValidGrade(grade)) {
            return "Grade is not valid. Choose 0-10.";
        }
        Certificate certificate = new Certificate(grade, EmployeeName);
        certificateDAO.create(certificate);
        return "Certificate added.";
    }

    // Update student via DAO
    public ArrayList<Certificate> update(String columnToChange, String changeInto, int certificateID) {
        certificateDAO.update(columnToChange, changeInto, certificateID);
        return this.getAll();
    }

    // Gets columnnames
    public ArrayList<String> getColumns() {
        if (columns.isEmpty()) {
            columns = sql.getColumns("Certificate");
        }
        return columns;
    }
}
