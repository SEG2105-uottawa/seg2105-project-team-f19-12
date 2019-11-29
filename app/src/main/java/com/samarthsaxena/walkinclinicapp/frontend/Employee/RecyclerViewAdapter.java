package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samarthsaxena.walkinclinicapp.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context mContext;
    private int[][] Hours;

    public RecyclerViewAdapter(Context C, int[][] H) {
        mContext=C;
        Hours=H;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_employeesetworkinghours, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Start.setText(Hours[0][position]);
        holder.To.setText("To");
        holder.End.setText(Hours[1][position]);
    }

    @Override
    public int getItemCount() {
        return Hours.length/2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Start;
        TextView To;
        TextView End;

        public ViewHolder(View itemView) {
            super(itemView);
            Start = itemView.findViewById(R.id.StartHours);
            To = itemView.findViewById(R.id.WordTo);
            End = itemView.findViewById(R.id.EndHours);
        }
    }

}
