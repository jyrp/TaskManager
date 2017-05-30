package com.example.a15017135.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Task> tasks;
    Integer tvID;
    String tvName;
    String tvDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) this.findViewById(R.id.lv);

        tasks = new ArrayList<Task>();
        Helper dbh = new Helper(MainActivity.this);
        tasks.clear();
        tasks.addAll(dbh.getAllTasks());
        dbh.close();
            aa = new TaskAdapter(this, R.layout.taskrow, tasks);
            lv.setAdapter(aa);
        Log.d("a",tasks.size()+"");
            btnAdd = (Button)findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {


                }
            });
        }
    }
