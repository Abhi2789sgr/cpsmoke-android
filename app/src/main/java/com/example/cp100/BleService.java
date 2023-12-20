package com.example.cp100;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.UUID;

import static android.bluetooth.BluetoothGatt.GATT_SUCCESS;

public class BleService extends Service {

    public static final String ACTION_STATE_CONNECTING = "ACTION_STATE_CONNECTING";
    public static final String ACTION_STATE_CONNECTED = "ACTION_STATE_CONNECTED";
    public static final String ACTION_STATE_DISCONNECTING = "ACTION_STATE_DISCONNECTING";
    public static final String ACTION_STATE_DISCONNECTED = "ACTION_STATE_DISCONNECTED";
    public final static String ACTION_CONNECTION_STATE_CHANGE = "ACTION_CONNECTION_STATE_CHANGE";
    public final static String ACTION_SERVICE_DISCOVER = "ACTION_SERVICE_DISCOVER";
    public final static String ACTION_DATA_AVAILABLE = "ACTION_DATA_AVAILABLE";
    public static final String DATA = "TESTING_DATA";
    public static final String STRING_DATA = "STRING_DATA";
    public static final String BYTE_DATA = "BYTE_DATA";
    public static final String TAG = "cp100";
    private final static UUID UUID_HM_SERIAL_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    private final static UUID UUID_HM_SERIAL_CHAR    = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");
    private static final UUID CCC_DESCRIPTOR_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic bluetoothGattCharacteristic;
    private final IBinder binder = new LocalBinder();

    public boolean startThisService(){
        if (bluetoothManager == null){
            bluetoothManager =  (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            if (bluetoothManager == null){
                return false;
            }
        }
        bluetoothAdapter = bluetoothManager.getAdapter();
        return bluetoothAdapter != null;
    }

    public boolean connect(String address){
        Log.e(TAG,"Connect address "+address);
        if(bluetoothGatt !=null){
            Log.e(TAG,"Trying to already running service");
            return false;
        }
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
        bluetoothGatt = device.connectGatt(this, false,bluetoothGattCallback);
        return true;
    }

    public void disconnect(){
        if (bluetoothGatt==null) return;
        bluetoothGatt.close();
        bluetoothGatt = null;
    }

    private final BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            switch (newState){
                case BluetoothProfile.STATE_CONNECTING :
                    startBroadcast(ACTION_STATE_CONNECTING);
                    break;
                case BluetoothProfile.STATE_CONNECTED :
                    startBroadcast(ACTION_STATE_CONNECTED);
                    if(status == GATT_SUCCESS){
                        startBroadcast(DATA,"Gatt SUCCESS in Connection State");
                        bluetoothGatt.discoverServices();
                    }
                    break;
                case BluetoothProfile.STATE_DISCONNECTING :
                    startBroadcast(ACTION_STATE_DISCONNECTING);
                    break;
                case BluetoothProfile.STATE_DISCONNECTED :
                    startBroadcast(ACTION_STATE_DISCONNECTED);
                    break;
            }

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if(status == BluetoothGatt.GATT_SUCCESS){
                if (bluetoothGatt != null){
                    List<BluetoothGattService> gattServices = bluetoothGatt.getServices();
                    for (BluetoothGattService foundservice : gattServices){
                        if (UUID_HM_SERIAL_SERVICE.equals(foundservice.getUuid())) {
                            bluetoothGattCharacteristic = foundservice.getCharacteristic(UUID_HM_SERIAL_CHAR);

                            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(CCC_DESCRIPTOR_UUID);

                            int perm = bluetoothGattCharacteristic.getProperties();
                            if( (perm & BluetoothGattCharacteristic.PROPERTY_NOTIFY) >0){
                                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                                bluetoothGatt.writeDescriptor(descriptor);
                                Log.e(TAG,"ENABLE_NOTIFICATION_VALUE allowed");
                            }
                            else if( (perm & BluetoothGattCharacteristic.PROPERTY_INDICATE) >0){
                                descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                                bluetoothGatt.writeDescriptor(descriptor);
                                Log.e(TAG,"ENABLE_INDICATION_VALUE allowed");
                            }
                            else {
                                Log.e(TAG,"ERROR: BLE does not have notify or indicate property");
                            }

                            bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
                            bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic,true);
                        }
                    }
                }
            }
            else{
                startBroadcast(DATA,"Gatt FAIL in Service Discover");
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            startBroadcast(ACTION_DATA_AVAILABLE,characteristic);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
//            Log.e(TAG, "onCharacteristicChanged");
            startBroadcast(ACTION_DATA_AVAILABLE,characteristic);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
            if(descriptor.getUuid().equals(CCC_DESCRIPTOR_UUID)) {
                if (status != GATT_SUCCESS) {
                    Log.e(TAG, "ERROR: Write CCC_DESCRIPTOR_UUID failed");
                }
                if (status == GATT_SUCCESS) {
                    Log.e(TAG, "Write CCC_DESCRIPTOR_UUID success");
                }
            }
        }
    };

    public void writeBytes(byte[] msg){
        try {
            bluetoothGattCharacteristic.setValue(msg);
            boolean status = bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
            Log.e(TAG,status ? "Write Bytes Successfull" : "Write Bytes Fail");
        }
        catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
    public void write(String msg){
        try {
            bluetoothGattCharacteristic.setValue(msg);
            boolean status = bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
            Log.e(TAG,status ? "Write Successfull" : "Write Fail");
        }
        catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
    public void startBroadcast(String action){
        Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    public void startBroadcast(String action,String data){
        Intent intent = new Intent(action);
        intent.putExtra(STRING_DATA,data);
        sendBroadcast(intent);
    }

    public void startBroadcast(String action,BluetoothGattCharacteristic characteristic){
        Intent intent = new Intent(action);
        final byte[] data = characteristic.getValue();
        if (data !=null && data.length>0){
            intent.putExtra(STRING_DATA,new String(data));
        }
        sendBroadcast(intent);
    }

    public void startByteBroadcast(String action,BluetoothGattCharacteristic characteristic){
        Intent intent = new Intent(action);
        final byte[] data = characteristic.getValue();
        if (data !=null && data.length>0){
            intent.putExtra(BYTE_DATA,data);
        }
        sendBroadcast(intent);
    }


    public BluetoothGatt getBluetoothGatt() {
        return bluetoothGatt;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    public class LocalBinder extends Binder{
        public BleService getService(){
            return BleService.this;
        }
    }
}