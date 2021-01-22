package CCStatistics.Domain;

import java.util.ArrayList;
import java.util.Date;

public class Student {
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String street;
    private String houseNumber;
    private String postalcode;
    private String city;
    private String country;
    private ArrayList<Signup> signUps;


    public Student(String email, String firstName, String lastName, String dateOfBirth, String gender, String street, String houseNumber, String city, String country, String postalcode) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalcode = postalcode;
        this.city = city;
        this.country = country;
    }

    public void addSignups(ArrayList<Signup> signUps){
       this.signUps = signUps;
    }
}
