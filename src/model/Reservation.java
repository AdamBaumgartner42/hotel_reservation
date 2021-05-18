package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    protected Customer customer;
    protected IRoom room;
    protected Date checkInDate;
    protected Date checkOutDate;

    // Constructor
    public Reservation(Customer customer, IRoom room, Date checkInDate,
                       Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer(){
        return customer;
    }

    public Date getCheckInDate(){ return checkInDate;}

    public Date getCheckOutDate(){
        return checkOutDate;
    }

    public IRoom getRoom(){
        return room;
    }

    @Override
    public String toString() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d yyyy");

        return "Reservation\n"
                + customer.getFirstName()
                + " " + customer.getLastName() + "\n"
                + "Room: " + room.getRoomNumber() + " - " + room.getRoomTypeText() + "\n"
                + "Price: " + "$" + room.getRoomPrice() + " price per night \n"
                + "CheckIn Date: " + dateFormat.format(checkInDate) + "\n"
                + "CheckOut Date: " + dateFormat.format(checkOutDate);
    }
}
