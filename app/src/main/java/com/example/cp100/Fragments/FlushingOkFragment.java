package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class FlushingOkFragment extends Fragment {

    public static TextView rpmvaluefctext,otvaluefctext, flushingok_name, textflushingcycleround, textflushingcycle3;
    public static ImageView back_flushing_cycle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_flushing_ok, container, false);

        rpmvaluefctext = view.findViewById(R.id.rpmvalue_flushing_cycle);
        otvaluefctext = view.findViewById(R.id.otvalue_flushing_cycle);
        back_flushing_cycle = view.findViewById(R.id.back_flushing_cycle);
        flushingok_name = view.findViewById(R.id.flushingok_name);
        textflushingcycleround = view.findViewById(R.id.textflushingcycleround);
        textflushingcycle3 = view.findViewById(R.id.textflushingcycle3);

        back_flushing_cycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("#");
            }
        });


        return view;
    }
}