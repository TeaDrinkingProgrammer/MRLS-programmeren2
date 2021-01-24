package CCStatistics.Logic;

import java.util.ArrayList;
import CCStatistics.DAO.MixedDAO;

public class MixedLogic {
    MixedDAO mixedDAO = new MixedDAO();

    // Voor een geselecteerde cursus, geef per module de gemiddelde voortgang in
    // percentage van de totale lengte, voor alle accounts.
    public ArrayList<String[]> getAverageProgressForCourse(String courseName) {
        return mixedDAO.getAverageProgressForCourse(courseName);
    }

    public String[] getAverageProgressForCourseColumns() {
        return mixedDAO.averageProgressForCourseColumns;
    }

    // Voor een geselecteerd account en geselecteerde cursus, geef per module de
    // voortgang als percentage.
    public ArrayList<String[]> getModuleProgress(String courseName, String studentEmail) {
        return mixedDAO.getModuleProgress(courseName, studentEmail);
    }

    public String[] getModuleProgressColumns() {
        return mixedDAO.moduleProgressColumns;
    }

    // Geef een top 3 van meest bekeken webcasts.
    public ArrayList<String[]> getTop3Webcasts() {
        return mixedDAO.getTop3Webcasts();
    }

    public String[] getTop3WebcastsColumns() {
        return mixedDAO.top3WebcastsColumns;
    }

    // Voor een geselecteerde cursus, geef hoeveel cursisten deze in het geheel
    // behaald hebben.
    public ArrayList<String[]> getStudentsPassedInCourse(String courseInput) {
        return mixedDAO.getStudentsPassedInCourse(courseInput);
    }

    public String[] getStudentsPassedInCourseColumns() {
        return mixedDAO.studentsPassedInCourseColumns;
    }

    // Voor een cursist kan aangegeven worden wat de voortgang in een module is.
    public ArrayList<String[]> getProgressInModule(String studentEmail, int moduleID) {
        return mixedDAO.getProgressInModule(studentEmail, moduleID);
    }

    public String[] getProgressInModuleColumns() {
        return mixedDAO.progressInModuleColumns;
    }

    // Voor een cursist kan aangegeven worden hoeveel procent van een webcast
    // bekeken is.
    public ArrayList<String[]> getProgressInWebcast(String studentEmail, int webcastID) {
        return mixedDAO.getProgressInWebcast("student@live.com", 1);
    }

    public String[] getProgressInWebcastColumns() {
        return mixedDAO.progressInWebcastColumns;
    }
}
