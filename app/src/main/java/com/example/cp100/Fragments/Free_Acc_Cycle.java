package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cp100.R;


public class Free_Acc_Cycle extends Fragment {

    public static TextView rpmrange_acc_cycle2 , rpmrange_acc_cycle4 , rpmvalue_acc_cycle , hsuvalue_acc_cycle , otvalue_acc_cycle , kvalue_acc_cycle, freeaccCycle_name, textacccyclename, textacccycle3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_free__acc__cycle, container, false);

        rpmrange_acc_cycle2 = view.findViewById(R.id.rpmrange_acc_cycle2);
        rpmrange_acc_cycle4 = view.findViewById(R.id.rpmrange_acc_cycle4);
        textacccyclename = view.findViewById(R.id.textfreeacccycle2);
        rpmvalue_acc_cycle = view.findViewById(R.id.rpmvalue_acc_cycle);
        hsuvalue_acc_cycle = view.findViewById(R.id.hsuvalue_acc_cycle);
        otvalue_acc_cycle = view.findViewById(R.id.otvalue_acc_cycle);
        kvalue_acc_cycle = view.findViewById(R.id.kvalue_acc_cycle);
        textacccycle3 = view.findViewById(R.id.textacccycle3);


        return view;
    }
}