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

public class CustomListAdapterUser extends ArrayAdapter {

    //to reference the Activity
    private Activity context;

    private ArrayList<String> userNameArray;
    private ArrayList<String> userEmailArray;
    private ArrayList<String> userTypeArray;

    public CustomListAdapterUser(Activity context,
                                 ArrayList<String> userNameArray,
                                 ArrayList<String> userEmailArray,
                                 ArrayList<String> userTypeArray){

        super(context, R.layout.user_info_row , userNameArray);

        this.context=context;
        this.userNameArray = userNameArray;
        this.userEmailArray = userEmailArray;
        this.userTypeArray = userTypeArray;
    }

    public View getView(final int position, View view, final ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        final View rowView=inflater.inflate(R.layout.user_info_row, null,true);

        final TextView userField = (TextView) rowView.findViewById(R.id.userField);
        final TextView emailField = (TextView) rowView.findViewById(R.id.emailField);
        final TextView typeField = (TextView) rowView.findViewById(R.id.typeField);

        //this code sets the values of the objects to values from the arrays
        userField.setText(userNameArray.get(position));
        emailField.setText(userEmailArray.get(position));
        typeField.setText(userTypeArray.get(position));

        // set button handlers
        final Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);
        final Button editButton = (Button) rowView.findViewById(R.id.editBtn);
        final Button addButton = (Button) context.findViewById(R.id.addBtn);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Replace with user deletion from database
                // Update in layout
                userNameArray.remove(position);
                notifyDataSetChanged();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText userNameText = context.findViewById(R.id.usernameInputField);
                final EditText userEmailText = context.findViewById(R.id.emailInputField);
                final EditText userTypeText = context.findViewById(R.id.typeInputField);

                String newUsername = userNameText.getText().toString();
                String newEmail = userEmailText.getText().toString();
                String newType = userTypeText.getText().toString();

                // Check field validation
                if (newUsername.isEmpty() || newType.isEmpty() || newEmail.isEmpty()) {
                    return;
                }

                // Update in layout
                userNameArray.set(position, newUsername);
                userTypeArray.set(position, newType);
                userEmailArray.set(position, newEmail);
                notifyDataSetChanged();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText userNameText = context.findViewById(R.id.usernameInputField);
                final EditText userEmailText = context.findViewById(R.id.emailInputField);
                final EditText userTypeText = context.findViewById(R.id.typeInputField);

                String newUsername = userNameText.getText().toString();
                String newEmail = userEmailText.getText().toString();
                String newType = userTypeText.getText().toString();

                // Check field validation
                if (newUsername.isEmpty() || newType.isEmpty() || newEmail.isEmpty()) {
                    return;
                }

                // Update in layout
                userNameArray.add(newUsername);
                userTypeArray.add(newType);
                userEmailArray.add(newEmail);
                notifyDataSetChanged();
            }
        });


        return rowView;
    };

}
