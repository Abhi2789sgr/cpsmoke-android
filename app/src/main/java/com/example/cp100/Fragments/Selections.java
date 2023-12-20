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

public class Selections extends Fragment {

    TextView cylindcount_selections , rpmtype_selections , vehicletype_selections;
    Button modify_selections_btn , enter_selections_btn;
    ImageView back_selections;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selections, container, false);

        vehicletype_selections = view.findViewById(R.id.vehicletype_selections);
        rpmtype_selections = view.findViewById(R.id.rpmtype_selections);
        cylindcount_selections = view.findViewById(R.id.cylindercount_selections);

        modify_selections_btn = view.findViewById(R.id.modify_selections_btn);
        enter_selections_btn = view.findViewById(R.id.enter_selections_btn);

        back_selections = view.findViewById(R.id.back_selections);

        back_selections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,Smoke_meterR#");
            }
        });

        modify_selections_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("Modify_Selections_btn");
            }
        });

        enter_selections_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$1,4,0,#");
            }
        });
        return view;
    }
}