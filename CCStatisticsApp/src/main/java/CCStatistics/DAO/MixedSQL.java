package CCStatistics.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MixedSQL {
    private SQLWithPrepStatement sqlWithPrepStatement = new SQLWithPrepStatement();
    public Connection getConnection(){
        return sqlWithPrepStatement.getConnection();
    }
    
    public ArrayList<ArrayList<String>> readQuery(ArrayList<String> columns,PreparedStatement preparedStatement){
        // ResultSet is de tabel die we van de database terugkrijgen.
        // We kunnen door de rows heen stappen en iedere kolom lezen.
        ResultSet rs = null;

        ArrayList<ArrayList<String>> tableList = new ArrayList<>();
        try {
            // 'Importeer' de driver die je gedownload hebt.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Pak de prepared statement en voert hem uit
            rs = preparedStatement.executeQuery();

            // Als de resultset waarden bevat dan lopen we hier door deze waarden en printen ze.
            // ArrayList 1 is voor de rij, de geneste ArrayList is voor de kolom in de rij.
            
            while (rs.next()) {
                //row (i)
                ArrayList<String> strings = new ArrayList<>();
                for(String column : columns){
                    //column j in row i wordt toegevoegd.
                    strings.add(rs.getString(column));
                }
                tableList.add(strings);
            }
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
        }

        return tableList;
    }

    // public void updateQuery(PreparedStatement preparedStatement){
    //     this.cudQuery(preparedStatement);
    // }

    // public int deleteQuery(PreparedStatement preparedStatement){
    //     return sqlWithPrepStatement.deleteQuery(preparedStatement);
    // }

    // public void createQuery(PreparedStatement preparedStatement){
    //     this.cudQuery( preparedStatement);
    // }
    
    // public void cudQuery(PreparedStatement preparedStatement){
    //     try {
    //         // 'Importeer' de driver die je gedownload hebt.
    //         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

    //         // Pak de prepared statement en voert hem uit
    //         preparedStatement.executeUpdate();
    //     }

    //     // Handle any errors that may have occurred.
    //     catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

	public static Boolean printSQLException(SQLException ex) {
        return SQLWithPrepStatement.printSQLException(ex);
    }

}
