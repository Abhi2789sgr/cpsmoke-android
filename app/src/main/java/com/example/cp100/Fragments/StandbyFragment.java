package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class StandbyFragment extends Fragment {
    public static Button auto_standby_btn, standby_on_btn, standby_off_btn , standby_enter_btn;
    private int standyByNum = 0;
    public  static ImageView back_standby;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_standby, container, false);

        auto_standby_btn = view.findViewById(R.id.auto_standby_btn);
        standby_on_btn = view.findViewById(R.id.standby_on_btn);
        standby_off_btn = view.findViewById(R.id.standby_off_btn);
        standby_enter_btn = view.findViewById(R.id.standby_enter_btn);

        back_standby = view.findViewById(R.id.back_standby);

        back_standby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,Gas_Analyser#");
            }
        });
        auto_standby_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unselectButtons();
//                auto_standby_btn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                standyByNum = 0;
            }
        });

        standby_on_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unselectButtons();
//                standby_on_btn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                standyByNum = 2;
            }
        });

        standby_off_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unselectButtons();
//                standby_off_btn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                standyByNum = 1;
            }
        });

        standby_enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$0,7,"+standyByNum+"#");
            }
        });



        return view;
    }

    private void unselectButtons() {
//        auto_standby_btn.setBackgroundColor(getResources().getColor(R.color.appSkyBlue));
//        standby_on_btn.setBackgroundColor(getResources().getColor(R.color.appSkyBlue));
//        standby_off_btn.setBackgroundColor(getResources().getColor(R.color.appSkyBlue));
    }
}