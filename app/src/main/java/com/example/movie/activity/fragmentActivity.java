package com.example.movie.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.movie.R;
import com.example.movie.fragment.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class fragmentActivity extends AppCompatActivity {

    private ViewPager2 mViewPages2;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mViewPages2 =  findViewById(R.id.view_pages);
        mBottomNavigationView = findViewById(R.id.button_navigation);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mViewPages2.setAdapter(viewPagerAdapter);

        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_tab_1){
                    mViewPages2.setCurrentItem(0);
                }else if(id == R.id.menu_tab_2) {
                    mViewPages2.setCurrentItem(1);
                }else if(id == R.id.menu_tab_3) {
                    mViewPages2.setCurrentItem(2);
                }else if(id == R.id.menu_tab_4){
                    mViewPages2.setCurrentItem(3);
                }
                return true;
            }
        });

        mViewPages2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                    mBottomNavigationView.getMenu().findItem(R.id.menu_tab_1).setChecked(true);
                    break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_tab_2).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_tab_3).setChecked(true);
                        break;
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_tab_4).setChecked(true);
                        break;
                }
            }
        });
    }
}