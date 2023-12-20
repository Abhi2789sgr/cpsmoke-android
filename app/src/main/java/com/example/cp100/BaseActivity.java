package com.example.cp100;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cp100.Fragments.AutoCal_Fail;
import com.example.cp100.Fragments.AutoCal_Pass;
import com.example.cp100.Fragments.AutoCal_ins;
import com.example.cp100.Fragments.Auto_Cal;
import com.example.cp100.Fragments.Band_Selection;
import com.example.cp100.Fragments.CalibrationCo2Fragment;
import com.example.cp100.Fragments.CalibrationCoFragment;
import com.example.cp100.Fragments.CalibrationFailFragment;
import com.example.cp100.Fragments.CalibrationFragment;
import com.example.cp100.Fragments.CalibrationHcFragment;
import com.example.cp100.Fragments.CalibrationIns;
import com.example.cp100.Fragments.CalibrationPassFragment;
import com.example.cp100.Fragments.CenterDetailsFragment;
import com.example.cp100.Fragments.ConnectingRpmFragment;
import com.example.cp100.Fragments.CylinderFailFragment;
import com.example.cp100.Fragments.DateTimeFragment;
import com.example.cp100.Fragments.DefaultCellFragment;
import com.example.cp100.Fragments.FlushHCDoneFragment;
import com.example.cp100.Fragments.FlushHCFragment;
import com.example.cp100.Fragments.FlushingDoneFragment;
import com.example.cp100.Fragments.FlushingFragment;
import com.example.cp100.Fragments.FlushingOkFragment;
import com.example.cp100.Fragments.Flushing_Fail;
import com.example.cp100.Fragments.Free_Acc_Cycle;
import com.example.cp100.Fragments.Free_Acc_Done;
import com.example.cp100.Fragments.Free_Acc_Start;
import com.example.cp100.Fragments.Free_Acc_Test;
import com.example.cp100.Fragments.GStandbyFragment;
import com.example.cp100.Fragments.GasAnalyzerFragment;
import com.example.cp100.Fragments.IncorrectPinFragment;
import com.example.cp100.Fragments.KeypadFragment;
import com.example.cp100.Fragments.LeakTestFailFragment;
import com.example.cp100.Fragments.LeakTestFragment;
import com.example.cp100.Fragments.LeakTestPassFragment;
import com.example.cp100.Fragments.LeakTestProgressFragment;
import com.example.cp100.Fragments.MeasureCycleFragment;
import com.example.cp100.Fragments.MeasureFragment;
import com.example.cp100.Fragments.MeasurementFragment;
import com.example.cp100.Fragments.O2CheckingFragment;
import com.example.cp100.Fragments.O2FailFragment;
import com.example.cp100.Fragments.O2PassFragment;
import com.example.cp100.Fragments.PrintResultFragment;
import com.example.cp100.Fragments.Print_Copy;
import com.example.cp100.Fragments.RPM_Incorrect;
import com.example.cp100.Fragments.RPM_NotRange;
import com.example.cp100.Fragments.RPM_sel_2;
import com.example.cp100.Fragments.ReturnPageFragment;
import com.example.cp100.Fragments.RpmFailFragment;
import com.example.cp100.Fragments.RpmPassFragment;
import com.example.cp100.Fragments.Run_Flush_Cycle;
import com.example.cp100.Fragments.SM_Calibration;
import com.example.cp100.Fragments.Selections;
import com.example.cp100.Fragments.ServicePinFragment;
import com.example.cp100.Fragments.Service_Mode;
import com.example.cp100.Fragments.ServicesFragment;
import com.example.cp100.Fragments.SmokeFinalFragment;
import com.example.cp100.Fragments.SmokeMeterFragment;
import com.example.cp100.Fragments.SmokeMeterR;
import com.example.cp100.Fragments.StandbyFragment;
import com.example.cp100.Fragments.Fuel_Type;
import com.example.cp100.Fragments.RPM_TYPE;
import com.example.cp100.Fragments.WarmUpFragment;
import com.example.cp100.Fragments.WarmupDoneFragment;
import com.example.cp100.Fragments.ZeroCalibrationFragment;
import com.example.cp100.Fragments.Zero_Fail;
import com.example.cp100.Fragments.ZeroingDoneFragment;
import com.example.cp100.Fragments.ZeroingFragment;
import com.example.cp100.Fragments.RPM_sel_1;

import java.util.List;
import java.util.UUID;

import static com.example.cp100.Fragments.ZeroingFragment.right_anim;

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = "cp100";
    private static BleService bleService;
    String deviceAddress;
    public LinearLayout fragment_linear_layout;
    public static FragmentTransaction fragmentTransaction;
    public static FragmentManager fragmentManager;
    Switch switchBtn;
    public static String dataForm;
    String startingString=null;
    String middleString=null;
    String endingString=null;
    String fullString=null;
    String fullString2=null;
    int counter = 0;
    public static Context context;
    MainScreenFragment mainScreenFragment;
    CalibrationFragment calibrationFragment;
    CalibrationCoFragment calibrationCoFragment;
    CalibrationCo2Fragment calibrationCo2Fragment;
    CalibrationHcFragment calibrationHcFragment;
    CalibrationPassFragment calibrationPassFragment;
    CalibrationFailFragment calibrationFailFragment;
    CenterDetailsFragment centerDetailsFragment;
    CylinderFailFragment cylinderFailFragment;
    ConnectingRpmFragment connectingRpmFragment;
    DateTimeFragment dateTimeFragment;
    FlushHCDoneFragment flushHCDoneFragment;
    FlushHCFragment flushHCFragment;
    FlushingDoneFragment flushingDoneFragment;
    FlushingFragment flushingFragment;
    FlushingOkFragment flushingOkFragment;
    GasAnalyzerFragment gasAnalyzerFragment;
    Selections selections;
    RPM_NotRange rpm_notRange;
    Free_Acc_Cycle free_acc_cycle;
    public static IncorrectPinFragment incorrectPinFragment;
    KeypadFragment keypadFragment;
    LeakTestFailFragment leakTestFailFragment;
    LeakTestFragment leakTestFragment;
    LeakTestPassFragment leakTestPassFragment;
    LeakTestProgressFragment leakTestProgressFragment;
    MeasureCycleFragment measureCycleFragment;
    MeasureFragment measureFragment;
    MeasurementFragment measurementFragment;
    O2CheckingFragment o2CheckingFragment;
    O2PassFragment o2PassFragment;
    O2FailFragment o2FailFragment;
    PrintResultFragment printResult_Fragment;
    ReturnPageFragment returnPageFragment;
    RpmPassFragment rpmPassFragment;
    RpmFailFragment rpmFailFragment;
    ServicesFragment servicesFragment;
    Service_Mode service_mode;
    Print_Copy print_copy;
    SmokeMeterR smokeMeterR;
    RPM_sel_2 rpm_sel_2;
    Band_Selection band_selection;
    DefaultCellFragment default_cell;
    CalibrationIns calibrationIns;
    Flushing_Fail flushing_fail;
    RPM_Incorrect rpm_incorrect;
    Free_Acc_Start free_acc_start;
    Free_Acc_Test free_acc_test;
    Free_Acc_Done free_acc_done;
    AutoCal_Pass autoCal_pass;
    Run_Flush_Cycle run_flush_cycle;
    AutoCal_Fail autoCal_fail;
    RPM_sel_1 rpm_sel_1;
    AutoCal_ins autoCal_ins;
    SM_Calibration sm_calibration;
    public static ServicePinFragment servicePinFragment;
    SmokeFinalFragment smokeFinalFragment;
    SmokeMeterFragment smokeMeterFragment;
    StandbyFragment standbyFragment;
    GStandbyFragment gStandbyFragment;
    Fuel_Type fuelType;
    RPM_TYPE RPMTYPE;
    WarmupDoneFragment warmupDoneFragment;
    WarmUpFragment warmUpFragment;
    ZeroCalibrationFragment zeroCalibrationFragment;
    ZeroingDoneFragment zeroingDoneFragment;
    Auto_Cal autocalibrationFragment;
    Zero_Fail zero_fail;
    ZeroingFragment zeroingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Request for no title of the app on screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // For using full screen of the android phone...
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hide Action bar of the screen
        getSupportActionBar().hide();
        setContentView(R.layout.activity_base);
        context = BaseActivity.this;
        fragmentManager = getSupportFragmentManager();

        getViewIds();

        // get device address from the activity intent
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        deviceAddress = bundle.getString("Address");
        if (deviceAddress == null) finish();

        // set mainscreen fragment to base activity
        mainScreenFragment = new MainScreenFragment();
        replaceFargment(mainScreenFragment);


        // on click listeners
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(bleService.connect(deviceAddress)) {
                        switchBtn.setText("Connecting...");
                    }else{
                        switchBtn.setText("Connect");
                        switchBtn.setChecked(false);
                    }
                }
                else{
                    bleService.disconnect();
                    switchBtn.setText("Connect");
                }
            }
        });

        registerReceiver(deviceBroadcastReceiver, bleIntentFilters());
    }

    public void getViewIds(){
        switchBtn = findViewById(R.id.ble_switch_btn);
        fragment_linear_layout = findViewById(R.id.fragment_linear_layout);
    }

    public static void toast(String msg){
        //Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent serviceIntent = new Intent(this,BleService.class);
        bindService(serviceIntent, serviceConnection,BIND_AUTO_CREATE);
    }

    public static void sendBleMsg(String msg){
        Log.e(TAG,"Send : "+msg);
        bleService.write(msg);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            receiveText.append("\nService Connected");
            // Get instance of BleService through its local binder class
            bleService = ((BleService.LocalBinder) service).getService();
            // starting local function to start bluetooth adapter in service class
            if(!bleService.startThisService()){
                toast("Service Bluetooth Not Started");
                finish();
            }
            if (bleService != null) {
                if (bleService.connect(deviceAddress)) {
                    List<BluetoothGattService> gattservices = bleService.getBluetoothGatt().getServices();
                    for (BluetoothGattService foundservice : gattservices){
//                        receiveText.append("\n"+foundservice.getUuid().toString());
                    }
//                    receiveText.append("\nDevice Connected");
                }
                else {
                    toast("\nDevice not connected");
                }
            }
            else{
                toast("Bleservice is null");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            toast("Service Disconnected");

        }
    };

    public IntentFilter bleIntentFilters(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BleService.ACTION_CONNECTION_STATE_CHANGE);
        intentFilter.addAction(BleService.ACTION_SERVICE_DISCOVER);
        intentFilter.addAction(BleService.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(BleService.ACTION_STATE_CONNECTING);
        intentFilter.addAction(BleService.ACTION_STATE_CONNECTED);
        intentFilter.addAction(BleService.ACTION_STATE_DISCONNECTING);
        intentFilter.addAction(BleService.ACTION_STATE_DISCONNECTED);
        intentFilter.addAction(BleService.DATA);
        return intentFilter;
    }

    private BroadcastReceiver deviceBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            assert action != null;
            switch (action) {
                case BleService.ACTION_CONNECTION_STATE_CHANGE:
//                    toast("ConnectionStateChange");
                    break;

                case BleService.ACTION_STATE_CONNECTING:
//                    toast("ACTION_STATE_CONNECTING");
                    break;

                case BleService.ACTION_STATE_CONNECTED:
//                    toast("ACTION_STATE_CONNECTED");
                    switchBtn.setChecked(true);
                    switchBtn.setText("Disconnect");
                    break;

                case BleService.ACTION_STATE_DISCONNECTING:
//                    toast("ACTION_STATE_DISCONNECTING");
                    break;

                case BleService.ACTION_STATE_DISCONNECTED:
//                    toast("ACTION_STATE_DISCONNECTED");
                    switchBtn.setChecked(false);
                    switchBtn.setText("Connect");
                    break;

                case BleService.ACTION_SERVICE_DISCOVER:
                    break;

                case BleService.ACTION_DATA_AVAILABLE:
                    dataForm = intent.getStringExtra(BleService.STRING_DATA);
                    Log.e(TAG,"dataForm : " +dataForm);
                    fullString=""+dataForm;
                    if (fullString.startsWith("*")&&fullString.endsWith("#")){
                        fullString=dataForm;
                        updateValues(fullString);
                    }
                    else{
                        if (fullString.startsWith("*")){
                            startingString=dataForm;
                        }
                        else if (fullString.endsWith("#")){
                            endingString=dataForm;
                        }
                        else{
                            middleString+=dataForm;
                        }

                        if (!TextUtils.isEmpty(startingString)&&!TextUtils.isEmpty(middleString)&&!TextUtils.isEmpty(endingString)){
                            fullString2=startingString+middleString+endingString;
                            updateValues(fullString2);
                            startingString="";
                            middleString="";
                            endingString="";
                            fullString="";
                        }
                        else if (!TextUtils.isEmpty(startingString)&&!TextUtils.isEmpty(endingString)){
                            fullString2=startingString+endingString;
                            updateValues(fullString2);
                            startingString="";
                            middleString="";
                            endingString="";
                            fullString="";
                        }
                    }
                    break;

                case BleService.DATA:
//                    data = intent.getStringExtra(BleService.STRING_DATA);
                    break;

                default:
//                    toast(action);
                    break;
            }
        }
    };
    private void updateValues(String dataString){
        Log.e(TAG,"Update : " +dataString);
        String[] maxStrings = dataString.split("\\*@");
//        Log.e(TAG,"maxStrings : " +maxStrings.length);
        if (maxStrings.length >= 2){
            int noOfStrings = maxStrings.length-1;
            for (int i=1;i<=noOfStrings;i++){
                updateSingleValue("*@"+maxStrings[i]);
            }
        }
    }

    private void updateSingleValue(String fullData)
    {
        Log.e(TAG,"FUllString : " +fullData);


        try {
            //SOURAB BISWAS COLORS
            String colorchanger = ""+fullData;

            if(colorchanger.contains("Smoke")){
                // Found world
                SmokeMeterR.smokemeterr_center_name.setTextColor(ContextCompat.getColor(this, R.color.orangered));
                ZeroingFragment.zeroingfragments_text1.setTextColor(ContextCompat.getColor(this, R.color.orangered));
               // Auto_Cal.auto_cal_text1.setTextColor(ContextCompat.getColor(this, R.color.orangered));

            }
        }catch (Exception e){

        }


        String[] dataArray = fullData.split(",");
        String funcName="";
        String scrName ="";

        int noOfPara=0;

        scrName = dataArray[0];
        funcName = dataArray[1];
        noOfPara = Integer.parseInt(dataArray[2]);
        Log.e(TAG,"Screen : "+scrName+" FuncName : "+funcName+" NoofPar : "+noOfPara );

//        if (dataArray.length>=4)
        if (true)
        {

            int count = 0;
            for (int i = 3; i < (noOfPara+3); i++) {
                count++;
            }
//            toast("No of parameters "+count);

            if (scrName.startsWith("*@")){
                scrName=scrName.substring(2);
                Log.e(TAG,"Screen : "+scrName);
               // toast("No of parameters "+scrName);
                switch (scrName) {

                    case "100":        //Main Screen
                        try {
                            if (mainScreenFragment == null)
                                mainScreenFragment = new MainScreenFragment();
                        } catch (Exception e) {

                        }

                        break;


                    case "103":        // G_Zeroing_Test zeroing screen
                        if (zeroingFragment == null) {
                            zeroingFragment = new ZeroingFragment();
                        }
                        replaceFargment(zeroingFragment);
                        if (noOfPara == 1) {
                            String warmupValue = dataArray[3].split("#")[0];
                            final int value = Integer.parseInt(warmupValue);
//                            toast(dataArray[3]+":"+warmupValue+":"+value);
                            try {
//                                zeroingFragment.progressBarZeroing.setProgress(value);
                            } catch (Exception e) {
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
//                                        zeroingFragment.progressBarZeroing.setProgress(value);
                                    }
                                }, 10);
                            }

                        } else {
//                            toast("Improper Zeroing Value : "+noOfPara+" parameters");
                        }
                        break;

                    case "104":    // G_Zeroing_Done zeroing done screen
                        if (zeroingDoneFragment == null)
                            zeroingDoneFragment = new ZeroingDoneFragment();
                        replaceFargment(zeroingDoneFragment);
                        break;

                    case "105":    // G_Leak_Test_1 ,leak test (enter) screen
                        if (leakTestFragment == null)
                            leakTestFragment = new LeakTestFragment();
                        replaceFargment(leakTestFragment);
                        break;

                    case "106":    // G_Leak_Test_2 ,leak test progress screen
                        if (leakTestProgressFragment == null) {
                            leakTestProgressFragment = new LeakTestProgressFragment();
                        }
                        replaceFargment(leakTestProgressFragment);
                        if (noOfPara == 1) {
                            String warmupValue = dataArray[3].split("#")[0];
                            int value = Integer.parseInt(warmupValue);
//                            toast(dataArray[3]+":"+warmupValue+":"+value);
                            try {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        leakTestProgressFragment.progressBarLeakTest.setProgress(value);
                                    }
                                },300);
                            } catch (Exception e) {
                                toast(e.getMessage());
                            }

                        } else {
//                            toast("Improper Zeroing Value : "+noOfPara+" parameters");
                        }
                        break;


                    case "107":   // G_LeakTest_Pas ,leak test pass screen
                        if (leakTestPassFragment == null)
                            leakTestPassFragment = new LeakTestPassFragment();
                        replaceFargment(leakTestPassFragment);
                        break;

                    // G_LeakTestt_Fai , leak test fail screen
                    case "108":
                        if (leakTestFailFragment == null)
                            leakTestFailFragment = new LeakTestFailFragment();
                        replaceFargment(leakTestFailFragment);
                        break;

                    case "109":  // G_Flush_HC , flush HC residue screen
                        if (flushHCFragment == null)
                            flushHCFragment = new FlushHCFragment();
                        replaceFargment(flushHCFragment);

                        if (funcName.equals("304") && noOfPara == 1) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    flushHCFragment.hc_residue_text.setText(dataArray[3].split("#")[0]);
                                }
                            },300);
                        }
                        break;

                    case "110":  // G_Flush_HC_ok , flush HC residue done screen
                        if (flushHCDoneFragment == null)
                            flushHCDoneFragment = new FlushHCDoneFragment();
                        replaceFargment(flushHCDoneFragment);
                        break;


                    case "221": //  Service_Mode Smoke
                        if (service_mode == null)
                            service_mode = new Service_Mode();
                        replaceFargment(service_mode);
                        try {
                            switch (funcName) {
                                case "411":
                                    if (noOfPara == 7) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                service_mode.servicemode_hsuvalue.setText(dataArray[3]);
                                                service_mode.servicemode_smkvalue.setText(dataArray[5]);
                                                service_mode.servicemode_kvalue.setText(dataArray[4]);
                                                service_mode.servicemode_ngcvalue.setText(dataArray[6]);
                                                service_mode.servicemode_rpmvalue.setText(dataArray[7]);
                                                service_mode.servicemode_otvalue.setText(dataArray[8]);
                                                service_mode.servicemode_ctvalue.setText(dataArray[9].substring(0, dataArray[9].length() - 1));
                                            }
                                        },300);
                                    }
                                    if (noOfPara == 1) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(BaseActivity.this, dataArray[3].substring(0, dataArray[3].length() - 1), Toast.LENGTH_LONG).show();
                                            }
                                        },300);
                                    }
                                    break;
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                        break;

                    case "222": //  SM Calibration Smoke
                        if (sm_calibration == null)
                            sm_calibration = new SM_Calibration();
                        replaceFargment(sm_calibration);

                        try {
                            switch (funcName) {
                                case "413":

                                    if (noOfPara == 2) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                sm_calibration.smcal_hsuvalue_text.setText(dataArray[3]);
                                                sm_calibration.smcal_kvalue_text.setText(dataArray[4].substring(0, dataArray[4].length() - 1));
                                            }
                                        },300);
                                    }
                                    if (noOfPara == 1) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                sm_calibration.smcal_name.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                            }
                                        },300);
                                    }
                                    break;
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                        break;

                    case "208":  // Smoke_Meter_R Smoke

                        if (smokeMeterR == null) {
                            smokeMeterR = new SmokeMeterR();
                        }
                        replaceFargment(smokeMeterR);

                        try {
                            switch (funcName) {
                                case "408":
                                    if (noOfPara == 5) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                smokeMeterR.smokemeterr_time_value.setText(dataArray[6] + ":" + dataArray[7].substring(0, dataArray[7].length() - 1));
                                                smokeMeterR.smokemeterr_date_value.setText(dataArray[3] + "/" + dataArray[4] + "/" + dataArray[5]);
                                            }
                                        },300);

                                    }
                                    if (noOfPara == 4) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                smokeMeterR.hsu_value.setText(dataArray[3]);
                                                smokeMeterR.rpm_value.setText(dataArray[5]);
                                                smokeMeterR.k_value.setText(dataArray[4]);
                                                smokeMeterR.ot_value.setText(dataArray[6].substring(0, dataArray[6].length() - 1));
                                            }
                                        },300);
                                    }
                                    if (noOfPara == 1) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                smokeMeterR.smokemeterr_center_name.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                            }
                                        },300);
                                    }
                                    break;

                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }

                        break;

                    case "209": // SM_Services Smoke
                        if(servicesFragment==null)
                            servicesFragment = new ServicesFragment();
                        replaceFargment(servicesFragment);
                        break;

                    case "Sm_Services": // Back to SM_Services from Service Mode Smoke
                        if(servicesFragment==null)
                            servicesFragment = new ServicesFragment();
                        replaceFargment(servicesFragment);
                        break;

                    case "211": // Service_Pin Smoke

                        if(servicePinFragment==null)
                            servicePinFragment = new ServicePinFragment();
                        replaceFargment(servicePinFragment);

                        try {
                            if (funcName.equals("402") && noOfPara == 1) {
                                Log.e(TAG,"PIN : "+ dataArray[3].substring(0,dataArray[3].length()-1));
                                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("name", ""+dataArray[3].substring(0,dataArray[3].length()-1));
                                myEdit.commit();

                                final String pin = dataArray[3].substring(0,dataArray[3].length()-1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        servicePinFragment.pin_number_text.setText(pin);
                                    }
                                },300);
                            }
                        }
                        catch (Exception e){
                            Log.e(TAG,e.getMessage());
                        }
                        break;

                    case "212": // Incorrect Pin Smoke
                        if(incorrectPinFragment==null)
                            incorrectPinFragment= new IncorrectPinFragment();
                        replaceFargment(incorrectPinFragment);

                        break;

                    case "214":  // Center_Details Smoke
                        if(centerDetailsFragment==null)
                            centerDetailsFragment = new CenterDetailsFragment();
                        replaceFargment(centerDetailsFragment);

                        try {
                            if (funcName.equals("405") && noOfPara == 1) {
                                final String cname = dataArray[3].substring(0, dataArray[3].length()-1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        centerDetailsFragment.center_name.setText(cname);
                                    }
                                },100);
                            }
                        }
                        catch (Exception e){
                            Log.e(TAG,e.getMessage());
                        }
                        break;

                    case "215": // Date_Time Smoke
                        if (dateTimeFragment==null)
                            dateTimeFragment = new DateTimeFragment();
                        replaceFargment(dateTimeFragment);

                    case "401":
                        if(noOfPara == 0) {
//                            dateTimeFragment.date_input.setText("");
//                            dateTimeFragment.month_input.setText("");
//                            dateTimeFragment.year_input.setText(("");
//                            dateTimeFragment.hour_input.setText("");
//                            dateTimeFragment.minute_input.setText("");
//                            dateTimeFragment.second_input.setText("");
                        }

                        if(noOfPara == 6) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dateTimeFragment.date_input.setText(dataArray[3]);
                                    dateTimeFragment.month_input.setText(dataArray[4]);
                                    dateTimeFragment.year_input.setText(dataArray[5]);
                                    dateTimeFragment.hour_input.setText(dataArray[6]);
                                    dateTimeFragment.minute_input.setText(dataArray[7]);
                                    dateTimeFragment.second_input.setText(dataArray[8].substring(0, dataArray[8].length() - 1).toString());
                                }
                            },300);
                        }
                        break;

                    case "217": // Selections Smoke
                        if(selections==null)
                            selections= new Selections();
                        replaceFargment(selections);
                        break;

                    case "218": // RPM SEL_1 Smoke
                        if(rpm_sel_1==null)
                            rpm_sel_1= new RPM_sel_1();
                        replaceFargment(rpm_sel_1);
                        break;

                    case "Sm_RPM_Sel_1": // Back to  RPM SEL_1 from Smoke
                        if(rpm_sel_1==null)
                            rpm_sel_1= new RPM_sel_1();
                        replaceFargment(rpm_sel_1);
                        break;

                    case "Sm_RPM_Sel_2": // RPM SEL_2 Smoke
                        if(rpm_sel_2==null)
                            rpm_sel_2= new RPM_sel_2();
                        replaceFargment(rpm_sel_2);
                        break;

                    case "219": // RPM SEL_2 Smoke
                        if(rpm_sel_2==null)
                            rpm_sel_2= new RPM_sel_2();
                        replaceFargment(rpm_sel_2);
                        break;


                    case "112" :    // Standby_type
                        break;

                    case "113" :    // G_Standby
                        if (funcName.equals("312")) {
                            if(gStandbyFragment==null)
                                gStandbyFragment = new GStandbyFragment();
                            replaceFargment(gStandbyFragment);
                        }
                        else
                        {
                            if(standbyFragment==null)
                                standbyFragment = new StandbyFragment();
                            replaceFargment(standbyFragment);
                        }
                        break;

                    case "114": // G_Services
                        if(keypadFragment==null)
                            keypadFragment = new KeypadFragment();
                        replaceFargment(keypadFragment);
                        break;

                    case "115":     // Keypad_2
                        if(keypadFragment==null)
                            keypadFragment = new KeypadFragment();
                        replaceFargment(keypadFragment);
                        break;

                    case "117":  // G_Fuel_Type
                        if(fuelType ==null)
                            fuelType = new Fuel_Type();
                        replaceFargment(fuelType);
                        break;

                    case "118":  // G_Engine_Type
                        break;

                    case "119": // G_RPM_Type
                        if(RPMTYPE ==null)
                            RPMTYPE = new RPM_TYPE();
                        replaceFargment(RPMTYPE);
                        break;

                    case "120":  // G_Cylinder_Cnt
                        break;

                    case "122":  // Qwerty
                        break;

                    case "124": // Keypad_SP
                        break;

                    case "125":  //Incorrect PIN
                        break;

                    case "126": // G_Zero_Cali
                        if(zeroCalibrationFragment==null)
                            zeroCalibrationFragment = new ZeroCalibrationFragment();
                        replaceFargment(zeroCalibrationFragment);
                        break;

                    case "127": // Factory_Cfrm
                        break;

                    case "128": // Keypad_SP
                        break;

                    case "129": //G_Zero_Cali_1 co
                        if(calibrationCoFragment==null)
                            calibrationCoFragment = new CalibrationCoFragment();
                        replaceFargment(calibrationCoFragment);

                        if (funcName.equals("313") && noOfPara == 1){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    calibrationCoFragment.co_reset_value1.setText(dataArray[3].split("#")[0]);
                                }
                            },300);
                        }
                        break;

                    case "130": // G_Zero_Cali_2 hc
                        if(calibrationHcFragment==null)
                            calibrationHcFragment = new CalibrationHcFragment();
                        replaceFargment(calibrationHcFragment);

                        if (funcName.equals("313") && noOfPara == 1){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    calibrationHcFragment.hc_resent_value1.setText(dataArray[3].split("#")[0]);
                                }
                            },300);
                        }
                        break;

                    case "131": // G_Zero_Cali_3 co2
                        if(calibrationCo2Fragment==null)
                            calibrationCo2Fragment = new CalibrationCo2Fragment();
                        replaceFargment(calibrationCo2Fragment);

                        if (funcName.equals("313") && noOfPara == 1){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    calibrationCo2Fragment.co2_resent_value1.setText(dataArray[3].split("#")[0]);
                                }
                            },300);
                        }
                        break;

                    case "132": // G_Zero_Cali_4
                        break;

                    case "133": // Cal_cy_fail
                        if (cylinderFailFragment==null)
                            cylinderFailFragment = new CylinderFailFragment();
                        replaceFargment(cylinderFailFragment);
                        break;

                    case "201": // Auto Calibrating Percent Count Smoke

                        try {

                            if (autocalibrationFragment == null)
                                autocalibrationFragment = new Auto_Cal();
                            replaceFargment(autocalibrationFragment);

                            if (funcName.equals("407")) {
                                if (noOfPara == 1) {
                                    String prValue2 = (dataArray[3].split("#")[0]).toString();
                                    if (prValue2.contains("Smoke")) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Auto_Cal.auto_cal_text1.setText(dataArray[3].split("#")[0]);
                                            }
                                        },300);
                                    } else {
                                        final int value2 = Integer.parseInt(prValue2);
                                        try {
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    autocalibrationFragment.progressBarautoCalibration.setProgress(Integer.parseInt(prValue2));
                                                }
                                            },300);
                                        } catch (Exception e) {
                                            Log.e(TAG, e.getMessage());
                                        }
                                    }

                                }
                            }
                        }catch (Exception e){

                        }
                        break;

                    case "202": // Auto Calibrating OK / Pass Smoke
                        if (autoCal_pass==null)
                            autoCal_pass = new AutoCal_Pass();

                        if ( funcName.equals("407") ){
                            if (noOfPara == 0) {
                                replaceFargment(autoCal_pass);
                            }
                        }
                        break;

                    case "203": // Auto Calibrating Fail Smoke
                        if (autoCal_fail==null)
                            autoCal_fail = new AutoCal_Fail();

                        if ( funcName.equals("407") ){
                            if (noOfPara == 0) {
                                replaceFargment(autoCal_fail);
                            }
                        }
                        break;

                    case "204": // Auto Calibrating INS Smoke
                        if (autoCal_ins==null)
                            autoCal_ins = new AutoCal_ins();

                        if ( funcName.equals("407") ){
                            if (noOfPara == 0) {
                                replaceFargment(autoCal_ins);
                            }
                        }
                        break;

                    case "205": // SM Zeroing percent count smoke
                        if (zeroingFragment==null)
                            zeroingFragment = new ZeroingFragment();
                        replaceFargment(zeroingFragment);

                        if ( funcName.equals("409") ){
                            if (noOfPara == 1) {
                                String prValue = (dataArray[3].split("#")[0]).toString();
                                Log.e(TAG,prValue);
                                if(prValue.contains("Smoke"))
                                {
                                    if(counter == 0)
                                    {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                zeroingFragment.zeroingfragments_text1.startAnimation(right_anim);
                                                counter++;
                                            }
                                        },300);
                                    }
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            zeroingFragment.zeroingfragments_text1.setText(prValue);
                                        }
                                    },300);

                                }
                                else
                                {
                                    final int value = Integer.parseInt(prValue);
                                    try {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                zeroingFragment.progressBarZeroingsmoke.setProgress(Integer.parseInt(prValue));
                                            }
                                        },300);
                                    } catch (Exception e) {
                                        Log.e(TAG, e.getMessage());
                                    }
                                }

                            }
                        }
                        break;

                    case "206": // SM Zeroing Done Smoke
                        if (zeroingDoneFragment==null)
                            zeroingDoneFragment = new ZeroingDoneFragment();
                        replaceFargment(zeroingDoneFragment);
                        break;

                    case "207": // SM Zeroing Fail Smoke
                        if (zero_fail==null)
                            zero_fail = new Zero_Fail();
                        replaceFargment(zero_fail);
                        break;



                    case "223": // RPM Connecting Percent Count Smoke
                        if (connectingRpmFragment==null)
                            connectingRpmFragment = new ConnectingRpmFragment();
                        replaceFargment(connectingRpmFragment);


                        if ( funcName.equals("418") ){
                            if (noOfPara == 1) {
                                String prValue = dataArray[3].split("#")[0];
                                int value = Integer.parseInt(prValue);
                                try {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            connectingRpmFragment.progressBarRPM.setProgress(value);
                                        }
                                    },300);
                                } catch (Exception e) {
                                    Log.e(TAG, e.getMessage());
                                }
                            }
                        }
                        break;

                    case "224": // RPM_Pass Smoke
                        if (rpmPassFragment==null)
                            rpmPassFragment = new RpmPassFragment();
                        replaceFargment(rpmPassFragment);
                        break;

                    case "225": // RPM_Fail Smoke
                        if (rpmFailFragment==null)
                            rpmFailFragment = new RpmFailFragment();
                        replaceFargment(rpmFailFragment);
                        break;

                    case "226" : //  Flushing Smoke
                        if(flushingFragment==null)
                            flushingFragment = new FlushingFragment();
                        replaceFargment(flushingFragment);

                        try {
                            switch (funcName) {
                                case "415":
                                    if (noOfPara == 1) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                flushingFragment.flushing_name.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                            }
                                        },300);
                                    }
                                    break;
                            }
                        }
                        catch (Exception e){
                            Log.e(TAG,e.getMessage());
                        }
                        break;

                    case "227" : //  Flushing Cycle Smoke
                        if(flushingOkFragment==null)
                            flushingOkFragment = new FlushingOkFragment();
                        replaceFargment(flushingOkFragment);


                        try {
                            switch (funcName) {
                                case "416":
                                    if (noOfPara == 0) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                flushingOkFragment.flushingok_name.setText("");
                                            }
                                        },300);
                                    }
                                    if (noOfPara == 1) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(!dataArray[3].contains("NULL") && !dataArray[3].contains("null")) {
                                                    flushingOkFragment.flushingok_name.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                                }else{
                                                    flushingOkFragment.flushingok_name.setText("");
                                                }
                                            }
                                        },300);
                                    }

                                    if(noOfPara==2)
                                    {
                                        if(dataArray[3].equals("1") || dataArray[3].equals("2") || dataArray[3].equals("3"))
                                        {
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    flushingOkFragment.textflushingcycleround.setText(dataArray[3]);
                                                    flushingOkFragment.textflushingcycle3.setText(dataArray[4].substring(0, dataArray[4].length() - 1));
                                                    flushingOkFragment.flushingok_name.setText("");
                                                }
                                            },300);
                                        }
                                        else
                                        {
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    flushingOkFragment.rpmvaluefctext.setText(dataArray[3]);
                                                    flushingOkFragment.otvaluefctext.setText(dataArray[4].substring(0, dataArray[4].length() - 1));
                                                    flushingOkFragment.flushingok_name.setText("");
                                                }
                                            },300);
                                        }
                                    }
                                    break;
                                case "417":
                                    if(noOfPara == 1)
                                    {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(!dataArray[3].contains("NULL") && !dataArray[3].contains("null")) {
                                                    flushingOkFragment.flushingok_name.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                                }else{
                                                    flushingOkFragment.flushingok_name.setText("");
                                                }
                                            }
                                        },300);
                                    }
                                    if(noOfPara == 2)
                                    {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (dataArray[4].equals("1#") || dataArray[4].equals("2#") || dataArray[4].equals("3#")) {
                                                    flushingOkFragment.textflushingcycle3.setText(dataArray[3]);
                                                    flushingOkFragment.textflushingcycleround.setText(dataArray[4].substring(0, dataArray[4].length() - 1));
                                                } else {
                                                    flushingOkFragment.rpmvaluefctext.setText(dataArray[3]);
                                                    flushingOkFragment.otvaluefctext.setText(dataArray[4].substring(0, dataArray[4].length() - 1));
                                                }
                                                flushingOkFragment.flushingok_name.setText("");
                                            }
                                        },300);
                                    }
                                    break;
                            }
                        }
                        catch (Exception e){
                            Log.e(TAG,e.getMessage());
                        }
                        break;

                    case "228" : //  RPM_Incorrect Smoke
                        if(rpm_incorrect==null)
                            rpm_incorrect = new RPM_Incorrect();
                        replaceFargment(rpm_incorrect);

                        try {
                            switch (funcName) {
                                case "416":
                                    if (noOfPara == 1) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rpm_incorrect.rpmincorrect_name.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                            }
                                        },300);
                                    }
                                    break;
                            }
                        }
                        catch (Exception e){
                            Log.e(TAG,e.getMessage());
                        }
                        break;

                    case "229": // Flushing Done Smoke
                        if (flushingDoneFragment==null)
                            flushingDoneFragment = new FlushingDoneFragment();
                        replaceFargment(flushingDoneFragment);
                        break;

                    case "230": // Flushing_Fail Smoke
                        if (flushing_fail==null)
                            flushing_fail = new Flushing_Fail();
                        replaceFargment(flushing_fail);
                        break;

                    case "231": // Free_Acc Start Smoke
                        if (free_acc_start==null)
                            free_acc_start = new Free_Acc_Start();
                        replaceFargment(free_acc_start);
                        break;

                    case "232": // Free_Acc Test Smoke
                        if (free_acc_test==null)
                            free_acc_test = new Free_Acc_Test();
                        replaceFargment(free_acc_test);
                        break;

                    case "233": // Free_Acc Cycle Smoke
                        if (free_acc_cycle==null)
                            free_acc_cycle= new Free_Acc_Cycle();
                        replaceFargment(free_acc_cycle);

                        try {
                            switch (funcName) {
                                case "417":
                                    if(noOfPara == 0){
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                free_acc_cycle.textacccyclename.setText("");
                                                free_acc_cycle.rpmrange_acc_cycle2.setText("");
                                                free_acc_cycle.rpmrange_acc_cycle4.setText("");
                                                free_acc_cycle.rpmvalue_acc_cycle.setText("");
                                                free_acc_cycle.hsuvalue_acc_cycle.setText("");
                                                free_acc_cycle.otvalue_acc_cycle.setText("");
                                                free_acc_cycle.kvalue_acc_cycle.setText("");
                                                free_acc_cycle.textacccycle3.setText("");
                                            }
                                        },300);
                                    }

                                    if (noOfPara == 1) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(!dataArray[3].contains("NULL")) {
                                                    free_acc_cycle.textacccycle3.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                                }
                                            }
                                        },300);

                                    }
                                    if (noOfPara == 2) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (dataArray[3].equals("1") || dataArray[3].equals("2") || dataArray[3].equals("3")) {
                                                    free_acc_cycle.textacccyclename.setText(dataArray[3]);
                                                    if(!dataArray[4].contains("NULL")) {
                                                        free_acc_cycle.textacccycle3.setText(dataArray[4].substring(0, dataArray[4].length() - 1));
                                                    }
                                                } else {
                                                    free_acc_cycle.rpmrange_acc_cycle2.setText(dataArray[3]);
                                                    free_acc_cycle.rpmrange_acc_cycle4.setText(dataArray[4].substring(0, dataArray[4].length() - 1));
                                                }
                                            }
                                        },300);
                                    }
                                    if (noOfPara == 4) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (dataArray[3].equals("1") || dataArray[3].equals("2") || dataArray[3].equals("3")) {
                                                    free_acc_cycle.textacccyclename.setText(dataArray[3]);
                                                    free_acc_cycle.textacccycle3.setText(dataArray[4]);
                                                    free_acc_cycle.rpmrange_acc_cycle2.setText(dataArray[5]);
                                                    free_acc_cycle.rpmrange_acc_cycle4.setText(dataArray[6].substring(0, dataArray[6].length() - 1));
                                                } else {
                                                    free_acc_cycle.hsuvalue_acc_cycle.setText(dataArray[3]);
                                                    free_acc_cycle.kvalue_acc_cycle.setText(dataArray[4]);
                                                    free_acc_cycle.rpmvalue_acc_cycle.setText(dataArray[5]);
                                                    free_acc_cycle.otvalue_acc_cycle.setText(dataArray[6].substring(0, dataArray[6].length() - 1));
                                                }
                                            }
                                        },300);
                                    }
                                    break;
                            }
                        }
                        catch (Exception e){
                            Log.e(TAG,e.getMessage());
                        }
                        break;

                    case "234" : //  RPM_Not Range Smoke
                        if(rpm_notRange==null)
                            rpm_notRange = new RPM_NotRange();
                        replaceFargment(rpm_notRange);
                        try {
                            switch (funcName) {
                                case "417":
                                    if(noOfPara == 0)
                                    {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rpm_notRange.rpmnotrange_name.setText("");
                                                rpm_notRange.rpmnotrangetext3.setText("");
                                                rpm_notRange.rpmnotrangetext5.setText("");
                                            }
                                        },300);
                                    }
                                    if (noOfPara == 1) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(!dataArray[3].contains("NULL") && !dataArray[3].contains("null")) {
                                                    rpm_notRange.rpmnotrange_name.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                                } else {
                                                    rpm_notRange.rpmnotrange_name.setText("");
                                                }
                                            }
                                        },300);
                                    }
                                    if (noOfPara == 2) {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rpm_notRange.rpmnotrangetext3.setText(dataArray[3]);
                                                rpm_notRange.rpmnotrangetext5.setText(dataArray[4].substring(0, dataArray[4].length() - 1));
                                                rpm_notRange.rpmnotrange_name.setText("");
                                            }
                                        },300);
                                }
                                    break;
                            }
                        }
                        catch (Exception e){
                            Log.e(TAG,e.getMessage());
                        }
                        break;

                    case "235": // Free Acc Done
                        if(free_acc_done==null)
                            free_acc_done = new Free_Acc_Done();
                        replaceFargment(free_acc_done);
                        break;

                    case "236": // Print_result Smoke
                        if(printResult_Fragment==null)
                            printResult_Fragment = new PrintResultFragment();
                        replaceFargment(printResult_Fragment);

                        case "400":
                            if(noOfPara == 0) {
//                                printResult_Fragment.printresulttext1.setText("");
                            }

                            if(noOfPara == 1) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        printResult_Fragment.printresulttext1.setText(dataArray[3].substring(0, dataArray[3].length() - 1).toString());
                                    }
                                },300);
                            }
                            break;

                        case "417":
                            if(noOfPara == 0) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        printResult_Fragment.printresulttext1.setText("");
                                    }
                                },300);
                            }
                            if(noOfPara == 1)
                            {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast(" " + dataArray[3].length());
                                        toast(" " + dataArray[3]);
                                        int len = dataArray[3].length();
                                        if (dataArray[3].contains("Sending")) {
                                            toast("" + dataArray[3]);
                                            printResult_Fragment.printresulttext1.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                        }
                                        if (dataArray[3].contains("Printing")) {
                                            toast("" + dataArray[3]);
                                            printResult_Fragment.printresulttext1.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                        }
                                        if (dataArray[3].contains("Printer")) {
                                            toast("" + dataArray[3]);
                                            printResult_Fragment.printresulttext2.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                        }
                                    }
                                },300);
                            }
                            break;

                    case "237": // Cali_Done
                        if(calibrationPassFragment==null)
                            calibrationPassFragment = new CalibrationPassFragment();
                        replaceFargment(calibrationPassFragment);
                        break;

                    case "238": // Calibrating Percent Count Smoke
                        if (calibrationFragment==null)
                            calibrationFragment = new CalibrationFragment();
                        replaceFargment(calibrationFragment);

                        if ( funcName.equals("414") ){
                            if (noOfPara == 1) {
                                String prValue3 = (dataArray[3].split("#")[0]).toString();
                                if(prValue3.contains("Smoke"))
                                {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            calibrationFragment.cal_text11.setText(dataArray[3].split("#")[0]);
                                        }
                                    },300);
                                }
                                else
                                {
                                    final int value2 = Integer.parseInt(prValue3);
                                    try {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                calibrationFragment.progressBarCalibration.setProgress(Integer.parseInt(prValue3));
                                            }
                                        },300);
                                    } catch (Exception e) {
                                        Log.e(TAG, e.getMessage());
                                    }
                                }
                            }
                        }
                        break;

                    case "239": // Cali_Pass
                        if(calibrationPassFragment==null)
                            calibrationPassFragment = new CalibrationPassFragment();
                        replaceFargment(calibrationPassFragment);
                        break;

                    case "240": // Cali_Fail_INS
                        if(calibrationIns==null)
                            calibrationIns = new CalibrationIns();
                        replaceFargment(calibrationIns);
                        break;

                    case "241": // Cali_Fail
                        if(calibrationFailFragment==null)
                            calibrationFailFragment = new CalibrationFailFragment();
                        replaceFargment(calibrationFailFragment);
                        break;

                    case "242": // Run Flush Cycle
                        if(run_flush_cycle==null)
                            run_flush_cycle = new Run_Flush_Cycle();
                        replaceFargment(run_flush_cycle);

                        if(noOfPara==1)
                        {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (dataArray[3].contains("Sensor")) {
                                        run_flush_cycle.runflushcycle_text.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                    } else if(!(dataArray[3].contains("NULL"))){
                                        run_flush_cycle.runflushcycle_rpmvalue.setText(dataArray[3].substring(0, dataArray[3].length() - 1));
                                    }
                                }
                            },300);
                        }
                        break;

                        // Page 243 is made but no need to call as per Firmware.
                        // Page 244 is made and pending to call.


                    case "245" :      //Warm up Screen
                        if (warmUpFragment==null) {
                            warmUpFragment = new WarmUpFragment();
                        }
                        replaceFargment(warmUpFragment);
                        if (noOfPara == 1){
                            String warmupValue =dataArray[3].split("#")[0];
                            final int value = Integer.parseInt(warmupValue);

                            try {
                                warmUpFragment.warmupProgressBar.setProgress(value);
                            }catch (Exception e){
//                                this error is caused because replacing fragment takes some time
//                                and we try to setProgress before it is created
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        warmUpFragment.warmupProgressBar.setProgress(value);
                                    }
                                },10);
                            }
                        }
                        else{
                            toast("Improper Warmup Value : "+noOfPara+" parameters");
                        }
                        break;

                    case "246" :      // Smoke_Warmup_Done warm up done screen
                        if (warmupDoneFragment == null)
                            warmupDoneFragment = new WarmupDoneFragment();
                        replaceFargment(warmupDoneFragment);
                        break;

                    case "247": // Print Copy Smoke
                        if(print_copy==null)
                            print_copy = new Print_Copy();
                        replaceFargment(print_copy);
                        break;

                    case "248": // Band Selection Smoke
                        if(band_selection==null)
                            band_selection= new Band_Selection();
                        replaceFargment(band_selection);
                        break;

                        //SOURAB BISWAS

                    case "249": // Band Selection Smoke
                        if(default_cell==null)
                            default_cell= new DefaultCellFragment();
                        replaceFargment(default_cell);
                        break;

                    // *****************************************************************

                    //                Page 248 is the Last page to call

                    // *****************************************************************

                    case "138": // G_O2_Fail
                        if(o2FailFragment==null)
                            o2FailFragment = new O2FailFragment();
                        replaceFargment(o2FailFragment);
                        break;

                    case "139": // G_O2_Pass
                        if(o2PassFragment==null)
                            o2PassFragment = new O2PassFragment();
                        replaceFargment(o2PassFragment);
                        break;

                    case "140": // Print_result
//                        if(printResultFragment==null)
//                            printResultFragment = new PrintResultFragment();
//                        replaceFargment(printResultFragment);
//                        break;
                        break;
                    case "141": // Connecting_RPM
                        if (connectingRpmFragment == null) {
                            connectingRpmFragment = new ConnectingRpmFragment();
                        }
                        replaceFargment(connectingRpmFragment);

                        if ( funcName.equals("305") ){
                            if (noOfPara == 1) {
                                String prValue = dataArray[3].split("#")[0];
                                int value = Integer.parseInt(prValue);
                                try {
//                                    zeroingFragment.progressBarZeroing.setProgress(value);
                                } catch (Exception e) {
                                    Log.e(TAG, e.getMessage());
                                }
                            }
                        }
                        break;

                    case "142": // RPM_Pass
                        if (rpmPassFragment==null)
                            rpmPassFragment = new RpmPassFragment();
                        replaceFargment(rpmPassFragment);
                        break;

                    case "143": // RPM_Fail
                        if (rpmFailFragment==null)
                            rpmFailFragment = new RpmFailFragment();
                        replaceFargment(rpmFailFragment);
                        break;

                    case "Smoke_meter_R":
                        if(smokeMeterR==null)
                            smokeMeterR = new SmokeMeterR();
                        replaceFargment(smokeMeterR);
                        break;

                    case "Flushing":
                        if (flushingFragment==null)
                            flushingFragment = new FlushingFragment();
                        replaceFargment(flushingFragment);
                        break;

                    case "Flushing_Done":
                        if(flushingDoneFragment==null)
                            flushHCDoneFragment= new FlushHCDoneFragment();
                        replaceFargment(flushingDoneFragment);
                        break;
                }
            }
        }
    }

    public static void incorrectPIN(){
        Log.e(TAG,"Incrrect Pin Funciton");
        if(incorrectPinFragment==null)
            incorrectPinFragment= new IncorrectPinFragment();
        replaceFargment(incorrectPinFragment);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(servicePinFragment==null)
                    servicePinFragment = new ServicePinFragment();
                replaceFargment(servicePinFragment);
            }
        },1000);

    }

    public static void replaceFargment(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_linear_layout,fragment);
        fragmentTransaction.commit();
    }



    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(deviceBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(deviceBroadcastReceiver,bleIntentFilters());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bleService.disconnect();
        if(deviceBroadcastReceiver.isOrderedBroadcast()) {
            unregisterReceiver(deviceBroadcastReceiver);
        }
        unbindService(serviceConnection);
    }
}