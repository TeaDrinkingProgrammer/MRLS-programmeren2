package CCStatistics.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Login {

   // Based of
   // https://www.tutorialspoint.com/how-to-read-the-data-from-a-properties-file-in-java
   public String getLogin() throws IOException {
      FileInputStream file = null;
      Properties prop = null;
      // leest login.properties uit
      String fileName = "login.properties";
      try {
         file = new FileInputStream(fileName);
         prop = new Properties();
         prop.load(file);
      } catch (FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } finally {
         file.close();
      }
      try {
         // Als een gebruik windowsUserLogin gebruikt, wordt die gepakt met apparaatnaam
         if (prop.getProperty("windowsUserLogin").equalsIgnoreCase("true")) {
            return String.format("jdbc:sqlserver://%s;databaseName=QuatroOpdracht;integratedSecurity=true",
                  prop.getProperty("windowsDeviceName"));
            // Anders de login via username en password
         } else if (prop.getProperty("windowsUserLogin").equalsIgnoreCase("false")) {
            return String.format("jdbc:sqlserver://localhost;databaseName=QuatroOpdracht;user=%s;password=%s",
                  prop.getProperty("username"), prop.getProperty("password"));
         }
      } catch (Exception e) {
         System.out.println(
               "Het bestand heeft niet alle goede waarden, probeer nog een keer de instructies te lezen en ze toe te passen");
      }
      return null;
   }
}
