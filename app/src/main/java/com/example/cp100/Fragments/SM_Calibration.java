package com.example.cp100.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;


public class SM_Calibration extends Fragment {
    public static TextView  smcal_hsuvalue_text , smcal_kvalue_text,smcal_name;
    public static EditText smcal_filtervalue_text ;
    public static Button smcal_calibrate_btn , smcal_back_btn,reset_calibrate_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_s_m__calibration, container, false);
        smcal_filtervalue_text = view.findViewById(R.id.smcal_filtervalue_text);
        smcal_hsuvalue_text = view.findViewById(R.id.smcal_hsuvalue_text);
        smcal_kvalue_text = view.findViewById(R.id.smcal_cal_kvalue_text);
        smcal_back_btn = view.findViewById(R.id.smcal_back_btn);
        smcal_calibrate_btn = view.findViewById(R.id.smcal_calibrate_btn);
        reset_calibrate_btn = view.findViewById(R.id.reset_calibrate_btn);
        smcal_name = view.findViewById(R.id.smcal_name);

        smcal_filtervalue_text.getText().toString();

        reset_calibrate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity.sendBleMsg("^");
            }
        });

        smcal_calibrate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,8," + smcal_filtervalue_text.getText().toString() + "#");
            }
        });

        smcal_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseActivity.sendBleMsg("*$3,20,Sm_Services#");

            }
        });
        return view;
    }


}