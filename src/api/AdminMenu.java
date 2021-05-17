package api;

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
        splashScreen();
        boolean keepRunning = true;
        while(keepRunning){
            splashScreen();
            int action = getAction();
            switch (action) {
                case 1:
                    seeAllCustomers();
                    break;
                case 2:
                    seeAllRooms(); // OK
                    break;
                case 3:
                    seeAllReservations();
                    break;
                case 4:
                    addARoom(); // OK
                    break;
                case 5:
                    mainMenu();
                    keepRunning = false;
                    break;
            }
        }
    }

    public void splashScreen(){
        System.out.println(" Admin Menu \n"
                + "----------- \n"
                + "1. See all Customers \n"
                + "2. See all Rooms \n"
                + "3. See all Reservations\n"
                + "4. Add a Room\n"
                + "5. Add Test Data \n"
                + "6. Back to Main Menu\n"
                + "-----------");
    }

    public int getAction(){
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
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
            System.out.println(iterator.next());
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add a room");
        System.out.println("Enter room number");
        String roomNumber = scanner.nextLine();
        System.out.println("Enter room price");
        Double roomPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter room type: Single (1) / Double (2)");
        int roomTypeInput = Integer.parseInt(scanner.nextLine()); // missing enum specifically
        RoomType roomType = null;
        if (roomTypeInput == 1){
            roomType = RoomType.SINGLE;
        } else if (roomTypeInput == 2){
            roomType = RoomType.DOUBLE;
        } else {
            System.out.println("unrecognized room type");
        }

        // Create List<IRoom> for AdminResource function
        // public void addRoom(List<IRoom> rooms){ ... }
        List<IRoom> roomList = new ArrayList<>();
        IRoom tempRoom = new Room(roomNumber, roomPrice, roomType);
        roomList.add(tempRoom);

        // Add to actual collection
        AdminResource adminResource = AdminResource.getInstance();
        adminResource.addRoom(roomList);

    }

// Add Test Data



// Back to Main Menu
    public void mainMenu(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.startActions();
    }




}

