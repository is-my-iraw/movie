package com.example.movie.fragment;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.movie.activity.ProfileActivity;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                Log.d("TAG", "createFragment: " + position);
                return new CategoryFragment();
            case 2:
                return new SearchFragment();
            case 3:
                return new ProfileActivity();
            default:
                return new HomeFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
