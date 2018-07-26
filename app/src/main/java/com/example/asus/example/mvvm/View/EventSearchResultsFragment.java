package com.example.asus.example.mvvm.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asus.example.R;
import com.example.asus.example.databinding.FragmentEventSearchResultBinding;
import com.example.asus.example.mvvm.Model.Entities.Event;
import com.example.asus.example.mvvm.Model.Entities.User;
import com.example.asus.example.mvvm.View.Adapter.EventAdapter;
import com.example.asus.example.mvvm.View.Adapter.OnItemClickListenerEvent;
import com.example.asus.example.mvvm.ViewModel.EventViewModel;

import java.util.List;

/**
 * This fragment displays events related to the searched terms.
 */
public class EventSearchResultsFragment extends Fragment {

    private String query;

    private EventViewModel eventViewModel;
    private FragmentEventSearchResultBinding fragmentEventSearchResultBinding;

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        Toast.makeText(getContext(), "EventSearchREsult", Toast.LENGTH_LONG);
        //set viewmodel
        final EventViewModel eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        eventViewModel.init(getContext());

        //set adapter
        final EventAdapter eventAdapter = new EventAdapter();
        OnItemClickListenerEvent listener = new OnItemClickListenerEvent() {
            @Override
            public void onItemClick(Event item) {
                Navigation_Drawer_Activity navigation_drawer_activity = (Navigation_Drawer_Activity) getActivity();
                navigation_drawer_activity.launchEventFeedFragment(item);
            }
        };
        eventAdapter.setListener(listener);

        final FragmentEventSearchResultBinding fragmentEventSearchResultBinding =
                FragmentEventSearchResultBinding.inflate(inflater, parent, false);
        fragmentEventSearchResultBinding.eventSearchResultEventRV.setAdapter(eventAdapter);


        final Observer<List<Event>> eventsObserver = new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                if (events != null) {
                    eventViewModel.setEventsToSearchResults(query);
                    eventAdapter.setEventList(eventViewModel.getEvents().getValue());
                    fragmentEventSearchResultBinding.eventSearchResultEventRV.setAdapter(eventAdapter);

                }
            }
        };

        eventViewModel.getCurrentUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    eventViewModel.getEvents().observe(EventSearchResultsFragment.this, eventsObserver);
                }
            }
        });


        fragmentEventSearchResultBinding.eventSearchResultEventRV.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return inflater.inflate(R.layout.fragment_event_search_result, parent, false);
    }
}
