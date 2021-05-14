package api;

import java.util.*;
import model.*;
import service.*;

public class AdminResource {
    /* --- adminResource Class - Static Reference -- */
    private static AdminResource adminResourceInstance;

    private AdminResource() {}

    public static AdminResource getInstance(){
        if (adminResourceInstance == null){
            adminResourceInstance = new AdminResource();
        }
        return adminResourceInstance;
    }

    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email){

        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){

        for(int i = 0; i < rooms.size(); i++){
            reservationService.addRoom(rooms.get(i));
        }
    }

    public Collection<IRoom> getAllRooms(){

        return reservationService.getAllRooms();

    }

    public Collection<Customer> getAllCustomers(){

        return customerService.getAllCustomers();

    }

    public void displayAllReservations(){

        reservationService.printAllReservation();

    }

}
