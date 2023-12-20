package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class SmokeMeterR extends Fragment {
    public static TextView co_value,hsu_value,k_value,ot_value,rpm_value,lambda_value,pef_value,smokemeterr_center_name;
    public static TextView smokemeterr_time_value, smokemeterr_date_value;
    public static Button smokemeterr_services_btn,smokemeterr_measure_btn, smokemeterr_zeroing_btn, smokemeterr_print_btn,fuel_type_value,rpm_type_value,engine_type_value;
//    public static ImageView engine_arrow, fuel_arrow, rpm_arrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_smoke_meter_r, container, false);

        hsu_value = view.findViewById(R.id.smokemeterr_hsu_value);
        ot_value = view.findViewById(R.id.smokemeterr_ot_value);
        k_value = view.findViewById(R.id.smokemeterr_k_value);
        rpm_value = view.findViewById(R.id.smokemeterr_rpm_value);
        smokemeterr_center_name = view.findViewById(R.id.smokemeterr_center_name);
        smokemeterr_time_value = view.findViewById(R.id.smokemeterr_time_value);
        smokemeterr_date_value = view.findViewById(R.id.smokemeterr_date_value);

        smokemeterr_services_btn = view.findViewById(R.id.smokemeterr_services_btn);
        smokemeterr_measure_btn = view.findViewById(R.id.smokemeterr_measure_btn);
        smokemeterr_zeroing_btn = view.findViewById(R.id.smokemeterr_zeroing_btn);
        smokemeterr_print_btn = view.findViewById(R.id.smokemeterr_print_btn);



        smokemeterr_services_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,3#");
            }
        });
        smokemeterr_measure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(), "Measure Button Cliked", Toast.LENGTH_SHORT).show();
                BaseActivity.sendBleMsg("*$3,20,Sm_RPM_Sel_1#");


            }
        });

        smokemeterr_zeroing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Gas Analyzer",Toast.LENGTH_SHORT).show();
                BaseActivity.sendBleMsg("*$1,2#");
            }
        });
        smokemeterr_print_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,10#");
            }
        });

        return view;
    }
}