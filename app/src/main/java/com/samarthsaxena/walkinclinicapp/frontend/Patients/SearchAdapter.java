package com.samarthsaxena.walkinclinicapp.frontend.Patients;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.samarthsaxena.walkinclinicapp.backend.models.Profile;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends ArrayAdapter<Profile> {

    private Context mContext;

    public SearchAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Profile> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
