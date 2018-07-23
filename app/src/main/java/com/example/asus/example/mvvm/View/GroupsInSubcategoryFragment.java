package com.example.asus.example.mvvm.View;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.example.R;
import com.example.asus.example.databinding.FragmentGroupsInSubcategoryBinding;
import com.example.asus.example.mvvm.Model.Entities.Subcategory;
import com.example.asus.example.mvvm.View.Adapter.GroupAdapter;
import com.example.asus.example.mvvm.ViewModel.GroupViewModel;

/**
 * Activity class for the view regarding showing subcategories and the groups which belong to the parent category.
 */
public class GroupsInSubcategoryFragment extends Fragment {

    private GroupViewModel groupViewModel;
    private FragmentGroupsInSubcategoryBinding fragmentGroupsInSubcategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Subcategory subcategory = (Subcategory) getArguments().getSerializable("subcategory");

        fragmentGroupsInSubcategory = FragmentGroupsInSubcategoryBinding.inflate(inflater, parent, false);
        //set viewmodel
        groupViewModel = ViewModelProviders.of(this).get(GroupViewModel.class);
        /*keine Ahnung welche Query*/
        groupViewModel.setGroupsFilteredBySubcategory(subcategory);

        //set adapter
        GroupAdapter groupAdapter = new GroupAdapter();
        groupAdapter.setGroupList(groupViewModel.getGroups().getValue());
        fragmentGroupsInSubcategory.groupsInSubcategoryGroupRV.setAdapter(groupAdapter);
        fragmentGroupsInSubcategory.groupsInSubcategoryGroupRV.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //TODO: observe livedata somehow
        // Defines the xml file for the fragment
        return fragmentGroupsInSubcategory.getRoot();
    }

}
