package com.example.cp100.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cp100.R;

public class ZeroingFragment extends Fragment {

    public static ProgressBar progressBarZeroingsmoke;
    public static TextView zeroingfragments_text1,zeroing_in_progress_text;
    Context context;
    public static Animation right_anim;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zeroing, container, false);

        context = view.getContext();
        right_anim = AnimationUtils.loadAnimation(context,R.anim.top_animation);

        progressBarZeroingsmoke = view.findViewById(R.id.progressBarZeroingsmoke);
        zeroing_in_progress_text = view.findViewById(R.id.zeroing_in_progress_text);
        zeroingfragments_text1 = view.findViewById(R.id.zeroing_fragment_text1);
        zeroing_in_progress_text.startAnimation(right_anim);
        progressBarZeroingsmoke.setProgress(0,true);
        return view;
    }
}