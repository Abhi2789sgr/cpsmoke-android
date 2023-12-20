package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.cp100.BaseActivity;
import com.example.cp100.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service_Mode extends Fragment {

    public static TextView servicemode_hsuvalue , servicemode_kvalue , servicemode_smkvalue , servicemode_ngcvalue , servicemode_rpmvalue , servicemode_otvalue , servicemode_ctvalue;
    public static Button servicemode_back_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service__mode, container, false);
        servicemode_hsuvalue = view.findViewById(R.id.servicemode_hsuvalue_text);
        servicemode_kvalue = view.findViewById(R.id.servicemode_kvalue_text);
        servicemode_smkvalue = view.findViewById(R.id.servicemode_smkvalue_text);
        servicemode_ngcvalue = view.findViewById(R.id.servicemode_ngcvalue_text);
        servicemode_rpmvalue = view.findViewById(R.id.servicemode_rpmvalue_text);
        servicemode_otvalue = view.findViewById(R.id.servicemode_otvalue_text);
        servicemode_ctvalue = view.findViewById(R.id.servicemode_ctvalue_text);
        servicemode_back_btn = view.findViewById(R.id.back_servicemode_btn);

        servicemode_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,Sm_Services#");

            }
        });

        return view;
    }

}