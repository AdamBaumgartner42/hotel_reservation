package api;

import java.util.*;
import model.*;




public class MainMenu {
    /*
    1. Find and reserve a room - search for and book a room
    2. See my reservations - viewing reservations
    3. Create an account - create customer account
    4. Admin
    5. Exit
     */

    public MainMenu(){}

    public void startActions(){
        splashScreen();
        boolean keepRunning = true;
        while (keepRunning){
            splashScreen();
            int action = getAction();
            switch (action) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    adminMenu();
                    break;
                case 5:
                    exitMenu();
                    break;
            }
        }
    }

    public void splashScreen(){
        System.out.println(" Main Menu \n"
                + "----------- \n"
                + "1. Find and reserve a rooms \n"
                + "2. See my reservations \n"
                + "3. Create an account\n"
                + "4. Admin \n"
                + "5. Exit \n"
                + "-----------");
    }

    public int getAction(){
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

/*
    public void findAndBookRoom(){

    }

    public Collection<Reservation> customerReservations(){

    }
*/
    public void createAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create a Customer");

        System.out.println("Please enter first name");
        String firstName = scanner.nextLine();

        System.out.println("Please enter last name");
        String lastName = scanner.nextLine();

        System.out.println("Please enter email");
        String email = scanner.nextLine();

        HotelResource hs = HotelResource.getInstance();
        hs.createACustomer(email, firstName, lastName);
    }

    public void adminMenu(){
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.startActions();
    }

    public void exitMenu(){
        System.exit(0);

    }


}
