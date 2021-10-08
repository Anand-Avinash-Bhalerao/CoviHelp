package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Scanner;
    Button pdf;
    RelativeLayout scan;
    RelativeLayout guidelines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try
//        {
//            this.getSupportActionBar().hide();
//        }
//        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);
    }

    public void openActivity(View v){
        Intent intent = new Intent(this,BluetoothConfirm.class);
        startActivity(intent);
    }
    public void openHospital(View v){
        Intent intent = new Intent(this,covidHospitals.class);
        startActivity(intent);
    }

    public void openUrl(View view) {
        Uri uri = Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/prevention-H.pdf"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void Toast(View view) {
        Toast.makeText(this, "AHHHHHHH Hand wash karo!!!", Toast.LENGTH_SHORT).show();
    }
    public void Toast1(View view) {
        Toast.makeText(this, "Tum logo k part se replace. ye temp hai", Toast.LENGTH_SHORT).show();
    }
    public void openCases(View view) {
        Uri uri = Uri.parse("https://www.covid19india.org/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void hospitals(View view) {
        Toast1(view);
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

    public void coivdorg(View view) {
        Intent intent = new Intent(this,casesorg.class);
        startActivity(intent);
    }


}