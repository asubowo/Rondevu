package me.rondevu.rondevu;

/**
 * This class manages all events
 * Created by Andrew on 4/11/2015.
 */
public class EventManager {




    /**
     * Constructor for the manager class
     */
    public EventManager() {

    }

    /**
     * Changes the vote of an event
     * @param event
     * @param vote
     */
    public void changeEventVote(Event event, boolean vote) {
        event.changeVote(vote);
    }

    /**
     * Creates an event
     * TODO: SEND INFO TO SERVER
     */
    public void createEvent() {

    }

}
