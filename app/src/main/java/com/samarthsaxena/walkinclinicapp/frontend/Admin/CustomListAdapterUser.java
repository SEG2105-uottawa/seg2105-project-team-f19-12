package com.samarthsaxena.walkinclinicapp.frontend.Admin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.facades.Admin;
import com.samarthsaxena.walkinclinicapp.backend.models.User;

import java.util.ArrayList;

public class CustomListAdapterUser extends ArrayAdapter {

    //to reference the Activity
    private Activity context;
    private ArrayList<User> users;

    public CustomListAdapterUser(Activity context, ArrayList<User> users){

        super(context, R.layout.user_info_row , users);

        this.context=context;
        this.users = users;
    }

    public View getView(final int position, View view, final ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        final View rowView=inflater.inflate(R.layout.user_info_row, null,true);

        final TextView userField = (TextView) rowView.findViewById(R.id.userFieldRow);
        final TextView emailField = (TextView) rowView.findViewById(R.id.emailFieldRow);
        final TextView typeField = (TextView) rowView.findViewById(R.id.typeFieldRow);

        //this code sets the values of the objects to values from the arrays
        userField.setText(users.get(position).getUsername());
        emailField.setText(users.get(position).getEmail());
        typeField.setText(users.get(position).getType());

        // set button handlers
        final Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove from both layout array and database
                Admin.deleteUser(users.get(position).getUsername());
                users.remove(position);
                notifyDataSetChanged();
            }
        });

        return rowView;
    };

}
