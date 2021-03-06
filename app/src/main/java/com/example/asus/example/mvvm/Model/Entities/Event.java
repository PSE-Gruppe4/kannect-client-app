package com.example.asus.example.mvvm.Model.Entities;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents an event, providing access to the event's unique id, name, description, category, subcategory,
 * creator, profile picture url and participants.
 */
public class Event implements Serializable {

    private int id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;
    private String description;
    private User creator;
    private Category category;
    private Subcategory subcategory;
    private String imageUrl;
    private List<User> participants;

    /**
     * Overwrites Default Constructor and
     * initializes all lists
     */
    public Event() {
        this.participants = new LinkedList<>();
    }

    /**
     * method to get the id of the Event.
     * @return id of the Event.
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of the Event.
     * @param id sets the id of the Event.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * method to get the Name of the Event.
     * @return name of the Event.
     */
    public String getName() {
        return name;
    }

    /**
     * sets the Name of the Event.
     * @param name name of the Event.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method to get the Date, on which the Event will take place.
     * @return the Date on which the Event will take place.
     */
    public Date getDate() {
        return date;
    }

    /**
     * sets the Date, on which the Event will take place.
     * @param date date, on which the Event will take place.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * method to get the description of the Event.
     * @return description of the Event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the Event.
     * @param description description of the Event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * method to get the user who created the Event.
     * @return the Creator of the Event.
     */
    public User getCreator() {
        return creator;
    }

    /**
     * sets the User who created the Event.
     * @param creator creator of the Event.
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * method to get the Category this Event belongs to.
     * @return the Category this Events belongs to.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * sets the Category this Event belongs to.
     * @param category Category this Event belongs to.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * method to get the Subcategory this Event belongs to.
     * @return the Subcategory this Event belongs to.
     */
    public Subcategory getSubcategory() {
        return subcategory;
    }

    /**
     * sets the Subcategory of this Event.
     * @param subcategory Subcategory of this Event.
     */
    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    /**
     * method to get the imageUrl of the profile picture of the Event.
     * @return the imageUrl of the profile picture.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * sets the imageUrl of this Event.
     * @param imageUrl imageUrl of this Event.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * method to get the List of User who want to participate in this Event.
     * @return List of Participants
     */
    public List<User> getParticipants() {
        return participants;
    }

    /**
     * set the List of User who want to participate in this Event.
     * @param participants List of Participants.
     */
    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    /**
     * compares two Event by their ids
     *
     * @param object event to compare with
     * @return true if object and this Event have the same id, else false
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!Event.class.isAssignableFrom(object.getClass())) {
            return false;
        }
        final Event other = (Event) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}