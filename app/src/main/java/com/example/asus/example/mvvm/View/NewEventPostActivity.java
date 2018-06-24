package com.example.asus.example.mvvm.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.asus.example.mvvm.Model.Entities.Event;
import com.example.asus.example.mvvm.ViewModel.ItemEventViewModel;

public class NewEventPostActivity extends AppCompatActivity {

    private ItemEventViewModel viewModel;
    //binding


    /**
     * creates a newPostActivity
     * @param viewModel addes the needed changes in the model and in the view
     */
    public NewEventPostActivity(ItemEventViewModel viewModel) { //add the binding to the constructor manually :binding not possible without xml
        this.viewModel = viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Is the method allowing to reach the activity
     */
    public void launchWithDetails(Context context, Event event){ }

    public ItemEventViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ItemEventViewModel viewModel) {
        this.viewModel = viewModel;
    }
}