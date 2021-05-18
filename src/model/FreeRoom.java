package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
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
                ", Room price: $" + price;
    }
}
