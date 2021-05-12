package service;

import model.*;
import static model.RoomType.*;
import java.util.*;



public class ReservationServiceTest {

    public static void main(String[] args){
        // Init ReservationService Instance
        ReservationService rs = ReservationService.getInstance();

        // Test addRooms()
        rs.addRoom(new Room("101",50.0, SINGLE));
        rs.addRoom(new Room("102", 50.0, DOUBLE));
        rs.addRoom(new FreeRoom("103", DOUBLE));
        rs.addRoom(new Room("104", 50.0, SINGLE));
        rs.addRoom(new Room("201", 50.0, DOUBLE));
        rs.addRoom(new Room("301", 50.0, SINGLE));
        rs.addRoom(new Room("302", 50.0, DOUBLE));

        // Test getRooms()

        System.out.println(rs.getARoom("103"));

        // Test reserveARoom()
        Customer Adam = new Customer("Adam", "Baumgartner", "adam@gmail.com");
        Calendar _checkInDate = Calendar.getInstance();
        Calendar _checkOutDate = Calendar.getInstance();
        _checkInDate.set(2021,02,01);
        _checkOutDate.set(2021, 03,01);

        Reservation testReservation = rs.reserveARoom(Adam,rs.getARoom("201"),_checkInDate.getTime(), _checkOutDate.getTime());

        // Test findRooms()
        _checkInDate.set(2021,01,21);
        _checkOutDate.set(2021, 03,04);

        Collection<IRoom> openRooms = new ArrayList<>();

        openRooms = rs.findRooms(_checkInDate.getTime(), _checkOutDate.getTime());

        ArrayList<IRoom> _openRooms = new ArrayList<>();
        _openRooms.addAll(openRooms);

        for(int i = 0; i < _openRooms.size(); i++){
            System.out.println(_openRooms.get(i).getRoomNumber());
        }


        // Test Print All Reservations
        rs.printAllReservation();
    }
}
