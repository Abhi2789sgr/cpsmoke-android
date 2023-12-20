package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class KeypadFragment extends Fragment {
    EditText textkeypad;
    ImageView img_back_keypad;
    Button keypad_enter_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keypad, container, false);

        textkeypad = view.findViewById(R.id.textkeypad);
        img_back_keypad = view.findViewById(R.id.back_keypad);
        keypad_enter_btn = view.findViewById(R.id.keypad_enter_btn);


        keypad_enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keypadvalue = textkeypad.getText().toString();
                BaseActivity.sendBleMsg("*$3,2," + keypadvalue + "#");
                textkeypad.setText("");
            }
        });

        img_back_keypad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,20,Service_Pin#");
            }
        });

        return view;
    }
}