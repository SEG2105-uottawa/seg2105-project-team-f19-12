package com.samarthsaxena.walkinclinicapp.frontend.Employee;

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

public class CustomEmployeeDeleteList extends ArrayAdapter {

    private Activity context;
    private ArrayList<Service> services;
    private String username;

    public CustomEmployeeDeleteList(final Activity context, ArrayList<Service> services, String username) {

        super(context, R.layout.employee_delete_service_row, services);

        this.context = context;
        this.services = services;
        this.username = username;
    }

    public View getView(final int position, View view, final ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.employee_delete_service_row, null, true);

        final TextView serviceField = (TextView) rowView.findViewById(R.id.servField);
        final TextView roleField = (TextView) rowView.findViewById(R.id.roleField);

        serviceField.setText(services.get(position).getServiceOffered());
        roleField.setText(services.get(position).getRole());

        // set button handlers
        final Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String service = serviceField.getText().toString();
                Employee.deleteServiceOfUser(username, service);
                services.remove(position);
                notifyDataSetChanged();
            }
        });



        return rowView;
    }
}
