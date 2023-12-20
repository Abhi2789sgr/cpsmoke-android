package com.example.cp100;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class DevicesActivity extends AppCompatActivity {
    private final int PERMISSION_REQUEST_CODE = 11;
    private final int REQUEST_PERMISSION_SETTING = 12;
    private ListView pairedListView , availableListView;
    private TextView paired_textView, available_textView;
    private ArrayAdapter pairedListAdapter, availableListAdapter;
    private Button scanButton;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Request for no title of the app on screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // For using full screen of the android phone...
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hide Action bar of the screen
        getSupportActionBar().hide();
        setContentView(R.layout.activity_devices);

        getViewIds();

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        checkBluetooth();

        enableBluetooth();

        pairedListAdapter = new ArrayAdapter(this,R.layout.device_list_item);
        availableListAdapter = new ArrayAdapter(this,R.layout.device_list_item);

        showPairedDevices();

        availableListView.setAdapter(availableListAdapter);
        scanDevices();
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paired_textView.setVisibility(View.VISIBLE);
                available_textView.setVisibility(View.VISIBLE);
                pairedListView.setVisibility(View.VISIBLE);
                availableListView.setVisibility(View.VISIBLE);
                if (availableListAdapter.getCount()>0) {
                    availableListAdapter.clear();
                }
                scanDevices();
            }
        });
        availableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (bluetoothAdapter.isDiscovering()) {
                    bluetoothAdapter.cancelDiscovery();
                }
                String deviceInfo = ((TextView) view).getText().toString();
                String address = deviceInfo.substring(deviceInfo.length()-17);

                Intent connectIntent = new Intent(DevicesActivity.this,BaseActivity.class);
                connectIntent.putExtra("Address",address);
                startActivity(connectIntent);
            }
        });

        IntentFilter deviceFoundFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver,deviceFoundFilter);
        IntentFilter discoveryFinishFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(broadcastReceiver,discoveryFinishFilter);


    }

    public void getViewIds(){
        paired_textView = findViewById(R.id.text_paired_devices);
        available_textView = findViewById(R.id.text_available_devices);
        pairedListView = findViewById(R.id.paired_listview);
        availableListView = findViewById(R.id.available_listview);
        scanButton = findViewById(R.id.scan_button);
    }

    public void checkBluetooth(){
        if (bluetoothAdapter == null){
            toast("Bluetooth Not Found");
            finish();
        }
    }

    public void enableBluetooth(){
        if (!bluetoothAdapter.isEnabled()){
            Intent startBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(startBluetoothIntent);
        }
        else{
            requestLocationPermission();
        }
    }

    public void requestLocationPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DevicesActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //permission is granted
            }
            else{
                final boolean requestingallowed = shouldShowRequestPermissionRationale(permissions[0]);

                new AlertDialog.Builder(this)
                        .setMessage("GPS Permissions Required For This App To Run")
                        .setCancelable(false)
                        .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (requestingallowed) {
                                    requestLocationPermission();
                                }else{
                                    toast("Requesting permission declined");

                                    //Redirect to Android app settings
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                                    finish();
                                }
                            }
                        })
                        .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            }
        }

    }

    public void showPairedDevices(){

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.toArray().length>0) {
            for (BluetoothDevice device : pairedDevices) {
                pairedListAdapter.add(device.getName() + " = " + device.getAddress());
            }
        }else
        {
            pairedListAdapter.add("No Paired Devices Found");
        }

        pairedListView.setAdapter(pairedListAdapter);
    }

    public void toast(String msg){
       // Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    public void scanDevices(){
        if(pairedListAdapter.isEmpty()){
            showPairedDevices();
        }
        if (bluetoothAdapter.isDiscovering()){
            bluetoothAdapter.cancelDiscovery();
        }
        bluetoothAdapter.startDiscovery();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                availableListAdapter.add(device.getName()+" : "+device.getAddress());
                if (availableListAdapter.getCount()>0){

                }
                else{
                    toast("No devices Found");
                }
            }
            else if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
                toast("Discovery Finished");
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bluetoothAdapter.isEnabled()) {
            unregisterReceiver(broadcastReceiver);
        }

        if (bluetoothAdapter != null){
            bluetoothAdapter.cancelDiscovery();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}