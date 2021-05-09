package service;

import model.Customer;

import java.util.Collection;

public class CustomerService {

    // Eagerly Loading of singleton Instance
    private static final CustomerService instance = new CustomerService();

    // Private to prevent anyone else from instantiating
    private CustomerService(){

    }

    // Not sure what this does
    public static CustomerService getInstance(){
        return instance;
    }

    public String getTestValue(){
        return "Customer Service Test Value";
    }

    // Class Methods
    public void addCustomer(String email, String firstName, String lastName){

    }

    public Customer getCustomer (String customerEmail){

    }

    public Collection<Customer> getAllCustomers(){

    }
}
