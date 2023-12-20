package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class RPM_TYPE extends Fragment {
    public static Button battery_btn, vibration_btn, enter_btn;
    String value = "0";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_rpm_type, container, false);

        battery_btn = view.findViewById(R.id.battery_btn);
        vibration_btn = view.findViewById(R.id.vibration_btn);
        enter_btn = view.findViewById(R.id.enter_rpm_btn);

        battery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "0";
            }
        });
        vibration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "1";
            }
        });
        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value.equals("0"))
                    BaseActivity.sendBleMsg("*$3,20,txt#");
                if (value.equals("1"))
                    BaseActivity.sendBleMsg("*$0,4,R,1,04#");

            }
        });

        return view;
    }
}