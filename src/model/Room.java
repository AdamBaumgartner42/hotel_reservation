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
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public String getRoomTypeText(){
        String roomTypeMsg = null;
        if (enumeration.equals(RoomType.SINGLE)){
            roomTypeMsg = "Single Bed";
        } else {
            roomTypeMsg = "Double Bed";
        }
        return roomTypeMsg;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public String toString() {

        String roomTypeMsg = null;
        if (enumeration.equals(RoomType.SINGLE)){
            roomTypeMsg = "Single Bed";
        } else {
            roomTypeMsg = "Double Bed";
        }
        return " Room number: " + roomNumber +
                ", RoomType: " + roomTypeMsg +
                ", Room price: $" + price ;
    }
}
