package com.example.cp100.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cp100.BaseActivity;
import com.example.cp100.R;

import java.util.Calendar;

public class DateTimeFragment extends Fragment {

    public static TextView date_input,month_input,year_input,hour_input,minute_input, second_input;
    public static ImageView back_datetime;
    public static Button date_time_enter;
    public int mYearx,mMonthx,mDayx,mHourx, mMinutex,mSecondx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_time, container, false);

        back_datetime = view.findViewById(R.id.back_datetime);
        date_input = view.findViewById(R.id.date_input);
        month_input = view.findViewById(R.id.month_input);
        year_input = view.findViewById(R.id.year_input);
        hour_input = view.findViewById(R.id.hour_input);
        minute_input = view.findViewById(R.id.minute_input);
        second_input = view.findViewById(R.id.second_input);
        date_time_enter = view.findViewById(R.id.date_time_enter);

        hour_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettimedialoginput();
            }
        });

        minute_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettimedialoginput();
            }
        });

        second_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettimedialoginput();
            }
        });

        date_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getcalldatedialoguge();
            }
        });
        month_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcalldatedialoguge();
            }
        });

        year_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcalldatedialoguge();
            }
        });

        date_time_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String dt = getDateTime();
                if (!dt.equals("")) {
                    BaseActivity.sendBleMsg("*$3,0,");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            BaseActivity.sendBleMsg(dt+"#,");
                        }
                    },100);
                }
                else {
                    Log.e(BaseActivity.TAG,"Empty datetime");
                }
            }
        });

        back_datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.sendBleMsg("*$3,21#,");
            }
        });

        return view;
    }

    private void gettimedialoginput() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHourx = c.get(Calendar.HOUR_OF_DAY);
        mMinutex = c.get(Calendar.MINUTE);
        mSecondx = c.get(Calendar.SECOND);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {



                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

//                                hour_input.setText(hourOfDay + ":" + minute);
                        hour_input.setText(hourOfDay + "");
                        minute_input.setText(minute + "");
                        second_input.setText(mSecondx+"");
                    }
                }, mHourx, mMinutex,false);
        timePickerDialog.show();
    }

    private void getcalldatedialoguge() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYearx = c.get(Calendar.YEAR);
        mMonthx = c.get(Calendar.MONTH);
        mDayx = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        //date_input.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        date_input.setText(dayOfMonth + "");
                        month_input.setText((monthOfYear + 1)+"");
                        year_input.setText(year+"");


                    }
                }, mYearx, mMonthx, mDayx);
        datePickerDialog.show();
    }

    private String getDateTime(){
        int d = 0 , m =0 , y=0 , h=0 , i=0 , s=0;

        String sd = date_input.getText().toString();
        if(!sd.equals(""))
            d = Integer.parseInt(sd);

        String sm = month_input.getText().toString();
        if(!sm.equals(""))
            m = Integer.parseInt(sm);

        String sy = year_input.getText().toString();
        if(!sy.equals(""))
            y = Integer.parseInt(sy);

        String sh = hour_input.getText().toString();
        if(!sh.equals(""))
            h = Integer.parseInt(sh);

        String si = minute_input.getText().toString();
        if(!si.equals(""))
            i = Integer.parseInt(si);

        String ss = second_input.getText().toString();
        if(!ss.equals(""))
            s = Integer.parseInt(ss);


        if (d < 1 || d > 31) {
            toast("Invalid Date");
            Log.e(BaseActivity.TAG,"Invalid Date");
            return "";
        }

        if (m < 1 || m > 12) {
            toast("Invalid Month");
            Log.e(BaseActivity.TAG,"Invalid Month");
            return "";
        }

//        if (y < 0 || y > 99) {
//            toast("Invalid Year");
//            Log.e(BaseActivity.TAG,"Invalid Year");
//            return "";
//        }

        if (h < 0 || h > 24) {
            toast("Invalid Hours");
            Log.e(BaseActivity.TAG,"Invalid Hours");
            return "";
        }

        if (i < 0 || i > 60) {
            toast("Invalid Minutes");
            Log.e(BaseActivity.TAG,"Invalid Minutes");
            return "";
        }

        if (s < 0 || s > 60) {
            toast("Invalid Seconds");
            Log.e(BaseActivity.TAG,"Invalid Seconds");
            return "";
        }

        return d+","+m+","+y+","+h+","+i+","+s;

    }

    private void toast(String msg){
        BaseActivity.toast(msg);
    }
}