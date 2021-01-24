package CCStatistics.Logic;

public class ValidationFormatLogic {

    // Validatie datum methode
    public static boolean validateDate(int day, int month, int year) {
        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                && (day >= 1 && day <= 31)) {
            return true;
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day >= 1 && day <= 30)) {
            return true;
        } else if (month == 2 && (day >= 1 && day <= 29) && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
            return true;
        } else if (month == 2 && (day >= 1 && day <= 28) && (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0))) {
            return true;
        } else {
            return false;
        }
    }

    // Validatie emailadres methode
    public static boolean validateMailAddress(String email) {
        if (!email.contains("@") || email.split("@")[0].length() < 1 || email.split("@")[1].split("\\.").length > 2
                || email.split("@")[1].split("\\.")[0].length() < 1 || email.split("@")[1].split("\\.").length == 1) {
            return false;
        }
        return true;
    }

    //Validatie en formatting postcode methode
    public static String formatPostalCode(/* non_null */ String postalCode) {

        if (Integer.valueOf(postalCode.trim().substring(0, 4)) > 999
                && Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999
                && postalCode.trim().substring(4).trim().length() == 2
                && Character.isLetter(postalCode.trim().substring(4).trim().toUpperCase().charAt(0))
                && Character.isLetter(postalCode.trim().substring(4).trim().toUpperCase().charAt(1))) {

            postalCode = postalCode.trim().substring(0, 4).trim() + postalCode.trim().substring(4).trim().toUpperCase();

            return postalCode;
        } else if (postalCode == null) {
            throw new NullPointerException();
        }

        throw new IllegalArgumentException();
    }

    //Formatting date methode
    public static String formatDate(/* non_null */ int day, int month, int year) {
        
        if (day < 10 && month < 10) {
            return String.format("0%d-0%d-%d", month, day, year);   
        } else if (day < 10) {
            return String.format("%d-0%d-%d", month, day, year);   
        } else if (month < 10) {
            return String.format("0%d-%d-%d", month, day, year);
        }

        return String.format("%d-%d-%d", month, day, year);   
    }

    //Formatting gender methode
    public static String formatGender(/* non_null */ String gender) {
        
        if (gender.equals("m")) {
            return "M";
        } else if (gender.equals("v")) {
            return "V";
        }

        return gender; 
    }

    //Validatie grade methode
    public static boolean isValidGrade(double grade) {
        if (grade >= 0 && grade <= 10) {
            return true;
        }

        return false;
    }
}
