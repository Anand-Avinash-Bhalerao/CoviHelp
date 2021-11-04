package com.example.covihelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.*;
//yes it works on the browser
public class BluetoothConfirm extends AppCompatActivity {
    BluetoothAdapter myAdapter=BluetoothAdapter.getDefaultAdapter();
    TextView stateText;
    TextView stateText1;
    Button onOff;
    Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_confirm);
        stateText = findViewById(R.id.textView2);
        stateText1 = findViewById(R.id.textView3);
        onOff = findViewById(R.id.oOf);
        settings = findViewById(R.id.setngs);

        if(myAdapter.isEnabled()){
            stateText.setTextColor(Color.GREEN);
            stateText.setText(R.string.on);
            stateText1.setText("Press to disable Bluetooth");
            onOff.setText(R.string.sOff);
        }
        else{
            stateText.setTextColor(Color.RED);
            stateText.setText(R.string.off);
            onOff.setText(R.string.sOn);
            stateText1.setText("Press to enable Bluetooth");
        }
        settings.setOnClickListener(v -> {

            final Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.android.settings",
                    "com.android.settings.bluetooth.BluetoothSettings");
            intent.setComponent(cn);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        });


        onOff.setOnClickListener(v -> {
            if(myAdapter.isEnabled()){
                myAdapter.disable();
                onOff.setText(R.string.sOn);
                stateText.setText(R.string.off);
                stateText1.setText("Press to enable Bluetooth");
                stateText.setTextColor(Color.RED);
            }
            else{
                Intent enable_Bluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enable_Bluetooth, 1);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if(myAdapter.isEnabled()){
                        onOff.setText(R.string.sOff);
                        stateText.setText(R.string.on);
                        stateText1.setText("Press to disable Bluetooth");
                        stateText.setTextColor(Color.GREEN);
                    }
                    else{
                        Toast.makeText(this, "Please Allow Bluetooth", Toast.LENGTH_SHORT).show();
                    }
                }, 4000);


                    

            }

        });
    }
    public void openActivity1(View v) {
        if(myAdapter.isEnabled()) {
            Intent intent = new Intent(this, ScanPage.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please switch ON bluetooth", Toast.LENGTH_SHORT).show();
        }
    }
}