package CCStatistics.Logic;

import java.util.ArrayList;
import CCStatistics.DAO.CertificateDAOWithPrepStatement;
import CCStatistics.Domain.Certificate;

public class CertificateLogic implements Logic<Certificate> {
    CertificateDAOWithPrepStatement CertificatetDAO = new CertificateDAOWithPrepStatement();

//Haal alle studenten op in de DAO
    @Override
    public ArrayList<Certificate> getAll() {
        return CertificatetDAO.getAll();
    }
//Verwijder student via DAO
    public void delete(Certificate object) {
        CertificatetDAO.create(object);
    }
//Creëer student via DAO
    public void create(Certificate object) {
        CertificatetDAO.create(object);
    }
//Update student via DAO
    public void update(Certificate object) {
        CertificatetDAO.create(object);
    }
}
