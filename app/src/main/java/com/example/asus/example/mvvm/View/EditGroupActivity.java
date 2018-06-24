package com.example.asus.example.mvvm.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.asus.example.mvvm.Model.Entities.Group;
import com.example.asus.example.mvvm.ViewModel.GroupViewModel;

/**
 * The activity displayed allowing the user to edit the informations concerning a group.
 *
 */
public class EditGroupActivity extends AppCompatActivity {

    GroupViewModel viewModel;
    //bin (can only be done with xml)


    /**
     * creates a EditGroupActivity
     * @param viewModel addes the needed changes in the model and in the view
     */
    public EditGroupActivity(GroupViewModel viewModel) { //add the binding to the constructor manually :binding not possible without xml
        this.viewModel = viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Is the method allowing to reach the activity
     */
    public void launchWithDetails(Context context, Group group){ }

    public GroupViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(GroupViewModel viewModel) {
        this.viewModel = viewModel;
    }
}