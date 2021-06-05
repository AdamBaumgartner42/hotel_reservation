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
        //RESERVATION_COLLECTION = new ArrayList<>();
        RESERVATION_COLLECTION = new HashSet<>();
    }

    /* --- IROOM_COLLECTION - Static Reference --- */
    public static final Collection<IRoom> IROOM_COLLECTION;
    static{
        IROOM_COLLECTION = new ArrayList<>();
    }

}
