package service;

import java.util.*;
import model.*;
import static api.CollectionResource.*;

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

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        // Array init
        ArrayList<Reservation> _ReservationList = new ArrayList<>(RESERVATION_COLLECTION);
        ArrayList<IRoom> _AvailableRoomList = new ArrayList<>(IROOM_COLLECTION);
        ArrayList<IRoom> _BookedRooms = new ArrayList<>();

        // For Loop to identify which rooms have interference with planned date
        for (int i = 0; i < _ReservationList.size(); i++){
            // Flag incorrect rooms:
            if(checkInDate.before(_ReservationList.get(i).getCheckOutDate()) &&
                (checkOutDate.after(_ReservationList.get(i).getCheckInDate())))
            {
                _BookedRooms.add(_ReservationList.get(i).getRoom());
            }
        }

        // For Loop to remove those _BookedRooms from the list of all Rooms
        for(int i = 0; i < _BookedRooms.size(); i++){
            if(_AvailableRoomList.contains(_BookedRooms.get(i))){
                _AvailableRoomList.remove(_BookedRooms.get(i));
            }
        }

        return _AvailableRoomList;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> _CustomerReservationCollection = new ArrayList<>();

        ArrayList<Reservation> _ReservationList = new ArrayList<>(RESERVATION_COLLECTION);

        for(int i = 0; i < _ReservationList.size(); i++){
            if(_ReservationList.get(i).getCustomer() == customer){
                _CustomerReservationCollection.add(_ReservationList.get(i));
            }
        }
        return _CustomerReservationCollection;
    }


    public void printAllReservation(){
        ArrayList<Reservation> _ReservationList = new ArrayList<>(RESERVATION_COLLECTION);

        for(int i = 0; i < _ReservationList.size(); i++){
            System.out.println(_ReservationList.get(i));
        }
    }

    public Collection<Reservation> getAllReservations(){
        return RESERVATION_COLLECTION;
    }

}
