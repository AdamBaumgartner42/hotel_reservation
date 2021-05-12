package service;


import model.Customer;

import java.util.ArrayList;

public class CustomerServiceTest {

    public static void main(String[] args){
        // getInstance();
        CustomerService customerService = CustomerService.getInstance();

        // addCustomer();
        System.out.println(" ---- addCustomer() - test ----");
        customerService.addCustomer("Adam", "Baumgartner", "adam@gmail.com");

        // getCustomer()
        System.out.println(" ---- getCustomer() - test ----");
        System.out.println(customerService.getCustomer("adam@gmail.com"));

        // getAllCustomers();
        ArrayList<Customer> _testArrayList = new ArrayList<>(customerService.getAllCustomers());
        System.out.println(" ---- getAllCustomers() ----");
        for (Customer customer : _testArrayList){
            System.out.println(customer.getLastName());
        }

    }
}
