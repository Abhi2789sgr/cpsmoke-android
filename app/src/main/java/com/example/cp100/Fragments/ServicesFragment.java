package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class ServicesFragment extends Fragment {
    public static Button date_time_btn, centername_btn, servicemode_btn, calibration_btn, testapi_btn, done_btn, band_selection_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_services, container, false);

        date_time_btn = v.findViewById(R.id.date_time_btn);
        centername_btn = v.findViewById(R.id.centername_btn);
        servicemode_btn = v.findViewById(R.id.servicemode_btn);
        calibration_btn = v.findViewById(R.id.calibration_btn);
        testapi_btn = v.findViewById(R.id.testapi_btn);
        band_selection_btn = v.findViewById(R.id.bandselection_btn);

        done_btn = v.findViewById(R.id.done_btn);

        date_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,5#");
            }
        });

        centername_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    BaseActivity.sendBleMsg("*$3,1,2#");
                }catch (Exception e)
                {

                }


            }
        });

        servicemode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,7#");
            }
        });

        calibration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,1,1#");
            }
        });

        testapi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,13#");
            }
        });

        band_selection_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,14#");
            }
        });

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,9#");
            }
        });


        return v;
    }
}