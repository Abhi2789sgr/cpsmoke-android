package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class CalibrationCo2Fragment extends Fragment {

    public static TextView co2_resent_value1;
    public static EditText co2_set_value1;
    public static Button calibrate_co2_btn, back_co2_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calibration_co2, container, false);

        co2_resent_value1 = view.findViewById(R.id.co2_resent_value1);
        co2_set_value1 = view.findViewById(R.id.co2_set_value1);
        calibrate_co2_btn = view.findViewById(R.id.calibrate_co2_btn);
        back_co2_btn = view.findViewById(R.id.back_co2_btn);
        calibrate_co2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$0,6,82,"+co2_set_value1.getText().toString().trim()+"#");
            }
        });
        back_co2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}