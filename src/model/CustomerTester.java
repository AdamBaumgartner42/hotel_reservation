package model;

public class CustomerTester {

    public static void main(String[] args){

        try {
            Customer Adam = new Customer("Adam", "Baumgartner", "A@twitch.tv");
            System.out.println(Adam);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }








    }
}
