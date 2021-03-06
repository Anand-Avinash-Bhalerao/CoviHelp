package com.example.covihelp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private List<CovidCityStats> list;

    public RecyclerAdapter(Context context, List<CovidCityStats> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cases_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        CovidCityStats covidCityStats = list.get(position);
        TextView city = holder.city;
        TextView confirmed = holder.confirmed;
        TextView active = holder.active;
        TextView recover = holder.recover;
        TextView ded = holder.ded;
        LinearLayout linearLayout = holder.linearLayout;
        city.setText(covidCityStats.getCity());
        city.setTextColor(ContextCompat.getColor(context,covidCityStats.getTitleColour()));
        confirmed.setText(String.valueOf(covidCityStats.getConfirmed()));
        active.setText(String.valueOf(covidCityStats.getActive()));
        recover.setText(String.valueOf(covidCityStats.getRecovered()));
        ded.setText(String.valueOf(covidCityStats.getDed()));
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context,covidCityStats.getBackTint())));
        Log.d("ColorKa", "City- " + covidCityStats.getCity() + " and the color is " + covidCityStats.getBackTint()+" and red is "+R.color.red );
//        linearLayout.setBackgroundResource(covidCityStats.getColor());
//        linearLayout.setBackgroundTintList(ColorStateList.valueOf(covidCityStats.getBackTint()));
//        linearLayout.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context,covidCityStats.getBackTint())));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView city;
        public TextView confirmed;
        public TextView active;
        public TextView recover;
        public TextView ded;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city);
            confirmed = itemView.findViewById(R.id.confirmed);
            active = itemView.findViewById(R.id.active);
            recover = itemView.findViewById(R.id.recovered);
            ded = itemView.findViewById(R.id.ded);
            linearLayout = itemView.findViewById(R.id.back);
        }
    }
}

