package CCStatistics.DAO;
public class Login {
    private String loginStijn = "jdbc:sqlserver://localhost;databaseName=QuatroOpdracht;user=sa;password=dblogin";
    private String loginMo = "jdbc:sqlserver://LAPTOP-G3BMQ00M;databaseName=QuatroOpdracht;integratedSecurity=true";

    public String getLogin(){
        return loginStijn;
    }
    
}
