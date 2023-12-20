package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class GStandbyFragment extends Fragment {

    public static ImageView screen_standby;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_g_standby, container, false);

        screen_standby = view.findViewById(R.id.screen_standby);
        screen_standby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$0,11#");
            }
        });

        return view;
    }
}