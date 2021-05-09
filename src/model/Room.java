package model;

public class Room implements IRoom {

    protected String roomNumber;
    protected Double price;
    protected RoomType enumeration;

    // Constructor

    public Room (String roomNumber, Double price,
                 RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return null;
    }

    @Override
    public Double getRoomPrice() {
        return null;
    }

    @Override
    public RoomType getRoomtype() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room number: " + roomNumber +
                "\nRoom price: " + price +
                "\nRoomType: " + enumeration;
    }
}
