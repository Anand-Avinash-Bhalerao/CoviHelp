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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    private Context context;
    private List<HospitalStats> list;
    private OnNoteClickListener onNoteClickListener;
    public HospitalAdapter(Context context, List<HospitalStats> list,OnNoteClickListener onNoteClickListener) {
        this.context = context;
        this.list = list;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_list_item,parent,false);
        return new ViewHolder(view,onNoteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, int position) {
        HospitalStats hospitalStats = list.get(position);

        TextView name = holder.name;
        TextView address = holder.address;
        name.setText(hospitalStats.getHospitalName());
        address.setText(hospitalStats.getHospitalAddress());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView address;
        public ConstraintLayout constraintLayout;
        OnNoteClickListener onNoteClickListener;
        public ViewHolder(View itemView,OnNoteClickListener onNoteClickListener){
            super(itemView);
            this.onNoteClickListener = onNoteClickListener;
            name = itemView.findViewById(R.id.hospital_name);
            address = itemView.findViewById(R.id.hospital_address);
            constraintLayout = itemView.findViewById(R.id.back);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            onNoteClickListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteClickListener{
        void onNoteClick(int pos);
    }
}



