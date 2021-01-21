package CCStatistics.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Login {

   // public static void main(String args[]) throws IOException {
   //    Properties prop = readPropertiesFile("credentials.properties");
   //    System.out.println("username: "+ prop.getProperty("username"));
   //    System.out.println("password: "+ prop.getProperty("password"));
   // }
   public String getLogin() throws IOException {
      FileInputStream file = null;
      Properties prop = null;
      String fileName = "login.properties";
      try {
         file = new FileInputStream(fileName);
         prop = new Properties();
         prop.load(file);
      } catch(FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      } catch(IOException ioe) {
         ioe.printStackTrace();
      } finally {
         file.close();
      }
      try {
         if(prop.getProperty("windowsUserLogin").equalsIgnoreCase("true")){
            return String.format("jdbc:sqlserver://%s;databaseName=QuatroOpdracht;integratedSecurity=true",prop.getProperty("windowsDeviceName"));
         } else if(prop.getProperty("windowsUserLogin").equalsIgnoreCase("false")){
            return String.format("jdbc:sqlserver://localhost;databaseName=QuatroOpdracht;user=%s;password=%s",prop.getProperty("username"),prop.getProperty("password"));
         }
      } catch (Exception e) {
         System.out.println("Het bestand heeft niet alle goede waarden, probeer nog een keer de instructies te lezen en ze toe te passen");
      }
   }
}

