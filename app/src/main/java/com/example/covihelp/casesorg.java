package com.example.covihelp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class casesorg extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<CovidCityStats>> {
    Button covidOrg;
    //    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String LOG_TAG = "DebugK";
    public static final String covid_url = "https://data.covid19india.org/v4/min/data.min.json";
    public ArrayList<CovidCityStats> toDisplay;
    TextView recycler_empty;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    public List<CovidCityStats> covidCityStats = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casesorg);

        covidOrg = findViewById(R.id.covid19india);

        Log.d(LOG_TAG, "at the start the covid stats has " + covidCityStats.size());

        recycler_empty = findViewById(R.id.recycler_empty);
        recyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progress_circular);

        try {
//            adapterC = new AdapterC(this,  covidCityStats);
            if (isOnline())
                getSupportLoaderManager().initLoader(1, null, this);
            else {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                recycler_empty.setVisibility(View.VISIBLE);
                recycler_empty.setText("Not connected to the internet.\nPlease try again");
            }


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
        List<CovidCityStats> temp = new ArrayList<>();

        progressBar.setVisibility(View.GONE);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, data);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (data.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            recycler_empty.setVisibility(View.VISIBLE);
            recycler_empty.setText("No data was found!");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<CovidCityStats>> loader) {

    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}