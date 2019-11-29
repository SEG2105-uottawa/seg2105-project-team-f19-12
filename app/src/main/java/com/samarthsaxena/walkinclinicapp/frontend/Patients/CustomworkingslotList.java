//package com.samarthsaxena.walkinclinicapp.frontend.Patients;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.samarthsaxena.walkinclinicapp.R;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//
//public class CustomworkingslotList extends ArrayAdapter {
//
//    private Activity context;
//    private ArrayList<String> timeslot;
//    private String username;
//    private TextView start;
//    private TextView toview;
//    private TextView end;
//    private Button book;
//    String time1;
//    String time2;
//
//    public CustomworkingslotList(final Activity context, ArrayList<String> timeslot, String username) {
//
//        super(context, R.layout.row_working_time_slot, timeslot);
//
//        this.context = context;
//        this.timeslot = timeslot;
//        this.username = username;
//    }
//
//    public View getView(final int position, View view, final ViewGroup parent) {
//
//        LayoutInflater inflater = context.getLayoutInflater();
//        final View rowView = inflater.inflate(R.layout.row_working_time_slot, null, true);
//        start = (TextView) rowView.findViewById(R.id.start);
//        toview = (TextView) rowView.findViewById(R.id.toview);
//        end = (TextView) rowView.findViewById(R.id.end);
//        book = (Button) rowView.findViewById(R.id.book);
//        int fakestart_time=6;
//        int fakeend_time=12;
//        int fakedifference=6;
//
//         time1 = convertto(fakestart_time);
//
//         time2 = convertto(fakeend_time);
//
////        String format = "hh:mm";
////
////        SimpleDateFormat sdf = new SimpleDateFormat(format);
////
////        Date dateObj1 = null;
////        try {
////            dateObj1 = sdf.parse(time1);
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////        Date dateObj2 = null;
////        try {
////            dateObj2 = sdf.parse( time2);
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////        System.out.println("Time Start: "+dateObj1);
////        System.out.println("Time End: "+dateObj2);
////
////
////
////        long dif = dateObj1.getTime();
////        while (dif < dateObj2.getTime()) {
////            Date slot = new Date(dif);
//////            System.out.println("Hour Slot --->" + slot);
////            timeslot.add((sdf.format(slot)));
////            dif += 3600000;
////        }
//
//        // set button handlers
////        final Button deleteButton = (Button) rowView.findViewById(R.id.deleteBtn);
////book.setOnClickListener(new View.OnClickListener() {
////    @Override
////    public void onClick(View v) {
//////
////        AlertDialog.Builder adb = new AlertDialog.Builder(this);
////        adb.setView(alertDialogView);
////        adb.setTitle("Title of alert dialog");
////        adb.setIcon(android.R.drawable.ic_dialog_alert);
////        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
////            public void onClick(DialogInterface dialog, int which) {
////                EditText et = (EditText)alertDialogView.findViewById(R.id.EditText1);
////                Toast.makeText(W.this, et.getText(),
////                        Toast.LENGTH_SHORT).show();
////            }
////        });
////        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////            public void onClick(DialogInterface dialog, int which) {
////                finish();
////            }
////        });
////        adb.show();
////    }
////});
//
//
////
////        AlertDialog.Builder adb = new AlertDialog.Builder(this);
////        adb.setView(alertDialogView);
////        adb.setTitle("Title of alert dialog");
////        adb.setIcon(android.R.drawable.ic_dialog_alert);
////        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
////            public void onClick(DialogInterface dialog, int which) {
////                EditText et = (EditText)alertDialogView.findViewById(R.id.EditText1);
////                Toast.makeText(Tutoriel18_Android.this, et.getText(),
////                        Toast.LENGTH_SHORT).show();
////            }
////        });
////        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////            public void onClick(DialogInterface dialog, int which) {
////                finish();
////            }
////        });
////        adb.show();
////        return rowView;
////    }
////public String convertto (int i){
////    return Integer.toString(i)+":00";
////}
////}
//
