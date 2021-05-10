package api;

import java.util.*;
import model.*;

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
/*
    public Customer getCustomer(String email){

    }

    public void addRoom(List<IRoom> rooms){

    }

    public Collection<IRoom> getAllRooms(){

    }

    public Collection<Customer> getAllCustomers(){

    }

    public void displayAllReservations(){

    }

 */
}
