package api;

import java.util.*;
import model.*;
import service.*;

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

    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email){

        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){

        customerService.addCustomer(firstName, lastName, email);
    }

    public IRoom getRoom(String roomNumber){

        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room,
                                 Date checkInDate, Date checkOutDate){

        Customer _customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(_customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){

        Customer _customer = customerService.getCustomer(customerEmail);
        // Is the object showing up here properly
        return reservationService.getCustomersReservation(_customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){

        return reservationService.findRooms(checkIn, checkOut);
    }
}
