package service;

import java.util.*;
import model.IRoom;
import model.Reservation;
import model.Customer;

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
    static final Map<String, IRoom> ROOM_LIST;

    static{
        ROOM_LIST = new HashMap<String, IRoom>();
    }

    /* --- RESERVATION_LIST - Static Reference --- */
    static final Collection<Reservation> RESERVATION_LIST = null;

    static{
        RESERVATION_LIST = new Collection<Reservation>;
    }

    /* --- Class Methods --- */
    public void addRoom(IRoom room){
        // Add room to ROOM_LIST >?
        //ROOM_LIST.put(// breakout of room info); >?
    }

    public IRoom getARoom (String roomId){
        //Check that this is a valid email
        return ROOM_LIST.get(roomId);
    }

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
    public Collection<Reservation> getCustomersReservation(Customer customer){
        // Not isolated for single customer
        // -> Change to hashmap(key: customer), then return only those values as collection?
        return RESERVATION_LIST;
    }

    /*
    public void printAllReservation(){

    }

 */

}
