package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;


public class Free_Acc_Start extends Fragment {

    public static Button free_acc_start_enter_btn, enter_btn;
    String value = "0";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_free__acc__start, container, false);

        free_acc_start_enter_btn = view.findViewById(R.id.free_acc_start_enter_btn);
//        check_flushing_btn = view.findViewById(R.id.check_flushing_btn);
//        enter_btn = view.findViewById(R.id.enter_rpm_btn);

        free_acc_start_enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,6#");
            }
        });
//        check_flushing_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                value = "1";
//            }
//        });
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