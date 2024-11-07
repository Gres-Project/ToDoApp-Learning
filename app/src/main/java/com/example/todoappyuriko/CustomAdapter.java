package com.example.todoappyuriko;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> taskID, taskTitle, taskKeterangan, taskStatus;

    public CustomAdapter(
            Context context,
            ArrayList<String> taskID,
            ArrayList<String> taskTitle,
            ArrayList<String> taskKeterangan,
            ArrayList<String> taskStatus) {
        this.context = context;
        this.taskID = taskID;
        this.taskTitle = taskTitle;
        this.taskKeterangan = taskKeterangan;
        this.taskStatus = taskStatus;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflateer = LayoutInflater.from(context);
        View view = inflateer.inflate(R.layout.my_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.textViewTaskID.setText(String.valueOf(taskID.get(position)));
        holder.textViewTaskTitle.setText(String.valueOf(taskTitle.get(position)));
        holder.textViewTaskKeterangan.setText(String.valueOf(taskKeterangan.get(position)));
        holder.textViewTaskStatus.setText(String.valueOf(taskStatus.get(position)));
    }

    @Override
    public int getItemCount() {
        return taskID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTaskID, textViewTaskTitle, textViewTaskKeterangan, textViewTaskStatus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskID = itemView.findViewById(R.id.textViewTaskID);
            textViewTaskTitle = itemView.findViewById(R.id.textViewTaskTitle);
            textViewTaskKeterangan = itemView.findViewById(R.id.textViewTaskKeterangan);
            textViewTaskStatus = itemView.findViewById(R.id.textViewTaskStatus);

        }
    }
}
