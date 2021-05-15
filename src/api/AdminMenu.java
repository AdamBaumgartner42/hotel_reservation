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
                    break;
                case 2:
                    seeAllRooms();
                    break;
                case 3:
                    break;
                case 4:
                    addARoom();
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

    public void addARoom(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add a room");
        System.out.println("Enter room number");
        String roomNumber = scanner.nextLine();
        System.out.println("Enter room price");
        Double roomPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter room type: Single / Double");
        //String roomType = scanner.nextLine(); // missing enum specifically
        RoomType roomType = RoomType.SINGLE;



        // I don't think this is actually updating Room List
        // Based on test with seeAllRooms().
        List<IRoom> roomList = new ArrayList<>();
        roomList.add(new Room(roomNumber, roomPrice, roomType));

        // Add to actual collection
        AdminResource adminResource = AdminResource.getInstance();
        adminResource.addRoom(roomList);

        // temp
        // print the current list of rooms.
    }

    public void seeAllRooms(){
        // Waiting on update for addARoom to actually update IROOM_COLLECTION
        // Loop over the Collection<IRoom>
        AdminResource adminResource = AdminResource.getInstance();

        System.out.println(adminResource.testAdminResource());

        for(IRoom room: adminResource.getAllRooms()){
            System.out.println();
        }
    }

    public void mainMenu(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.startActions();
    }

}

