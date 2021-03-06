package model;

public interface IRoom {

    public String getRoomNumber();

    public Double getRoomPrice();

    public RoomType getRoomType();

    public String getRoomTypeText();

    public boolean isAvailable();
}
