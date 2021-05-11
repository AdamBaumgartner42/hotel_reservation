package service;

import java.util.*;
import model.IRoom;
import model.Reservation;
import model.Customer;
import model.Room;

public class ReservationService {
    /* --- ReservationService Class - Static Reference -- */
    private static ReservationService reservationServiceInstance;

    private ReservationService() {}

    public static ReservationService getInstance(){
        if (reservationServiceInstance == null){
            reservationServiceInstance = new ReservationService();
        }
        return reservationServiceInstance;
    }

    /* --- ROOM_LIST - Static Reference --- */
    static final List<IRoom> ROOM_LIST;

    static{
        ROOM_LIST = new ArrayList<>();
    }

    /* --- RESERVATION_LIST - Static Reference --- */
    static final List<Reservation> RESERVATION_LIST;

    static{
        RESERVATION_LIST = new ArrayList<>();
    }

    /* --- Class Methods --- */
    public void addRoom(IRoom room){
        ROOM_LIST.add(room);
    }

//    public IRoom getARoom (String roomId){
//        //Check that this is a valid email
//
//
//    }

    /*
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        // need a place to store reservations
        //return RESERVATION_LIST

    }

     */

/*
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){

    }
*/
//    public Collection<Reservation> getCustomersReservation(Customer customer){
//        // Not isolated for single customer
//        // -> Change to hashmap(key: customer), then return only those values as collection?
//        return RESERVATION_LIST;
//    }

    /*
    public void printAllReservation(){

    }

 */

}
