package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class ZeroCalibrationFragment extends Fragment {
    public static Button co_calibration_btn, c02_calibration_btn, hc_calibration_btn, mix_calibration_btn, factory_reset_btn;
    public static ImageView back_zero_calibration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_zero_calibration, container, false);

        back_zero_calibration = view.findViewById(R.id.back_zero_calibration);
        co_calibration_btn = view.findViewById(R.id.co_calibration_btn);
        c02_calibration_btn = view.findViewById(R.id.c02_calibration_btn);
        hc_calibration_btn = view.findViewById(R.id.hc_calibration_btn);
        mix_calibration_btn = view.findViewById(R.id.mix_calibration_btn);
        factory_reset_btn = view.findViewById(R.id.factory_reset_btn);

        back_zero_calibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,G_Services#");
            }
        });

        return view;
    }
}