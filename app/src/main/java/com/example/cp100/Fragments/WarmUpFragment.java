package com.example.cp100.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cp100.R;

public class WarmUpFragment extends Fragment {
    public static ProgressBar warmupProgressBar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_warm_up, container, false);

        warmupProgressBar = view.findViewById(R.id.progressBarWarmup);
//        warmupProgressBar.setProgress(0,true);
        return view;
    }
}