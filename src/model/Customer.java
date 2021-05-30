package model;

import java.util.regex.*;

public class Customer {

    // Todo: Note "final" is added here
    protected final String firstName;
    protected final String lastName;
    protected final String email;

    // Constructor
    public Customer (String firstName, String lastName, String email){
        // Guard Statement for email
        if(!Pattern.matches("^(.+)@(.+)\\.(.+)$", email)){
            throw new IllegalArgumentException("Invalid email for " + firstName + " " + lastName );
        }

        // Assign Values
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }


    @Override
    public String toString() {
        return "First Name: " + firstName + ", Last Name: "
                + lastName + ", Email: " + email;

    }
}
