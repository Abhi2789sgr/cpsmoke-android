package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class Fuel_Type extends Fragment {
    public static Button petrol_btn, lpg_btn, cng_btn, enter_btn;
    public static String fuel_value="0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_fuel_type, container, false);

        petrol_btn = v.findViewById(R.id.fuel_type_petrol_btn);
        lpg_btn = v.findViewById(R.id.fuel_type_lpg_btn);
        cng_btn = v.findViewById(R.id.fuel_type_cng_btn);
        enter_btn = v.findViewById(R.id.enter_fuel_type_btn);

        petrol_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuel_value = "0";
            }
        });
        lpg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuel_value = "1";
            }
        });
        cng_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuel_value = "2";
            }
        });
        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$0,4,F,"+fuel_value+"#");
            }
        });


        return v;
    }
}