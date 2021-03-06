package com.example.asus.example.mvvm.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.example.R;
import com.example.asus.example.databinding.ItemCategoryBinding;
import com.example.asus.example.mvvm.Model.Entities.Category;
import com.example.asus.example.mvvm.ViewModel.ItemCategoryViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Adapter class for Recycler View.
 * Handles the items which will be shown in the Recycler View.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder> {


    private List<Category> categoryList;
    private OnItemClickListenerCategory listener;


    /**
     * sets the item click listener for the adapter.
     *
     * @param listener which will be set.
     */
    public void setListener(OnItemClickListenerCategory listener) {

        this.listener = listener;
    }

    /**
     * Constructor.
     * Initializes the private categoryList attribute.
     */
    public CategoryAdapter() {
        this.categoryList = Collections.emptyList();
    }


    /**
     * Method inflates the View, meaning it creates the layout for every list item, using DataBindingUtil inflate method.
     * @param parent parent ViewGroup of the inflated view.
     * @param viewType indicates what type of view should be inflated.
     * @return a new instance of the CategoryAdapterViewHolder with the created Binding object.
     */
    @Override public CategoryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCategoryBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_category,
                        parent, false);
        return new CategoryAdapterViewHolder(itemCategoryBinding, parent.getContext().getApplicationContext());
    }

    /**
     * Method which binds a ViewHolder to a position in the Recycler View, using the bindCategory method.
     * And sets the onclickListener for the ViewHolder.
     * @param holder ViewHolder which will be shown.
     * @param position of the item in the list.
     */
    @Override public void onBindViewHolder(CategoryAdapterViewHolder holder, int position) {
        holder.bindCategory(categoryList.get(position));
        final Category model = categoryList.get(position);
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
        return categoryList.size();
    }

    /**
     * sets the list of groups which will be shown in the ui.
     * @param categoryList list of Categories
     */
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * Nested Class.
     * Builds for every item in the Recycler View its View Model.
     */
    public static class CategoryAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryBinding mItemCategoryBinding;
        private Context context;

        /**
         * Constructor.
         * Creates an UserAdapterViewHolder object.
         * @param itemCategoryBinding the Binding object of the new CategoryAdapterViewHolder.
         * @param context of the Application.
         */
        public CategoryAdapterViewHolder(ItemCategoryBinding itemCategoryBinding, Context context) {
            super(itemCategoryBinding.itemCategory);
            this.mItemCategoryBinding = itemCategoryBinding;
            this.context = context;
        }

        /**
         * Method which binds an item of the recycler view to its View Model if that wasn´t already done.
         * @param category which will be bound.
         */
        void bindCategory(Category category) {
            if (mItemCategoryBinding.getItemCategoryViewModel() == null) {
                ItemCategoryViewModel itemCategoryViewModel = new ItemCategoryViewModel();
                itemCategoryViewModel.init(category, context);
                mItemCategoryBinding.setItemCategoryViewModel(itemCategoryViewModel);
            } else {
                mItemCategoryBinding.getItemCategoryViewModel().init(category, context);
            }
        }
    }
}
