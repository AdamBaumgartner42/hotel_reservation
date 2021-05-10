package api;

import java.util.*;
import model.*;


public class HotelResource {
    /* --- HotelResource Class - Static Reference -- */
    private static HotelResource hotelResourceInstance;

    private HotelResource() {}

    public static HotelResource getInstance(){
        if (hotelResourceInstance == null){
            hotelResourceInstance = new HotelResource();
        }
        return hotelResourceInstance;
    }

    /*
    public Customer getCustomer(String email){

    }

    public void createACustomer(String email, String firstName, String lastName){

    }

    public IRoom getRoom(String roomNumber){

    }

    public Reservation bookARoom(String customerEmail, IRoom room,
                                 Date checkInDate, Date checkOutDate){

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){

    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){

    }

     */


}
