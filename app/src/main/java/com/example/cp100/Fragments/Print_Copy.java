package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;


public class Print_Copy extends Fragment {

    Button printcopy_print_btn , printcopy_exit_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_print__copy, container, false);

        printcopy_print_btn = view.findViewById(R.id.printcopy_print_btn);
        printcopy_exit_btn = view.findViewById(R.id.printcopy_exit_btn);

        printcopy_print_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("");
            }
        });

        printcopy_exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,1,2#");
            }
        });
        return view;
    }
}