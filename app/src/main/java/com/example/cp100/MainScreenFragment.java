package com.example.cp100;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cp100.Fragments.WarmUpFragment;

public class MainScreenFragment extends Fragment {
    Button gas_analyzer_btn, smoke_meter_btn, standby_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        // get view id's
//        gas_analyzer_btn = view.findViewById(R.id.gas_analyzer_btn);
//        smoke_meter_btn = view.findViewById(R.id.smoke_meter_btn);
//
//        gas_analyzer_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"Gas Analyzer",Toast.LENGTH_SHORT).show();
//                BaseActivity.sendBleMsg("*$0,1#");
//            }
//        });
//        smoke_meter_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BaseActivity.sendBleMsg("*$1,1#");
//            }
//        });
        
        return view;
    }
}