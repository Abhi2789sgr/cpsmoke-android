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


public class RPM_NotRange extends Fragment {

   public static TextView rpmnotrangetext3 , rpmnotrangetext5, rpmnotrange_name;
    public static Button back_rpmnotrange_btn , repeat_rpmnotrange_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_r_p_m__not_range, container, false);

        rpmnotrangetext3 = view.findViewById(R.id.rpmnotrangetext3);
        rpmnotrangetext5 = view.findViewById(R.id.rpmnotrangetext5);
        back_rpmnotrange_btn = view.findViewById(R.id.back_rpmnotrange_btn);
        repeat_rpmnotrange_btn = view.findViewById(R.id.repeat_rpmnotrange_btn);
        rpmnotrange_name = view.findViewById(R.id.rpmnotrange_name);

        back_rpmnotrange_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("<");
            }
        });

        repeat_rpmnotrange_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("&");
            }
        });

        return view;
    }
}