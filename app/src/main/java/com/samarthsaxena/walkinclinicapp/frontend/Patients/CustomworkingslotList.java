
package com.samarthsaxena.walkinclinicapp.frontend.Patients;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.facades.Admin;
import com.samarthsaxena.walkinclinicapp.backend.facades.Employee;
import com.samarthsaxena.walkinclinicapp.backend.models.Service;
import com.samarthsaxena.walkinclinicapp.backend.models.User;


import java.util.ArrayList;

public class CustomworkingslotList extends ArrayAdapter {

    private Activity context;
    private ArrayList<Integer> slot;
    private String username;

    public CustomworkingslotList(final Activity context, ArrayList<Integer> slot, String username) {

        super(context, R.layout.row_working_time_slot, slot);

        this.context = context;
        this.slot = slot;
        this.username = username;
    }

    public View getView(final int position, View view, final ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.row_working_time_slot, null, true);

        final TextView serviceField = (TextView) rowView.findViewById(R.id.servField);
        final TextView roleField = (TextView) rowView.findViewById(R.id.roleField);

//        serviceField.setText(slot.get(position).//methodtocalllist());


        // set button handlers
//        final Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);


//package com.samarthsaxena.walkinclinicapp.frontend.Employee;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.samarthsaxena.walkinclinicapp.R;
//import com.samarthsaxena.walkinclinicapp.backend.facades.Admin;
//import com.samarthsaxena.walkinclinicapp.backend.facades.Employee;
//import com.samarthsaxena.walkinclinicapp.backend.models.Service;
//import com.samarthsaxena.walkinclinicapp.backend.models.User;
//
//
//import java.util.ArrayList;
//
//public class CustomworkingslotList extends ArrayAdapter {
//
//    private Activity context;
//    private ArrayList<Integer> slot;
//    private String username;
//
//    public CustomworkingslotList(final Activity context, ArrayList<Integer> slot, String username) {
//
//        super(context, R.layout.row_working_time_slot, slot);
//
//        this.context = context;
//        this.slot = slot;
//        this.username = username;
//    }
//
//    public View getView(final int position, View view, final ViewGroup parent) {
//
//        LayoutInflater inflater = context.getLayoutInflater();
//        final View rowView = inflater.inflate(R.layout.row_working_time_slot, null, true);
//
//        final TextView serviceField = (TextView) rowView.findViewById(R.id.servField);
//        final TextView roleField = (TextView) rowView.findViewById(R.id.roleField);
//
//        //serviceField.setText(slot.get(position).//methodtocalllist());
//
//
//        // set button handlers
//        final Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);
//

//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String service = serviceField.getText().toString();
//                Employee.deleteServiceOfUser(username, service);

////                services.remove(position);
//                notifyDataSetChanged();
//            }
//        });



     return rowView;
    }
}

//                services.remove(position);
//                notifyDataSetChanged();
//            }
//        });
//
//
//
//        return rowView;
//    }
//}
