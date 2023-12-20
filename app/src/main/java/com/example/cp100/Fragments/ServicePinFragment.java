package com.example.cp100.Fragments;

import static com.example.cp100.BaseActivity.context;
import static com.example.cp100.BaseActivity.replaceFargment;
import static com.example.cp100.BaseActivity.servicePinFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

public class ServicePinFragment extends Fragment {
    public static TextView pin_number_text;
    public static ImageView back_service_pin;
    public static EditText entered_pin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_service_pin, container, false);

        pin_number_text = view.findViewById(R.id.pin_number_text);
        back_service_pin = view.findViewById(R.id.back_service_pin);
        entered_pin = view.findViewById(R.id.entered_pin);

        entered_pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.e(BaseActivity.TAG,"s :"+ s );
                if (checkPIN(s)){
                    BaseActivity.sendBleMsg("*$3,2,"+s+"#");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        back_service_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,21#");
            }
        });

        return  view;
    }

    private boolean checkPIN(CharSequence s) {
        if(s.length()!=6 ) return false;
        String pin = pin_number_text.getText().toString();


        if(pin.length() != 6) return false;

        int a = Integer.parseInt(String.valueOf(s.subSequence(0,2)));
        int b = Integer.parseInt(String.valueOf(s.subSequence(2,4)));
        int c = Integer.parseInt(String.valueOf(s.subSequence(4,6)));

        String sd = String.valueOf(Integer.parseInt(pin.substring(0,2)) * 5);
        String se = String.valueOf(Integer.parseInt(pin.substring(2,4)) * 2);

        int d = Integer.parseInt(sd);
        int e = Integer.parseInt(se);
        int f = Integer.parseInt(pin.substring(4,6));

        try {
            if (sd.length() > 2) {
                d = Integer.parseInt(sd.substring(0, 2));
            }
            if (se.length() > 2) {
                e = Integer.parseInt(se.substring(0, 2));
            }
        }
        catch (Exception ex)
        {
            Log.e(BaseActivity.TAG,"ERROR : "+ex);
        }

        Log.e(BaseActivity.TAG,a+" "+b+" "+c+" "+d+" "+e+" "+f+" ");
        if( a==d && b==e && c==f ){
            return true;
        }
        else{
            entered_pin.setText("");
            BaseActivity.incorrectPIN();
        }

        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        String s1 = sh.getString("name", "");
       // Toast.makeText(context, ""+s1, Toast.LENGTH_SHORT).show();
        pin_number_text.setText(""+s1);
    }
}