package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cp100.R;

public class CalibrationFragment extends Fragment {
    public static ProgressBar progressBarCalibration;
    public static TextView cal_text11;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_calibration, container, false);
        cal_text11 = view.findViewById(R.id.calibration_text11);
        progressBarCalibration = view.findViewById(R.id.calib_progressBarCalibration);
        progressBarCalibration.setProgress(0,true);
        return view;
    }
}