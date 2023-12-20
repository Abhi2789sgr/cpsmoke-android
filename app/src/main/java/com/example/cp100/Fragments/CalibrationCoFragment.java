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

public class CalibrationCoFragment extends Fragment {

    public static TextView co_reset_value1;
    public static EditText co_set_value1;
    public static Button calibrate_co_btn, back_co_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calibration_co, container, false);

        co_reset_value1 = view.findViewById(R.id.hc_reset_value1);
        co_set_value1 = view.findViewById(R.id.co_set_value1);
        calibrate_co_btn = view.findViewById(R.id.calibrate_co_btn);
        back_co_btn = view.findViewById(R.id.back_co_btn);
        calibrate_co_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$0,6,80,"+co_set_value1.getText().toString().trim()+"#");
            }
        });

        back_co_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
}