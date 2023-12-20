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


public class RPM_Incorrect extends Fragment {

    public static Button back_rpmincorrect_btn, repeat_rpmincorrect_btn, enter_btn;
    public static TextView rpmincorrect_name;
    String value = "0";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_r_p_m__incorrect, container, false);

        back_rpmincorrect_btn = view.findViewById(R.id.back_rpmincorrect_btn);
        repeat_rpmincorrect_btn = view.findViewById(R.id.repeat_rpmincorrect_btn);
        rpmincorrect_name = view.findViewById(R.id.rpmincorrect_name);
//        enter_btn = view.findViewById(R.id.enter_rpm_btn);

        back_rpmincorrect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("<");
            }
        });
        repeat_rpmincorrect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("@");
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