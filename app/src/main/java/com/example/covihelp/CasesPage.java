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
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CasesPage extends AppCompatActivity {
    Button covidOrg;
    TextView recycler_empty;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    public List<CovidCityStats> covidCityStats;

    public SearchView searchView;
    public boolean added = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases_page);
        covidOrg = findViewById(R.id.covid19india);

//        Log.d(LOG_TAG, "at the start the covid stats has " + covidCityStats.size());

        recycler_empty = findViewById(R.id.recycler_empty);
        recyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progress_circular);
        searchView = findViewById(R.id.search);

        try {
//            adapterC = new AdapterC(this,  covidCityStats);
            if (isOnline())
                fetchData();
            else {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                recycler_empty.setVisibility(View.VISIBLE);
                recycler_empty.setText("Not connected to the internet.\nPlease try again");
            }


        } catch (Exception e) {
            Log.e("Crash", "onCreate me hai problem");
        }
        covidOrg.setOnClickListener(v -> covidSiteRedirect());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<CovidCityStats> temp = new ArrayList<>();
                for (int i = 0; i < covidCityStats.size(); i++) {
                    if (covidCityStats.get(i).getCity().toLowerCase().contains(newText.toLowerCase())) {
                        temp.add(covidCityStats.get(i));
                    }
                }
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(CasesPage.this, temp);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(CasesPage.this));
                return false;
            }
        });
    }

    public void covidSiteRedirect() {
        String url = "https://www.incovid19.org/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void fetchData() {
        String url = "https://data.incovid19.org/v4/min/data.min.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, baseJsonResponse -> {
            try {
                Log.d("WITH_VOLLEY", "Base length is: " + baseJsonResponse.length());
                JSONObject maharashtra = baseJsonResponse.optJSONObject("MH");
                assert maharashtra != null;
                Log.d("WITH_VOLLEY", "Maharashtra length is: " + maharashtra.length());
                JSONObject disData = maharashtra.optJSONObject("districts");
                assert disData != null;
                Log.d("WITH_VOLLEY", "District length is: " + disData.length());
//                JSONObject pune = disData.optJSONObject("Pune");
                List<CovidCityStats> inside = new ArrayList<>();
                for (int i = 0; i < Objects.requireNonNull(disData.names()).length(); i++) {
                    CovidCityStats temp = new CovidCityStats();
                    String city = Objects.requireNonNull(disData.names()).optString(i);
                    JSONObject current = disData.getJSONObject(city);
                    JSONObject total = current.getJSONObject("total");
                    int confirmed = total.optInt("confirmed");
                    int ded = total.optInt("deceased");
                    int back;
                    int titColor = R.color.black;
                    if (confirmed > 500000) {
                        back = R.color.maroon;
                        titColor = R.color.red;
                    } else if (confirmed > 100000) {
                        back = R.color.orange;
                    } else if (confirmed > 50000) {
                        back = R.color.peach_puff;
                    } else back = R.color._light_green;

//                int back = R.color.red;
                    int recovered = total.optInt("recovered");
                    int active = confirmed - ded - recovered;
                    temp.setCity(city);
                    temp.setConfirmed(confirmed);
                    temp.setActive(active);
                    temp.setRecovered(recovered);
                    temp.setDed(ded);
                    temp.setBackTint(back);
                    temp.setTitleColour(titColor);
                    inside.add(temp);
                    Log.d("WITH_VOLLEY", "City = " + city + ", Confirmed = " + confirmed + ",Active  = " + active + ",Recovered  = " + recovered + ", ded = " + ded);
                }
                if (!added) {
                    covidCityStats = new ArrayList<>(inside);
                    added = true;
                }

                progressBar.setVisibility(View.GONE);
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, covidCityStats);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                if (inside.size() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    recycler_empty.setVisibility(View.VISIBLE);
                    recycler_empty.setText(R.string.no_data_found);
                }
            } catch (Exception ignored) {

            }
        }, error -> Log.e("LOG", error.toString()));
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}