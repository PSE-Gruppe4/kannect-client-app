package com.example.asus.example.mvvm.View.Adapter;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.example.R;
import com.example.asus.example.databinding.ItemGroupBinding;
import com.example.asus.example.mvvm.Model.Entities.Group;
import com.example.asus.example.mvvm.ViewModel.ItemGroupViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Adapter class for Recycler View.
 * Handles the items which will be shown in the Recycler View.
 */
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupAdapterViewHolder> {
    private List<Group> groupsList;
    private OnItemClickListenerGroup listener;


    /**
     * sets the on click listener fot the adapter
     *
     * @param listener which will be set
     */
    public void setListener(OnItemClickListenerGroup listener) {

        this.listener = listener;
    }

    /**
     * Constructor.
     * Initializes the private groupsList attribute.
     */
    public GroupAdapter() {
        this.groupsList = Collections.emptyList();
    }


    /**
     * Method inflates the View, meaning it creates the layout for every list item, using DataBindingUtil inflate method.
     * @param parent parent ViewGroup of the inflated view.
     * @param viewType indicates what type of view should be inflated.
     * @return a new instance of the GroupAdapterViewHolder with the created Binding object.
     */
    @Override public GroupAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGroupBinding itemGroupBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_group,
                        parent, false);
        return new GroupAdapterViewHolder(itemGroupBinding, parent.getContext().getApplicationContext());
    }

    /**
     * Method which binds a ViewHolder to a position in the Recycler View, using the bindUser method.
     * And sets the onClickListener for the ViewHolder.
     * @param holder ViewHolder which will be shown.
     * @param position of the item in the list.
     */
    @Override public void onBindViewHolder(GroupAdapterViewHolder holder, int position) {
        holder.bindGroup(groupsList.get(position));
        final Group model = groupsList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(model);
                }
            }
        });
    }

    /**
     * method to get the size of the List of items that will be shown in the ui.
     * @return size of the list
     */
    @Override public int getItemCount() {
        return groupsList.size();
    }

    /**
     * sets the list of groups which will be shown in the ui.
     * @param GroupsList list of groups
     */
    public void setGroupList(List<Group> GroupsList) {
        this.groupsList = GroupsList;
    }

    /**
     * Nested Class.
     * Builds for every item in the Recycler View its View Model.
     */
    public static class GroupAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemGroupBinding mItemGroupBinding;
        private Context context;

        /**
         * Constructor.
         * Creates an GroupAdapterViewHolder object.
         * @param mItemGroupBinding the Binding object of the new GroupAdapterViewHolder.
         * @param context context of the application
         */
        public GroupAdapterViewHolder(ItemGroupBinding mItemGroupBinding, Context context) {
            super(mItemGroupBinding.itemGroup);
            this.mItemGroupBinding = mItemGroupBinding;
            this.context = context;
        }

        /**
         * Method which binds an item of the recycler view to its View Model if that wasn´t already done.
         * @param group which will be bound.
         */
        void bindGroup(Group group) {
            MutableLiveData<Group> g = new MutableLiveData<>();
            g.setValue(group);
            if (mItemGroupBinding.getItemGroupViewModel() == null) {
                ItemGroupViewModel itemGroupViewModel = new ItemGroupViewModel();
                itemGroupViewModel.init(group, context);
                mItemGroupBinding.setItemGroupViewModel(itemGroupViewModel);
            } else {
                mItemGroupBinding.getItemGroupViewModel().init(group, context);
            }
        }
    }
}
