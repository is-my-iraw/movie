package com.example.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.movie.R;
import com.example.movie.network.Constant;

public class ProfileActivity extends Fragment {

    Button btnLogout, get_Premium;

    LinearLayout save_favourite;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_profile, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner();
        setGet_Premium();
        linearLayout();
    }

    private void linearLayout(){
        save_favourite = mView.findViewById(R.id.save_favourite);
        save_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FavouriteActivity.class));

            }
        });
    }




    private void setGet_Premium(){
        get_Premium = mView.findViewById(R.id.get_Premium);
        get_Premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
            }
        });
    }

    private void initBanner() {
        btnLogout = mView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout(){
        SharedPreferences myPrefs = getContext().getSharedPreferences(Constant.ROOT_KET, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.clear();
        editor.apply();
        Log.d("TAG", "Now log out and start the activity login");
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(getContext(), "You have been logged out", Toast.LENGTH_SHORT).show();
    }
}