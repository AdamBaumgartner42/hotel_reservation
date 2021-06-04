package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import model.*;

public class MainMenu {

    public MainMenu(){}

    public void startActions(){
        boolean validInput = false;

        while (true){
            switch (getAction()) {
                case 1 -> {
                    HotelResource hr = HotelResource.getInstance();

                    Date checkInDate = getValidDate("Enter CheckIn Date mm/dd/yyyy example 07/01/2021");
                    Date checkOutDate = getValidDate("Enter CheckIn Date mm/dd/yyyy example 06/01/2021", checkInDate);

                    DisplayRooms(checkInDate, checkOutDate, 7);

//                    // Display available rooms
//                    Collection<IRoom> _openRooms = hr.findARoom(checkInDate, checkOutDate);
//                    System.out.println("Available Rooms:");
//                    for (IRoom openRoom : _openRooms) {
//                        System.out.println(openRoom);
//                    }
//                    if(_openRooms.size() == 0) {
//                        System.out.println("no rooms in original range");
//                        // Try again with +7 days
//                        Calendar cal = Calendar.getInstance();
//
//                        // Update checkInDate
//                        cal.setTime(checkInDate);
//                        cal.add(Calendar.DATE, 7);
//                        checkInDate = cal.getTime();
//
//                        // Update checkOutDate
//                        cal.setTime(checkOutDate);
//                        cal.add(Calendar.DATE, 7);
//                        checkOutDate = cal.getTime();
//
//                        _openRooms = hr.findARoom(checkInDate, checkOutDate);
//                        System.out.println("Available Rooms + 7 days:");
//                        for (IRoom openRoom : _openRooms) {
//                            System.out.println(openRoom);
//                        }
//                        if (_openRooms.size() == 0) {
//                            System.out.println("no rooms in +7 day range");
//                            startActions(); // exit back to main menu
//                        }
//                    }


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
                            customerEmail = createAccountReturnEmail();
                        }
                        if ((res.equals("y") || res.equals("Y") && validInput)){
                            System.out.println("Please enter your account email"); // repetitive for new acct folks
                            customerEmail = getValidNewEmail();
                        }
                    }


                    // Room Selection + confirm no reservations already
                    validInput = false;
                    while(!validInput){
                        String _roomSelection = requestRoomSelection();
                        validInput = checkRoomSelection(_roomSelection, checkInDate, checkOutDate);
                        if(validInput){
                            roomNumber = _roomSelection;
                        }
                    }

                    // Add new Reservation to the collection.
                    Reservation _newReservation = hr.bookARoom(customerEmail, hr.getRoom(roomNumber), checkInDate, checkOutDate);
                    System.out.println(_newReservation);
                }
                case 2 -> viewCustomerReservations(getValidEmail());
                case 3 -> createAccount();
                case 4 -> adminMenu();
                case 5 -> exitMenu();
                default -> System.out.println("Not a valid choice");
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

    public int getAction(){
        splashScreen();
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        String actionString;
        int action = 0;

        while(!validInput){
            actionString = scanner.nextLine();
            try {
                action = Integer.parseInt(actionString);
            } catch(NumberFormatException ex){
                System.out.println("Invalid Input: must be an integer");
                continue;
            }
            validInput = true;
        }
        return action;
    }

// Viewing Reservations

    public void viewCustomerReservations(String customerEmail){
        HotelResource hs = HotelResource.getInstance();
        Collection<Reservation> personReservations = hs.getCustomersReservations(customerEmail);

        if (personReservations.size() == 0){
            System.out.println("No Reservations found for " + customerEmail);
        }

         Iterator <Reservation> iterator = personReservations.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next() + "\n");
        }

    }

    public String requestCustomerEmail(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer email to search for reservations");
        return scanner.nextLine();
    }

    public void createAccount(){
        HotelResource hs = HotelResource.getInstance();
        hs.createACustomer(getValidNewEmail(), getValidName("First Name"), getValidName("Last Name"));
    }

    public String createAccountReturnEmail(){
        HotelResource hs = HotelResource.getInstance();
        String email = getValidNewEmail();
        hs.createACustomer(email, getValidName("First Name"), getValidName("Last Name"));

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

    public void DisplayRooms(Date checkIn, Date checkOut, int retryDays){
        HotelResource hr = HotelResource.getInstance();

        Collection<IRoom> _openRooms = hr.findARoom(checkIn, checkOut);
        System.out.println("Available Rooms:");
        for (IRoom openRoom : _openRooms) {
            System.out.println(openRoom);
        }
        if(_openRooms.size() == 0) {
            System.out.println("no rooms in original range");
            // Try again with +7 days
            Calendar cal = Calendar.getInstance();

            // Update dates forward by retryDays
            checkIn = incrementDate(checkIn, retryDays);
            checkOut = incrementDate(checkOut, retryDays);

            _openRooms = hr.findARoom(checkIn, checkOut);
            System.out.println("Available Rooms + 7 days:");
            for (IRoom openRoom : _openRooms) {
                System.out.println(openRoom);
            }
            if (_openRooms.size() == 0) {
                System.out.println("no rooms in +7 day range");
                startActions(); // exit back to main menu
            }
        }
    }

    public Date incrementDate(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    //DisplayRooms(checkInDate, checkOutDate, 7);

    // Display available rooms




// Request Room Number
    public String requestRoomSelection(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What room would you like?");
        return scanner.nextLine();
    }

    public boolean checkRoomSelection(String roomSelection, Date checkInDate, Date checkOutDate){
        HotelResource hr = HotelResource.getInstance();
        if(!hr.roomExists(roomSelection)) {
            System.out.println(roomSelection + " is not a room at this hotel");
            return false;
        }

        // check if there is already a reservation
        ArrayList<IRoom> _openRooms = new ArrayList<>(hr.findARoom(checkInDate, checkOutDate));

        for (int i = 0; i < _openRooms.size(); i++){
            if (roomSelection.equals(_openRooms.get(i).getRoomNumber())) {
                return true;
            }
        }
        // fallthrough
        System.out.println(roomSelection + " already has a reservation, try another room");
        return false;

    }

// Date
    public Date getValidDate(String msg){
        Date date = null;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        while(!validInput){
            System.out.println(msg);
            try{
                date = new SimpleDateFormat("MM/dd/yyy").parse(scanner.nextLine());
            } catch (ParseException ex){
                System.out.println("Invalid Input");
                continue;
            }
            if(date.before(new java.util.Date())){
                System.out.println(date + " is already in the past");
                continue;
            }
            validInput = true;
        }
        return date;
    }

    public Date getValidDate(String msg, Date firstDate){
        Date date = null;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        while(!validInput){
            System.out.println(msg);
            try{
                date = new SimpleDateFormat("MM/dd/yyy").parse(scanner.nextLine());
            } catch (ParseException ex){
                System.out.println("Invalid Input");
                continue;
            }
            if(date.before(firstDate)){
                System.out.println("Must be after " + firstDate);
                continue;
            }
            validInput = true;
        }
        return date;
    }

// CheckIn Date
    public String requestCheckInDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter CheckIn Date mm/dd/yyyy example 06/01/2021");
        return scanner.nextLine();
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
    public String getValidNewEmail (){
        String email = null;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        while(!validInput){
            System.out.println("Enter Email format: name@domain.com");
            email = scanner.nextLine();
            if (!checkEmail(email)){ continue; }
            if (!checkEmailExists(email)){ continue; }
            validInput = true;
        }
        return email;
    }

    public String getValidEmail(){
        String email = null;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        while(!validInput){
            System.out.println("Enter Email format: name@domain.com");
            email = scanner.nextLine();
            if (!checkEmail(email)){ continue; }
            validInput = true;
        }
        return email;
    }

    public boolean checkEmail(String email){
        if(Pattern.matches("^(.+)@(.+)\\.(.+)$", email)){ return true; }
        else {
            System.out.println(email + " is incorrect. format: name@domain.com");
            return false;
        }
    }

    public boolean checkEmailExists(String email){
        AdminResource ar = AdminResource.getInstance();
        ArrayList<Customer> _customerList = new ArrayList<>(ar.getAllCustomers());

        for (int i = 0; i < _customerList.size(); i++){
            if(email.equals(_customerList.get(i).getEmail())){
                System.out.println(email + " is already used by another account");
                return false;
            }
        }
        return false;
    }



// Name
    public String getValidName(String msg){
        String name = null;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);
        while(!validInput){
            System.out.println(msg);
            name = scanner.nextLine();
            if(name.length() == 0){
                System.out.println("Input cannot be blank");
                continue;
            }
            if(name.length() >= 30){
                System.out.println("Input must be < 30 char");
                continue;
            }
            validInput = true;
        }
        return name;
    }


    public void adminMenu(){
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.startActions();
    }

    public void exitMenu(){
        System.exit(0);

    }


}
