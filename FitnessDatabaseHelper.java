package com.example.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class FitnessDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "FitnessDB";
    private static final int DB_VERSION = 1;

    public FitnessDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE fitness (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT," +
                "steps INTEGER," +
                "distance REAL," +
                "calories REAL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS fitness");
        onCreate(db);
    }

    public boolean insertFitnessData(String date, int steps, float distance, float calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("steps", steps);
        values.put("distance", distance);
        values.put("calories", calories);
        long result = db.insert("fitness", null, values);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM fitness ORDER BY id DESC", null);
    }
}
