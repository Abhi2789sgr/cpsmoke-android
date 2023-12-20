package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class Run_Flush_Cycle extends Fragment {
    public static TextView runflushcycle_text, runflushcycle_rpmvalue;
    public static ImageView back_run_flush_cycle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_run__flush__cycle, container, false);

        runflushcycle_rpmvalue = view.findViewById(R.id.runflushcycle_rpmvalue);
        runflushcycle_text = view.findViewById(R.id.runflushcycle_txt);
        back_run_flush_cycle = view.findViewById(R.id.backbtn_runflushcycle);

        back_run_flush_cycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("#");
            }
        });

        return view;
    }
}