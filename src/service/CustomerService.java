package service;

import model.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CustomerService {
    /* --- CustomerService Class - Static Reference -- */
    private static CustomerService customerServiceInstance;

    private CustomerService() {}

    public static CustomerService getInstance(){
        if (customerServiceInstance == null){
            customerServiceInstance = new CustomerService();
        }
        return customerServiceInstance;
    }

    /* --- CUSTOMER_LIST - Static Reference --- */
    static final Map<String, Customer> CUSTOMER_LIST;

    static{
        CUSTOMER_LIST = new HashMap<String, Customer>();
    }

    /* --- Class Methods --- */
    public void addCustomer(String email, String firstName, String lastName){
        // Check that the email does not already exist.
        if(CUSTOMER_LIST.get(email) != null){
            throw new IllegalArgumentException("Email: " + email + " already exists");
        }
        // Add Customer to CUSTOMER_LIST
        CUSTOMER_LIST.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer (String customerEmail){
        // Check that this is a valid email
        return CUSTOMER_LIST.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers(){
        return CUSTOMER_LIST.values();
        // Looks correct, but I don't quite understand what it means
    }


}


