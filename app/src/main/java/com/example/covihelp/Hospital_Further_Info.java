package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Hospital_Further_Info extends AppCompatActivity {
    public TextView name,address,phone,type;
    public TextView with,without,icu_with,icu_without;
    HospitalStats current;
    public boolean clickable = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_further_info);
        name = findViewById(R.id.hospital_name);
        address = findViewById(R.id.hospital_address);
        phone = findViewById(R.id.hospital_phone);
        type = findViewById(R.id.hospital_type);
        with = findViewById(R.id.with_oxy);
        without = findViewById(R.id.without_oxy);
        icu_with = findViewById(R.id.icu_with);
        icu_without = findViewById(R.id.icu_without);


        Intent intent = getIntent();
        current = (HospitalStats) intent.getSerializableExtra("LIST");
        name.setText(current.getHospitalName());
        address.setText(current.getHospitalAddress());
        phone.setText(String.valueOf(current.getHospitalPhone()));
        type.setText(current.getHospitalCategory());
        String withS = current.getAvailableBedsWithOxygen()+" / "+current.getTotalBedsWithOxygen();
        String withoutS = current.getAvailableBedsWithoutOxygen()+" / "+current.getTotalBedsWithoutOxygen();
        String with_icuS = current.getAvailableIcuBedsWithVentilator()+" / "+current.getTotalIcuBedsWithVentilator();
        String without_icuS = current.getAvailableIcuBedsWithoutVentilator()+" / "+current.getTotalIcuBedsWithoutVentilator();
        with.setText(withS);
        without.setText(withoutS);
        icu_with.setText(with_icuS);
        icu_without.setText(without_icuS);


        if(!current.getLocation().equals("null")){
            address.setTextColor(ContextCompat.getColor(this,R.color.cyan));
        }
        else{
            clickable = false;
        }
        address.setOnClickListener(view->{
            openLocation();
        });

    }

    public void openLocation(){
        if(clickable) {
            Uri uri = Uri.parse(current.getLocation());
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
        else{
            Toast.makeText(this, "No Map location Available", Toast.LENGTH_SHORT).show();
        }
    }
}