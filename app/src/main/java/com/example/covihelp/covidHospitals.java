package com.example.covihelp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class covidHospitals extends AppCompatActivity implements HospitalAdapter.OnNoteClickListener {

    public List<HospitalStats> hospitalStats = new ArrayList<>();
    public List<HospitalStats> duringSearch = new ArrayList<>();
    public RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_hospitals);
        Button button = findViewById(R.id.button2);
        fetchData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://covidpune.com/");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        recyclerView = findViewById(R.id.hospital_recycler);
        SearchView searchView = findViewById(R.id.search_hospital);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<HospitalStats> temp = new ArrayList<>();
                for (int i = 0; i < hospitalStats.size(); i++) {
                    if (hospitalStats.get(i).getHospitalName().toLowerCase().contains(newText.toLowerCase())) {
                        temp.add(hospitalStats.get(i));
                    }
                }
                duringSearch = new ArrayList<>(temp);
                HospitalAdapter hospitalAdapter = new HospitalAdapter(covidHospitals.this,temp,covidHospitals.this);
                recyclerView.setAdapter(hospitalAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(covidHospitals.this));
                return false;
            }
        });


    }

    public void fetchData() {
        String url = "https://api.covidbedsindia.in/v1/storages/6089822703eef30c1cd05a6e/Pune";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, baseJsonResponse -> {
            for (int i = 0; i < baseJsonResponse.length(); i++) {
                JSONObject current = baseJsonResponse.optJSONObject(i);
                String district = current.optString("DISTRICT");
                String hospital_category = current.optString("HOSPITAL_CATEGORY");
                String hospital_name = current.optString("HOSPITAL_NAME");
                hospital_name = hospital_name.replaceAll("\\(.*\\)", "");
                String hospital_address = current.optString("HOSPITAL_ADDRESS");
                String hospital_phone = current.optString("CONTACT");
                Long last_updated_on = Long.parseLong(current.optString("LAST_UPDATED"));
                String officer_name = current.optString("OFFICER_NAME");
                String officer_designation = current.optString("OFFICER_DESIGNATION");
                String charges = current.optString("CHARGES");
                String locations = current.optString("LOCATION");

                int fee_regulated_beds = Integer.parseInt(current.optString("FEE_REGULATED_BEDS"));
                int total_beds_allocated_to_covid = Integer.parseInt(current.optString("TOTAL_BEDS_ALLOCATED_TO_COVID"));
                int total_beds_without_oxygen = Integer.parseInt(current.optString("TOTAL_BEDS_WITHOUT_OXYGEN"));
                int available_beds_without_oxygen = Integer.parseInt(current.optString("AVAILABLE_BEDS_WITHOUT_OXYGEN"));
                int total_beds_with_oxygen = Integer.parseInt(current.optString("TOTAL_BEDS_WITH_OXYGEN"));
                int available_beds_with_oxygen = Integer.parseInt(current.optString("AVAILABLE_BEDS_WITH_OXYGEN"));
                int total_icu_beds_without_ventilator = Integer.parseInt(current.optString("TOTAL_ICU_BEDS_WITHOUT_VENTILATOR"));
                int available_icu_beds_without_ventilator = Integer.parseInt(current.optString("AVAILABLE_ICU_BEDS_WITHOUT_VENTILATOR"));
                int total_icu_beds_with_ventilator = Integer.parseInt(current.optString("TOTAL_ICU_BEDS_WITH_VENTILATOR"));
                int available_icu_beds_with_ventilator = Integer.parseInt(current.optString("AVAILABLE_ICU_BEDS_WITH_VENTILATOR"));
                HospitalStats currentElement = new HospitalStats(district, hospital_category, hospital_name, hospital_address, hospital_phone, last_updated_on, officer_name, officer_designation, charges, fee_regulated_beds, total_beds_allocated_to_covid, total_beds_without_oxygen, available_beds_without_oxygen, total_beds_with_oxygen, available_beds_with_oxygen, total_icu_beds_without_ventilator, available_icu_beds_without_ventilator, total_icu_beds_with_ventilator, available_icu_beds_with_ventilator,locations,i);
                Log.d("HOSPITAL_INFO", currentElement.toString());
                hospitalStats.add(currentElement);
            }
            HospitalAdapter hospitalAdapter = new HospitalAdapter(this,hospitalStats,this);
            recyclerView.setAdapter(hospitalAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }, error -> Log.e("LOG", error.toString()));
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onNoteClick(int pos) {
        Intent intent = new Intent(this,Hospital_Further_Info.class);

        int i = pos;
        if(!duringSearch.isEmpty())
        i = duringSearch.get(pos).getIndex();
        intent.putExtra("LIST", (Serializable) hospitalStats.get(i));
//        Toast.makeText(this, "in current is "+pos+" but in list is "+i+" and the size is "+hospitalStats.size(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}