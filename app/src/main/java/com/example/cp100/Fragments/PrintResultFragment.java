package com.example.cp100.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cp100.R;

public class PrintResultFragment extends Fragment {

    public static TextView printresulttext1, printresulttext2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_print_result, container, false);

        printresulttext1 = view.findViewById(R.id.printresulttext1);
        printresulttext2 = view.findViewById(R.id.printresulttext2);

        return view;
    }
}