package com.example.covihelp;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;


public class ScanPage extends AppCompatActivity {
    public static final String EXTRA_NAME=" com.example.covihelp.extra.name";
    Button scanButton;
    ListView scanListView;
    RippleBackground findDe;
    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    BluetoothAdapter myAdapter= BluetoothAdapter.getDefaultAdapter();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize each element
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_page);
        scanButton = findViewById(R.id.button);
        findDe = findViewById(R.id.findDevices);
        scanListView = findViewById(R.id.ListView);

        //permission check
        int permissionCheck=this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
        permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
        if (permissionCheck != 0)
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},1001);


        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(myReceiver, intentFilter);
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArrayList);
        scanListView.setAdapter(arrayAdapter);




//        scanButton.setOnClickListener(v -> {
                //start animation
                findDe.startRippleAnimation();
                //request to be discoverable for 120 sec
                startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE), 1);
                myAdapter.startDiscovery();
                Toast.makeText(this, "Please wait for 12 Seconds", Toast.LENGTH_SHORT).show();
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    findDe.stopRippleAnimation();
                    myAdapter.cancelDiscovery();
                    int[] array=new int[stringArrayList.size()];

                    for (int i = 0; i < stringArrayList.size(); i++) {
                        array[i] = Integer.parseInt(stringArrayList.get(i));
                    }

                    if (array.length != 0) {
                       int min = array[0];
                       for (int value : array) {
                            if (value >= min)
                                min = value;
                       }
                       if(min>-60) {
                           Intent intent = new Intent(this, notsafe.class);
                           startActivity(intent);
                           Toast.makeText(this, ""+min, Toast.LENGTH_SHORT).show();
                       }
                       else {
                           Intent intent = new Intent(this, safe.class);
                           startActivity(intent);
                           Toast.makeText(this, ""+min, Toast.LENGTH_SHORT).show();
                       }
                    } else {
                        Intent intent = new Intent(this, safe.class);
                        Toast.makeText(this, "empty- 0", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }, 12000);
                stringArrayList.clear();
//        });
    }

    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                stringArrayList.add(String.valueOf(rssi));
                arrayAdapter.notifyDataSetChanged();
            }
        }
    };
}