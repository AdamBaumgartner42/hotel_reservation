package service;

public class CustomerServiceTester {

    public static void main(String[] args){
        CustomerService guests = CustomerService.getInstance();

        try{
            guests.addCustomer("adammail.com", "Adam1", "Baumgartner");
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }

        try{
            guests.addCustomer("adam@gmail.com", "Adam2", "Baumgartner");
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }

        System.out.println(guests.getCustomer("adam@gmail.com"));


    }
}
