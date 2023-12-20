package com.example.cp100.Fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class CenterDetailsFragment extends Fragment {

    public static EditText center_name;
    public static Button save_center_name,buttonspace,buttonsecondSpace,button3space,buttonEdit;
    public static ImageView back_center_details;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_center_details, container, false);
        center_name = view.findViewById(R.id.center_name_detail);
        save_center_name = view.findViewById(R.id.save_center_name);
        back_center_details = view.findViewById(R.id.back_center_details);
        buttonspace = view.findViewById(R.id.buttonspace);
        buttonsecondSpace = view.findViewById(R.id.buttonsecondSpace);
        button3space = view.findViewById(R.id.button3space);
        buttonEdit = view.findViewById(R.id.edit_center_name);
        button3space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button3space:
                        hideKeybaord(v);
                        break;
                }
            }
        });

        buttonsecondSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonsecondSpace:
                        hideKeybaord(v);
                        break;
                }
            }
        });

        buttonspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonspace:
                        hideKeybaord(v);
                        break;
                }
            }
        });

        save_center_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(BaseActivity.TAG,"*$3,6,"+center_name.getText().toString()+"#");
                BaseActivity.sendBleMsg("*$3,6,"+center_name.getText().toString()+"#");
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(BaseActivity.TAG,"*$3,6,"+center_name.getText().toString()+"#");
                BaseActivity.sendBleMsg("*$3,6,"+center_name.getText().toString()+"#");
            }
        });


        back_center_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,21#");
            }
        });

        return view;
    }

    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);

    }


}