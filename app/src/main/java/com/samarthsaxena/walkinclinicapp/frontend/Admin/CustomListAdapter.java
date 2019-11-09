package com.samarthsaxena.walkinclinicapp.frontend.Admin;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.R;

import org.w3c.dom.Text;

public class CustomListAdapter extends ArrayAdapter {

    //to reference the Activity
    private Activity context;

    private String[] userNameArray;
    private String[] userEmailArray;
    private String[] userTypeArray;

    public CustomListAdapter(Activity context, String[] userNameArray, String[] userEmailArray, String[] userTypeArray){

        super(context, R.layout.user_info_row , userNameArray);

        this.context=context;
        this.userNameArray = userNameArray;
        this.userEmailArray = userEmailArray;
        this.userTypeArray = userTypeArray;
    }

    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.user_info_row, null,true);

        //this code gets references to objects xml file
        TextView userField = (TextView) rowView.findViewById(R.id.userField);
        TextView emailField = (TextView) rowView.findViewById(R.id.emailField);
        TextView typeField = (TextView) rowView.findViewById(R.id.typeField);

        //this code sets the values of the objects to values from the arrays
        userField.setText(userNameArray[position]);
        emailField.setText(userEmailArray[position]);
        typeField.setText(userTypeArray[position]);

        // set button handlers
        Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Replace with user deletion from database
                Log.i("USER DELETE", "User " + userNameArray[position] + " deleted!");
            }
        });

        return rowView;
    };

}
