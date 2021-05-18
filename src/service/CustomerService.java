package service;

import model.*;
import java.util.*;
import static api.CollectionResource.*;

public class CustomerService {
    /* --- CustomerService Class - Static Reference -- */
    private static CustomerService customerServiceInstance;
    private CustomerService() {}
    public static CustomerService getInstance(){
        if (customerServiceInstance == null){
            customerServiceInstance = new CustomerService();
        }
        return customerServiceInstance;
    }

    /* --- Class Methods --- */
    public void addCustomer(String firstName, String lastName, String email){
        CUSTOMER_COLLECTION.add(new Customer(firstName, lastName, email));
    }

    public Customer getCustomer (String customerEmail){
        // Plan: Change to array list and do a for loop?
        ArrayList<Customer> _CustomerArrayList = new ArrayList<>(CUSTOMER_COLLECTION);

        for(int i = 0; i < _CustomerArrayList.size(); i++){
            if(_CustomerArrayList.get(i).getEmail() == customerEmail){
                //System.out.println("Match" + customerEmail);
                return _CustomerArrayList.get(i);
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return CUSTOMER_COLLECTION;
    }


}


