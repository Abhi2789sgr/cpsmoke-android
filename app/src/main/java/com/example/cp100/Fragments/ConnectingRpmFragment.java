package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cp100.R;

public class ConnectingRpmFragment extends Fragment {

    public static ProgressBar progressBarRPM;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_connecting_rpm, container, false);

        progressBarRPM = v.findViewById(R.id.progressBarRPM);
        progressBarRPM.setProgress(0,true);
        return v;
    }
}