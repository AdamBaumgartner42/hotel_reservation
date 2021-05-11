package model;

import java.util.regex.*;

public class Customer {

    protected String firstName;
    protected String lastName;
    protected String email;

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
        return "First Name: " + firstName +
        "\nLast Name: " + lastName +
                "\nEmail: " + email;

    }
}
