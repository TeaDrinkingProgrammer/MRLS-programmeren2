package CCStatistics.Logic;

import java.util.ArrayList;

import CCStatistics.DAO.SignupDAOWithPrepStatement;
import CCStatistics.Domain.Signup;

public class SignupLogic implements Logic<Signup> {
    SignupDAOWithPrepStatement signupDAO = new SignupDAOWithPrepStatement();

//Haal alle Signup op in de DAO
    @Override
    public ArrayList<Signup> getAll() {
        return signupDAO.getAll();
    }
//Verwijder Signup via DAO
    public void delete(int ID) {
    signupDAO.delete(ID);
}
//Creëer Signup via DAO
    public void create(Signup object, String account) {
        signupDAO.create(object, account);
    }
//Update Signup via DAO
    public void update(Signup object, String account) {
        signupDAO.create(object, account);
    }

}
