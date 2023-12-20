package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cp100.R;


public class Ideal_RPM extends Fragment {

    TextView idealrpm_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ideal__r_p_m, container, false);
        idealrpm_text = view.findViewById(R.id.idealrpm_text);
        return view;
    }
}