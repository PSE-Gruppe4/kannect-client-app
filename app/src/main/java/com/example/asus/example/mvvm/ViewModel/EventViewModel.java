package com.example.asus.example.mvvm.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.asus.example.mvvm.Model.Entities.Category;
import com.example.asus.example.mvvm.Model.Entities.Event;
import com.example.asus.example.mvvm.Model.Entities.Subcategory;
import com.example.asus.example.mvvm.Model.Repository.EventRepository;

import java.util.List;

/**
 * ViewModel class for events, that is responsible for preparing and managing the data for Views,
 * which need a list of events, by handling the communication of the View with the
 * EventRepository class, which has the group business logic of the application.
 * Objects received from repositories will be stored as MutableLiveData Objects.
 */
public class EventViewModel extends ViewModel {

    private MutableLiveData<List<Event>> events;
    private Context context;
    private EventRepository eventRepository;

    /**
     * Creates an instance with the given application context.
     *
     * @param context of the application.
     */
    public EventViewModel(Context context) {
        this.context = context;
    }

    /**
     * Sets the events to search results of the given query.
     * @param query to search.
     */
    public void setEventsToSearchResults(String query) {
    }

    /**
     * Sets the events with all groups of the given category.
     * @param category to filter
     */
    public void setEventsFilteredByCategory(MutableLiveData<Category> category) {
    }

    /**
     * Sets the events with all groups of the given subcategory.
     * @param subcategory to filter.
     */
    public void setEventsFilteredBySubcategory(MutableLiveData<Subcategory> subcategory) {
    }

    /**
     * Sets the events to current user's list of participating events.
     */
    public void setEventsToParticipatingEvents() {
    }


    /**
     * Gets the list of groups.
     * @return observable list of groups.
     */
    public MutableLiveData<List<Event>> getEvents() {
        return events;
    }


}
