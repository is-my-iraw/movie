package com.example.movie.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.movie.R;
import com.example.movie.network.Constant;

public class ProfileFragment extends Fragment {

    private View mView;
    private TextView tvProfileName;

    private Button sigout;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_profile, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sigout = mView.findViewById(R.id.btnLogout);
        preferences = getActivity().getSharedPreferences(Constant.ROOT_KET, Context.MODE_PRIVATE);
        editor = preferences.edit();
        sigout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void init(){
    }


}