package service;

import java.util.*;
import model.*;
import static api.CollectionResource.*;

public class ReservationService{
    /* --- ReservationService Class - Static Reference -- */
    private static ReservationService reservationServiceInstance;
    private ReservationService() {}
    public static ReservationService getInstance(){
        if (reservationServiceInstance == null){
            reservationServiceInstance = new ReservationService();
        }
        return reservationServiceInstance;
    }

    /* --- Class Methods --- */
    public void addRoom(IRoom room){

        IROOM_COLLECTION.add(room);
    }

    public Collection<IRoom> getAllRooms(){

        return IROOM_COLLECTION;
    }

    public IRoom getARoom (String roomNumber){
        ArrayList<IRoom> _IRoomArrayList = new ArrayList<>(IROOM_COLLECTION);

        for(int i = 0; i < _IRoomArrayList.size(); i++){
            if(_IRoomArrayList.get(i).getRoomNumber().equals(roomNumber)){
                return _IRoomArrayList.get(i);
            }
        }

        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation _newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        RESERVATION_COLLECTION.add(_newReservation);
        return _newReservation;
    }

    public Collection<IRoom> findRooms(Date checkIn, Date checkOut){
        HashSet<Reservation> _ReservationList = new HashSet<>(RESERVATION_COLLECTION);
        ArrayList<IRoom> _AvailableRoomList = new ArrayList<>(IROOM_COLLECTION);
        ArrayList<IRoom> _BookedRooms = new ArrayList<>();

        // Identify which rooms have interference with planned date
        for(Reservation val : _ReservationList){
            if(checkIn.before(val.getCheckOutDate()) &&
              (checkOut.after(val.getCheckInDate()))) {
                _BookedRooms.add(val.getRoom());
            }
        }

        // Remove those _BookedRooms from the list of all Rooms
        for (IRoom bookedRoom : _BookedRooms) {
            _AvailableRoomList.remove(bookedRoom);
        }
        return _AvailableRoomList;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        HashSet<Reservation> _reservations = new HashSet<>(RESERVATION_COLLECTION);
        Collection<Reservation> _CustomerReservationCollection = new HashSet<>();

        for(Reservation res : _reservations){
            if(res.getCustomer().equals(customer)){
                _CustomerReservationCollection.add(res);
            }
        }

        return _CustomerReservationCollection;
    }

    public Collection<Reservation> getAllReservations(){
        return RESERVATION_COLLECTION;
    }

    // default access modifier (aka "blank") example
    int getCustomerReservationCount(Customer customer){
        HashSet<Reservation> _reservations = new HashSet<>(RESERVATION_COLLECTION);
        int resCount = 0;
        for(Reservation val : _reservations){
            if(val.getCustomer().equals(customer)){
                resCount++;
            }
        }
        return resCount;
    }

}
