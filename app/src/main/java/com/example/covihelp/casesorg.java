package com.example.covihelp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class casesorg extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<CovidCityStats>> {
    Button covidOrg;
    //    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String LOG_TAG = "DebugK";
    public static final String covid_url = "https://data.covid19india.org/v4/min/data.min.json";
    public ArrayList<CovidCityStats> toDisplay;
    public List<CovidCityStats> covidCityStats = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casesorg);

        covidOrg = findViewById(R.id.covid19india);

        Log.d(LOG_TAG, "at the start the covid stats has " + covidCityStats.size());


        try {
//            adapterC = new AdapterC(this,  covidCityStats);
            getSupportLoaderManager().initLoader(1, null, this);

        } catch (Exception e) {
            Log.e("Crash", "oncreate me ho raha hai problem");
        }
        covidOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covidSiteRedirect();
            }
        });
    }

    public void covidSiteRedirect() {
        String url = "https://www.covid19india.org";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    @NonNull
    @Override
    public Loader<List<CovidCityStats>> onCreateLoader(int id, @Nullable Bundle args) {
        return new CovidAsyncTask(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<CovidCityStats>> loader, List<CovidCityStats> data) {
        Log.d("DebugK", "onLoadFinished k andar");
        RecyclerView recyclerView = findViewById(R.id.recycler);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, data);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<CovidCityStats>> loader) {

    }
}