package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Scanner;
    Button pdf;
    LinearLayout scan;
    LinearLayout gl;
    LinearLayout hos;
    LinearLayout cases;
    LinearLayout vac;
    LinearLayout updates;
    ImageView covid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        scan = findViewById(R.id.scan);
        gl = findViewById(R.id.gl);
        hos = findViewById(R.id.hos);
        cases = findViewById(R.id.cases);
        vac = findViewById(R.id.vacc);
        updates = findViewById(R.id.updates);
        covid = findViewById(R.id.imageView);
        scan.setOnClickListener(this::openActivity);
        gl.setOnClickListener(this::openGD);
        hos.setOnClickListener(this::openHospital);
        cases.setOnClickListener(this::covidOrg);
        vac.setOnClickListener(this::vaccine);
        updates.setOnClickListener(this::updates);
        covid.setOnClickListener(this::handWash);
    }

    public void openActivity(View v){
        Intent intent = new Intent(this,BluetoothConfirm.class);
        startActivity(intent);
    }
    public void openHospital(View v){
        Intent intent = new Intent(this,covidHospitals.class);
        startActivity(intent);
    }

    public void openGD(View view) {
        Uri uri = Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/prevention-H.pdf"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void handWash(View view) {
        Toast.makeText(this, "AHHHHHHH Hand wash karo!!!", Toast.LENGTH_SHORT).show();
    }
    public void hospitals(View view) {
        Uri uri = Uri.parse("https://www.cghspune.gov.in/images/stories/cghsimages/pdfs/hospitalcontacts.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void updates(View view) {
        Uri uri = Uri.parse("https://pune.gov.in/corona-virus-updates/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void vaccine(View view) {
        Uri uri = Uri.parse("https://www.cowin.gov.in/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void covidOrg(View view) {
        Intent intent = new Intent(this, CasesPage.class);
        startActivity(intent);
    }


}