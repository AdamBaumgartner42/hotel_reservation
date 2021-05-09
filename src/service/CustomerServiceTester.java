package service;

public class CustomerServiceTester {

    public static void main(String[] args){

        CustomerService cs = CustomerService.getInstance();

        System.out.println(cs.getTestValue());

        CustomerService ap = CustomerService.getInstance();

    }
}
