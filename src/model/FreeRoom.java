package model;

public class FreeRoom extends Room{

    // Free Room (Removes the input for price)
    // simply hard-codes it in as 0.0.
    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
    }



    @Override
    public String toString() {
        return "Room number: " + roomNumber +
                "\nRoom price: " + price +
                "\nRoomType: " + enumeration + " *Free Room* ";
    }
}
