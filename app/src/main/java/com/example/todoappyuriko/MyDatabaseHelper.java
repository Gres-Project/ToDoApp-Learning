// MyDatabaseHelper.java
package com.example.todoappyuriko;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ToDoDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "ToDoTable";
    private static final String COLUMN_ID = "taskID";
    private static final String COLUMN_TITLE = "taskTitle";
    private static final String COLUMN_KETERANGAN = "taskKeterangan";
    private static final String COLUMN_STATUS = "taskStatus";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_KETERANGAN + " TEXT, " +
                COLUMN_STATUS + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void tambahTask(String taskTitle, String taskKeterangan, String taskStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, taskTitle);
        cv.put(COLUMN_KETERANGAN, taskKeterangan);
        cv.put(COLUMN_STATUS, taskStatus);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Tambahkan data gagal", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Tambahkan data berhasil", Toast.LENGTH_SHORT).show();
            Log.d("Database", "Data added: Title = " + taskTitle + ", Keterangan = " + taskKeterangan + ", Status = " + taskStatus);
        }
        db.close();
    }


    Cursor TampilkanSemuaTask() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery(query,null);
//        Cursor cursor = null;
//
//        try {
//            cursor = db.rawQuery(query, null);
//            Log.d("Database", "Number of records retrieved: " + cursor.getCount());
//        } catch (Exception e) {
//            Log.e("Database", "Error getting data: " + e.getMessage());
//        }
//
//        return cursor;
    }

}