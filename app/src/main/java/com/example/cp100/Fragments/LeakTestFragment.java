package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class LeakTestFragment extends Fragment {
    Button leak_test_enter_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leak_test, container, false);

        leak_test_enter_btn = view.findViewById(R.id.leak_test_enter_btn);

        leak_test_enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.toast("Enter Leak test");
                BaseActivity.sendBleMsg("*$0,2#");
            }
        });

        return view;
    }
}