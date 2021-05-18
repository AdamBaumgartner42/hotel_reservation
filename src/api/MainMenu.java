package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        boolean keepRunning = true;
        while (keepRunning){
            splashScreen();
            int action = getAction();
            switch (action) {
                case 1 -> {
                    // HotelResource instance
                    HotelResource hr = HotelResource.getInstance();

                    // Customer
                    System.out.println("Please enter your account email");
                    String customerEmail = getScannerEmail();

                    // Get check in and check out dates
                    System.out.println("Check in Date: dd/MM/yyyy");
                    Date checkInDate = getScannerDate();
                    System.out.println("Check out Date: dd/MM/yyyy");
                    Date checkOutDate = getScannerDate();

                    // See all available rooms
                    Collection<IRoom> _openRooms = hr.findARoom(checkInDate, checkOutDate);

                    // Display available rooms
                    System.out.println("Available Rooms:");
                    for (IRoom openRoom : _openRooms) {
                        System.out.println(openRoom.getRoomNumber());
                    }

                    // Get book requested room
                    System.out.println("Which room would you like?");
                    String roomNumber = getScannerRoom();

                    // Book reservation
                    Reservation _newReservation = hr.bookARoom(customerEmail, hr.getRoom(roomNumber), checkInDate, checkOutDate);
                    System.out.println("Your reservation is complete");
                    System.out.println(_newReservation);
                }
                case 2 -> {
                    System.out.println("Enter customer email to search for reservations");
                    viewCustomerReservations(getScannerEmail());
                }
                case 3 -> createAccount();
                case 4 -> adminMenu();
                case 5 -> exitMenu();
            }
        }
    }

    public void splashScreen(){
        System.out.println(
                "--------------------------------- \n"
                + "1. Find and reserve a rooms \n"
                + "2. See my reservations \n"
                + "3. Create an account\n"
                + "4. Admin \n"
                + "5. Exit \n"
                + "--------------------------------- \n"
                + "Please select a number for the menu option");
    }

    public int getAction(){
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

// Find a room   // Book a room
    public Date getScannerDate(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Date checkInDate;
        try {
            checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(input);
        } catch (ParseException ex){
            System.out.println("Incorrect Date Format");
            checkInDate = null;
        }

        return checkInDate;
    }

    public String getScannerRoom(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getScannerEmail(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }



// Viewing Reservations

    public Collection<Reservation> viewCustomerReservations(String customerEmail){
        HotelResource hs = HotelResource.getInstance();
        Collection<Reservation> personReservations = hs.getCustomersReservations(customerEmail);

        Iterator <Reservation> iterator = personReservations.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        return personReservations;

    }

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
