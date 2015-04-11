package me.rondevu.rondevu;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This is the class that contains Event information
 * Created by Andrew on 4/11/2015.
 */
public class Event {

    private String eventName, host, info;
    ArrayList<String> tags;
    private int personLimit, guestsCheckedIn, currentVotes;

    public Event(String eventName, String host, String location, String info, ArrayList<String> tags) {
        this.eventName = eventName;
        this.host = host;
        this.info = info;
        personLimit = 0;
        currentVotes = 0;
        guestsCheckedIn = 0;
        tags = new ArrayList<String>();

    }

    /**
     * Returns the name of this event
     *
     * @return The event name
     */
    public String getEventName() {

        return eventName;
    }

    /**
     * Returns the array of tags for this event
     * @return An array of tags listed for this event.
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Returns the host of this event
     *
     * @return The host of the event
     */
    public String getHost() {

        if (host.equals("") || host == null) {
            return "a mysterious person";
        }

        return host;
    }


    public String getLocation() {
        return "";
    }

    /**
     * Converts this event to a string
     * @return Returns the event as a readable string
     */
    public String toString() {
        return eventName + ", hosted by " + getHost() + " at " + getLocation();
    }

    /**
     * Returns the info of this event
     *
     * @return The string representation of this event
     */
    public String getInfo() {

        if (info.equals("") || info == null) {
            return "No information available for this event.";
        }

        return info;
    }

    /**
     * Gets the current party limit
     *
     * @return an integer representing the current party limit
     */
    public int getLimit() {
        return personLimit;
    }

    /**
     * Return the current amount of votes for this party
     *
     * @return The current amount of votes for the partyc
     */
    public int getCurrentVotes() {
        return currentVotes;
    }

    /**
     * Returns the amount of guests that have checked in
     *
     * @return The amount of guests that have checked into the event.
     */
    public int getGuestsCheckedIn() {
        return guestsCheckedIn;
    }

    /**
     * Returns if this event is at capacity
     *
     * @return A boolean if this event is at capacity.
     */
    public boolean atCapacity() {
        return guestsCheckedIn >= personLimit;
    }

    /**
     * Changes the current amount of votes
     *
     * @param voteType True for an upvote, false for downvote
     */
    public void changeVote(boolean voteType) {
        if (voteType) {
            currentVotes++;
        } else {
            currentVotes--;
        }
    }


}
