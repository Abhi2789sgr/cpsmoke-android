package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class FlushingFragment extends Fragment {

    public static Button back_flushing_btn, check_flushing_btn, enter_btn;
    public static TextView flushing_name;
    String value = "0";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_flushing, container, false);

        back_flushing_btn = view.findViewById(R.id.back_flushing_btn);
        check_flushing_btn = view.findViewById(R.id.check_flushing_btn);
        flushing_name = view.findViewById(R.id.flushing_name);
//        enter_btn = view.findViewById(R.id.enter_rpm_btn);

        back_flushing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,9#*$3,20,Smoke_meter_R#");
            }
        });
        check_flushing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,5#");
            }
        });
//        enter_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (value.equals("0"))
//                    BaseActivity.sendBleMsg("*$3,20,G_Cylinder_Cnt#");
//                if (value.equals("1"))
//                    BaseActivity.sendBleMsg("*$0,4,R,1,04#");
//
//            }
//        });

        return view;
    }
}