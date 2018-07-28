package com.example.asus.example.mvvm.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.example.R;
import com.example.asus.example.databinding.FragmentUserProfileBinding;
import com.example.asus.example.mvvm.Model.Entities.Post;
import com.example.asus.example.mvvm.Model.Entities.User;
import com.example.asus.example.mvvm.View.Adapter.OnItemClickListenerPost;
import com.example.asus.example.mvvm.View.Adapter.PostAdapter;
import com.example.asus.example.mvvm.ViewModel.ItemUserViewModel;

import java.util.List;

/**
 * User Profile Activity to show all Posts for this User
 */
public class UserProfileFragment extends Fragment implements View.OnClickListener {

    protected User user;
    private FragmentUserProfileBinding fragmentUserProfileBinding;
    private ItemUserViewModel itemUserViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        fragmentUserProfileBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_user_profile, parent, false);


        //set viewmodel
        itemUserViewModel = ViewModelProviders.of(this).get(ItemUserViewModel.class);
        itemUserViewModel.init(user, this.getContext().getApplicationContext());

        //set adapter
        final PostAdapter postAdapter = new PostAdapter();

        OnItemClickListenerPost listener = new OnItemClickListenerPost() {
            @Override
            public void onItemClick(Post post) {

                Navigation_Drawer_Activity navigation_drawer_activity = (Navigation_Drawer_Activity) getActivity();
                navigation_drawer_activity.launchShowPostFragment(post);

            }
        };
        postAdapter.setListener(listener);




        final Observer<List<Post>> postsObserver = new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                if (posts != null) {
                    postAdapter.setPostList(itemUserViewModel.getUserProfile().getValue());
                    fragmentUserProfileBinding.userProfilePostRV.setAdapter(postAdapter);
                }
            }
        };


        itemUserViewModel.getCurrentUser().observe(this, new Observer<User>() {


            @Override
            public void onChanged(@Nullable User user1) {
                if (user1 != null) {
                    fragmentUserProfileBinding.setItemUserViewModel(itemUserViewModel);
                    itemUserViewModel.getUserProfile().observe(UserProfileFragment.this, postsObserver);
                    Log.d("" + itemUserViewModel.getCurrentUser().getValue().getId(), "" + user.getId());
                    if (itemUserViewModel.getCurrentUser().getValue().getId() == user.getId()) {
                        fragmentUserProfileBinding.userProfileSubscribeButton.setVisibility(View.GONE);
                    }
                }

            }
        });


        fragmentUserProfileBinding.userProfilePostRV.setLayoutManager(new LinearLayoutManager(this.getContext()));
        Button b = (Button) fragmentUserProfileBinding.userProfileSubscribeButton;
        b.setOnClickListener(this);
        TextView im1 = (TextView) fragmentUserProfileBinding.userSubscribersTV;
        im1.setOnClickListener(this);
        TextView im2 = (TextView) fragmentUserProfileBinding.userSubscriptionsTV;
        im2.setOnClickListener(this);
        return fragmentUserProfileBinding.getRoot();

    }


    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void onClick(View view) {
        Navigation_Drawer_Activity navigation_drawer_activity = (Navigation_Drawer_Activity) getActivity();
        switch (view.getId()) {
            case R.id.userSubscriptionsTV:
                Log.d("zeazeazeazeazea", "errerererereAAAAAAAAAAA");
                navigation_drawer_activity.launchSubscriptionsFragment(user.getSubscriptions());
                break;
            case R.id.userSubscribersTV:
                Log.d("zeazeazeazeazea", "errerererereBBBBBBBBBB");
                navigation_drawer_activity.launchSubscriptionsFragment(user.getSubscribers());
                break;
            case R.id.userProfileSubscribeButton:
                Log.d("zeazeazeazeazea", "errerererereCCCCCCCCC");
                if (fragmentUserProfileBinding.userProfileSubscribeButton.getText().equals("FOLGEN")) {
                    fragmentUserProfileBinding.userProfileSubscribeButton.setText("NICHT MEHR FOLGEN");
                    itemUserViewModel.subscribeUser();
                } else {
                    fragmentUserProfileBinding.userProfileSubscribeButton.setText("FOLGEN");
                    itemUserViewModel.unsubscribeUser();
                }

        }
    }
}
