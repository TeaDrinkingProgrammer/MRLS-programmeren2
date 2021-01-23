package CCStatistics.Logic;

import java.util.ArrayList;
import CCStatistics.DAO.CertificateDAOWithPrepStatement;
import CCStatistics.DAO.SQLWithPrepStatement;
import CCStatistics.Domain.Certificate;

public class CertificateLogic implements Logic<Certificate> {
    private CertificateDAOWithPrepStatement certificateDAO = new CertificateDAOWithPrepStatement();
    private SQLWithPrepStatement sql = new SQLWithPrepStatement();
    private ArrayList<String> columns = new ArrayList<>();
//Haal alle studenten op in de DAO
    @Override
    public ArrayList<Certificate> getAll() {
        return certificateDAO.getAll();
    }
//Verwijder student via DAO
    public String delete(int ID) {
        int deleted = certificateDAO.delete(ID);
        return String.format("%d records deleted", deleted);
    }
//Creëer student via DAO
    public void create(Certificate certificate) {
        certificateDAO.create(certificate);
    }
//Update student via DAO
    public ArrayList<Certificate> update(String columnToChange, String changeInto, int certificateID) {
        certificateDAO.update(columnToChange, changeInto, certificateID);
        return this.getAll();
    }

    public ArrayList<String> getColumns(){
        if(columns.isEmpty()){
            columns = sql.getColumns("Certificate");
        }
        return columns;
    }
}
