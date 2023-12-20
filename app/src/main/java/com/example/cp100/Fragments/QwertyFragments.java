package com.example.cp100.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cp100.R;

public class QwertyFragments extends Fragment {

    EditText textqwerty;
    ImageView img_back_qwerty;
    Button qwerty_enter_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qwerty_fragments, container, false);

        textqwerty = view.findViewById(R.id.textqwerty);
        img_back_qwerty = view.findViewById(R.id.back_qwerty);
        qwerty_enter_btn = view.findViewById(R.id.qwerty_enter_btn);

        return  view;

    }
}