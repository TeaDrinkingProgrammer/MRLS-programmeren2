package CCStatistics.DAO;
public class Login {
    private String Stijn = "jdbc:sqlserver://localhost;databaseName=QuatroOpdracht;user=sa;password=dblogin";
    private String Mo = "jdbc:sqlserver://LAPTOP-G3BMQ00M;databaseName=QuatroOpdracht;integratedSecurity=true";

    public String getLogin(String name){
        if (name.equals("Mo") || name.equals("mo")){ 
            return this.Mo;
        } else if (name.equals("Stijn") || name.equals("stijn")){ 
            return this.Stijn;
        }
        return "wrongUser";
    }
    
}
