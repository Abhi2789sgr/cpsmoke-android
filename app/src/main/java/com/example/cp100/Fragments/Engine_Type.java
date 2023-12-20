package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class Engine_Type extends Fragment {
    public static Button ts_tw_btn , fs_fw_btn , ts_fw_btn , dis_btn, enter_btn;
    public static String engine_value;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_fuel_type, container, false);

        ts_tw_btn = v.findViewById(R.id.twos_twow_btn);
        fs_fw_btn = v.findViewById(R.id.fours_fourw_btn);
        ts_fw_btn = v.findViewById(R.id.twos_fourw_btn);
        dis_btn = v.findViewById(R.id.dis_btn);
        enter_btn = v.findViewById(R.id.enter_engine_type_btn);

        ts_tw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engine_value = "0";
            }
        });
        fs_fw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engine_value = "1";
            }
        });
        ts_fw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engine_value = "2";
            }
        });
        dis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engine_value = "3";
            }
        });
        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$0,4,E,"+engine_value+"#");
            }
        });


        return v;
    }
}