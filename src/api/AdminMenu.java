package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import model.*;

public class AdminMenu {
    /*
    1. See all Customers - Display all customer accounts
    2. See all Rooms - View all the rooms in the hotel
    3. See all Reservations - View all the hotel reservations
    4. Add a room - Add a room to the hotel application
    5. Back to Main Menu
     */

    public AdminMenu(){}

    public void startActions(){
        boolean keepRunning = true;
        while(keepRunning){
            splashScreen();
            String actionString = null;
            int action = 0;
            boolean validInput = false;
            while (!validInput){
                actionString = getActionString();
                validInput = checkGetAction(actionString, 1, 6);
                if(validInput){
                    action = Integer.parseInt(actionString);
                }
            }
            switch (action) {
                case 1 -> seeAllCustomers();
                case 2 -> seeAllRooms();
                case 3 -> seeAllReservations();
                case 4 -> addARoom();
                case 5 -> {
                    testAddRooms();
                    testAddCustomers();
                    testAddReservations();
                }
                case 6 -> {
                    mainMenu();
                    keepRunning = false;
                }
            }
        }
    }

    public void splashScreen(){
        System.out.println("Admin Menu\n"
                + "--------------------------------- \n"
                + "1. See all Customers \n"
                + "2. See all Rooms \n"
                + "3. See all Reservations\n"
                + "4. Add a Room\n"
                + "5. Add Test Data \n"
                + "6. Back to Main Menu\n"
                + "---------------------------------\n"
                + "Please select a number for the menu option");
    }

//    public int getAction(){
//        Scanner scanner = new Scanner(System.in);
//        return Integer.parseInt(scanner.nextLine());
//    }

    public String getActionString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean checkGetAction(String action, int low, int high){
        if(action.equals("1") || action.equals("2") || action.equals("3") ||
                action.equals("4") || action.equals("5") || action.equals("6")) {
            return true;
        } else {
            System.out.println("Invalid Input: Must be between " + low + " and " + high);
            return false;
        }
    }

// See all Customers
    public void seeAllCustomers(){
        AdminResource ar = AdminResource.getInstance();
        Collection<Customer> _customerList = ar.getAllCustomers();

        Iterator<Customer> iterator = _customerList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

// See all Reservations
    public void seeAllReservations(){
        AdminResource ar = AdminResource.getInstance();
        Collection<Reservation> _reservationList = ar.getAllReservations();

        Iterator<Reservation> iterator = _reservationList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next() + "\n");
        }
    }


// See all Rooms
    public void seeAllRooms(){
        AdminResource ar = AdminResource.getInstance();
        Collection<IRoom> temp = ar.getAllRooms();
        Iterator<IRoom> iterator = temp.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

// Add a Room
    public void addARoom(){
        List<IRoom> roomList = new ArrayList<>();
        boolean addAnotherRoom = false;
        boolean inputValid;

        do {
            addAnotherRoom = false;
            Scanner scanner = new Scanner(System.in);

            // Variables
            String roomNumber = null;
            String roomPriceRaw = null;
            Double roomPrice = null;
            int roomTypeInput;
            RoomType roomType = null;
            String anotherRoomResponse = null;

            // Input Room Number
            inputValid = false;
            while (!inputValid){
                roomNumber = requestRoomNumber();
                inputValid = checkRoomNumber(roomNumber);
            }

            // Input Price Per Night
            inputValid = false;
            while (!inputValid){
                roomPriceRaw = requestRoomPrice();
                inputValid = checkRoomPrice(roomPriceRaw);
                if(inputValid){
                    roomPrice = Double.parseDouble(roomPriceRaw);
                }
            }

            // Input Room Type
            inputValid = false;
            while (!inputValid){
                String _roomTypeInput = requestRoomTypeInput();
                inputValid = checkRoomType(_roomTypeInput);
                if (inputValid){
                    int roomTypeInt = Integer.parseInt(_roomTypeInput);
                    roomType = intToRoomType(roomTypeInt);
                }
            }

            // Create new room and add to list
            IRoom tempRoom = new Room(roomNumber, roomPrice, roomType);
            roomList.add(tempRoom);

            // Request another Room Response
            // Check another Room Response
            inputValid = false;
            while (!inputValid){
                anotherRoomResponse = requestAnotherRoomResponse();
                inputValid = checkAnotherRoomResponse(anotherRoomResponse);
                if (inputValid){
                    if(anotherRoomResponse.equals("y") ||
                        anotherRoomResponse.equals("Y")){
                        addAnotherRoom = true;
                    }
                }
            }
        } while (addAnotherRoom);


        AdminResource adminResource = AdminResource.getInstance();
        adminResource.addRoom(roomList);

    }

    public String requestRoomNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room number");
        return scanner.nextLine();
    }

    public String requestRoomPrice(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room price per night");
        return scanner.nextLine();
    }



    public String requestRoomTypeInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
        return scanner.nextLine();
    }

    public String requestAnotherRoomResponse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to add another room? y/n");
        return scanner.nextLine();
    }

    public boolean checkRoomNumber(String roomNumber){

        // Confirm string roomNumber is a valid number
        int roomInt = 0;
        try {
            roomInt = Integer.parseInt(roomNumber);
        } catch (NumberFormatException ex){
            //System.out.println(ex.getLocalizedMessage());
            System.out.println("Invalid Input: Room Number must be an integer");
            return false;
        }

        if(roomInt < 0){
            System.out.println("Room Number must be positive integer");
            return false;
        } else if (roomInt > 10000){
            System.out.println("Room Number must be at or below 10,000");
            return false;
        } else {
            return true;
        }
    }


    public boolean checkRoomPrice(String roomPriceRaw){

        // Confirm valid double
        try {
            Double.parseDouble(roomPriceRaw);
        } catch (NumberFormatException ex){
            //System.out.println(ex.getLocalizedMessage());
            System.out.println("Invalid Input: Room Price should be a double");
            return false;
        }

        return true;
    }

    public boolean checkRoomType(String roomTypeInput){
        int _input = 0;
        try{
            _input = Integer.parseInt(roomTypeInput);
        } catch (NumberFormatException ex){
            System.out.println("Invalid Input");
            return false;
        }

        if (!( _input == 1 || _input == 2)){
            System.out.println(_input + " is not recognized");
            return false;
        } else {
            return true;
        }
    }

    public RoomType intToRoomType(int roomTypeInput){
        if (roomTypeInput == 1) {
            return RoomType.SINGLE;
        } else if (roomTypeInput == 2) {
            return RoomType.DOUBLE;
        }
        else {
            System.out.println("Unrecognized room -> save as double");
            return RoomType.DOUBLE;
        }

    }

    public boolean checkAnotherRoomResponse(String anotherRoomResponse){
        if(anotherRoomResponse.equals("y") || anotherRoomResponse.equals("Y")){
            return true;
        } else if (anotherRoomResponse.equals("n") || anotherRoomResponse.equals("N")){
            return true;
        } else {
            System.out.println("Please enter Y (Yes) or N (No)");
            return false;
        }
    }





// Add Test Data

    public void testAddRooms(){
        List<IRoom> roomList = new ArrayList<>();
        IRoom room_1 = new Room("100", 200.0, model.RoomType.SINGLE);
        roomList.add(room_1);
        IRoom room_2 = new Room("101", 200.0, model.RoomType.SINGLE);
        roomList.add(room_2);
        IRoom room_3 = new Room("102", 250.0, model.RoomType.DOUBLE);
        roomList.add(room_3);
        IRoom room_4 = new Room("103", 200.0, model.RoomType.SINGLE);
        roomList.add(room_4);
        IRoom room_5 = new Room("104", 200.0, model.RoomType.SINGLE);
        roomList.add(room_5);
        IRoom room_6 = new FreeRoom("105", model.RoomType.SINGLE);
        roomList.add(room_6);

        // Add to actual collection
        AdminResource ar = AdminResource.getInstance();
        ar.addRoom(roomList);
    }

    public void testAddCustomers(){
        HotelResource hr = HotelResource.getInstance();
        hr.createACustomer("adam@gmail.com", "Adam", "Baumgartner");
        hr.createACustomer("mark@gmail.com", "Mark", "Ford");
        hr.createACustomer("steve@gmail.com", "Steve", "Rogers");
        hr.createACustomer("fred@gmail.com", "Fred", "Talbot");
        hr.createACustomer("dale@gmail.com", "Dale", "Roberts");
        hr.createACustomer("jim@gmail.com", "Jim", "Bouton");
        hr.createACustomer("cecil@gmail.com", "Cecil", "Perkins");
        hr.createACustomer("fritz@gmail.com", "Fritz", "Peterson");
        hr.createACustomer("hal@gmail.com", "Hal", "Reniff");
    }

    public void testAddReservations(){
        HotelResource hr = HotelResource.getInstance();

        hr.bookARoom("adam@gmail.com", hr.getRoom("100"), setDate("18/05/2021"), setDate("19/05/2021"));
        hr.bookARoom("steve@gmail.com", hr.getRoom("101"), setDate("18/05/2021"), setDate("19/05/2021"));

    }

    public Date setDate(String input){
        Date returnDate;
        try {
            returnDate = new SimpleDateFormat("dd/MM/yyyy").parse(input);
        } catch (ParseException ex){
            System.out.println("Incorrect Date Format");
            returnDate = null;
        }
        return returnDate;
    }

// Back to Main Menu
    public void mainMenu(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.startActions();
    }




}

