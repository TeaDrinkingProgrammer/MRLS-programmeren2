package CCStatistics.Logic;

import CCStatistics.DAO.CertificateDAOWithPrepStatement;

public class CertificateLogic implements Logic<Certificate>{
    CertificateDAOWithPrepStatement CertificatetDAO = new CertificateDAOWithPrepStatement();

//Haal alle studenten op in de DAO
    @Override
    public ArrayList<Student> getAll() {
        return studentDAO.getAll();
    }
//Creëer student via DAO
    @Override
    public void create(Student object) {
        studentDAO.create(object);
    }
//Update student via DAO
    @Override
    public void update(Student object) {
        studentDAO.create(object);
    }
//Verwijder student via DAO
    @Override
    public void delete(Student object) {
        studentDAO.create(object);
    }
}
