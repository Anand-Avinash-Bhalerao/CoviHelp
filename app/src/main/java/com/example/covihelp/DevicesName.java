package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DevicesName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_name);
        Intent intent = getIntent();
        ArrayList<String> list = intent.getStringArrayListExtra("LIST");
        TextView names = findViewById(R.id.names);
        StringBuilder value = new StringBuilder();
        for(int i = 0;i<list.size();i++){
            value.append(list.get(i)+"\n");
        }
        names.setText(value.toString());
    }
}