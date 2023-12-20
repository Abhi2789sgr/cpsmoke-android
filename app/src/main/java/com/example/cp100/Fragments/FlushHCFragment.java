package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cp100.R;

public class FlushHCFragment extends Fragment {
    public static TextView hc_residue_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_flush_h_c, container, false);

        hc_residue_text = v.findViewById(R.id.hc_residue_text);

        return v;
    }
}