package com.example.covihelp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class HospitalLoader extends AsyncTaskLoader<List<HospitalStats>> {

    public HospitalLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<HospitalStats> loadInBackground() {
        return null;
    }
}
