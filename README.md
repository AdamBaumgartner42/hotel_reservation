# hotel_reservation

Hotel Reservation System

Udacity Java nanodegree project (1/3) 

--- User Scenarios ---
* Creating a customer account: 
  The user needs to first create a customer account 
  before they can make a reservation.
  
* Searching for rooms: 
  The app should allow the user to search for available rooms
  based on provided check in and checkout dates. 
  
 * If the application has available rooms for the specified date
  range, a list of the corresponding rooms will be displayed to
  the user for choosing.
  
* Booking a room: 
  Once the user has chosen a room, the app will allow them to
  book the room and create a reservation.
  
* Viewing reservations: 
  After book a room, the app allows customers to view a list
  of all their reservations.
  

--- Admin Scenarios ---
* Displaying all customer accounts.
* Viewing all the rooms in the hotel.
* Viewing all the hotel reservations.
* Adding a room to the hotel application


--- Reserving a Room - Requirements ---
* Avoid conflicting reservations: 
  A single room may only be reserved by a single customer per
  a check in and checkout date range. 
  
* Search for recommended rooms:
  If there are no available rooms for the customer's date
  range, a search will be performed that displays recommended
  rooms on alternative dates.  The recommended room search 
  will add seven days to the original check in and checkout
  dates to see if the hotel has any availabilities, and then
  display the recommended room/dates to the customer. 
  
--- Room requirements ---
* Room Cost:
  Rooms will contain a price per night. When displaying rooms, 
  paid rooms will display the price per night and free rooms
  will display "Free" or have a $0 price. 
  
* Unique room numbers:
  Each room will have a unique room number, meaning that no
  two rooms can have the same room number.
  
* Room type: 
  Rooms can be either single occupant or double occupant
  (Enumberation: SINGLE, DOUBLE).
  

--- Customer Requirements ---
  The Application will have customer accounts, 
  each account has,
  
