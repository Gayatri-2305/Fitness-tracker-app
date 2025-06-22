package com.example.fitnesstracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddFitnessActivity extends AppCompatActivity {

    EditText etDate, etSteps, etDistance, etCalories;
    Button btnSave;
    FitnessDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fitness);

        etDate = findViewById(R.id.etDate);
        etSteps = findViewById(R.id.etSteps);
        etDistance = findViewById(R.id.etDistance);
        etCalories = findViewById(R.id.etCalories);
        btnSave = findViewById(R.id.btnSave);
        dbHelper = new FitnessDatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            String date = etDate.getText().toString();
            int steps = Integer.parseInt(etSteps.getText().toString());
            float distance = Float.parseFloat(etDistance.getText().toString());
            float calories = Float.parseFloat(etCalories.getText().toString());

            boolean inserted = dbHelper.insertFitnessData(date, steps, distance, calories);
            if (inserted) {
                Toast.makeText(this, "Entry Saved", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
