package api;

import model.*;
import java.util.*;


public class CollectionResource {

    /* --- CUSTOMER_COLLECTION - Static Reference --- */
    public static final Collection<Customer> CUSTOMER_COLLECTION;
    static{
        CUSTOMER_COLLECTION = new ArrayList<>();
    }

    /* --- RESERVATION_COLLECTION - Static Reference --- */
    public static final Collection<Reservation> RESERVATION_COLLECTION;
    static{
        RESERVATION_COLLECTION = new ArrayList<>();
    }

    /* --- IROOM_COLLECTION - Static Reference --- */
    public static final Collection<IRoom> IROOM_COLLECTION;
    static{
        IROOM_COLLECTION = new ArrayList<>();
    }

    // IRoom list will be defined in Admin Resource as a local change

}
