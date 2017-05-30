package com.example.a15017135.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017135 on 30/5/2017.
 */

public class TaskAdapter extends ArrayAdapter {
    private ArrayList<Task> tasks;
    private Context context;
    private TextView tvID;
    private TextView tvName;
    private TextView tvDesc;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.taskrow, parent, false);


        tvID = (TextView)rowView.findViewById(R.id.tvId);
        tvName = (TextView)rowView.findViewById(R.id.tvName);
        tvDesc = (TextView)rowView.findViewById(R.id.tvDescription);

        Task currentTask = tasks.get(position);

        tvID.setText(currentTask.getId());
        tvName.setText(currentTask.getName());
        tvDesc.setText(currentTask.getDescription());
        return rowView;
    }
    public TaskAdapter(Context context, int resource, ArrayList<Task> objects){
        super(context, resource, objects);

        tasks = objects;
        this.context = context;
    }
}