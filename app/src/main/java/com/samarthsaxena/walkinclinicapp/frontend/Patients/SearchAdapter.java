package com.samarthsaxena.walkinclinicapp.frontend.Patients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends ArrayAdapter<Profile> {

    private Context mContext;
    private int mRessource;
    private int day=0;

    public SearchAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Profile> objects) {
        super(context, resource, objects);
        mContext=context;
        mRessource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String N = getItem(position).getClinic();
        String A = getItem(position).getAddress();
        String SH = getItem(position).getWorkingTime().get(0).get(day);
        String EH = getItem(position).getWorkingTime().get(1).get(day);

        Profile profile = new Profile(getItem(position).getUser(), getItem(position).getAddress(),getItem(position).getPhoneNumber(),getItem(position).getClinic(),getItem(position).getInsuranceType(),getItem(position).getPaymentMethod());

        LayoutInflater inflator = LayoutInflater.from(mContext);
        convertView = inflator.inflate(mRessource, parent, false);

        TextView clinicName = (TextView) convertView.findViewById(R.id.CN);
        TextView clinicAdd = (TextView) convertView.findViewById(R.id.CA1);
        TextView clinicService = (TextView) convertView.findViewById(R.id.CS);

        TextView clinicOp = (TextView) convertView.findViewById(R.id.CO);
        TextView clinicT = (TextView) convertView.findViewById(R.id.WT);
        TextView clinicEn = (TextView) convertView.findViewById(R.id.CC);

        clinicName.setText(N);
        clinicAdd.setText(A);



        clinicOp.setText(SH);
        clinicEn.setText(EH);

        return convertView;
    }

    @Nullable
    @Override
    public Profile getItem(int position) {
        return super.getItem(position);
    }

    public void setDay(int i){
        this.day=i;
    }

}
