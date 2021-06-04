package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import model.*;

public class MainMenu {

    public MainMenu(){}

    public void startActions(){
        while (true){
            switch (getAction()) {
                case 1 -> {
                    Date checkIn = getValidDate("Enter CheckIn Date: mm/dd/yyyy");
                    Date checkOut = getValidDate("Enter CheckOut Date: mm/dd/yyyy", checkIn);
                    DisplayRooms(checkIn, checkOut, 7);

                    if(!binaryResponse("Would you like to book a room?")){
                        startActions();
                    }

                    String customerEmail;
                    if(binaryResponse("Do you have an account with us?")){ customerEmail = getValidCurrentEmail();}
                    else { customerEmail = createAccountReturnEmail();}

                    String roomNumber = getValidRoomSelection(checkIn, checkOut, "What room would you like?");

                    HotelResource hr = HotelResource.getInstance();
                    Reservation _newReservation = hr.bookARoom(customerEmail, hr.getRoom(roomNumber), checkIn, checkOut);
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

    public boolean binaryResponse(String msg){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(msg + " y or n");
            String res = scanner.nextLine();
            if(res.equals("y") || res.equals("Y")){ return true;}
            if(res.equals("n") || res.equals("N")){ return false;}
            System.out.println("Invalid Input: options are y or n");
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


// Request Room Number
    public String getValidRoomSelection(Date checkIn, Date checkOut, String msg){
        Scanner scanner = new Scanner(System.in);
        String roomNumber = null;
        boolean validInput = false;
        while(!validInput){
            System.out.println(msg);
            roomNumber = scanner.nextLine();
            if(!checkRoomSelection(roomNumber, checkIn, checkOut)){ continue;}
            validInput = true;
        }
        return roomNumber;
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

// Email
    public String getValidNewEmail (){
        String email = null;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        while(!validInput){
            System.out.println("Enter email format: name@domain.com");
            email = scanner.nextLine();
            if (!checkEmail(email)){ continue; }
            if (checkEmailExists(email)){
                System.out.println(email + " is already used by another account");
                continue;
            }
            validInput = true;
        }
        return email;
    }

    public String getValidCurrentEmail (){
        String email = null;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        while(!validInput){
            System.out.println("Enter existing account email: name@domain.com");
            email = scanner.nextLine();
            if (!checkEmail(email)){ continue; }
            if (!checkEmailExists(email)){
                System.out.println("email not on file, try again and create an account");
                continue;
            }

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
        if(Pattern.matches("^(.+)@(.+)\\.(.+)$", email)){
            return true;
        } else {
            System.out.println(email + " is incorrect. format: name@domain.com");
            return false;
        }
    }

    public boolean checkEmailExists(String email){
        AdminResource ar = AdminResource.getInstance();
        ArrayList<Customer> _customerList = new ArrayList<>(ar.getAllCustomers());

        for (int i = 0; i < _customerList.size(); i++){
            if(email.equals(_customerList.get(i).getEmail())){
                return true;
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
