package com.example.cp100.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;


public class Band_Selection extends Fragment {

    public static Button enter_bandselection, btn_ch0,btn_ch1,btn_ch2,btn_ch3,btn_ch4,btn_ch5,btn_ch6,btn_ch7;
    public String value = "";
    public static ImageView back_band_selection;
    Context context;
    Animation blink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_band__selection, container, false);
        context = view.getContext();
        blink = AnimationUtils.loadAnimation(context,R.anim.blink);

        btn_ch0 = view.findViewById(R.id.btn_ch0);
        btn_ch1 = view.findViewById(R.id.btn_ch1);
        btn_ch2 = view.findViewById(R.id.btn_ch2);
        btn_ch3 = view.findViewById(R.id.btn_ch3);
        btn_ch4 = view.findViewById(R.id.btn_ch4);
        btn_ch5 = view.findViewById(R.id.btn_ch5);
        btn_ch6 = view.findViewById(R.id.btn_ch6);
        btn_ch7 = view.findViewById(R.id.btn_ch7);
        back_band_selection = view.findViewById(R.id.back_bandselection);

        enter_bandselection = view.findViewById(R.id.enter_band_selection);

        btn_ch0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_ch0.startAnimation(blink);
                btn_ch0.setBackgroundResource(R.color.pink);
                btn_ch1.setBackgroundResource(R.color.white);
                btn_ch2.setBackgroundResource(R.color.white);
                btn_ch3.setBackgroundResource(R.color.white);
                btn_ch4.setBackgroundResource(R.color.white);
                btn_ch5.setBackgroundResource(R.color.white);
                btn_ch6.setBackgroundResource(R.color.white);
                btn_ch7.setBackgroundResource(R.color.white);

                value = "0";
            }
        });

        btn_ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_ch1.startAnimation(blink);
                btn_ch1.setBackgroundResource(R.color.pink);
                btn_ch0.setBackgroundResource(R.color.white);
                btn_ch2.setBackgroundResource(R.color.white);
                btn_ch3.setBackgroundResource(R.color.white);
                btn_ch4.setBackgroundResource(R.color.white);
                btn_ch5.setBackgroundResource(R.color.white);
                btn_ch6.setBackgroundResource(R.color.white);
                btn_ch7.setBackgroundResource(R.color.white);
                value = "1";
            }
        });

        btn_ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_ch2.startAnimation(blink);
                btn_ch2.setBackgroundResource(R.color.pink);
                btn_ch0.setBackgroundResource(R.color.white);
                btn_ch1.setBackgroundResource(R.color.white);
                btn_ch3.setBackgroundResource(R.color.white);
                btn_ch4.setBackgroundResource(R.color.white);
                btn_ch5.setBackgroundResource(R.color.white);
                btn_ch6.setBackgroundResource(R.color.white);
                btn_ch7.setBackgroundResource(R.color.white);
                value = "2";
            }
        });

        btn_ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_ch3.startAnimation(blink);
                btn_ch3.setBackgroundResource(R.color.pink);
                btn_ch1.setBackgroundResource(R.color.white);
                btn_ch2.setBackgroundResource(R.color.white);
                btn_ch0.setBackgroundResource(R.color.white);
                btn_ch4.setBackgroundResource(R.color.white);
                btn_ch5.setBackgroundResource(R.color.white);
                btn_ch6.setBackgroundResource(R.color.white);
                btn_ch7.setBackgroundResource(R.color.white);
                value = "3";
            }
        });

        btn_ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_ch4.startAnimation(blink);
                btn_ch4.setBackgroundResource(R.color.pink);
                btn_ch1.setBackgroundResource(R.color.white);
                btn_ch2.setBackgroundResource(R.color.white);
                btn_ch3.setBackgroundResource(R.color.white);
                btn_ch0.setBackgroundResource(R.color.white);
                btn_ch5.setBackgroundResource(R.color.white);
                btn_ch6.setBackgroundResource(R.color.white);
                btn_ch7.setBackgroundResource(R.color.white);
                value = "4";
            }
        });

        btn_ch5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_ch5.startAnimation(blink);
                btn_ch5.setBackgroundResource(R.color.pink);
                btn_ch1.setBackgroundResource(R.color.white);
                btn_ch2.setBackgroundResource(R.color.white);
                btn_ch3.setBackgroundResource(R.color.white);
                btn_ch4.setBackgroundResource(R.color.white);
                btn_ch0.setBackgroundResource(R.color.white);
                btn_ch6.setBackgroundResource(R.color.white);
                btn_ch7.setBackgroundResource(R.color.white);
                value = "5";
            }
        });

        btn_ch6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_ch6.startAnimation(blink);
                btn_ch6.setBackgroundResource(R.color.pink);
                btn_ch1.setBackgroundResource(R.color.white);
                btn_ch2.setBackgroundResource(R.color.white);
                btn_ch3.setBackgroundResource(R.color.white);
                btn_ch4.setBackgroundResource(R.color.white);
                btn_ch5.setBackgroundResource(R.color.white);
                btn_ch0.setBackgroundResource(R.color.white);
                btn_ch7.setBackgroundResource(R.color.white);
                value = "6";
            }
        });

        btn_ch7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_ch7.startAnimation(blink);
                btn_ch7.setBackgroundResource(R.color.pink);
                btn_ch1.setBackgroundResource(R.color.white);
                btn_ch2.setBackgroundResource(R.color.white);
                btn_ch3.setBackgroundResource(R.color.white);
                btn_ch4.setBackgroundResource(R.color.white);
                btn_ch5.setBackgroundResource(R.color.white);
                btn_ch6.setBackgroundResource(R.color.white);
                btn_ch0.setBackgroundResource(R.color.white);
                value = "7";
            }
        });


        back_band_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,Sm_Services#");
            }
        });
        enter_bandselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_bandselection.startAnimation(blink);
                BaseActivity.sendBleMsg("*$3,8,"+value+"#");
            }
        });


        return view;
    }
}