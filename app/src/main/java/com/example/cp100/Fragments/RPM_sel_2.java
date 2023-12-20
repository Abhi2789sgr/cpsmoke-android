package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class RPM_sel_2 extends Fragment {

    public static Button enter_cylindercount, btn_01,btn_02,btn_03,btn_04,btn_05,btn_06,btn_08,btn_10,btn_12;;
    public String value = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_r_p_m_sel_2, container, false);

        btn_01 = view.findViewById(R.id.btn_01);
        btn_02 = view.findViewById(R.id.btn_02);
        btn_03 = view.findViewById(R.id.btn_03);
        btn_04 = view.findViewById(R.id.btn_04);
        btn_05 = view.findViewById(R.id.btn_05);
        btn_06 = view.findViewById(R.id.btn_06);
        btn_08 = view.findViewById(R.id.btn_08);
        btn_10 = view.findViewById(R.id.btn_10);
        btn_12 = view.findViewById(R.id.btn_12);

        enter_cylindercount = view.findViewById(R.id.enter_cylinder_name);

        btn_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "01";
            }
        });

        btn_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "02";
            }
        });

        btn_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "03";
            }
        });

        btn_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "04";
            }
        });

        btn_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "05";
            }
        });

        btn_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "06";
            }
        });

        btn_08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "08";
            }
        });

        btn_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "10";
            }
        });

        btn_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "12";
            }
        });
        enter_cylindercount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,4,1,00,00,"+value+"#");
            }
        });

        return view;
    }
}