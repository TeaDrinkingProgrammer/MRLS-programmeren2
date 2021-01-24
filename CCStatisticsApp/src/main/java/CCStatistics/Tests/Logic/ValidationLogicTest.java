
package CCStatistics.Logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidationLogicTest {
    //--------------------DATUM VALIDATIETEST
     /**
    * @desc Validates is a given date in the form of day, month and year is valid.
    * 
    * @subcontract 31 days in month {
    *   @requires (month == 1 || month == 3 || month == 5 || month == 7 || 
    *             month == 8 || month == 10 || month == 12) && 1 <= day <= 31;
    *   @ensures \result = true;
    * }
    */
    
    @Test
    public void testValidateDateRequires31_01_2021EnsuresTrue() {
        //Arrange
        int day = 31;
        int month = 1;
        int year = 2021;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
    @Test
    public void testValidateDateRequires31_05_2021EnsuresTrue() {
        //Arrange
        int day = 31;
        int month = 5;
        int year = 2021;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
    @Test
    public void testValidateDateRequires31_08_2021EnsuresTrue() {
        //Arrange
        int day = 31;
        int month = 8;
        int year = 2021;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
    @Test
    public void testValidateDateRequires31_12_2021EnsuresTrue() {
        //Arrange
        int day = 31;
        int month = 12;
        int year = 2021;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
    
   /** @subcontract 30 days in month {
    *   @requires (month == 4 || month == 6 || month == 9 || month == 11) &&
    *              1 <= day <= 30;
    *   @ensures \result = true;
    * }
    */
    
    @Test
    public void testValidateDateRequires30_04_2021EnsuresTrue() {
        //Arrange
        int day = 30;
        int month = 4;
        int year = 2021;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
    @Test
    public void testValidateDateRequires30_11_2021EnsuresTrue() {
        //Arrange
        int day = 30;
        int month = 11;
        int year = 2021;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
   /** @subcontract 29 days in month {
    *   @requires month == 2 && 1 <= day <= 29 &&
    *             (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    *   @ensures \result = true;
    * }
    */
    
    @Test
    public void testValidateDateRequires29_02_2020EnsuresTrue() {
        //Arrange
        int day = 29;
        int month = 2;
        int year = 2020;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
    @Test
    public void testValidateDateRequires29_02_2004EnsuresTrue() {
        //Arrange
        int day = 29;
        int month = 2;
        int year = 2004;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
    
   /** @subcontract 28 days in month {
    *   @requires month == 2 && 1 <= day <= 28 &&
    *             (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0));
    *   @ensures \result = true;
    * }
    */
    
    @Test
    public void testValidateDateRequires28_02_2021EnsuresTrue() {
        //Arrange
        int day = 28;
        int month = 2;
        int year = 2021;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
    @Test
    public void testValidateDateRequires28_02_1998EnsuresTrue() {
        //Arrange
        int day = 28;
        int month = 2;
        int year = 1998;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(true, result);
    }
    
   /** @subcontract all other cases {
    *   @requires no other accepting precondition;
    *   @ensures \result = false;
    * }
    */ 

    @Test
    public void testValidateDateRequires30_02_2021EnsuresFalse() {
        //Arrange
        int day = 30;
        int month = 02;
        int year = 2021;
        
        //Act
        boolean result = ValidationLogic.validateDate(day, month, year);
        
        //Assert
        assertEquals(false, result);
    }
    
    //--------------------EMAILADRES VALIDATIETEST
     /**
     * @desc Validates if mailAddress is valid. It should be in the form of:
     *       <at least 1 character>@<at least 1 character>.<at least 1 character>
     * 
     * @subcontract no mailbox part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[0].length < 1;
     *   @ensures \result = false;
     * }
     */
    
    @Test
    public void testValidateMailAddressRequiresMailboxsubdomaindottldEnsuresFalse() {
        //Arrange
        String aString = "mailboxsubdomain.tld";
        //Act
        boolean result = ValidationLogic.validateMailAddress(aString);
        //Assert
        assertEquals(false, result);
    }
    
    @Test
    public void testValidateMailAddressRequiresAtsubdomaindottldEnsuresFalse() {
        //Arrange
        String aString = "@subdomain.tld";
        //Act
        boolean result = ValidationLogic.validateMailAddress(aString);
        //Assert
        assertEquals(false, result);
    }
    
    /**
     * @subcontract subdomain-tld delimiter {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".").length > 2;
     *   @ensures \result = false;
     * }
     */
    
    @Test
    public void testValidateMailAddressRequiresMailboxatsubdomaindotsubdomaindottldEnsuresFalse() {
        //Arrange
        String aString = "mailbox@subdomain.subdomain.tld";
        //Act
        boolean result = ValidationLogic.validateMailAddress(aString);
        //Assert
        assertEquals(false, result);
    }
    
    /** @subcontract no subdomain part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[0].length < 1;
     *   @ensures \result = false;
     * }
     */
    
    @Test
    public void testValidateMailAddressRequiresMailboxatdottldEnsuresFalse() {
        //Arrange
        String aString = "mailbox@.tld";
        //Act
        boolean result = ValidationLogic.validateMailAddress(aString);
        //Assert
        assertEquals(false, result);
    }
    
    /** @subcontract no tld part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[1].length < 1;
     *   @ensures \result = false;
     * }
     */
    
    @Test
    public void testValidateMailAddressRequiresMailboxatsubdomaindotEnsuresFalse() {
        //Arrange
        String aString = "mailbox@subdomain.";
        //Act
        boolean result = ValidationLogic.validateMailAddress(aString);
        //Assert
        assertEquals(false, result);
    }
    
    /** 
     * @subcontract valid email {
     *   @requires no other precondition
     *   @ensures \result = true;
     * }
     */
    
    @Test
    public void testValidateMailAddressRequiresMailboxatsubdomaindottldEnsuresTrue() {
        //Arrange
        String aString = "mailbox@subdomain.tld";
        //Act
        boolean result = ValidationLogic.validateMailAddress(aString);
        //Assert
        assertEquals(true, result);
        
    }
    
    //--------------------POSTCODE FORMAATTEST
    /**
     * @desc Formats the input postal code to a uniform output in the form
     * nnnn<space>MM, where nnnn is numeric and > 999 and MM are 2 capital letters.
     * Spaces before and after the input string are trimmed.
     * 
     * @subcontract null postalCode {
     *   @requires postalCode == null;
     *   @signals (NullPointerException) postalCode == null;
     * }
     */
    @Test(expected = NullPointerException.class)
    public void testFormatPostalCodeRequiresNullSignalsNullPointerException() {
        //Arrange
        String aString = null;
        //Act
        String result = ValidationLogic.formatPostalCode(aString);
    }
    
    /** @subcontract valid postalCode {
     *   @requires Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
     *             Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
     *             postalCode.trim().substring(4).trim().length == 2 &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z';
     *   @ensures \result = postalCode.trim().substring(0, 4) + " " +
     *                  postalCode.trim().substring(4).trim().toUpperCase()
     * }
     */
    
    @Test
    public void testFormatPostalCodeRequires_1111_aa_Ensures1111_AA() {
        //Arrange
        String aString = " 1111 aa ";
        //Act
        String result = ValidationLogic.formatPostalCode(aString);
        //Assert
        assertEquals("1111 AA", result);
    }
    
    /** @subcontract invalid postalCode {
     *   @requires no other valid precondition;
     *   @signals (IllegalArgumentException);
     * }
     */
    
    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequires111aaSignalsIllegalArgumentException() {
        //Arrange
        String aString = "111aa";
        //Act
        String result = ValidationLogic.formatPostalCode(aString);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequires1111aaaSignalsIllegalArgumentException() {
        //Arrange
        String aString = "1111aaa";
        //Act
        String result = ValidationLogic.formatPostalCode(aString);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFormatPostalCodeRequiresSome_text_123SignalsIllegalArgumentException() {
        //Arrange
        String aString = "Some text 123";
        //Act
        String result = ValidationLogic.formatPostalCode(aString);
    }
    
    //--------------------GRADE VALIDATIETEST
    /**
     * @desc Validates if the input is within range of 0-10
     * 
     * @subcontract value within valid range {
     *   @requires 0 <= grade <= 10;
     *   @ensures \result = true;
     * }
     */
    
    @Test
    public void testIsValidGradeRequires5EnsuresTrue() {
        //Arrange
        double grade = 5;
        //Act
        boolean result = ValidationLogic.isValidGrade(grade);
        //Assert
        assertEquals(true, result);
    }
    
    @Test
    public void testIsValidGradeRequires0EnsuresTrue() {
        //Arrange
        double grade = 0;
        //Act
        boolean result = ValidationLogic.isValidGrade(grade);
        //Assert
        assertEquals(true, result);
    }
    
    @Test
    public void testIsValidGradeRequires10EnsuresTrue() {
        //Arrange
        double grade = 10;
        //Act
        boolean result = ValidationLogic.isValidGrade(grade);
        //Assert
        assertEquals(true, result);
    }
    
    /** @subcontract value out of range low {
     *   @requires percentage < 0;
     *   @ensures \result = false;
     * }
     */
    
    @Test
    public void testIsValidGradeRequiresMinus0dot1EnsuresFalse() {
        //Arrange
        double grade = -0.1;
        //Act
        boolean result = ValidationLogic.isValidGrade(grade);
        //Assert
        assertEquals(false, result);
    }
    
    /** @subcontract value out of range high {
     *   @requires percentage > 10;
     *   @signals \result = false;
     * }
     */
    
    @Test
    public void testIsValidGradeRequires10dot1EnsuresFalse() {
        //Arrange
        double grade = 10.1;
        //Act
        boolean result = ValidationLogic.isValidGrade(grade);
        //Assert
        assertEquals(false, result);
    }
}
