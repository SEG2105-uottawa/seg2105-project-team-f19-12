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
import com.samarthsaxena.walkinclinicapp.backend.models.Service;

import java.util.ArrayList;

public class CustomListAdapterServices extends ArrayAdapter {

    //to reference the Activity
    private Activity context;
    private ArrayList<Service> services;

    public CustomListAdapterServices(final Activity context, ArrayList<Service> services) {

        super(context, R.layout.service_info_row, services);

        this.context = context;
        this.services = services;
    }

    public View getView(final int position, View view, final ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.service_info_row, null, true);

        final TextView serviceField = (TextView) rowView.findViewById(R.id.servField);
        final TextView roleField = (TextView) rowView.findViewById(R.id.roleField);

        serviceField.setText(services.get(position).getServiceOffered());
        roleField.setText(services.get(position).getRole());

        // set button handlers
        final Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);
        final Button editButton = (Button) rowView.findViewById(R.id.editBtn);
        final Button addButton = (Button) context.findViewById(R.id.addBtn);

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

                // Update in layout and db
                Service service = new Service(newService, newRole);
                service.dbStore(null);
                services.add(service);
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
                services.get(position).setServiceOffered(newService);
                services.get(position).setRole(newRole);

                notifyDataSetChanged();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service.dbDelete(services.get(position).getServiceOffered());
                services.remove(position);
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}
