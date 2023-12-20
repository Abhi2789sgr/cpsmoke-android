package com.example.cp100.Fragments;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;



public class RPM_sel_1 extends Fragment {
    public static Button battery_btn, vibration_btn, enter_btn,piezo_btn,wheeler3_btn,wheeler4_btn;
    ImageView rpmsel1_imgback;
    String value = "3";
    String wheeler = "1";
    String clickpoints = "0";
    String clickpointssecond = "0";
    Context context;
    Animation blink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_r_p_m_sel_1, container, false);
        context = view.getContext();
        blink = AnimationUtils.loadAnimation(context,R.anim.blink);

        battery_btn = view.findViewById(R.id.battery_btn);
        vibration_btn = view.findViewById(R.id.vibration_btn);
        enter_btn = view.findViewById(R.id.enter_rpm_btn);
        piezo_btn = view.findViewById(R.id.piezo_btn);
        wheeler3_btn = view.findViewById(R.id.wheeler3_btn);
        wheeler4_btn = view.findViewById(R.id.wheeler4_btn);
        rpmsel1_imgback = view.findViewById(R.id.backimage_rpm_sel1);

        wheeler3_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                wheeler3_btn.startAnimation(blink);
                wheeler3_btn.setBackgroundResource(R.color.pink);
                wheeler4_btn.setBackgroundResource(R.color.appSkyBlue);
                wheeler = "1";
                clickpoints = "1";

            }
        });


        wheeler4_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                wheeler4_btn.startAnimation(blink);
                wheeler3_btn.setBackgroundResource(R.color.appSkyBlue);
                wheeler4_btn.setBackgroundResource(R.color.pink);
                wheeler = "2";
                clickpoints = "1";
            }
        });

        piezo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piezo_btn.startAnimation(blink);
                piezo_btn.setBackgroundResource(R.color.pink);
                battery_btn.setBackgroundResource(R.color.appSkyBlue);
                vibration_btn.setBackgroundResource(R.color.appSkyBlue);
                value = "1";
                clickpointssecond = "2";

            }
        });

        battery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battery_btn.startAnimation(blink);
                battery_btn.setBackgroundResource(R.color.pink);
                piezo_btn.setBackgroundResource(R.color.appSkyBlue);
                vibration_btn.setBackgroundResource(R.color.appSkyBlue);
                value = "2";
                clickpointssecond = "2";
            }
        });

        vibration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vibration_btn.startAnimation(blink);
                vibration_btn.setBackgroundResource(R.color.pink);
                battery_btn.setBackgroundResource(R.color.appSkyBlue);
                piezo_btn.setBackgroundResource(R.color.appSkyBlue);
                value = "3";
                clickpointssecond = "2";
            }
        });

        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter_btn.startAnimation(blink);

                if(clickpoints != "0"){
                    if(clickpointssecond != "0"){
                        clickpointssecond = "0";
                        clickpoints = "0";


//                if((wheeler.equals("1") || wheeler.equals("2")) && value.equals("2"))
//                {
//                    wheeler = "0";
//                    value = " 0";
//                    BaseActivity.sendBleMsg("*$3,20,Sm_RPM_Sel_2#");
//                }
//                else if (wheeler.equals("1") && value.equals("1"))
//                {
//                    wheeler = "0";
//                    value = " 0";
//                    BaseActivity.sendBleMsg("*$1,4,1,00,00,04#");
//                }
                        if(wheeler.equals("1"))
                        {
                            wheeler = "0";
                            if(value.equals("1"))
                            {
                                value = " 0";
                                BaseActivity.sendBleMsg("*$1,4,1,00,00,04#");
                            }
                            else if (value.equals("2"))
                            {
                                value = " 0";
                                BaseActivity.sendBleMsg("*$3,20,Sm_RPM_Sel_2#");
                            }
                            else
                                value = " 0";
                            {
                                BaseActivity.sendBleMsg("*$1,4,02,00,04#");
                            }

                        }

                        if(wheeler.equals("2"))
                        {
                            wheeler = "0";
                            if(value.equals("1"))
                            {
                                value = " 0";
                                BaseActivity.sendBleMsg("*$1,4,1,00,01,04#");
                            }
                            else if (value.equals("2"))
                            {
                                value = " 0";
                                BaseActivity.sendBleMsg("*$3,20,Sm_RPM_Sel_2#");
                            }
                            else
                            {
                                value = " 0";
                                BaseActivity.sendBleMsg("*$1,4,02,01,04#");
                            }

                        }

                    }else {
                        Toast.makeText(context, "Please pick RPM Type", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Please pick Vehicle Type", Toast.LENGTH_SHORT).show();
                }



            }
        });
        rpmsel1_imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rpmsel1_imgback.startAnimation(blink);
                //BaseActivity.sendBleMsg("*$3,9#");
                clickpointssecond = "0";
                clickpoints = "0";
                BaseActivity.sendBleMsg("*$1,9#*$3,20,Smoke_meter_R#");


            }
        });

        return view;


    }
}