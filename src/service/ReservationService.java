package service;

import java.util.*
import model.IRoom;
import model.Reservation;
import model.Customer;

public class ReservationService {

    // Static Method?
    private static final ReservationService instance = new ReservationService();

    private ReservationService(){
        // Constructor info
    }

    public static ReservationService getInstance(){
        return instance;
    }
    // -- end Static Method ?

    // Class methods

    public void addRoom(IRoom room){

    }

    public IRoom getARoom (String roomId){

    }

    public Reservation reserveARoom(Customer customer, IRoom room,
                                    Date checkInDate, Date checkOutDate){

    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){

    }

    public Collection<Reservation> getCustomersReservation(Customer customer){

    }

    public void printAllReservation(){

    }

}
