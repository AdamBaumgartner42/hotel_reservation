package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

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

            String actionString = null;
            int action = 0;
            boolean validInput = false;
            while (!validInput){
                actionString = getActionString();
                validInput = checkGetAction(actionString, 1, 5);
                if(validInput){
                    action = Integer.parseInt(actionString);
                }
            }
            switch (action) {
                case 1 -> {
                    // HotelResource instance
                    HotelResource hr = HotelResource.getInstance();

                    Date checkInDate = null;
                    Date checkOutDate = null;

                    // Find Available Rooms

                    // CheckIn Date
                    validInput = false;
                    while(!validInput){
                        String _checkInDate = requestCheckInDate();
                        validInput = checkCheckInDate(_checkInDate);
                        if (validInput){
                            checkInDate = parseDate(_checkInDate);
                        }
                    }

                    // CheckOut Date
                    validInput = false;
                    while(!validInput){
                        String _checkOutDate = requestCheckOutDate();
                        //checkOutDate = requestCheckOutDate();
                        validInput = checkCheckOutDate(checkInDate, _checkOutDate);
                        if(validInput){
                            checkOutDate = parseDate(_checkOutDate);
                        }
                    }

                    // Display available rooms
                    Collection<IRoom> _openRooms = hr.findARoom(checkInDate, checkOutDate);
                    System.out.println("Available Rooms:");
                    for (IRoom openRoom : _openRooms) {
                        System.out.println(openRoom);
                    }
                    if(_openRooms.size() == 0) {
                        System.out.println("no rooms in original range");
                        // Try again with +7 days
                        Calendar cal = Calendar.getInstance();

                        // Update checkInDate
                        cal.setTime(checkInDate);
                        cal.add(Calendar.DATE, 7);
                        checkInDate = cal.getTime();

                        // Update checkOutDate
                        cal.setTime(checkOutDate);
                        cal.add(Calendar.DATE, 7);
                        checkOutDate = cal.getTime();

                        _openRooms = hr.findARoom(checkInDate, checkOutDate);
                        System.out.println("Available Rooms + 7 days:");
                        for (IRoom openRoom : _openRooms) {
                            System.out.println(openRoom);
                        }
                        if (_openRooms.size() == 0) {
                            System.out.println("no rooms in +7 day range");
                            startActions(); // exit back to main menu
                        }
                    }


                    // Book a room - y/n
                    validInput = false;
                    while(!validInput){
                        String res = requestBookRoomResponse();
                        validInput = checkRequestBookRoomResponse(res);
                        if((res.equals("n") || res.equals("N")) && validInput){
                            startActions(); // go back to MainMenu()
                        }
                    }

                    // Variables
                    String customerEmail = null;
                    String roomNumber = null;


                    // Existing account - y/n
                    validInput = false;
                    while(!validInput){
                        String res = requestCurrentAccount();
                        validInput = checkRequestCurrentAccount(res);
                            // No: Customer account Does Not Exist
                        if((res.equals("n") || res.equals("N")) && validInput){
                            customerEmail = createAccountReturnEmail(); // probably could use inheritance
                        }
                        if ((res.equals("y") || res.equals("Y") && validInput)){
                            System.out.println("Please enter your account email"); // repetitive for new acct folks
                            customerEmail = getScannerEmail();
                        }
                    }


                    // Room Selection
                    validInput = false;
                    while(!validInput){
                        String _roomSelection = requestRoomSelection();
                        validInput = checkRoomSelection(_roomSelection);
                        if(validInput){
                            roomNumber = _roomSelection;
                        }
                    }

                    // Book reservation
                    Reservation _newReservation = hr.bookARoom(customerEmail, hr.getRoom(roomNumber), checkInDate, checkOutDate);
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

    public String getActionString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean checkGetAction(String action, int low, int high){
        if(action.equals("1") || action.equals("2") || action.equals("3") ||
            action.equals("4") || action.equals("5")) {

            return true;

        } else {
            System.out.println("Invalid Input: Must be between " + low + " and " + high);
            return false;
        }
    }


// Find a room   // Book a room
    public Date getScannerDate(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Date checkInDate;
        try {
            checkInDate = new SimpleDateFormat("MM/dd/yyyy").parse(input);
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

    public void viewCustomerReservations(String customerEmail){
        HotelResource hs = HotelResource.getInstance();
        Collection<Reservation> personReservations = hs.getCustomersReservations(customerEmail);

        Iterator <Reservation> iterator = personReservations.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next() + "\n");
        }

    }

    public void createAccount(){
        String email = null;
        String firstName = null;
        String lastName = null;

        // Email
        boolean inputValid = false;
        while (!inputValid){
            email = requestEmail();
            inputValid = checkEmail(email);
        }

        // First Name
        inputValid = false;
        while (!inputValid){
            firstName = requestFirstName();
            inputValid = checkFirstName(firstName);
        }

        // Last Name
        inputValid = false;
        while (!inputValid){
            lastName = requestLastName();
            inputValid = checkLastName(lastName);
        }

        HotelResource hs = HotelResource.getInstance();
        hs.createACustomer(email, firstName, lastName);
    }

    public String createAccountReturnEmail(){
        String email = null;
        String firstName = null;
        String lastName = null;

        // Email
        boolean inputValid = false;
        while (!inputValid){
            email = requestEmail();
            inputValid = checkEmail(email);
        }

        // First Name
        inputValid = false;
        while (!inputValid){
            firstName = requestFirstName();
            inputValid = checkFirstName(firstName);
        }

        // Last Name
        inputValid = false;
        while (!inputValid){
            lastName = requestLastName();
            inputValid = checkLastName(lastName);
        }

        HotelResource hs = HotelResource.getInstance();
        hs.createACustomer(email, firstName, lastName);

        return email;
    }


    // Book room response
    public String requestBookRoomResponse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to book a room y/n");
        return scanner.nextLine();
    }

    public boolean checkRequestBookRoomResponse(String response){
        if(response.equals("y") || response.equals("Y") ||
           response.equals("n") || response.equals("N")){
            return true;
        } else {
            System.out.println("Options are y or n");
            return false;
        }
    }

    // Existing Account response
    public String requestCurrentAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you have an account with us? y/n");
        return scanner.nextLine();
    }

    public boolean checkRequestCurrentAccount (String response){
        if(response.equals("y") || response.equals("Y") ||
                response.equals("n") || response.equals("N")){
            return true;
        } else {
            System.out.println("Options are y or n");
            return false;
        }
    }

// Request Room Number
    public String requestRoomSelection(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What room would you like?");
        return scanner.nextLine();
    }

    public boolean checkRoomSelection(String roomSelection){
        HotelResource hs = HotelResource.getInstance();
        if(hs.roomExists(roomSelection)){
            return true;
        } else {
            System.out.println(roomSelection + " is not a room at this hotel");
            return false;
        }
    }


// CheckIn Date
    public String requestCheckInDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter CheckIn Date mm/dd/yyyy example 06/01/2021");
        return scanner.nextLine();
        //return getScannerDate();
    }

    public boolean checkCheckInDate(String input){
        Date _checkInDate = null;
        try{
            _checkInDate = new SimpleDateFormat("MM/dd/yyyy").parse(input);
        } catch (ParseException ex) {
            System.out.println("Invalid Input");
            return false;
        }


        if(_checkInDate.before(new java.util.Date())){
            System.out.println(_checkInDate + " is already in the past");
            return false;
        }
        return true;
    }

    public Date parseDate(String input){
        Date _checkInDate = null;
        try {
            _checkInDate = new SimpleDateFormat("MM/dd/yyyy").parse(input);
        } catch (ParseException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return _checkInDate;
    }

// CheckOut Date
    public String requestCheckOutDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter CheckOut Date mm/dd/yyyy example 06/21/2021");
        return scanner.nextLine();
    }

    public boolean checkCheckOutDate(Date checkInDate, String input){
        Date _checkOutDate = null;
        try{
            _checkOutDate = new SimpleDateFormat("MM/dd/yyyy").parse(input);
        } catch (ParseException ex) {
            System.out.println("Invalid Input");
            return false;
        }

        if(_checkOutDate.before(checkInDate)){
            System.out.println("CheckOut Date must be after CheckIn Date: " + checkInDate);
            return false;
        }
        return true;
    }



// Email
    public String requestEmail(){
        System.out.println("Enter Email format: name@domain.com");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean checkEmail(String email){
        if(Pattern.matches("^(.+)@(.+)\\.(.+)$", email)){
            return true;
        } else {
            System.out.println(email + " is incorrect. format: name@domain.com");
            return false;
        }
    }

    // Check for Email in service



// First Name
    public String requestFirstName(){
        System.out.println("First Name");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean checkFirstName(String firstName){
        if(firstName.length() == 0 ){
            System.out.println("First Name input cannot be blank");
            return false;
        } else if (firstName.length() > 30){
            System.out.println("First Name must be <= 30 chars");
            return false;
        } else {
            return true;
        }
    }

// Last Name
    public String requestLastName(){
        System.out.println("Last Name");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean checkLastName(String lastName){
        if(lastName.length() == 0 ){
            System.out.println("Last Name input cannot be blank");
            return false;
        } else if (lastName.length() > 30){
            System.out.println("Last Name must be <= 30 chars");
            return false;
        } else {
            return true;
        }
    }

    public void adminMenu(){
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.startActions();
    }

    public void exitMenu(){
        System.exit(0);

    }


}
