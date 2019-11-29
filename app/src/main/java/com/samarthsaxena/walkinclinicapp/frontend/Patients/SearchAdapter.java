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
import com.samarthsaxena.walkinclinicapp.backend.models.UserService;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends ArrayAdapter<Profile> {

    private Context mContext;
    private int mRessource;
    private SearchFilter SF;

    public SearchAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Profile> objects, SearchFilter sf) {
        super(context, resource, objects);
        mContext=context;
        mRessource=resource;
        SF=sf;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String N = getItem(position).getClinic();
        String A = "Address: "+getItem(position).getAddress();

        String S="Services: ";
        ArrayList<UserService> temp = SF.getUserServiceAssociations();

        for(int i=0;i<temp.size();i++){
            if(getItem(position).getUser().equals(temp.get(i).getUser())){
                if(!S.equals("Services: ")){
                    S=S+", ";
                }

                S=S+temp.get(i).getService();
            }
        }

        int sh= Integer.parseInt(getItem(position).getWorkingTime().get(0).get(SF.getDay()));
        int eh= Integer.parseInt(getItem(position).getWorkingTime().get(1).get(SF.getDay()));

        String m;
        if(sh<13){
            m="AM";
            if(sh==0){
                sh=12;
            }
        }else{
            sh=sh-12;
            m="PM";
        }

        String SH =""+sh+":00 "+m;

        if(eh<13){
            m="AM";
            if(eh==0){
                eh=12;
            }
        }else{
            eh=eh-12;
            m="PM";
        }

        String EH =""+eh+":00 "+m;




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
        clinicService.setText(S);
        clinicOp.setText(SH);
        clinicEn.setText(EH);

        return convertView;
    }

    @Nullable
    @Override
    public Profile getItem(int position) {
        return super.getItem(position);
    }

}
