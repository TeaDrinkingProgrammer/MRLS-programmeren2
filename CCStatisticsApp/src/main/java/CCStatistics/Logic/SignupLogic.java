package CCStatistics.Logic;

import java.util.ArrayList;

import CCStatistics.DAO.SignupDAOWithPrepStatement;
import CCStatistics.Domain.Signup;

public class SignupLogic implements LogicTwoParam<Signup>{
    SignupDAOWithPrepStatement signupDAO = new SignupDAOWithPrepStatement();

//Haal alle Signup op in de DAO
    @Override
    public ArrayList<Signup> getAll() {
        return signupDAO.getAll();
    }
//Creëer Signup via DAO
    @Override
    public void create(Signup object, String account) {
        signupDAO.create(object, account);
    }
//Update Signup via DAO
    @Override
    public void update(Signup object, String account) {
        signupDAO.create(object, account);
    }
//Verwijder Signup via DAO
    @Override
    public void delete(Signup object, String account) {
        signupDAO.create(object, account);
    }
}
