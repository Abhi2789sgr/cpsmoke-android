package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cp100.R;


public class Auto_Cal extends Fragment {
    public static ProgressBar progressBarautoCalibration;
    public static TextView auto_cal_text1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_auto__cal, container, false);
        progressBarautoCalibration = view.findViewById(R.id.progressBarautoCalibration);
        auto_cal_text1 = view.findViewById(R.id.auto_calibration_text1);
        progressBarautoCalibration.setProgress(0,true);
        return view;
    }
}