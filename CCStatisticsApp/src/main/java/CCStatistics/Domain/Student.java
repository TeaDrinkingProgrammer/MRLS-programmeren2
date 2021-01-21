package CCStatistics.Domain;

import java.util.Date;

public class Student {
    String email;
    String firstName;
    String lastName;
    Date dateOfBirth; //Is different from class diagram, date is not a valid type
    String gender;
    String street;
    String houseNumber;
    String city;
    String country;
    String postalcode;
    //ArrayList<SignUp> signUps;


    public Student(String email, String firstName, String lastName, Date dateOfBirth, String gender, String street, String houseNumber, String city, String country, String postalcode) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
        this.postalcode = postalcode;
    }

   // addSignups(ArrayList<Signup> signUps){
  //      this.signUps = signUps;
  //  }
}
