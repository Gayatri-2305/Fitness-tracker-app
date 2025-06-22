package com.example.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addEntryBtn;
    ListView fitnessListView;
    FitnessDatabaseHelper dbHelper;
    ArrayList<String> fitnessList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addEntryBtn = findViewById(R.id.btnAddEntry);
        fitnessListView = findViewById(R.id.fitnessList);
        dbHelper = new FitnessDatabaseHelper(this);

        loadFitnessData();

        addEntryBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddFitnessActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFitnessData();
    }

    private void loadFitnessData() {
        Cursor cursor = dbHelper.getAllData();
        fitnessList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String entry = "Date: " + cursor.getString(1)
                        + "\nSteps: " + cursor.getInt(2)
                        + ", Distance: " + cursor.getFloat(3) + " km"
                        + ", Calories: " + cursor.getFloat(4);
                fitnessList.add(entry);
            } while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fitnessList);
        fitnessListView.setAdapter(adapter);
    }
}
