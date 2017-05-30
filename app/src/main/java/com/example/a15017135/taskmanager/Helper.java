package com.example.a15017135.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15017135 on 30/5/2017.
 */

public class Helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_DESCRIPTION = "_description";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT ) ";
        db.execSQL(createNoteTableSql);
        for (int i = 0; i< 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, "Data name " + i);
            values.put(COLUMN_DESCRIPTION, "Data desc" + i);
            db.insert(TABLE_TASK, null, values);
        }
        Log.i("info", "dummy records inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

    public long insertTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, task.getName());
        values.put(COLUMN_DESCRIPTION, task.getDescription());
        long result = db.insert(TABLE_TASK, null, values);
        if (result == -1) {
            Log.d("DBHelper", "Insert failed");
        }
        db.close();
        Log.d("SQL Insert",""+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> tasks = new ArrayList<Task>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_DESCRIPTION + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                Task task1 =new Task(id,name,description);
                tasks.add(task1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }



}
