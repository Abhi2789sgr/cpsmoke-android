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

public class GasAnalyzerFragment extends Fragment {
    public static TextView co_value,hc_value,co2_value,o2_value,rpm_value,lambda_value,pef_value,center_name;
    public static TextView time_value, date_value;
    public static Button services_btn,standby_btn, zeroing_btn, print_btn,fuel_type_value,rpm_type_value,engine_type_value;
//    public static ImageView engine_arrow, fuel_arrow, rpm_arrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gas_analyzer, container, false);

        co_value = view.findViewById(R.id.co_value);
        hc_value = view.findViewById(R.id.hc_value);
        co2_value = view.findViewById(R.id.co2_value);
        o2_value = view.findViewById(R.id.o2_value);
        rpm_value = view.findViewById(R.id.rpm_value);
        lambda_value = view.findViewById(R.id.lambda_value);
        engine_type_value = view.findViewById(R.id.engine_type_value);
        fuel_type_value = view.findViewById(R.id.fuel_type_value);
        rpm_type_value = view.findViewById(R.id.rpm_type_value);
        pef_value = view.findViewById(R.id.pef_value);
        center_name = view.findViewById(R.id.center_name);
        time_value = view.findViewById(R.id.time_value);
        date_value = view.findViewById(R.id.date_value);

        services_btn = view.findViewById(R.id.services_btn);
        standby_btn = view.findViewById(R.id.standby_btn);
        zeroing_btn = view.findViewById(R.id.zeroing_btn);
        print_btn = view.findViewById(R.id.print_btn);
//        engine_arrow = view.findViewById(R.id.engine_arrow);
//        fuel_arrow = view.findViewById(R.id.fuel_arrow);
//        rpm_arrow= view.findViewById(R.id.rpm_arrow);


        services_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Gas Analyzer",Toast.LENGTH_SHORT).show();
                BaseActivity.sendBleMsg("*$0,3#");
            }
        });
        standby_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,113#");
            }
        });

        zeroing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Gas Analyzer",Toast.LENGTH_SHORT).show();
                BaseActivity.sendBleMsg("*$0,9#");
            }
        });
        print_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$0,10#");
            }
        });

        engine_type_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,118#");
            }
        });
        fuel_type_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,117#");
            }
        });
        rpm_type_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,119#");
            }
        });

        return view;
    }
}