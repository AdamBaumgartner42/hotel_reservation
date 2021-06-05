package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Reservation {

    protected Customer customer;
    protected IRoom room;
    protected Date checkIn;
    protected Date checkOut;

    // Constructor
    public Reservation(Customer customer, IRoom room, Date checkInDate,
                       Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkIn = checkInDate;
        this.checkOut = checkOutDate;
    }

    public Customer getCustomer(){
        return customer;
    }

    public Date getCheckInDate(){ return checkIn;}

    public Date getCheckOutDate(){
        return checkOut;
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
                + "CheckIn Date: " + dateFormat.format(checkIn) + "\n"
                + "CheckOut Date: " + dateFormat.format(checkOut);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reservation res))
            return false;
        return res.room.equals(room) &&
                res.checkIn.equals(checkIn) &&
                res.checkOut.equals(checkOut);
    }

    @Override
    public int hashCode(){
        return Objects.hash(room, checkIn, checkOut);
    }
}
