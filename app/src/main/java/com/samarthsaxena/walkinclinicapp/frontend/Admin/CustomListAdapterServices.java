package com.samarthsaxena.walkinclinicapp.frontend.Admin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samarthsaxena.walkinclinicapp.R;

import java.util.ArrayList;

public class CustomListAdapterServices extends ArrayAdapter {

    //to reference the Activity
    private Activity context;

    private ArrayList<String> serviceServiceArray;
    private ArrayList<String> serviceRoleArray;

    public CustomListAdapterServices(final Activity context, ArrayList<String> service, ArrayList<String> serviceRole) {

        super(context, R.layout.user_info_row, service);

        this.context = context;
        this.serviceServiceArray = service;
        this.serviceRoleArray = serviceRole;

    }

    public View getView(final int position, View view, final ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.service_info_row, null, true);

        final TextView serviceField = (TextView) rowView.findViewById(R.id.servField);

        final TextView roleField = (TextView) rowView.findViewById(R.id.roleField);

        serviceField.setText(serviceServiceArray.get(position));
        roleField.setText(serviceRoleArray.get(position));

        // set button handlers
        final Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);
        final Button editButton = (Button) rowView.findViewById(R.id.editBtn);
        final Button addButton = (Button) context.findViewById(R.id.addBtn);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Replace with user deletion from database
                // Update in layout
                serviceServiceArray.remove(position);
                serviceRoleArray.remove(position);
                notifyDataSetChanged();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText serviceText = context.findViewById(R.id.serviceInputField);
                final EditText roleText = context.findViewById(R.id.roleInputField);

                String newService = serviceText.getText().toString();
                String newRole = roleText.getText().toString();

                // Check field validation
                if (newService.isEmpty() || newRole.isEmpty()) {
                    return;
                }

                // Update in layout
                serviceServiceArray.set(position, newService);
                serviceRoleArray.set(position, newRole);

                notifyDataSetChanged();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText serviceText = context.findViewById(R.id.serviceInputField);

                final EditText roleText = context.findViewById(R.id.roleInputField);

                String newService = serviceText.getText().toString();

                String newRole = roleText.getText().toString();

                // Check field validation
                if (newService.isEmpty() || newRole.isEmpty()) {
                    return;
                }

                // Update in layout
                serviceServiceArray.add(newService);
                serviceRoleArray.add(newRole);

                notifyDataSetChanged();
            }
        });


        return rowView;
    }
}
