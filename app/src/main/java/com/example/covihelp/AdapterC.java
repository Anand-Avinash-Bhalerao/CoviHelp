package com.example.covihelp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterC extends ArrayAdapter<CovidCityStats> {
    public AdapterC(@NonNull Context context, @NonNull List<CovidCityStats> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        try {
            CovidCityStats covidCityStat = getItem(position);
            Log.d("adapterW", "convertView k pehle");
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cases_list_item, parent, false);
            Log.d("adapterW", "convertView k niche");
            TextView confirmed = convertView.findViewById(R.id.confirmed);
            TextView city = convertView.findViewById(R.id.city);
            TextView active = convertView.findViewById(R.id.active);
            TextView recovered = convertView.findViewById(R.id.recovered);
            TextView ded = convertView.findViewById(R.id.ded);
            city.setText(covidCityStat.getCity());
            active.setText(String.valueOf(covidCityStat.getActive()));
            confirmed.setText(String.valueOf(covidCityStat.getConfirmed()));
            recovered.setText(String.valueOf(covidCityStat.getRecovered()));
            ded.setText(String.valueOf(covidCityStat.getDed()));
            Log.d("adapterW", city.getText() + " " + confirmed.getText());
        }catch (Exception e) {
//            Toast.makeText(getContext(), "Haa ye adapter clas me hai problem", Toast.LENGTH_SHORT).show();
            Log.e("adapterW", "haa ye adapter class me hai probelm");
        }
        return convertView;
    }
}
